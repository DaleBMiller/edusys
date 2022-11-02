package com.tianlai.edusys.exception;

import com.tianlai.edusys.common.ResultCode;

// 未找数据到异常。因为这个异常返回状态码是404。
public class NoDataException extends ApplicationException {
public NoDataException() {
super(ResultCode.NO_RESPONSE_DATA);
}
public NoDataException(String message) {
super(ResultCode.NO_RESPONSE_DATA, message);
}
public NoDataException(Throwable cause) {
super(ResultCode.NO_RESPONSE_DATA, cause);
}
public NoDataException(String message, Throwable cause) {
super(ResultCode.NO_RESPONSE_DATA, message, cause);
}
}