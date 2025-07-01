package com.example.app.models.searchCriteria;

import java.math.BigDecimal;

public class BeatFilterCriteria {
    private Integer id;
    private String beatTitle;
    private Integer beatBpm;
    private String beatKey;
    private String beatDescription;
    private BigDecimal beatPrice;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String sort;
    private int page;
    private int size;

    public BeatFilterCriteria(Integer id, String beatTitle, Integer beatBpm, String beatKey, String beatDescription, BigDecimal beatPrice, BigDecimal minPrice, BigDecimal maxPrice) {
        this.id = id;
        this.beatTitle = beatTitle;
        this.beatBpm = beatBpm;
        this.beatKey = beatKey;
        this.beatDescription = beatDescription;
        this.beatPrice = beatPrice;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeatTitle() {
        return beatTitle;
    }

    public void setBeatTitle(String beatTitle) {
        this.beatTitle = beatTitle;
    }

    public Integer getBeatBpm() {
        return beatBpm;
    }

    public void setBeatBpm(Integer beatBpm) {
        this.beatBpm = beatBpm;
    }

    public String getBeatKey() {
        return beatKey;
    }

    public void setBeatKey(String beatKey) {
        this.beatKey = beatKey;
    }

    public String getBeatDescription() {
        return beatDescription;
    }

    public void setBeatDescription(String beatDescription) {
        this.beatDescription = beatDescription;
    }

    public BigDecimal getBeatPrice() {
        return beatPrice;
    }

    public void setBeatPrice(BigDecimal beatPrice) {
        this.beatPrice = beatPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
