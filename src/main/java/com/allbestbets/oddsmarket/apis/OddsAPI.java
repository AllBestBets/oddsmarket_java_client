package com.allbestbets.oddsmarket.apis;

import com.allbestbets.oddsmarket.OddsmarketAPI;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;

public class OddsAPI {

    public static final String PREMATCH_URL = "https://api-pr.oddsmarket.org/v1/bookmakers/%s/odds";
    public static final String LIVE_URL = "https://api-lv.oddsmarket.org/v1/bookmakers/%s/odds";

    private APIResponse lastResponse;
    private long lastUpdatedAt;
    private OddsAPIRequestData requestData;
    private OddsmarketAPI.APIType type;

    public OddsAPI(OddsAPIRequestData requestData, OddsmarketAPI.APIType type) {
        this.requestData = requestData;
        this.type = type;
    }

    public APIResponse execute() {
        try {
            final long newUpdatedAt = new Date().getTime();

            final String serviceUrl;

            if (type == OddsmarketAPI.APIType.PREMATCH) {
                serviceUrl = PREMATCH_URL;

            } else if (type == OddsmarketAPI.APIType.LIVE) {
                serviceUrl = LIVE_URL;

            } else {
                return null;
            }

            final String url = String.format(serviceUrl, StringUtils.join(requestData.getBookmakerIds(), ","));
            final Request req;

            if (requestData.getMethod() == OddsAPIRequestData.Method.GET) {
                req = Request.Get(OddsmarketAPI.encodeURL(url + "?" + requestData.getQueryForGetRequest()));

            } else if (requestData.getMethod() == OddsAPIRequestData.Method.POST) {
                req = Request.Post(url).bodyString(requestData.getBodyForPostRequest(), ContentType.APPLICATION_JSON);

            } else {
                return null;
            }

            if (lastResponse != null && lastResponse.getEtag() != null) {
                req.addHeader("If-None-Match", lastResponse.getEtag());
            }

            requestData.setLastUpdatedAt(lastUpdatedAt);

            final Response response = req.
                    addHeader("Content-Type", "text/" + requestData.getFormat().toString()).
                    addHeader("Accept", "application/" + requestData.getFormat().toString()).
                    execute();


            final HttpResponse httpResponse = response.returnResponse();

            String content = null;
            if (httpResponse.getEntity() == null && lastResponse != null){
                content = lastResponse.getContent();
            }else if (httpResponse.getEntity() != null){
                content = EntityUtils.toString(httpResponse.getEntity());
            }

            lastResponse = new APIResponse(content,
                    httpResponse.getFirstHeader("Etag") != null ? httpResponse.getFirstHeader("Etag").getValue() : null,
                    httpResponse.getStatusLine().getStatusCode());

            if (lastResponse.getCode() == 200)
                lastUpdatedAt = newUpdatedAt;

            return lastResponse;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}