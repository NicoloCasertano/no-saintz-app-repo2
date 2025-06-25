package com.example.app.models.searchCriteria;

import com.example.app.models.entities.User;

import java.time.LocalDate;
import java.util.List;

public class WorkFilterCriteria {

    private Integer workId;
    private String title;
    private Integer bpm;
    private String key;
    private List<User> user;
    private LocalDate dataDiCreazione;
    private LocalDate minData;
    private LocalDate maxData;
    private String nota;
    private String sort;
    private int page;
    private int size;

    public WorkFilterCriteria(Integer workId,
                              String title,
                              Integer bpm,
                              String key,
                              List<User> user,
                              LocalDate dataDiCreazione,
                              LocalDate minData,
                              LocalDate maxData,
                              String nota,
                              String sort) {
        this.workId = workId;
        this.title = title;
        this.bpm = bpm;
        this.key = key;
        this.user = user;
        this.dataDiCreazione = dataDiCreazione;
        this.minData = minData;
        this.maxData = maxData;
        this.nota = nota;
        this.sort = sort;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public LocalDate getDataDiCreazione() {
        return dataDiCreazione;
    }

    public void setDataDiCreazione(LocalDate dataDiCreazione) {
        this.dataDiCreazione = dataDiCreazione;
    }

    public LocalDate getMinData() {
        return minData;
    }

    public void setMinData(LocalDate minData) {
        this.minData = minData;
    }

    public LocalDate getMaxData() {
        return maxData;
    }

    public void setMaxData(LocalDate maxData) {
        this.maxData = maxData;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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
