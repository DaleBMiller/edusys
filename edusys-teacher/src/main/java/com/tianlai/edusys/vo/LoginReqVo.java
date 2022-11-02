package com.tianlai.edusys.vo;

import lombok.Data;

/**
* 登录请求表单数据封装对象
* @author xlZhang
* @since 2022-05-15
*/
@Data
public class LoginReqVo {
//用户名
private String username;
//密码
private String password;
// 验证码
private String checkCode;
// 记住我
private boolean saveMe;
}