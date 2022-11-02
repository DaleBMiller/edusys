package com.tianlai.edusys.controller;

import com.pig4cloud.captcha.ArithmeticCaptcha;
import com.tianlai.edusys.exception.ApplicationException;
import com.tianlai.edusys.service.impl.UserServiceImpl;
import com.tianlai.edusys.entity.User;
import com.tianlai.edusys.common.R;
import com.tianlai.edusys.vo.LoginReqVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户 前端控制器
 *
 * @author Dale
 * @since 2022-11-01
 */
@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "用户控制器类", description = "UserController 用户 后端数据接口")
public class UserController {
    private final UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * 获取验证码
     *
     * @param request  拦截获取验证码请求
     * @param response 响应验证码
     * @throws Exception 异常
     */
    @Operation(summary = "验证码获取", description = "提供验证码")
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置请求头为输出图片类型
        response.setContentType("image/gif");
        //关闭缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //获取验证码
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(120, 40, 2);
        //验证码存入session
        request.getSession().setAttribute("captcha", captcha.text());
        //输出图片流
        captcha.out(response.getOutputStream());
    }


    /**
     * 登录
     *
     * @param loginReqVo 用户登录vo
     * @return R
     */
    @Operation(summary = "用户登录", description = "用户登录功能")
    @PostMapping("/login")
    public R<User> login(@RequestBody @Validated LoginReqVo loginReqVo, HttpSession session, HttpServletResponse response) {
        //校验验证码
        //从session获取验证码
        Object captcha = session.getAttribute("captcha");
        //清除session当中的验证码
        session.removeAttribute("captcha ");
        //获取用户输入的验证码
        String checkCode = loginReqVo.getCheckCode();
        //判断验证码是否正确
        if (!checkCode.equals(captcha.toString())) {
            //验证码不正确抛出异常
            throw new ApplicationException("验证码错误！");
        }
        //校验用户名和密码
        R<User> userR = userService.login(loginReqVo);
        //到这里代表用户名，密码都正确
        //将用户保存到session当中
        session.setAttribute("user", userR.getData());
        //设置Cookie（记住我）
        //获取Cookie对象
        Cookie jsessionid = new Cookie("JSESSIONID", session.getId());
        if (loginReqVo.isSaveMe()) {
            //如果勾选了记住我，那么设置Cookie时长 (s为基础时间单位)
            jsessionid.setMaxAge(7 * 24 * 60 * 60);
        } else {
            //没有勾选，则设置时间失效
            jsessionid.setMaxAge(-1);
        }
        //设置响应对象的Cookie
        response.addCookie(jsessionid);
        return userR;
    }

    /**
     * 登录
     *
     * @param session 当前登录用户会话
     * @return R
     */
    @Operation(summary = "用户登出", description = "用户登出功能")
    @GetMapping("/logout")
    public R<Object> logout(HttpSession session) {
        // 直接让当前会话的session失效
        session.invalidate();
        // 返回消息
        return R.ok().setMessage("退出成功");
    }


    /**
     * 注册
     *
     * @param user 用户注册对象
     * @return R
     */
    @Operation(summary = "用户注册", description = "用户注册功能")
    @RequestMapping("/register")
    public R<User> register(@RequestBody @Validated User user) {
        return userService.register(user);
    }


    /**
     * 修改 用户
     *
     * @param user 用户
     * @return Result
     */
    @Operation(summary = "修改用户", description = "修改用户")
    @PutMapping("/update")
    public R<User> updateById(@RequestBody @Validated User user) {
        return userService.updateUser(user);
    }

    /**
     * 通过id删除 用户
     *
     * @param id id
     * @return Result
     */
    @Operation(summary = "通过id删除用户",
            description = "通过id删除用户")
    @DeleteMapping("/delete/{id}")
    public R<String> removeById(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }

}
