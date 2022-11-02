package com.tianlai.edusys.service;

import com.tianlai.edusys.common.R;
import com.tianlai.edusys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tianlai.edusys.vo.LoginReqVo;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Dale
 * @since 2022-11-01
 */
public interface UserService extends IService<User> {

    R<User> login(LoginReqVo loginReqVo);
}
