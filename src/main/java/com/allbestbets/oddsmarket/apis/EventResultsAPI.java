package com.allbestbets.oddsmarket.apis;

import com.allbestbets.oddsmarket.OddsmarketAPI;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class EventResultsAPI {
    public static final String API_URL = "https://api-mst.oddsmarket.org/v1/eventResults";

    private APIResponse lastResponse;
    private EventResultsAPIRequestData requestData;

    public EventResultsAPI(EventResultsAPIRequestData requestData) {
        this.requestData = requestData;
    }

    public APIResponse execute() {
        try {
            final Request req;

            if (requestData.getMethod() == ArbsAPIRequestData.Method.GET) {
                req = Request.Get(OddsmarketAPI.encodeURL(API_URL + "?" + requestData.getQueryForGetRequest()));

            } else if (requestData.getMethod() == ArbsAPIRequestData.Method.POST) {
                req = Request.Post(API_URL + "?apiKey=" + requestData.getApiKey()).
                        bodyString(requestData.getBodyForPostRequest(), ContentType.APPLICATION_JSON);
            } else {
                return null;
            }

            if (lastResponse != null) {
                if (lastResponse.getEtag() != null) {
                    req.addHeader("If-None-Match", lastResponse.getEtag());
                }
            }

            final Response response = req
                    .addHeader("Content-Type", "text/" + requestData.getFormat().toString())
                    .addHeader("Accept", "application/" + requestData.getFormat().toString())
                    .addHeader("Accept-Encoding", "gzip")
                    .execute();

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

            return lastResponse;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
