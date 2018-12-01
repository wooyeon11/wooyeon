package pyneer.full_time_wannabe.api;

/**
 * Created by ddjdd on 2018-10-10.
 */

public abstract class RestApiResult {

    private static final String TAG = RestApiResult.class.getSimpleName();

    private String apiName;
    private boolean result;

    public String getApiName() {
        return apiName;
    }

    public RestApiResult setApiName(String api) {
        this.apiName = api;
        return this;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
