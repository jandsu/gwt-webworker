package com.example.webworker;

import java.util.logging.Logger;

import com.example.webworker.dto.MyWorkerMessage;
import com.google.gwt.core.client.EntryPoint;

public class MyWebWorkerEntryPoint
    implements EntryPoint
{
    private int offset = 0;    

    @Override
    public void onModuleLoad()
    {
        initialize();
    }
    
    public native void initialize() /*-{
        var that = this;
        self.addEventListener('message', function(e) {
              console.log("Received message in web worker");
              that.@com.example.webworker.MyWebWorkerEntryPoint::onMessage(Ljava/lang/String;)(e.data);
        }, false);
    }-*/;

    private native void postMessage( MyWorkerMessage message ) /*-{
        self.postMessage(message, [message.payload.buffer]);
    }-*/;

    
    void onMessage( String message )
    {
        int count = 50;
        MyWorkerMessage msg = new MyWorkerMessage();
        msg.code = "OK";
        msg.payload = createBuffer(count, offset);
        postMessage( msg );
        offset += count;
    }

    private static final native GwtUint16Array createBuffer( int pointsCount, int valueOffset ) /*-{
        var buf = new Uint16Array(pointsCount);
        var i;
        for ( i = 0; i < pointsCount; i++) {
            buf[i] = valueOffset + i;
        }
        return buf;
    }-*/;
}
