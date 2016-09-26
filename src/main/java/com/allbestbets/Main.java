package com.allbestbets;

import com.allbestbets.oddsmarket.OddsmarketAPI;
import com.allbestbets.oddsmarket.apis.*;

import java.io.IOException;
import java.util.HashSet;

public class Main {
    private static final String API_KEY = "YOU_API_KEY_HERE";

    public static void main(String[] args) throws IOException {
        OddsAPIRequestData oddsAPIRequestData = new OddsAPIRequestData(API_KEY, 1, 11, 10).
                setMethod(OddsAPIRequestData.Method.GET)
                .setSportIds(new HashSet<Long>() {{
                    add(1l);
                    add(2l);
                    add(3l);
                }}).build();

        OddsAPI oddsAPI = new OddsAPI(oddsAPIRequestData, OddsmarketAPI.APIType.PREMATCH);

        for (int i = 0; i < 30; i++) {
            APIResponse resp = oddsAPI.execute();

            System.out.println("odds = " + resp.getContent().length());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ArbsAPI arbsAPI = new ArbsAPI(new ArbsAPIRequestData(API_KEY,
                new HashSet<Long>() {{
                    add(1L);
                    add(2L);
                }})
                .setMethod(OddsAPIRequestData.Method.GET).build(),
                OddsmarketAPI.APIType.LIVE);
        APIResponse arbsResp = arbsAPI.execute();

        System.out.println("arbs = " + arbsResp.getContent());

        EventResultsAPI resultsAPIAPI = new EventResultsAPI(new EventResultsAPIRequestData(API_KEY)
                .setMethod(OddsAPIRequestData.Method.GET)
                .setStartedFrom("2016-09-01 13:00:00")
                .setStartedTo("2016-09-10 13:00:00")
                .setSportIds(new HashSet<Long>() {{
                    add(1L);
                }}).build());

        APIResponse resultsResp = resultsAPIAPI.execute();

        System.out.println("results = " + resultsResp.getContent());

        System.out.println("sports = " + OddsmarketAPI.sports().getContent());
        System.out.println("bookmakers = " + OddsmarketAPI.bookmakers().getContent());
    }
}
