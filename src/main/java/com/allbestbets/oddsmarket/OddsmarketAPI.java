package com.allbestbets.oddsmarket;

import com.allbestbets.oddsmarket.apis.BookmakersAPIRequestData;
import com.allbestbets.oddsmarket.apis.OddsAPIRequestData;
import com.allbestbets.oddsmarket.apis.SportsAPIRequestData;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by andrey on 21.09.16.
 */
public class OddsmarketAPI {
    public enum APIType {
        PREMATCH, LIVE;
    }

    public static String encodeURL(String urlStr){
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

    public static Response sports(SportsAPIRequestData sportsAPIRequestData){
        try{
            if (sportsAPIRequestData.getMethod() == SportsAPIRequestData.Method.GET) {
                return Request.Get(OddsmarketAPI.encodeURL(SPORTS))
                        .addHeader("Content-Type", "text/" + sportsAPIRequestData.getFormat().toString())
                        .addHeader("Accept", "application/" + sportsAPIRequestData.getFormat().toString())
                        .execute();
            }

            if (sportsAPIRequestData.getMethod() == SportsAPIRequestData.Method.POST) {
                return Request.Post(SPORTS)
                        .addHeader("Content-Type", "text/" + sportsAPIRequestData.getFormat().toString())
                        .addHeader("Accept", "application/" + sportsAPIRequestData.getFormat().toString())
                        .execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Response sports(){
        return sports(new SportsAPIRequestData().build());
    }

    private static final String BOOKMAKERS = "https://api-mst.oddsmarket.org/v1/bookmakers";

    public static Response bookmakers(BookmakersAPIRequestData sportsAPIRequestData){
        try{
            if (sportsAPIRequestData.getMethod() == BookmakersAPIRequestData.Method.GET) {
                return Request.Get(OddsmarketAPI.encodeURL(BOOKMAKERS))
                        .addHeader("Content-Type", "text/" + sportsAPIRequestData.getFormat().toString())
                        .addHeader("Accept", "application/" + sportsAPIRequestData.getFormat().toString())
                        .execute();
            }

            if (sportsAPIRequestData.getMethod() == BookmakersAPIRequestData.Method.POST) {
                return Request.Post(BOOKMAKERS)
                        .addHeader("Content-Type", "text/" + sportsAPIRequestData.getFormat().toString())
                        .addHeader("Accept", "application/" + sportsAPIRequestData.getFormat().toString())
                        .execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Response bookmakers(){
        return bookmakers(new BookmakersAPIRequestData().build());
    }
}
