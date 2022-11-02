package com.tianlai.edusys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianlai.edusys.common.R;
import com.tianlai.edusys.common.ResultCode;
import com.tianlai.edusys.entity.User;
import com.tianlai.edusys.exception.ApplicationException;
import com.tianlai.edusys.mapper.UserMapper;
import com.tianlai.edusys.service.UserService;
import com.tianlai.edusys.vo.LoginReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Dale
 * @since 2022-11-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    //获取加密算法对象
    private final PasswordEncoder passwordEncoder;

    //注入
    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public R<User> login(LoginReqVo loginReqVo) {
        //创建查询条件对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //绑定查询对象
        queryWrapper.eq("username", loginReqVo.getUsername());
        //查询用户
        User user = baseMapper.selectOne(queryWrapper);
        //用户名不存在
        if (user == null) {
            //抛出异常
            throw new ApplicationException(ResultCode.SYSTEM_USERNAME_NOT_EXISTS);
        }
        //到这里代表用户存在
        //判断密码是否正确
        //密码解密（SHA-256加密算法（严格来说不叫解密，叫单向加密，复算核查结果是否一致））
        String encodePassword = passwordEncoder.encode(loginReqVo.getPassword());
        //比较密码
        boolean matches = passwordEncoder.matches(loginReqVo.getPassword(), encodePassword);
        //如果密码错误
        if (!matches) {
            //抛出异常
            throw new ApplicationException(ResultCode.SYSTEM_PASSWORD_ERROR);
        }
        //到这里代表用户名，密码都正确
        //返回结果对象
        return R.ok(user);
    }

    @Override
    public R<User> register(User user) {
        //加密密码
        String encodePassword = passwordEncoder.encode(user.getPassword());
        //设置加密后的密码
        user.setPassword(encodePassword);
        //注册新用户
        int insert = baseMapper.insert(user);
        if (!(insert > 0)) {
            throw new ApplicationException(ResultCode.ACCOUNT_EXISTS_ERROR);
        }
        return R.ok("注册成功",user);
    }

    @Override
    public R<User> updateUser(User user) {
        //加密密码
        String encodePassword = passwordEncoder.encode(user.getPassword());
        //设置加密后的密码
        user.setPassword(encodePassword);
        //更新用户信息
        int update = baseMapper.updateById(user);
        if (!(update > 0)) {
            throw new ApplicationException(ResultCode.SYSTEM_USERNAME_NOT_EXISTS);
        }
        return R.ok("修改成功",user);
    }

    @Override
    public R<String> deleteUserById(Integer id) {
        int deleteById = baseMapper.deleteById(id);
        if (!(deleteById > 0)) {
            throw new ApplicationException(ResultCode.SYSTEM_USERNAME_NOT_EXISTS);
        }
        return R.ok("删除成功");
    }
}
