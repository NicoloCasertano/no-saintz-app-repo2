package com.example.app.models.entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Entity
@Table(name = "beat")
public class Beat {

    @Id
    @Column(name = "beat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer beatId;

    @Column(name = "beat_title")
    private String beatTitle;

    @Column(name = "beat_bpm")
    private Integer beatBpm;

    @Column(name = "beat_key")
    private String beatKey;

    @Column(name = "beat_description")
    private String beatDescription;

    @Column(name = "beat_price")
    private BigDecimal beatPrice;

    public Beat() {
    }

    public Beat(Integer beatId, String beatTitle, int beatBpm, String beatKey, String beatDescription, BigDecimal beatPrice) {
        this.beatId = beatId;
        this.beatTitle = beatTitle;
        this.beatBpm = beatBpm;
        this.beatKey = beatKey;
        this.beatDescription = beatDescription;
        this.beatPrice = beatPrice;
    }


    public Integer getBeatId() {
        return beatId;
    }

    public void setBeatId(Integer beatId) {
        this.beatId = beatId;
    }

    public String getBeatTitle() {
        return beatTitle;
    }

    public void setBeatTitle(String beatTitle) {
        this.beatTitle = beatTitle;
    }

    public int getBeatBpm() {
        return beatBpm;
    }

    public void setBeatBpm(int beatBpm) {
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
