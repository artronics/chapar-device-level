package artronics.chapar.controller;

import artronics.chapar.transport.Transport;

class AbstractBaseController
{
    protected Transport transport;

    public void setTransport(Transport transport)
    {
        this.transport = transport;
    }
}
