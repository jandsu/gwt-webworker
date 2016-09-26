package com.example.webworker;

import java.util.logging.Logger;

import com.example.webworker.dto.MyWorkerMessage;
import com.google.gwt.core.client.EntryPoint;

public class MyWebWorkerEntryPoint
    implements EntryPoint
{
    private static final Logger LOGGER = Logger.getLogger( MyWebWorkerEntryPoint.class.getSimpleName() );

    @Override
    public void onModuleLoad()
    {
        LOGGER.info( "Loading web worker!" );
        initialize();
    }
    
    public native void initialize() /*-{
        var that = this;
        self.addEventListener('message', function(e) {
              console.log("Received message in web worker");
              that.@com.example.webworker.MyWebWorkerEntryPoint::onMessage(Ljava/lang/String;)(e.data);
              self.postMessage('Message ACK from web worker');
        }, false);
    }-*/;

    private native void postMessage( MyWorkerMessage message ) /*-{
        self.postMessage(message, [message.payload.buffer]);
    }-*/;

    
    void onMessage( String message )
    {
        MyWorkerMessage msg = new MyWorkerMessage();
        msg.code = "OK";
        msg.payload = createBuffer(50);
        postMessage( msg );
    }

    private static final native GwtUint16Array createBuffer( int pointsCount ) /*-{
        var buf = new Uint16Array(pointsCount);
        var i;
        for ( i = 0; i < pointsCount; i++) {
            buf[i] = i;
        }
        return buf;
    }-*/;
}
