/*
    Docs:
    https://www.oddsmarket.org/developers/prematch_arbs
    https://www.oddsmarket.org/developers/live_arbs
 */

package com.allbestbets.oddsmarket.apis;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import java.util.Set;

public class ArbsAPIRequestData extends BaseRequestData {

    public enum SortBy {
        percent {
            @Override
            public String toString() {
                return "percent";
            }
        },
        roi {
            @Override
            public String toString() {
                return "roi";
            }
        },
        middleValue {
            @Override
            public String toString() {
                return "middleValue";
            }
        },
        arbAge {
            @Override
            public String toString() {
                return "arbAge";
            }
        },
        beginningTime {
            @Override
            public String toString() {
                return "beginningTime";
            }
        }
    }

    private String apiKey;
    private Set<Long> bookmakerIds;
    private Set<Long> sportIds;
    private Boolean grouped;
    private Long eventId;
    private Float minPercent;
    private Float maxPercent;
    private Integer limit;
    private Set<Long> excludedBetIds;
    private Set<Long> excludedBkEventIds;
    private Set<Long> excludedArbIds;
    private SortBy sortBy;

    public ArbsAPIRequestData(String apiKey, Set<Long> bookmakerIds) {
        this.apiKey = apiKey;
        this.bookmakerIds = bookmakerIds;
    }

    public ArbsAPIRequestData(String apiKey, Set<Long> bookmakerIds, Method method) {
        this.apiKey = apiKey;
        this.bookmakerIds = bookmakerIds;
        this.method = method;
    }

    public ArbsAPIRequestData(String apiKey, Set<Long> bookmakerIds, Format format) {
        this.apiKey = apiKey;
        this.bookmakerIds = bookmakerIds;
        this.format = format;
    }

    public ArbsAPIRequestData(String apiKey, Set<Long> bookmakerIds, Method method, Format format) {
        this.apiKey = apiKey;
        this.bookmakerIds = bookmakerIds;
        this.method = method;
        this.format = format;
    }

    public ArbsAPIRequestData setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public ArbsAPIRequestData setBookmakerIds(Set<Long> bookmakerIds) {
        this.bookmakerIds = bookmakerIds;
        return this;
    }

    public ArbsAPIRequestData setSportIds(Set<Long> sportIds) {
        this.sportIds = sportIds;
        return this;
    }

    public ArbsAPIRequestData setGrouped(Boolean grouped) {
        this.grouped = grouped;
        return this;
    }

    public ArbsAPIRequestData setEventId(Long eventId) {
        this.eventId = eventId;
        return this;
    }

    public ArbsAPIRequestData setMinPercent(Float minPercent) {
        this.minPercent = minPercent;
        return this;
    }

    public ArbsAPIRequestData setMaxPercent(Float maxPercent) {
        this.maxPercent = maxPercent;
        return this;
    }

    public ArbsAPIRequestData setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public ArbsAPIRequestData setExcludedBetIds(Set<Long> excludedBetIds) {
        this.excludedBetIds = excludedBetIds;
        return this;
    }

    public ArbsAPIRequestData setExcludedBkEventIds(Set<Long> excludedBkEventIds) {
        this.excludedBkEventIds = excludedBkEventIds;
        return this;
    }

    public ArbsAPIRequestData setExcludedArbIds(Set<Long> excludedArbIds) {
        this.excludedArbIds = excludedArbIds;
        return this;
    }

    public ArbsAPIRequestData setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public Format getFormat() {
        return format;
    }

    public ArbsAPIRequestData setMethod(Method method) {
        this.method = method;
        return this;
    }

    public ArbsAPIRequestData setFormat(Format format) {
        this.format = format;
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

    public Boolean getGrouped() {
        return grouped;
    }

    public Long getEventId() {
        return eventId;
    }

    public Float getMinPercent() {
        return minPercent;
    }

    public Float getMaxPercent() {
        return maxPercent;
    }

    public Integer getLimit() {
        return limit;
    }

    public Set<Long> getExcludedBetIds() {
        return excludedBetIds;
    }

    public Set<Long> getExcludedBkEventIds() {
        return excludedBkEventIds;
    }

    public Set<Long> getExcludedArbIds() {
        return excludedArbIds;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    private String queryForGetRequest;
    private String bodyForPostRequest;

    public ArbsAPIRequestData build() {
        if (getMethod() == Method.GET) {
            final StringBuilder sb = new StringBuilder().
                    append("apiKey").append("=").append(getApiKey()).append("&");

            if (getSportIds() != null)
                sb.append("sportIds").append("=").append(StringUtils.join(getSportIds(), ",")).append("&");

            if (getGrouped() != null)
                sb.append("grouped").append("=").append(getGrouped()).append("&");

            if (getEventId() != null)
                sb.append("eventId").append("=").append(getEventId()).append("&");

            if (getMinPercent() != null)
                sb.append("minPercent").append("=").append(getMinPercent()).append("&");

            if (getMaxPercent() != null)
                sb.append("maxPercent").append("=").append(getMaxPercent()).append("&");

            if (getLimit() != null)
                sb.append("limit").append("=").append(getLimit()).append("&");

            if (getExcludedBetIds() != null)
                sb.append("excludedBetIds").append("=").append(StringUtils.join(getExcludedBetIds(), ",")).append("&");

            if (getExcludedBkEventIds() != null)
                sb.append("excludedBkEventIds").append("=").append(StringUtils.join(getExcludedBkEventIds(), ",")).append("&");

            if (getExcludedArbIds() != null)
                sb.append("excludedArbIds").append("=").append(StringUtils.join(getExcludedArbIds(), ",")).append("&");

            if (getSortBy() != null)
                sb.append("sortBy").append("=").append(getSortBy().toString()).append("&");

            queryForGetRequest = sb.toString();

        } else if (getMethod() == Method.POST) {
            final JSONObject json = new JSONObject();

            if (getSportIds() != null)
                json.put("sportIds", getSportIds());

            if (getGrouped() != null)
                json.put("grouped", getGrouped());

            if (getEventId() != null)
                json.put("eventId", getEventId());

            if (getMinPercent() != null)
                json.put("minPercent", getMinPercent());

            if (getMaxPercent() != null)
                json.put("maxPercent", getMaxPercent());

            if (getLimit() != null)
                json.put("limit", getLimit());

            if (getExcludedBetIds() != null)
                json.put("excludedBetIds", getExcludedBetIds());

            if (getExcludedBkEventIds() != null)
                json.put("excludedBkEventIds", getExcludedBkEventIds());

            if (getExcludedArbIds() != null)
                json.put("excludedArbIds", getExcludedArbIds());

            if (getSortBy() != null)
                json.put("sortBy", getSortBy().toString());

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