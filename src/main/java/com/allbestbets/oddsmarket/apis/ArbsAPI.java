package com.allbestbets.oddsmarket.apis;

import com.allbestbets.oddsmarket.OddsmarketAPI;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * Created by andrey on 22.09.16.
 */
public class ArbsAPI {

    public static final String PREMATCH_URL = "https://api-pr.oddsmarket.org/v1/bookmakers/%s/arbs";
    public static final String LIVE_URL = "https://api-lv.oddsmarket.org/v1/bookmakers/%s/arbs";

    private Response lastResponse;

    public Response execute(ArbsAPIRequestData requestData, OddsmarketAPI.APIType type) {
        try {
            String serviceUrl;
            if (type == OddsmarketAPI.APIType.PREMATCH) {
                serviceUrl = PREMATCH_URL;
            } else {
                serviceUrl = LIVE_URL;
            }

            if (requestData.getMethod() == ArbsAPIRequestData.Method.GET) {
                String url = String.format(serviceUrl, StringUtils.join(requestData.getBookmakerIds(), ","));
                lastResponse = Request.Get(OddsmarketAPI.encodeURL(url + "?" + requestData.getQueryForGetRequest()))
                        .addHeader("Content-Type", "text/" + requestData.getFormat().toString())
                        .addHeader("Accept", "application/" + requestData.getFormat().toString())
                        .execute();
                return lastResponse;
            }

            if (requestData.getMethod() == ArbsAPIRequestData.Method.POST) {
                String url = String.format(serviceUrl, StringUtils.join(requestData.getBookmakerIds(), ","));
                lastResponse = Request.Post(url + "?apiKey="+requestData.getApiKey())
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
