package com.cssweb.ops.nlp;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NLPService {



    Config config = new Config();

    Retrofit retrofit;
    APIService api;

    public boolean init() {
        if (!config.load()) {
            System.out.println("打开配置文件失败");
            return false;
        }

        System.out.println("打开配置文件成功");
        System.out.println("appid = " + config.getAppId());
        System.out.println("apikey = " + config.getApiKey());
        System.out.println("authid = " + config.getAuthId());
        System.out.println("scene = " + config.getScene());

        retrofit = new Retrofit.Builder()
                .baseUrl("http://openapi.xfyun.cn")
             //   .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();

        api = retrofit.create(APIService.class);

        return true;
    }

    public NLPResponse sendText(String text) {

        if (api == null)
            return null;


        NLPResponse nlpResponse = null;

        try {



            String curTime = System.currentTimeMillis() / 1000L + "";

            String param = "{\"auth_id\":\""+config.getAuthId()+"\",\"data_type\":\"text\",\"scene\":\""+config.getScene()+"\"}";
            //使用个性化参数时参数格式如下：
            //String param = "{\"aue\":\""+AUE+"\",\"sample_rate\":\""+SAMPLE_RATE+"\",\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\",\"pers_param\":\""+PERS_PARAM+"\"}";
            String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));

            String checkSum = DigestUtils.md5Hex(config.getApiKey() + curTime + paramBase64);



            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),text);

            Call<NLPResponse> call = api.nlp(config.getAppId(), curTime, paramBase64, checkSum, body);

            Response<NLPResponse> response = call.execute();

           // System.out.println(response.body().toString());
            nlpResponse = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return nlpResponse;
    }

    public NLPResponse sendAudio(String path) {


        if (!Files.exists(Paths.get(path))) {
            System.out.println("文件不存在");
            return null;
        }

        try {
            byte[] raw = Files.readAllBytes(Paths.get(path));
            return sendAudio(raw);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public NLPResponse sendAudio(byte[] audio) {
        if (api == null)
            return null;


        NLPResponse nlpResponse = null;

        try {



            String curTime = System.currentTimeMillis() / 1000L + "";


            String param = "{\"aue\":\"raw\",\"sample_rate\":\"16000\",\"auth_id\":\""+config.getAuthId()+"\",\"data_type\":\"audio\",\"scene\":\""+config.getScene()+"\"}";
            String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));

            String checkSum = DigestUtils.md5Hex(config.getApiKey() + curTime + paramBase64);




            RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), audio);

            Call<NLPResponse> call = api.nlp(config.getAppId(), curTime, paramBase64, checkSum, body);

            Response<NLPResponse> response = call.execute();

          //  System.out.println(response.body().toString());
            nlpResponse = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return nlpResponse;
    }


}
