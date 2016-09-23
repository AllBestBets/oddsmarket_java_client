package com.allbestbets.oddsmarket.apis;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import java.util.Set;

public class EventResultsAPIRequestData extends BaseRequestData {

    private String apiKey;
    private Set<Long> sportIds;
    private Long leagueId;
    private String startedFrom; // yyyy-MM-dd hh:mm:ss
    private String startedTo; // yyyy-MM-dd hh:mm:ss

    public EventResultsAPIRequestData(String apiKey) {
        this.apiKey = apiKey;
    }

    public EventResultsAPIRequestData(String apiKey, Method method) {
        this.apiKey = apiKey;
        this.method = method;
    }

    public EventResultsAPIRequestData(String apiKey, Format format) {
        this.apiKey = apiKey;
        this.format = format;
    }

    public EventResultsAPIRequestData(String apiKey, Method method, Format format) {
        this.apiKey = apiKey;
        this.method = method;
        this.format = format;
    }

    public EventResultsAPIRequestData setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public EventResultsAPIRequestData setSportIds(Set<Long> sportIds) {
        this.sportIds = sportIds;
        return this;
    }

    public EventResultsAPIRequestData setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
        return this;
    }

    public EventResultsAPIRequestData setStartedFrom(String startedFrom) {
        this.startedFrom = startedFrom;
        return this;
    }

    public EventResultsAPIRequestData setStartedTo(String startedTo) {
        this.startedTo = startedTo;
        return this;
    }

    public Set<Long> getSportIds() {
        return sportIds;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public String getStartedFrom() {
        return startedFrom;
    }

    public String getStartedTo() {
        return startedTo;
    }

    public Method getMethod() {
        return method;
    }

    public Format getFormat() {
        return format;
    }

    public EventResultsAPIRequestData setMethod(Method method) {
        this.method = method;
        return this;
    }

    public EventResultsAPIRequestData setFormat(Format format) {
        this.format = format;
        return this;
    }

    private String queryForGetRequest;
    private String bodyForPostRequest;

    public EventResultsAPIRequestData build() {
        if (getMethod() == Method.GET) {
            final StringBuilder sb = new StringBuilder().
                    append("apiKey").append("=").append(getApiKey()).append("&");

            if (getSportIds() != null)
                sb.append("sportIds").append("=").append(StringUtils.join(getSportIds(), ",")).append("&");

            if (getLeagueId() != null)
                sb.append("leagueId").append("=").append(getLeagueId()).append("&");

            if (getStartedFrom() != null)
                sb.append("startedFrom").append("=").append(getStartedFrom()).append("&");

            if (getStartedTo() != null)
                sb.append("startedTo").append("=").append(getStartedTo()).append("&");

            queryForGetRequest = sb.toString();

        } else if (getMethod() == Method.POST) {
            final JSONObject json = new JSONObject();

            if (getSportIds() != null)
                json.put("sportIds", getSportIds());

            if (getLeagueId() != null)
                json.put("leagueId", getLeagueId());

            if (getStartedFrom() != null)
                json.put("startedFrom", getStartedFrom());

            if (getStartedTo() != null)
                json.put("startedTo", getStartedTo());

            bodyForPostRequest = json.toString();

        } else {
            return null;
        }

        return this;
    }

    public String getQueryForGetRequest() {
        return queryForGetRequest;
    }

    public String getBodyForPostRequest() {
        return bodyForPostRequest;
    }
}
