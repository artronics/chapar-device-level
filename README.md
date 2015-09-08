# Chapar

## Introduction

**Chapar** is a Software Defined Network (SDN) framework using *java* programming language to make the process of designing a _Network Controller Service_ fast, robust and reliable. It is easy to use and also flexible enough to match with different scenarios. Although *Chapar* is not limited to specific implementation, it is designed with the idea of *Software Defined Wireless Networks (SDWN)* in mind.

## Usage

Let's create a `main()` method which will be the entry point of our program.

	public class myApp
	{
	    public static void main( String[] args )
	    {
	        Chapar.start();
	    }
	}
	
We start _Chapar_ by invoking its `start()` method, If you run the program, you will see an output similar to this.

	INFO  [Thread-1  ]: 1   ;UNK            ;22  ;1   ;0   ;0   ;0   ;0   ;2   ;20  ;0   ;0   ;0   ;255 ;3   ;0   ;50  ;211 ;0   ;30  ;204 ;0   ;39  ;205 ;;
	INFO  [Thread-1  ]: 2   ;UNK            ;22  ;1   ;0   ;50  ;0   ;0   ;2   ;19  ;0   ;0   ;1   ;255 ;3   ;0   ;30  ;235 ;0   ;39  ;247 ;0   ;0   ;211 ;;
	INFO  [Thread-1  ]: 3   ;UNK            ;22  ;1   ;0   ;30  ;0   ;0   ;2   ;19  ;0   ;0   ;1   ;255 ;3   ;0   ;39  ;246 ;0   ;0   ;204 ;0   ;50  ;236 ;;
	INFO  [Thread-1  ]: 4   ;UNK            ;22  ;1   ;0   ;39  ;0   ;0   ;2   ;19  ;0   ;0   ;1   ;255 ;3   ;0   ;0   ;204 ;0   ;50  ;246 ;0   ;30  ;245 ;;

	etc...

Notice that before running the program, I connected four *Wireless Sensor Devices* with addresses of 0, 30, 39 and 50. `INFO [Thread-1  ]` is the information related to `logger`. Each line starts with *Packet Serial-Number*, follows the type of packet (in this case **UNKown**). Each time you create a packet, or each time system receives a packet this serial number increases.

These packets are *put* in `PacketsIn` queue, until you *take* them with your controller. Later we will see how you can write a controller to take and use these packets.

If you check the parent directory, you also see a `/logs/Packets` directory, which contains `packets.csv` file. This file contains the same content of what you saw in console output. See [Analyzing Data](#analyzing_data) section.

### Installation

For Installation you need to add *Chapar* as a dependency to your  [*Apache Maven*](https://en.wikipedia.org/wiki/Apache_Maven) project. If you have access to the source code just open the `/Chapar` directory and your IDE (NetBeans, IntelliJ Idea, etc) will take care of other things for you. Then create a new project/module and add below lines to your `pom.xml`

	<dependency>
      <groupId>artronics.chapar</groupId>
      <artifactId>chapar</artifactId>
      <version>1.0-SNAPSHOT</version>
	</dependency>


#### Directory Structure

*Chapar* is on early development cycles so, if you are reading this text, it means that you have access to private source code of project. If you are already familiar with `maven` there is nothing worth noting on this section. The directory structure follows exactly the *maven* conventions.
The `/src` directory consists of all source codes of the project. inside `/src`, there are `/main` and `/tests`, see section [Tests](#tests).



### Architecture
*Chapar* has a [Modular Architecture](https://en.wikipedia.org/wiki/Modular_programming). If you decide to change behaviour of a _module_ you can simply write your own and then configure _Cahpar_ to use your own modules. Changing the implementation of a module is resolving by [Spring](https://en.wikipedia.org/wiki/Spring_Framework) [Dependency Injection](https://en.wikipedia.org/wiki/Dependency_injection). If you decide to change a module the first step is to create a [bean definition](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/) `xml` file. You can pass this `xml` like so `Chapar.start(myProjectBeans.xml)`

The below figure shows how different modules are connected.

![Chapar High Leve Architecture](/img/Chapar-HL.jpg)

Each _module_ talks to other modules by implementing a **contract** (i.e. _java_ _interfaces_) for that module. See [Services](#services) section for further information. 
For example *Chapar* uses its own implementation of `PacketFactory` contract, which is called `BasedPacketFactory`. This [factory](https://en.wikipedia.org/wiki/Factory_method_pattern) creates `BasePackets`. As said before, *Chapar* is protocol unaware, and this is the reason why you saw `UNK` as packet types when running the first program. You can write your own implementation of  `PacketFactory` and inject that implementation to `PacketBroker`. Here is an example of *Spring* beans configuration `xml` file.

	<?xml version="1.0" encoding="UTF-8"?>
	<beans
	    <bean id="PacketFactory" class="org.example.MyPacketFactory"/>
	</beans>

Using this file, *Dependency Injection* will inject `MyPacketFactory` instead of default implementation.
Suppose that `MyPacketFactory` create its own logs while creating packets. The output would be like this.

	1   ;REPORT         ;0              ;22  ;1   ;0   ;0   ;0   ;0   ;2   ;20  ;0   ;0   ;0   ;255 ;3   ;0   ;50  ;210 ;0   ;30  ;203 ;0   ;39  ;207 ;;
	2   ;REPORT         ;50             ;22  ;1   ;0   ;50  ;0   ;0   ;2   ;19  ;0   ;0   ;1   ;255 ;3   ;0   ;30  ;236 ;0   ;39  ;248 ;0   ;0   ;210 ;;

This way we make our program much more flexible. Let's say in future you decide to change your protocol packet types. You just need to create a new implementation and change that `xml` file.

We can break down all modules into two main categories. The first group is all modules which together serves as a message passing architecture. **Connection Layer** gets bytes from out side world, then passes them to [**Message Broker**](https://en.wikipedia.org/wiki/Message_broker). Finally, inside **Controller** you have access to actual **Packets**. Later I will explain each module in details.

The second group gives all you need for implementing your desire **Controller**. You can use **NodeFactory** to create a node. You can add links to other nodes, etc. **PacketFactory** helps you to create all kind of Packets.

### Controller

It is the reponsibility of developer to create a *Controller* for her network. Here is a demonstration of how you can use various available modules/services to create a controller. Notice that this is an snippet code, it serves no purpose.

    private void processPacket(Packet packet)
    {
        while (!packetsIn.isEmpty()) {
            Packet packet = packetsIn.take();
            switch (packet.getType()) {
                case REPORT:
                    MyReportPacket rPacket = (MyReportPacket) packet;
                    processReportPacket(rPacket);
                    break;
            }
            //etc ...
        }
    }

`PacketsIn` is a [`singleton`](https://en.wikipedia.org/wiki/Singleton_pattern) class which holds all received packets. In above example we take the last packet and based on its type we call appropriate method (`processReportPacket()`).

### <a id="analyzing_data">Analyzing Data <a/> 
No matter what does your `Controller` actually do, the final goal is to get some data that let you analyze the performance of your `Controller` and your algorithms. For achieving this goal, there would be two possible solutions. The first one is, implementing analyzing requirements inside java, and the second one is to record all required data somewhere and let other programs to analyze your data. Latter solution would give more flexibility. This approach is also less error prone.

Currently, *Chapar* records all generated packets, with their contents as a [`.csv` (Comma Separated Values)](https://en.wikipedia.org/wiki/Comma-separated_values) format. In this format each line of the file is known as a **record** and each record consists of one or more **field**s, separated by commas. The benefit of using `.csv` file is that, data can be easily imported to all data manipulation software such as GNU Octave, Matlab, Excel, etc. It is worth noting that in European standard the separator is a _semicolon_ ";" because _comma_ is used for decimal separator. 

In order to create csv records inside your program, you can use `ToCsv` helper class. It creates a `csv` formated `String` out of your important variables. This class has two `static` methods: `create` and `write`. `create` method accepts an infinite number of arguments ([varagrs](http://acacha.org/mediawiki/Java_varargs#.Vez7GnhVuEI)) and it returns a `csv` compatible **record** out of provided data. Remeber if you pass a `Collection` type, it iterate over each element and calls `toString` method for each element inside collection.
In the other hand, `write` method helps you to write your created `csv` record to a file. You should provide a name (inside `chapar.config` file) for that file before running the program. Otherwise `write` method ignores it without throwing any exceptions.

###<a name="tests"><a/>Tests

For almost all classes in *Chapar* there are various [unit tests](https://en.wikipedia.org/wiki/Unit_testing). Tests in *Chapar* serves two purposes. The first one is, obviously, testing functionality of a unit from various aspects. Secondly, tests are a great source of documentation. If you want to see how a particular component works, the first place to look is its test class. For example here is a test method from `BaseNodeTest` class. The behaviour under question is: For two equal `Node`s the `hash code`'s returned value must be the same.

    @Test
    public void For_equal_nodes_hashCode_must_be_the_same()
    {
        assertThat(aNode.hashCode(), equalTo(sameNode.hashCode()));
        //it is not mandatory for two diff nodes to return diff hash but lets test it
        assertNotEquals(aNode.hashCode(), notEqNode.hashCode());
    }
    
If you are familiar with unit testing and [junit](https://en.wikipedia.org/wiki/JUnit) this method name might look odd. It doesn't follow general method naming conventions. Method's name should describe the purpose of the test. This convention is used on modern unit tests frameworks.

Remember, although there are various tests, right now *Chapar* is in its early development cycles. At the time of this writing there is no stable release available yet. The APIs are all subjected to change.

### <a name="services"><a/>Services

There are various services available in *Chapar*. In this section we take a look at thoese services that would be intersting. As siad before you can always write you own service.

#### Connection

**Connection** is a _service_ which connect your program to outside world. Currently there are two implementation of this contract. _Simulator_ which is not completed yet, and `Serial Port`. If you want to connect to a device which requires `bluetooth`, you can write your implementation. The contract is called `ConnectionService` and it has three methods:

	public interface ConnectionService
	{
	    void establishConnection();

	    void open();

	    void close();
	}

#### Message and Packet Boker
There are two services wich behaves similarly, `MessageBroker` and `PacketBroker`. Before going further we need to define what is a **message** and **packet**. Everythings that *Connection Service* receives from outside world is called a *message*. For a message to be applicable it must follows a convention. It should starts with a `START_BYTE` follows by `LENGTH` of packet and ends with `STOP_BYTE`. There is a helper service inside `PacketBroker` which splits received messages to chunks of `int` streams which obeys start_length_stop rule. Remember though, a message would either consists of several packets or a part of a packet. It dosen't matter becuase that helper service has a `queue` which keeps track of previous received messages and as soon as it receives stop byte it produces a chunk of integers which together constructs a packet. `PacketBroker` uses `PacketFactory` to create actual packets out of thoese chunks and then it puts created packets in a queue object which is called `PacketsIn`. `PacketsIn` is a singleton and you can get the instance inside your controller. here is a code for controller which gets the received packet and puts it in another queue (`packetsQueue`):

	@Subscribe
	public void packetInEventHandler(PacketInEvent event)
	{
	   Packet<MyPacket.Type> packet = packetsIn.take();
	   try {
	       packetsQueue.put(packet);
	   }catch (InterruptedException e) {
	       e.printStackTrace();
	   }
	}

In a reverse direction, whenever you want to send a packet you need to get the instance of `PacketsOut` singleton and put your packet into it. There is no need to do any thing else, `PacketsOut` will take care of sending it to other layers.

You may ask how my controller is supposed to know when a packet is available. We introduce Chapar's [Event Model]() in following section.

#### Events

Whenever a service wants to make other services aware of an important occurrence, it fires an event so other service(s) who are intersted on that particular event will receive that event. These service(s) sould provide a handler to hook on to that event.

*Chapar* uses *google guava EventBus* library to implement its own event bus. Currently there is just one **Event Bus** which is called `mainbus`. If you want to leverage event driven architecture it is recommanded to create your own bus.

### Components
There are various components that helps you to create your network model fast and easy. In following sections you can find a short introduction to each component.

#### Node
The generic `Node` interface is the core _type_ of a Network Node. Currently, there is one implementation of this interface which is called `BaseNode`. It gets an integer number as the address of node .It also holds a `List` of `Link`s to other nodes. It is recommended that you `extend` this class and add other members and behaviours to it, based on your needs. `BaseNode` has everything that a vertex object needs to have to pass to graph libraries.

#### Packet
The generic `Packet` inteface is the core type of a Network Packet. Since *Chapar* is protocol unaware there is no much behaviour in `BasePacket`. Remember if you want to put/take packets to/from packet queue, your implementation of packet must implement `Packet` interface.

#### NetworkMap
`NetworkMap` is more like a service. It has a `Set` and a `List`. The set consists of updated view of network. If you want to know what is the current snapshot of network you can refer to `networkMap` which is a `HashTable`. In the other hand there is an `ArrayDequeue` field which is called `networkMapHistory`. As the name implies, it holds a history of changes inside your network. You can change the capacity of this queue inside configuration file.

### What's the meaning of Chapar?
From [wiki: Chapar-Khane](https://en.wikipedia.org/wiki/Chapar_Khaneh)
>**"Chapar Khane"** or "Chapar-Khaneh" is a term in Persian, meaning the "house of courier" as **"Chapar"** means **"courier"**, referring to the postal service used during the Achaemenid era. The system was created by Cyrus the Great the founder of the Persian Empire and later developed by Darius the Great, as the royal method of communication throughout the empire. Each "Chapar Khaneh" was a station mainly located along the Royal Road, a 2500 km ancient highway, which stretched from the Sardis to Susa, connecting most of the major cities of the empire

### License
The Chapar framework is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT)

Copyright (c) 2015  Seyed Jalal Hosseini
