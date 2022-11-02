package com.tianlai.edusys.handler;

import com.tianlai.edusys.common.R;
import com.tianlai.edusys.common.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

public class ValidationException {
    // GET请求中@Valid验证参数失败后抛出异常BindException
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400错误
    @ExceptionHandler(BindException.class)
    public R<String> handlerBindException(BindException e) {
        StringBuilder messages = new StringBuilder(1024);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            messages.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n\r");
        }
        return R.error(ResultCode.PARAM_VALID_ERROR,messages.toString());
    }
// @RequestParam参数校验失败后抛出异常ConstraintViolationException
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(ConstraintViolationException.class)
    public R<String> handlerMethodArgumentNotValidException(ConstraintViolationException e) {
        StringBuilder messages = new StringBuilder(1024);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> error : constraintViolations) {
            messages.append(error.getPropertyPath().toString()).append(": ").append(error.getMessage()).append("\n\r");
        }
        return R.error(ResultCode.PARAM_VALID_ERROR,messages.toString());
    }
    // @RequestBody参数校验失败后抛出异常MethodArgumentNotValidException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public R<String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder messages = new StringBuilder(1024);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            messages.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n\r");
        }
        return R.error(ResultCode.PARAM_VALID_ERROR,messages.toString());
    }

}
