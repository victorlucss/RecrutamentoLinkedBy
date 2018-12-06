package br.com.backend.Backend.Exception;

public class EcommerceException extends RuntimeException{
    private int code;
    public EcommerceException(String exception, int code){
        super(exception);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
