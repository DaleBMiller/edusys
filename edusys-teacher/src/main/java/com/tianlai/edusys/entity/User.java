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
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Dale
 * @since 2022-11-01
 * @description 用户
 */
@Data
// 链式语法
@Accessors(chain = true)
// 生成的Equals和HashCode方法是否包含父类属性
@EqualsAndHashCode(callSuper = true)
@TableName("acl_user")
@Schema(name = "User 对象", description = "用户 实体类")
public class User extends baseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "账户")
    @TableField("username")
    @NotEmpty(message = "用户名{userinfo.notEmpty}")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    @NotEmpty(message = "密码{userinfo.notEmpty}")
    @Size(min = 6,max = 12,message = "{userinfo.password.size}")
    private String password;

    @Schema(description = "昵称")
    @TableField("nick_name")
    private String nickName;

    @Schema(description = "用户头像")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "手机号码")
    @TableField("phone")
    @Length(min = 13,max = 13,message = "{userinfo.phone.length}")
    private String phone;

    @Schema(description = "用户签名")
    @TableField("token")
    private String token;

    @Schema(description = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    // 开启逻辑删除
    @TableLogic
    private Boolean deleted;


}
