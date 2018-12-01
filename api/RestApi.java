package pyneer.full_time_wannabe.api;

import com.google.gson.Gson;

/**
 * Created by ddjdd on 2018-10-10.
 */

public abstract class RestApi {


    public static String getBaseUrl() {
        return "http://127.0.0.1:3000/";
    }

    public String getApiUrl() {
        String apiName = getClass().getSimpleName().toLowerCase();
        return getBaseUrl() + apiName;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public abstract Class<? extends RestApiResult> getResultClass();

}
