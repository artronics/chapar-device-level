#Chapar
##Introduction
**Chapar** is a Software Defined Network (SDN) framework using *java* and a set of other open source libraries to make the process of designing a _Network Controller Service_ easy, flexible, fast, robust and reliable. Although _Chapar_ is not limited to specific implementation, it is designed with the idea of Software Defined Wireless Networks (SDWN) in mind. It is not protocol specific in a sence that it doesn't follow any specific protocol.


###Usage
Using Chapar is easy. You just need to provide your controller implementation and Chapar takes care of other things for you. For this to happen your `Controller` must extends Chapar's `BaseController`. Let's see this in an example.

	public class MySdwnController extends BaseController{

	}
	
This `class` is an empty `controller` which means it has **no behaviour**. Now let's create a `main()` method which will be the entry point of our program.

	public class Sdwn
	{
	    public static void main( String[] args )
	    {
	        Chapar.setController(MySdwnController.class);
	        Chapar.start();
	    }
	} 
First, we passed our controller to *Chapar* and then we start `Chapar` by invoking `start()` method. Now if you run the program, you would see an output similar to this.

	BEACON; IN; 10; 122; 19; 1; 0; 39; 0; 0; 2; 19; 0; 0; 1; 255; 2; 0; 30; 247; 0; 0; 201; 126; 
	BEACON; IN; 11; 122; 19; 1; 0; 30; 0; 0; 2; 19; 0; 0; 1; 255; 2; 0; 0; 205; 0; 39; 250; 126; 
	BEACON;OUT; 12; 122; 19; 1; 0; 0; 0; 0; 2; 20; 0; 0; 0; 255; 2; 0; 39; 199; 0; 30; 205; 126; 
	etc...
Notice that before running the program, I connected three Sensor Devices () with addresses of 39, 30, and 0. every line starts with type of packet, follows the direction of packet. The next number is the serial of packet. Each time you create a packet, or each time system receives a packet this serial number increases.
Since `MySdwnController` has no behaviour, you just see **BEACON** packets. Later we will see how can we add behaviour to our controller.

If you check the parent directory, you also see a `packet.csv` file which contains the same content of what you saw in console output.

###Installation
For Installation you need to add *Chapar* as a dependency to your  [*Apache Maven*](https://en.wikipedia.org/wiki/Apache_Maven) project. If you have access to the source code just open the `/Chapr` directory and your IDE (NetBeans, Intellij Idea, etc) will take care of other things for you.
####Directory Structure
There are two `maven` modules inside main project directory. One is `chapar` and the other is `sdwn`. As said before, *Chapar* has no specific implementation by itself and obvioudly, this is what is expected from a framework. However there is already an implementation of `sdwn` in a separate module. Although I wrote the code for this module, the credit for original codes for `sdwn` belongs to [**Radio Networks- Research Group**](http://www.robertoverdone.org/index.php?page=r) at [**University of Bologna**.](http://www.unibo.it/it)

*Chapar* has it own seperate name space which starts with `artronics.chapar`. All codes related to framework is there. For *SDWN* implementation name space (in context of `maven` it is known as `groupId`) starts with `it.unibo.sdwn`. If you want to create a new implementation of *SDWN* simply create another maven project and add chapar as its dependency. here is an example of `pom.xml` file.

	<dependency>
      <groupId>artronics.chapar</groupId>
      <artifactId>chapar</artifactId>
      <version>1.0-SNAPSHOT</version>
	</dependency>
	
###Architecture
_Chapar_ has a [Modular Architecture](https://en.wikipedia.org/wiki/Modular_programming). If you decide to change behaviour of a _module_ you can simply write your own and then change `chapar_DI.xml` file. This file contains all configuration for [Dependency Injection](https://en.wikipedia.org/wiki/Dependency_injection).

Each _module_ talks to other modules by implementing a _contract_ for that module. A good analogy for understanding what we exactly mean by _contract_ is the Leggo pieces. No matter what shape or colour your Leggo piece has, as long as it has ...

_Chapar_ uses [Dependency Injection](https://en.wikipedia.org/wiki/Dependency_injection) for resolving its modules implementations. For example currently there are two modules which both implements _Connection_ contract. The first one connects to outside world through _serial port_ and the other one implements the same contract, however serves as a _Simulator_. If you have no access to sensor devices you can plug _Simulator_ module.

Below is a diagram of _Chapar_ modules:

We can break down all modules into two main categories. The first group is all modules which together serves as a message passing architecture. **Connection Layer** gets bytes from out side world, then passes them to [**Message Broker**](https://en.wikipedia.org/wiki/Message_broker). Finally, inside **Controller** you have access to actual **Packets**. Later I will explain each module in details.

The second group gives all you need for implementing your desire **Controller**. You can use **NodeFactory** to create a node. You can add links to other nodes, etc. **PacketFactory** helps you to create all kind of Packets. 

###Usage
Let's see this in an example.

	public class MySdwnController extends BaseController
	{
	    public void someAction(){
	        Node node = nodeFactory.createNode(30);
	        Node anotherNode = nodeFactory.createNode(39);
	        int RSSI=200;
	        node.addLinkTo(anotherNode,RSSI);
	        Packet packet = packetFactory.create(Packet.Type.DATA,myPayload);
	    }
	}       
This is our `MySdwnController` which we just created. Now, it has a method `someAction()`. The purpose of this code is to show you some of available APIs.

###Analysing Data
No matter what does your `Controller` actually do, the final goal is to get some data that let you analyse the performance of your `Controller` and your algorithms. For achieving this goal, there would be two possible solutions. The first one is, implementing analysing requirements inside java, and the second one is to record all required data somewhere and let other programs to analyse your data. I decided to choose the latter solution which would result in great flexibility.

Currently, _Chapar_ records all generated packets, with their contents as a [`.csv` (Comma Separated Values)](https://en.wikipedia.org/wiki/Comma-separated_values) format. In this format each line of the file is a data record and each record consists of one or more fields, separated by commas. It is worth noting that in European standard the separator is a _semicolon_ ";" because _comma_ is used for decimal separator. 

After each run you can find a `packets.csv` file on root directory of project. The benefit of using `.csv` file is that data can be easily imported to all data manipulation software such as Matlab, Excel, Google sheets, etc. _Chapar_ is also capable of detecting `MALFORMED` packet type based on configurations which you provide. For acheiving desired performance goals. the data which framework provides must be reliable at first place. A question might be arised, is *Chapar* reliable?

###Tests
For almost all classes in *Chapar* there are various [unit tests](https://en.wikipedia.org/wiki/Unit_testing). It is intresting that the amount of code in `/tests` directory is much more than the actual code. Tests in *Chapar* serves two purposes. The first one is, obviousley, testing functionality of a unit from various aspects. Secondly tests are a great source of documentation. If you want to see how a particular component works, the first place to look is its test class. For example here is a test method from `BaseNodeTest` class. The behaviour under question is: For two equal `Node`s the `hash code`'s returned value must be the same.

    @Test
    public void For_equal_nodes_hashCode_must_be_the_same()
    {
        assertThat(aNode.hashCode(), equalTo(sameNode.hashCode()));
        //it is not mandatory for two diff nodes to return diff hash but lets test it
        assertNotEquals(aNode.hashCode(), notEqNode.hashCode());
    }
Method's name describes the purpose of the test and assertions are as readable as possible.

Although there are various tests, rigth now *Chapar* is in its early development cycles. At the time of this writing there is no stable releas available yet. The APIs are all subjected to change.


####Connection
**Connection** is a _service_ which connect your program to outside world. Currently there are two implementation of this contract. _Simulator_ which is not completed yet, and `Serial Port`. The contract (i.e. java interface) consists of three methods.
###What's the meaning of Chapar?
From [wiki: Chapar-Khane](https://en.wikipedia.org/wiki/Chapar_Khaneh)
>**"Chapar Khane"** or "Chapar-Khaneh" is a term in Persian, meaning the "house of courier" as **"Chapar"** means **"courier"**, referring to the postal service used during the Achaemenid era. The system was created by Cyrus the Great the founder of the Persian Empire and later developed by Darius the Great, as the royal method of communication throughout the empire. Each "Chapar Khaneh" was a station mainly located along the Royal Road, a 2500 km ancient highway, which stretched from the Sardis to Susa, connecting most of the major cities of the empire

