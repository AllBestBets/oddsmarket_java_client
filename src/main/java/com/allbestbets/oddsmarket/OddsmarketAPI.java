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

/**
 * Created by andrey on 21.09.16.
 */
public class OddsmarketAPI {
    public enum APIType {
        PREMATCH, LIVE;
    }

    public static String encodeURL(String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            return uri.toASCIIString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String SPORTS = "https://api-mst.oddsmarket.org/v1/sports";

    public static APIResponse sports(SportsAPIRequestData sportsAPIRequestData) {
        try {

            Request req = null;

            if (sportsAPIRequestData.getMethod() == SportsAPIRequestData.Method.GET) {
                req = Request.Get(OddsmarketAPI.encodeURL(SPORTS));
            }
            if (sportsAPIRequestData.getMethod() == SportsAPIRequestData.Method.POST) {
                req = Request.Post(SPORTS);
            }

            Response response = req
                    .addHeader("Content-Type", "text/" + sportsAPIRequestData.getFormat().toString())
                    .addHeader("Accept", "application/" + sportsAPIRequestData.getFormat().toString())
                    .execute();

            HttpResponse httpResponse = response.returnResponse();
            String content = EntityUtils.toString(httpResponse.getEntity());

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

    private static final String BOOKMAKERS = "https://api-mst.oddsmarket.org/v1/bookmakers";

    public static APIResponse bookmakers(BookmakersAPIRequestData sportsAPIRequestData) {
        try {
            Request req = null;

            if (sportsAPIRequestData.getMethod() == BookmakersAPIRequestData.Method.GET) {
                req = Request.Get(OddsmarketAPI.encodeURL(BOOKMAKERS));
            }

            if (sportsAPIRequestData.getMethod() == BookmakersAPIRequestData.Method.POST) {
                req = Request.Post(BOOKMAKERS);
            }

            Response response = req
                    .addHeader("Content-Type", "text/" + sportsAPIRequestData.getFormat().toString())
                    .addHeader("Accept", "application/" + sportsAPIRequestData.getFormat().toString())
                    .execute();

            HttpResponse httpResponse = response.returnResponse();
            String content = EntityUtils.toString(httpResponse.getEntity());

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
