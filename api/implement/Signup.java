package pyneer.full_time_wannabe.api.implement;

import pyneer.full_time_wannabe.api.GenericResult;
import pyneer.full_time_wannabe.api.RestApi;
import pyneer.full_time_wannabe.api.RestApiResult;

/**
 * Created by ddjdd on 2018-10-10.
 */

public class Signup extends RestApi {
    private String email;
    private String pw;
    private String name;
    @Override
    public Class<? extends RestApiResult> getResultClass() {
        return GenericResult.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}