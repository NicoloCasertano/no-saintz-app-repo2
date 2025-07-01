package com.example.app.models.searchCriteria;

public class UserFilterCriteria {
    private Integer userId;
    private String userName;
    private String email;
    private String artName;
    private String sort;
    private int page;
    private int size;

    public UserFilterCriteria(Integer userId, String userName, String email, String artName) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.artName = artName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
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
