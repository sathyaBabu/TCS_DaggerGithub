package com.sathya.tcs_daggergithub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubUser {
    /**
     * "name": "Sathya Babu ",
     * "company": "Edureka",
     * "blog": "www.edureka.co",
     * "location": "Bangalore",
     * "bio": "Been in IT for over two decades, Consulting, Development, and corporate training.",
     * "avatar_Url: url"......................image";
     */

    private String name;
    private String company;
    private String blog;
    private String bio;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getBlog() {
        return blog;
    }

    public String getCompany() {
        return company;
    }

    public String getBio() {
        return bio;
    }
}
