package com.tianlai.edusys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianlai.edusys.entity.baseEntity;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
  import lombok.EqualsAndHashCode;

/**
 * @author Dale
 * @since 2022-11-01
 * @description 权限
 */
@Data
// 链式语法
@Accessors(chain = true)
// 生成的Equals和HashCode方法是否包含父类属性
@EqualsAndHashCode(callSuper = true)
@TableName("acl_permission")
@Schema(name = "Permission 对象", description = "权限 实体类")
public class Permission extends baseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "所属上级")
    @TableField("pid")
    private Long pid;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "类型(1:菜单,2:按钮)")
    @TableField("type")
    private Integer type;

    @Schema(description = "权限值")
    @TableField("permission_value")
    private String permissionValue;

    @Schema(description = "访问路径")
    @TableField("path")
    private String path;

    @Schema(description = "组件路径")
    @TableField("component")
    private String component;

    @Schema(description = "图标")
    @TableField("icon")
    private String icon;

    @Schema(description = "状态(0:禁止,1:正常)")
    @TableField("status")
    private Integer status;

    @Schema(description = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    // 开启逻辑删除
    @TableLogic
    private Boolean deleted;


}
