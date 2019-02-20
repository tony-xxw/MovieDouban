package com.wynne.common.base;

import java.io.Serializable;

public class BaseParam implements Serializable {
    private Serializable obj;
    private Serializable obj1;
    private String argStr;
    private String argStr1;
    private int argInt = -1;
    private int argInt1 = -1;

    public static BaseParam getInstance() {
        return new BaseParam();
    }

    private BaseParam() {
    }

    public BaseParam(Serializable obj) {
        this.obj = obj;
    }

    public BaseParam(Serializable obj, String argStr) {
        this.obj = obj;
        this.argStr = argStr;
    }

    public BaseParam(Serializable obj, int argInt) {
        this.obj = obj;
        this.argInt = argInt;
    }

    public BaseParam(String argStr) {
        this.argStr = argStr;
    }

    public BaseParam(int argInt) {
        this.argInt = argInt;
    }

    public BaseParam obj(Serializable obj) {
        this.obj = obj;
        return this;
    }

    public BaseParam obj1(Serializable obj) {
        this.obj1 = obj;
        return this;
    }

    public BaseParam argStr(String argStr) {
        this.argStr = argStr;
        return this;
    }

    public BaseParam argStr1(String argStr) {
        this.argStr1 = argStr;
        return this;
    }

    public BaseParam argInt(int argInt) {
        this.argInt = argInt;
        return this;
    }

    public BaseParam argInt1(int argInt) {
        this.argInt1 = argInt;
        return this;
    }

}
