/*
    Docs:
    https://www.oddsmarket.org/developers/prematch_odds
    https://www.oddsmarket.org/developers/live_odds
 */
package com.allbestbets.oddsmarket.apis;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class OddsAPIRequestData extends BaseRequestData {

    private String apiKey;
    private Set<Long> bookmakerIds = new HashSet<Long>();

    private Set<Long> sportIds;
    private Boolean onlyMain;
    private Boolean onlyBack;
    private Boolean showDirectLink;
    private Long lastUpdatedAt;

    public OddsAPIRequestData(String apiKey, long... bookmakerIds) {
        this(apiKey, null, null, bookmakerIds);
    }

    public OddsAPIRequestData(String apiKey, Method method, long... bookmakerIds) {
        this(apiKey, method, null, bookmakerIds);
    }

    public OddsAPIRequestData(String apiKey, Format format, long... bookmakerIds) {
        this(apiKey, null, format, bookmakerIds);
    }

    public OddsAPIRequestData(String apiKey, Method method, Format format, long... bookmakerIds) {
        this.apiKey = apiKey;

        if (method != null)
            this.method = method;

        if (format != null)
            this.format = format;

        for (long bookmakerId : bookmakerIds)
            this.bookmakerIds.add(bookmakerId);
    }

    public OddsAPIRequestData setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public OddsAPIRequestData setBookmakerIds(Set<Long> bookmakerIds) {
        this.bookmakerIds = bookmakerIds;
        return this;
    }

    public OddsAPIRequestData setSportIds(Set<Long> sportIds) {
        this.sportIds = sportIds;
        return this;
    }

    public OddsAPIRequestData setOnlyMain(Boolean onlyMain) {
        this.onlyMain = onlyMain;
        return this;
    }

    public OddsAPIRequestData setOnlyBack(Boolean onlyBack) {
        this.onlyBack = onlyBack;
        return this;
    }

    public OddsAPIRequestData setShowDirectLink(Boolean showDirectLink) {
        this.showDirectLink = showDirectLink;
        return this;
    }

    public OddsAPIRequestData setLastUpdatedAt(Long lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Set<Long> getBookmakerIds() {
        return bookmakerIds;
    }

    public Set<Long> getSportIds() {
        return sportIds;
    }

    public Boolean getOnlyMain() {
        return onlyMain;
    }

    public Boolean getOnlyBack() {
        return onlyBack;
    }

    public Boolean getShowDirectLink() {
        return showDirectLink;
    }

    public Long getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    private String queryForGetRequest;
    private String bodyForPostRequest;

    public OddsAPIRequestData build() {
        if (getMethod() == Method.GET) {
            final StringBuilder sb = new StringBuilder().
                    append("apiKey").append("=").append(getApiKey()).append("&");

            if (getSportIds() != null)
                sb.append("sportIds").append("=").append(StringUtils.join(getSportIds(), ",")).append("&");

            if (getOnlyMain() != null)
                sb.append("onlyMain").append("=").append(getOnlyMain()).append("&");

            if (getOnlyBack() != null)
                sb.append("onlyBack").append("=").append(getOnlyBack()).append("&");

            if (getShowDirectLink() != null)
                sb.append("showDirectLink").append("=").append(getShowDirectLink()).append("&");

            if (getLastUpdatedAt() != null)
                sb.append("lastUpdatedAt").append("=").append(getLastUpdatedAt()).append("&");

            queryForGetRequest = sb.toString();

        } else if (getMethod() == Method.POST) {
            final JSONObject json = new JSONObject();

            json.put("apiKey", getApiKey());

            if (getSportIds() != null)
                json.put("sportIds", getSportIds());

            if (getOnlyMain() != null)
                json.put("onlyMain", getOnlyMain());

            if (getOnlyBack() != null)
                json.put("onlyBack", getOnlyBack());

            if (getShowDirectLink() != null)
                json.put("showDirectLink", getShowDirectLink());

            if (getLastUpdatedAt() != null)
                json.put("lastUpdatedAt", getLastUpdatedAt());

            bodyForPostRequest = json.toString();
        }

        return this;
    }

    public String getBodyForPostRequest() {
        return bodyForPostRequest;
    }

    public String getQueryForGetRequest() {
        return queryForGetRequest;
    }

    public Method getMethod() {
        return method;
    }

    public Format getFormat() {
        return format;
    }

    public OddsAPIRequestData setMethod(Method method) {
        this.method = method;
        return this;
    }

    public OddsAPIRequestData setFormat(Format format) {
        this.format = format;
        return this;
    }
}
