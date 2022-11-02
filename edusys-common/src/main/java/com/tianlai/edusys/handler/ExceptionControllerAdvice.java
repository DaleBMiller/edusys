package com.tianlai.edusys.handler;


import com.tianlai.edusys.common.R;
import com.tianlai.edusys.common.ResultCode;
import com.tianlai.edusys.exception.ApplicationException;
import com.tianlai.edusys.exception.NoDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    // GET请求中@Valid验证参数失败后抛出异常BindException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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



    // 不支持的请求方法
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<String> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        //return R.error(ResultCode.METHOD_NOT_SUPPORTED);
        return R.error(ResultCode.METHOD_NOT_SUPPORTED);
    }

    // 不支持的媒体类型
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public R<MediaType> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return R.error(ResultCode.MEDIA_TYPE_NOT_SUPPORTED,ex.getContentType());
    }

    // 处理资源未发现异常ResourceNotFoundException
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoDataException.class)
    public R<String> handleResourceNotFoundException(NoDataException e) {
        return R.error(ResultCode.NOT_FOUND,e.getMessage());
    }

    // 没有发现资源 404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public R<String> handleNoHandlerFoundException(NoHandlerFoundException e) {
        e.printStackTrace();
        return R.error(ResultCode.NO_HANDLER_FOUND,e.getMessage());
    }

    // 处理自定义异常ApplicationException
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ApplicationException.class)
    public R<String> ApplicationExceptionHandler(ApplicationException e) {
        e.printStackTrace();
        // 异常中指定的错误代码，消息按优先级合并在异常的message中
        return R.error(e.getResultCode(),e.getMessage());
    }

    // 通常在最后的方法处理最大的Exception，保证兜底
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public R<String> exceptionHandler(Exception e) {
        e.printStackTrace();
        return R.error(ResultCode.ERROR,e.getMessage());
    }
}