package com.sathya.tcs_daggergithub.REST;


import com.sathya.tcs_daggergithub.model.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubClient {

   @GET("users/{user}")
    Call<GithubUser> getUser(@Path("user") String user);



}
