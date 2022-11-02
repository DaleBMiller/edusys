package com.tianlai.edusys.exception;

import com.tianlai.edusys.common.ResultCode;

public class ApplicationException extends RuntimeException{
    private final ResultCode resultCode;
    public ApplicationException(String message) {
        super(message);
        this.resultCode = ResultCode.ERROR;
    }
    public ApplicationException(Throwable cause) {
        super(cause);
        this.resultCode = ResultCode.ERROR;
    }
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.resultCode = ResultCode.ERROR;
    }
    public ApplicationException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }
    public ApplicationException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }
    public ApplicationException(ResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;

    }
    public ApplicationException(ResultCode resultCode, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
    }
    public ResultCode getResultCode() {
        return resultCode;
    }

}
