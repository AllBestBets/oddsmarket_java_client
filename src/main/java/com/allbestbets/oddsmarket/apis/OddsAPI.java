package com.allbestbets.oddsmarket.apis;

import com.allbestbets.oddsmarket.OddsmarketAPI;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * Created by andrey on 21.09.16.
 */
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
            long newUpdatedAt = new Date().getTime();

            String serviceUrl;
            if (type == OddsmarketAPI.APIType.PREMATCH) {
                serviceUrl = PREMATCH_URL;
            } else {
                serviceUrl = LIVE_URL;
            }

            Request req = null;
            String url = String.format(serviceUrl, StringUtils.join(requestData.getBookmakerIds(), ","));

            if (requestData.getMethod() == OddsAPIRequestData.Method.GET) {
                req = Request.Get(OddsmarketAPI.encodeURL(url + "?" + requestData.getQueryForGetRequest()));
            }

            if (requestData.getMethod() == OddsAPIRequestData.Method.POST) {
                req = Request.Post(url);
                req = req.bodyString(requestData.getBodyForPostRequest(), ContentType.APPLICATION_JSON);
            }

            if (lastResponse != null) {
                if (lastResponse.getEtag() != null) {
                    req = req.addHeader("If-None-Match", lastResponse.getEtag());
                }
            }

            requestData.setLastUpdatedAt(lastUpdatedAt);

            Response response = req
                    .addHeader("Content-Type", "text/" + requestData.getFormat().toString())
                    .addHeader("Accept", "application/" + requestData.getFormat().toString())
                    .execute();

            HttpResponse httpResponse = response.returnResponse();

            String content = null;
            if (httpResponse.getEntity() == null && lastResponse != null){
                content = lastResponse.getContent();
            }else if (httpResponse.getEntity() != null){
                content = EntityUtils.toString(httpResponse.getEntity());
            }

            lastResponse = new APIResponse(content,
                    httpResponse.getFirstHeader("Etag") != null ? httpResponse.getFirstHeader("Etag").getValue() : null,
                    httpResponse.getStatusLine().getStatusCode());

            if (lastResponse.getCode() == 200) {
                lastUpdatedAt = newUpdatedAt;
            }

            return lastResponse;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}