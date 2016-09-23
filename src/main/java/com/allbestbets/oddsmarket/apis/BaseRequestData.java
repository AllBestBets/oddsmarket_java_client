package com.allbestbets.oddsmarket.apis;

public abstract class BaseRequestData {
    public enum Method {
        GET, POST;
    }

    public enum Format {
        XML {
            @Override
            public String toString() {
                return "xml";
            }
        }, JSON {
            @Override
            public String toString() {
                return "json";
            }
        };
    }

    protected Method method = Method.GET;
    protected Format format = Format.JSON;
}
