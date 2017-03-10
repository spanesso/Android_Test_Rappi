package com.rappi.androidtestrappi.Implementation.Models;


import com.rappi.androidtestrappi.App.Base.BaseModel;

/**
 * Created by sebastiangomez on 24/02/16.
 */
public class Error extends BaseModel {

    private String code;
    private String message;

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
