package com.allbestbets.oddsmarket;

import com.allbestbets.oddsmarket.apis.APIResponse;
import com.allbestbets.oddsmarket.apis.BookmakersAPIRequestData;
import com.allbestbets.oddsmarket.apis.SportsAPIRequestData;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class OddsmarketAPI {

    private static final String SPORTS = "https://api-mst.oddsmarket.org/v1/sports";
    private static final String BOOKMAKERS = "https://api-mst.oddsmarket.org/v1/bookmakers";

    public enum APIType {
        PREMATCH, LIVE;
    }

    public static String encodeURL(String urlStr) {
        final URL url;

        try {
            url = new URL(urlStr);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());

            return uri.toASCIIString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static APIResponse sports(SportsAPIRequestData sportsAPIRequestData) {
        try {
            final Request req;

            if (sportsAPIRequestData.getMethod() == SportsAPIRequestData.Method.GET) {
                req = Request.Get(OddsmarketAPI.encodeURL(SPORTS));

            } else if (sportsAPIRequestData.getMethod() == SportsAPIRequestData.Method.POST) {
                req = Request.Post(SPORTS);

            } else {
                return null;
            }

            final Response response = req
                    .addHeader("Content-Type", "text/" + sportsAPIRequestData.getFormat().toString())
                    .addHeader("Accept", "application/" + sportsAPIRequestData.getFormat().toString())
                    .execute();

            final HttpResponse httpResponse = response.returnResponse();
            final String content = EntityUtils.toString(httpResponse.getEntity());

            return new APIResponse(content,
                    httpResponse.getFirstHeader("Etag") != null ? httpResponse.getFirstHeader("Etag").getValue() : null,
                    httpResponse.getStatusLine().getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static APIResponse sports() {
        return sports(new SportsAPIRequestData().build());
    }


    public static APIResponse bookmakers(BookmakersAPIRequestData sportsAPIRequestData) {
        try {
            final Request req;

            if (sportsAPIRequestData.getMethod() == BookmakersAPIRequestData.Method.GET) {
                req = Request.Get(OddsmarketAPI.encodeURL(BOOKMAKERS));

            } else if (sportsAPIRequestData.getMethod() == BookmakersAPIRequestData.Method.POST) {
                req = Request.Post(BOOKMAKERS);

            } else {
                return null;
            }

            final Response response = req
                    .addHeader("Content-Type", "text/" + sportsAPIRequestData.getFormat().toString())
                    .addHeader("Accept", "application/" + sportsAPIRequestData.getFormat().toString())
                    .execute();

            final HttpResponse httpResponse = response.returnResponse();
            final String content = EntityUtils.toString(httpResponse.getEntity());

            return new APIResponse(content,
                    httpResponse.getFirstHeader("Etag") != null ? httpResponse.getFirstHeader("Etag").getValue() : null,
                    httpResponse.getStatusLine().getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static APIResponse bookmakers() {
        return bookmakers(new BookmakersAPIRequestData().build());
    }
}
