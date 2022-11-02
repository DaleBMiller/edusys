package com.tianlai.edusys.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 登录请求表单数据封装对象
 *
 * @author xlZhang
 * @since 2022-05-15
 */
@Data
public class LoginReqVo {
    //用户名
    @NotEmpty(message = "用户名{userinfo.notEmpty}")
    private String username;
    //密码
    @NotEmpty(message = "密码{userinfo.notEmpty}")
    private String password;
    // 验证码
    @NotEmpty(message = "验证码{userinfo.notEmpty}")
    private String checkCode;
    // 记住我
    private boolean saveMe;
}