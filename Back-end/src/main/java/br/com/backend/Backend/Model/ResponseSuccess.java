package br.com.backend.Backend.Model;

import java.util.List;

public class ResponseSuccess {
    private String message;
    private int code;
    private List data;

    public ResponseSuccess(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ResponseSuccess(String message, int code, List data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
