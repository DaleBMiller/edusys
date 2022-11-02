package com.tianlai.edusys.common;

/**
 * 统一的结果业务状态码定义枚举类
 */
public enum ResultCode {
    SUCCESS(20000,"操作成功"),
    ERROR(50000,"操作失败"),
    DATA_ERROR(50001,"参数异常"),
    NO_RESPONSE_DATA(50002,"无响应数据"),
    FILE_UPLOAD_ERROR(50004, "文件上传错误"),
    FILE_DELETE_ERROR(50005, "文件刪除错误"),
    EXCEL_DATA_IMPORT_ERROR(50006, "Excel数据导入错误"),

    SYSTEM_VERIFY_CODE_NOT_EMPTY(51001,"验证码不能为空"),
    SYSTEM_VERIFY_CODE_ERROR(51002,"验证码错误"),

    SYSTEM_USERNAME_NOT_EMPTY(40101,"账号不能为空"),
    SYSTEM_USERNAME_NOT_EXISTS(40102,"账号不存在"),
    SYSTEM_USERNAME_EXPIRED(40103,"账户过期"),
    SYSTEM_USERNAME_LOCKED(40104,"账户被锁"),
    SYSTEM_USERNAME_DISABLED(40105,"账户被禁用"),
    SYSTEM_PASSWORD_ERROR(40106,"账号或密码错误"),
    SYSTEM_PASSWORD_EXPIRED(40107,"密码过期"),
    SYSTEM_USERNAME_OFFLINE(40108,"已下线，请重新登录"),
    SYSTEM_ERROR(40109,"系统异常请稍后再试"),
    ACCOUNT_EXISTS_ERROR(40110,"该账号已存在"),
    TOKEN_ERROR(40111,"用户未登录，请先登录"),
    OLD_PASSWORD_ERROR(40112,"旧密码不匹配"),

    NOT_PERMISSION(40301,"没有权限访问该资源"),
    TOKEN_NOT_NULL(40302,"token 不能为空"),
    TOKEN_NO_AVAIL(40303,"token无效或过期"),
    TOKEN_PAST_DUE(40304,"登录失效,请重新登录"),
    TOKEN_EXISTS(40305,"账号异地登录，你已被迫退出"),

    NO_HANDLER_FOUND(40401,"请求路径异常"),
    NOT_FOUND(40402,"请求资源不存在"),

    PARAM_VALID_ERROR(40501, "请求参数校验错误"),
    METHOD_NOT_SUPPORTED(40502, "不支持的请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED(40503, "不支持的媒体类型"),

    OPERATION_MENU_PERMISSION_CATALOG_ERROR(0,"操作后的菜单类型是目录，所属菜单必须为默认顶级菜单或者目录"),
    OPERATION_MENU_PERMISSION_MENU_ERROR(0,"操作后的菜单类型是菜单，所属菜单必须为目录类型"),
    OPERATION_MENU_PERMISSION_BTN_ERROR(0,"操作后的菜单类型是按钮，所属菜单必须为菜单类型"),
    OPERATION_MENU_PERMISSION_URL_NOT_NULL(0,"菜单权限的url不能为空"),
    OPERATION_MENU_PERMISSION_URL_PERMS_NULL(0,"菜单权限的标识符不能为空"),
    OPERATION_MENU_PERMISSION_URL_METHOD_NULL(0,"菜单权限的请求方式不能为空"),
    OPERATION_MENU_PERMISSION_URL_CODE_NULL(0,"菜单权限的按钮标识不能为空"),
    OPERATION_MENU_PERMISSION_UPDATE(0,"操作的菜单权限存在子集关联不允许变更"),
    ROLE_PERMISSION_RELATION(0, "该菜单权限存在子集关联，不允许删除");


    final int code;
    final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}