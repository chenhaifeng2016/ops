package com.cssweb.ops.nlp;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;



public interface APIService {

    @POST("/v2/aiui")
    Call<NLPResponse> nlp(@Header("X-Appid") String appid, @Header("X-CurTime") String curTime, @Header("X-Param") String param, @Header("X-CheckSum") String checkSum, @Body RequestBody body);
}
