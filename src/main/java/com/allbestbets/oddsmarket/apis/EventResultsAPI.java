package com.allbestbets.oddsmarket.apis;

import com.allbestbets.oddsmarket.OddsmarketAPI;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by andrey on 22.09.16.
 */
public class EventResultsAPI {
    public static final String API_URL = "https://api-mst.oddsmarket.org/v1/eventResults";

    private Response lastResponse;

    public Response execute(EventResultsAPIRequestData requestData) {
        try {

            if (requestData.getMethod() == ArbsAPIRequestData.Method.GET) {
                lastResponse = Request.Get(OddsmarketAPI.encodeURL(API_URL + "?" + requestData.getQueryForGetRequest()))
                        .addHeader("Content-Type", "text/" + requestData.getFormat().toString())
                        .addHeader("Accept", "application/" + requestData.getFormat().toString())
                        .execute();
                return lastResponse;

            }

            if (requestData.getMethod() == ArbsAPIRequestData.Method.POST) {
                lastResponse = Request.Post(API_URL + "?apiKey="+requestData.getApiKey())
                        .addHeader("Content-Type", "text/" + requestData.getFormat().toString())
                        .addHeader("Accept", "application/" + requestData.getFormat().toString())
                        .bodyString(requestData.getBodyForPostRequest(), ContentType.APPLICATION_JSON)
                        .execute();
                return lastResponse;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
