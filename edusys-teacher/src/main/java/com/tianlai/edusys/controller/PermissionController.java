package com.tianlai.edusys.controller;
import com.tianlai.edusys.service.impl.PermissionServiceImpl;
import com.tianlai.edusys.entity.Permission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianlai.edusys.common.R;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限 前端控制器
 * @author Dale
 * @since 2022-11-01
 */
@Slf4j
@RestController
@RequestMapping("/permission")
@Tag(name = "权限控制器类", description = "PermissionController 权限 后端数据接口")
public class PermissionController {
    @Autowired
    private PermissionServiceImpl permissionService;


    /**
     * 分页查询
     * @param page 分页对象
     * @param permission 权限
     * @return R
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public R getPermissionPage(Page page, Permission permission) {
        return R.ok();
    }


    /**
     * 通过id查询 权限
     * @param id id
     * @return Result
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return R.ok();
    }

    /**
     * 新增 权限
     * @param permission 权限
     * @return Result
     */
    @Operation(summary = "新增权限", description = "新增权限")
    @PostMapping("")
    public R save(@RequestBody Permission permission) {
        return R.ok();
    }

    /**
     * 修改 权限
     * @param permission 权限
     * @return Result
     */
    @Operation(summary = "修改权限", description = "修改权限")
    @PutMapping("")
    public R updateById(@RequestBody Permission permission) {
        return R.ok();
    }

    /**
     * 通过id删除 权限
     * @param id id
     * @return Result
     */
    @Operation(summary = "通过id删除权限",
               description = "通过id删除权限")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Integer id) {
        return R.ok();
    }

}
