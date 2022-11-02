package com.tianlai.edusys.service;

import com.tianlai.edusys.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tianlai.edusys.vo.PageVo;
import com.tianlai.edusys.vo.TeacherQueryVo;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Dale
 * @since 2022-11-01
 */
public interface TeacherService extends IService<Teacher> {

    PageVo<Teacher> selecetPage(TeacherQueryVo teacherQueryVo, Integer page, Integer limit);
}
