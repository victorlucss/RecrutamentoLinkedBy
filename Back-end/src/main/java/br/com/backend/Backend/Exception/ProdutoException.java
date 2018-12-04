package br.com.backend.Backend.Exception;

public class ProdutoException extends RuntimeException{
    private int code;
    public ProdutoException(String exception, int code){
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
