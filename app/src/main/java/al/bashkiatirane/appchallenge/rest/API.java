package al.bashkiatirane.appchallenge.rest;

import al.bashkiatirane.appchallenge.model.UserAuthResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    String BASE_URL="https://lokumanager.almotech.al/public/api/";
//https://lokumanager.almotech.al/public/api/login

    @FormUrlEncoded
    @POST("login")
    Call<UserAuthResponse> login (
            @Field("email") String email,
            @Field("password") String password
    );


}
