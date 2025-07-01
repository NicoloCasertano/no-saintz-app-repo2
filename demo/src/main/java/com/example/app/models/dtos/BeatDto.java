package com.example.app.models.dtos;

import com.example.app.models.entities.Beat;

import java.math.BigDecimal;

public class BeatDto {
    private Integer id;
    private String beatTitle;
    private Integer beatBpm;
    private String beatKey;
    private String beatDescription;
    private BigDecimal beatPrice;

    public BeatDto(Integer id, String beatTitle, Integer beatBpm, String beatKey, String beatDescription, BigDecimal beatPrice) {
        this.id = id;
        this.beatTitle = beatTitle;
        this.beatBpm = beatBpm;
        this.beatKey = beatKey;
        this.beatDescription = beatDescription;
        this.beatPrice = beatPrice;
    }


    public Beat toBeat() {
        return new Beat(id, beatTitle, beatBpm, beatKey, beatDescription, beatPrice);
    }

    static public BeatDto toDto(Beat b) {
        return new BeatDto(b.getBeatId(), b.getBeatTitle(), b.getBeatBpm(), b.getBeatKey(), b.getBeatDescription(), b.getBeatPrice());
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
}
