package com.allbestbets.oddsmarket.apis;

public class APIResponse {
    public final String content;
    public final String etag;
    public final int code;

    public APIResponse(String content, String etag, int code) {
        this.content = content;
        this.etag = etag;
        this.code = code;
    }
}
