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
 * @description 角色
 */
@Data
// 链式语法
@Accessors(chain = true)
// 生成的Equals和HashCode方法是否包含父类属性
@EqualsAndHashCode(callSuper = true)
@TableName("acl_role")
@Schema(name = "Role 对象", description = "角色 实体类")
public class Role extends baseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色名称")
    @TableField("role_name")
    private String roleName;

    @Schema(description = "角色编码")
    @TableField("role_code")
    private String roleCode;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    // 开启逻辑删除
    @TableLogic
    private Boolean deleted;


}
