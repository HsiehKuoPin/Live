package com.benjamin.api;

public class ApiResult<T> {
    private final int DEF_RESULT_CODE = -999;
    private int status = -999;
    private T result;
    private String message;
    /** @deprecated */
    @Deprecated
    private int code = -999;
    /** @deprecated */
    @Deprecated
    private T obj;

    public ApiResult() {
    }

    /** @deprecated */
    @Deprecated
    public T getObj() {
        return this.obj;
    }

    /** @deprecated */
    @Deprecated
    public void setObj(T obj) {
        this.obj = obj;
    }

    /** @deprecated */
    @Deprecated
    public int getCode() {
        return this.code;
    }

    /** @deprecated */
    @Deprecated
    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return this.status == -999?this.getCode():this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getResult() {
        return this.result == null?this.getObj():this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
