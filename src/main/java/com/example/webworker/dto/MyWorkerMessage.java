package com.example.webworker.dto;

import com.example.webworker.GwtUint16Array;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class MyWorkerMessage
{
    @JsProperty
    public String code;

    @JsProperty
    public GwtUint16Array payload;
}
