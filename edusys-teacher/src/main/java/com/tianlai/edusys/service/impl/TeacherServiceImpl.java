package com.tianlai.edusys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianlai.edusys.entity.Teacher;
import com.tianlai.edusys.mapper.TeacherMapper;
import com.tianlai.edusys.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianlai.edusys.vo.PageVo;
import com.tianlai.edusys.vo.TeacherQueryVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Dale
 * @since 2022-11-01
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public PageVo<Teacher> selecetPage(TeacherQueryVo teacherQueryVo, Integer page, Integer limit) {
        //创建分页对象
        IPage<Teacher> teacherPage = new Page<>(page, limit);
        //创建条件对象
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();

        //具体业务
        //查询条件

        //查询的教师姓名
        String teacherName = teacherQueryVo.getName();
        //查询的教师等级
        Integer level = teacherQueryVo.getLevel();
        //教师加入时间（开始）
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        //教师加入时间（截止）
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();

        //条件判断
        //姓名判断
        if (!StringUtils.isEmpty(teacherName)) {
            teacherQueryWrapper.likeRight("name", teacherName);
        }
        //等级判断
        if (!ObjectUtils.isEmpty(level) && level > 0 && level < 5) {
            teacherQueryWrapper.eq("level", level);
        }
        //时间判断
        if (!StringUtils.isEmpty(joinDateBegin) && !StringUtils.isEmpty(joinDateEnd)) {
            teacherQueryWrapper.between("gmt_creat",joinDateBegin,joinDateEnd);
        }

        //查询
        IPage<Teacher> teacherPageData = baseMapper.selectPage(teacherPage, teacherQueryWrapper);

        //获取返回对象/返回数据
        return new PageVo<Teacher>()
                //设置返回数据列表
                .setDataList(teacherPageData.getRecords())
                //设置当前页
                .setCurrentPage(page)
                //设置总页数
                .setPages((int) teacherPageData.getPages())
                //设置总条数
                .setTotal((int) teacherPageData.getTotal());
    }
}
