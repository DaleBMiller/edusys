package com.tianlai.edusys.mapper;

import com.tianlai.edusys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author Dale
 * @since 2022-11-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
