package com.allbestbets.oddsmarket.apis;

public class SportsAPIRequestData extends BaseRequestData {

    public SportsAPIRequestData() {
    }

    public SportsAPIRequestData(Method method, Format format) {
        this.method = method;
        this.format = format;
    }

    public Method getMethod() {
        return method;
    }

    public Format getFormat() {
        return format;
    }

    public SportsAPIRequestData setMethod(Method method) {
        this.method = method;
        return this;
    }

    public SportsAPIRequestData setFormat(Format format) {
        this.format = format;
        return this;
    }

    public SportsAPIRequestData build() {
        return this;
    }
}
