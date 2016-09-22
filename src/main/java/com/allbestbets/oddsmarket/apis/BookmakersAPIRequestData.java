package com.allbestbets.oddsmarket.apis;

/**
 * Created by andrey on 22.09.16.
 */
public class BookmakersAPIRequestData extends BaseRequestData {
    public BookmakersAPIRequestData() {

    }

    public BookmakersAPIRequestData(Method method, Format format) {
        this.method = method;
        this.format = format;
    }

    public Method getMethod() {
        return method;
    }

    public Format getFormat() {
        return format;
    }

    public BookmakersAPIRequestData setMethod(Method method) {
        this.method = method;
        return this;
    }

    public BookmakersAPIRequestData setFormat(Format format) {
        this.format = format;
        return this;
    }

    public BookmakersAPIRequestData build(){
        return this;
    }
}
