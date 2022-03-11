package com.example.authenticationservice.retrofiet;

import com.example.authenticationservice.credential.KeycloakAccessToken;
import com.example.authenticationservice.user.KeycloakUser;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface RetrofietUserService {

    @FormUrlEncoded
    @POST("/auth/realms/springboot-quickstart/protocol/openid-connect/token")
    Call<KeycloakAccessToken> login(@FieldMap Map<String,String> params);

    @POST("/auth/admin/realms/springboot-quickstart/users")
    Call<Void> save(@Body KeycloakUser keycloakUser);

    @GET("/auth/admin/realms/springboot-quickstart/users")
    Call<List<KeycloakUser>> findAll();

    @GET("/auth/admin/realms/springboot-quickstart/users/{id}")
    Call<KeycloakUser> findById(@Path("id") String id);

    @PUT("/auth/admin/realms/springboot-quickstart/users/{id}")
    Call<Void> update(@Path("id") String id, @Body KeycloakUser updateKeycloakUser);

    @DELETE("/auth/admin/realms/springboot-quickstart/users/{id}")
    Call<Void> delete(@Path("id") String id);
}
