package com.tianlai.edusys.controller;
import com.tianlai.edusys.exception.NoDataException;
import com.tianlai.edusys.service.impl.TeacherServiceImpl;
import com.tianlai.edusys.entity.Teacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianlai.edusys.common.R;
import com.tianlai.edusys.vo.PageVo;
import com.tianlai.edusys.vo.TeacherQueryVo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.PatternProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 讲师 前端控制器
 * @author Dale
 * @since 2022-11-01
 */
@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "讲师控制器类", description = "TeacherController 讲师 后端数据接口")
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherService;


    /**
     * 分页查询
     * @param teacherQueryVo 分页对象
     * @param page 起始页
     * @param limit 每页条数
     * @return R
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/teachers")
    public R<PageVo<Teacher>> getTeacherPage(TeacherQueryVo teacherQueryVo, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        PageVo<Teacher> teacherPageVo = teacherService.selecetPage(teacherQueryVo,page,limit);
        return R.ok(teacherPageVo);
    }


    /**
     * 通过id查询 讲师
     * @param id id
     * @return Result
     */                                                                 
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/teacher/{id}")
    public R<Teacher> getById(@PathVariable("id") Long id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher == null) {
            throw new NoDataException("未查询到此老师的数据");
        }
        return R.ok(teacher);
    }

    /**
     * 新增 讲师
     * @param teacher 讲师
     * @return Result
     */
    @Operation(summary = "新增讲师", description = "新增讲师")
    @PostMapping("/teacher")
    public R<String> save(@RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (!save) {
            throw new NoDataException("添加失败");
        }
        return R.ok("添加成功");
    }

    /**
     * 修改 讲师
     * @param teacher 讲师
     * @return Result
     */
    @Operation(summary = "修改讲师", description = "修改讲师")
    @PutMapping("/teacher")
    public R<String> updateById(@RequestBody Teacher teacher) {
        boolean update = teacherService.updateById(teacher);
        if (!update) {
            throw new NoDataException("修改失败");
        }
        return R.ok("修改成功");
    }

    /**
     * 通过id删除 讲师
     * @param ids ids
     * @return Result
     */
    @Operation(summary = "通过id删除讲师",
               description = "通过id删除讲师")
    @DeleteMapping("/teacher/{ids}")
    public R<String > removeById(@PathVariable List<Long> ids) {
        boolean remove = teacherService.removeBatchByIds(ids);
        if (!remove) {
            throw new NoDataException("删除失败");
        }
        return R.ok("删除成功");
    }

}
