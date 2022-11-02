package com.tianlai.edusys.controller;
import com.tianlai.edusys.service.impl.RoleServiceImpl;
import com.tianlai.edusys.entity.Role;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianlai.edusys.common.R;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色 前端控制器
 * @author Dale
 * @since 2022-11-01
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Tag(name = "角色控制器类", description = "RoleController 角色 后端数据接口")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;


    /**
     * 分页查询
     * @param page 分页对象
     * @param role 角色
     * @return R
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public R getRolePage(Page page, Role role) {
        return R.ok();
    }


    /**
     * 通过id查询 角色
     * @param id id
     * @return Result
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return R.ok();
    }

    /**
     * 新增 角色
     * @param role 角色
     * @return Result
     */
    @Operation(summary = "新增角色", description = "新增角色")
    @PostMapping("")
    public R save(@RequestBody Role role) {
        return R.ok();
    }

    /**
     * 修改 角色
     * @param role 角色
     * @return Result
     */
    @Operation(summary = "修改角色", description = "修改角色")
    @PutMapping("")
    public R updateById(@RequestBody Role role) {
        return R.ok();
    }

    /**
     * 通过id删除 角色
     * @param id id
     * @return Result
     */
    @Operation(summary = "通过id删除角色",
               description = "通过id删除角色")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Integer id) {
        return R.ok();
    }

}
