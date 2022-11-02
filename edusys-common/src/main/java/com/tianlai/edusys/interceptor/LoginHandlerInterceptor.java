package com.tianlai.edusys.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianlai.edusys.common.R;
import com.tianlai.edusys.common.ResultCode;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取当前session
        HttpSession session = request.getSession();
        //获取"user"
        final Object user = session.getAttribute("user");
        //判断是否为空，为空，就是没有登录，拦截
        if (user == null) {
            //使用jackson工具转换响应（response）为json格式
            final ObjectMapper objectMapper = new ObjectMapper();
            //封装登陆异常信息
            R<Object> errorinfo = R.error(ResultCode.TOKEN_ERROR);
            //转换json
            final String json = objectMapper.writeValueAsString(errorinfo);
            //设置响应码
            response.setContentType("application/json;charset=utf-8");
            //通过响应对象返回数据
            response.getWriter().write(json);
            return false;
        }
        // 放行当前的请求资源，能走到这里，
        // 表示当前的session域中有用户对象，表示已经登录，所以直接放行
        return true;
    }
}
