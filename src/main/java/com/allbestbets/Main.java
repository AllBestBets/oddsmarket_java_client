package com.allbestbets;

import com.allbestbets.oddsmarket.OddsmarketAPI;
import com.allbestbets.oddsmarket.apis.*;
import org.apache.http.client.fluent.Response;

import java.io.IOException;
import java.util.HashSet;

public class Main {
    private static final String API_KEY = "YOU_API_KEY_HERE";

    public static void main(String[] args) throws IOException {

        OddsAPI oddsAPI = new OddsAPI(new OddsAPIRequestData(API_KEY,
                new HashSet<Long>(){{ add(1L); }})
                .setMethod(OddsAPIRequestData.Method.GET).build(),
                OddsmarketAPI.APIType.LIVE);
        APIResponse resp = oddsAPI.execute();

        System.out.println("odds = " + resp.getContent());

        ArbsAPI arbsAPI = new ArbsAPI(new ArbsAPIRequestData(API_KEY,
                new HashSet<Long>(){{ add(1L); add(2L); }})
                .setMethod(OddsAPIRequestData.Method.GET).build(),
                OddsmarketAPI.APIType.LIVE);
        APIResponse arbsResp = arbsAPI.execute();

        System.out.println("arbs = " + arbsResp.getContent());

        EventResultsAPI resultsAPIAPI = new EventResultsAPI(new EventResultsAPIRequestData(API_KEY)
                .setMethod(OddsAPIRequestData.Method.GET)
                .setStartedFrom("2016-09-01 13:00:00")
                .setStartedTo("2016-09-10 13:00:00")
                .setSportIds(new HashSet<Long>(){{ add(1L); }}).build());

        APIResponse resultsResp = resultsAPIAPI.execute();

        System.out.println("results = " + resultsResp.getContent());

        System.out.println("sports = " + OddsmarketAPI.sports().getContent());
        System.out.println("bookmakers = " + OddsmarketAPI.bookmakers().getContent());

    }
}
