package com.allbestbets;

import com.allbestbets.oddsmarket.OddsmarketAPI;
import com.allbestbets.oddsmarket.apis.*;

import java.io.IOException;
import java.util.HashSet;

public class Main {
    private static final String API_KEY = "YOU_API_KEY_HERE";

    public static void main(String[] args) throws IOException {
        final OddsAPI oddsAPI = new OddsAPI();
        final APIResponse resp = oddsAPI.execute(new OddsAPIRequestData(API_KEY,
                        new HashSet<Long>() {{
                            add(1L);
                        }})
                        .setMethod(OddsAPIRequestData.Method.GET).build(),
                OddsmarketAPI.APIType.LIVE);

        System.out.println("odds = " + resp.content.length());

        final ArbsAPI arbsAPI = new ArbsAPI();
        final APIResponse arbsResp = arbsAPI.execute(new ArbsAPIRequestData(API_KEY,
                        new HashSet<Long>() {{
                            add(1L);
                            add(2L);
                        }})
                        .setMethod(OddsAPIRequestData.Method.GET).build(),
                OddsmarketAPI.APIType.LIVE);

        System.out.println("arbs = " + arbsResp.content);

        final EventResultsAPI resultsAPIAPI = new EventResultsAPI();
        final APIResponse resultsResp = resultsAPIAPI.execute(new EventResultsAPIRequestData(API_KEY)
                .setMethod(OddsAPIRequestData.Method.GET)
                .setStartedFrom("2016-09-01 13:00:00")
                .setStartedTo("2016-09-10 13:00:00")
                .setSportIds(new HashSet<Long>() {{
                    add(1L);
                }}).build());

        System.out.println("results = " + resultsResp.content);

        System.out.println("sports = " + OddsmarketAPI.sports().content);
        System.out.println("bookmakers = " + OddsmarketAPI.bookmakers().content);
    }
}
