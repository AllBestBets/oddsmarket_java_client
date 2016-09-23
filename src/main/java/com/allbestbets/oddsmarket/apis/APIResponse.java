package com.allbestbets.oddsmarket.apis;

/**
 * Created by andrey on 22.09.16.
 */
public class APIResponse {
    private String content;
    private String etag;
    private int code;

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
