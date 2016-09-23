package com.allbestbets.oddsmarket.apis;

public class APIResponse {
    private final String content;
    private final String etag;
    private final int code;

    public APIResponse(String content, String etag, int code) {
        this.content = content;
        this.etag = etag;
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public String getEtag() {
        return etag;
    }

    public int getCode() {
        return code;
    }
}
