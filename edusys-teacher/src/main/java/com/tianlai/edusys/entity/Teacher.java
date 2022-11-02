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
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

/**
 * @author Dale
 * @since 2022-11-01
 * @description 讲师
 */
@Data
// 链式语法
@Accessors(chain = true)
// 生成的Equals和HashCode方法是否包含父类属性
@EqualsAndHashCode(callSuper = true)
@TableName("edu_teacher")
@Schema(name = "Teacher 对象", description = "讲师 实体类")
public class Teacher extends baseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "讲师姓名")
    @TableField("name")
    @NotEmpty(message = "讲师姓名{userinfo.notEmpty}")
    private String name;

    @Schema(description = "讲师简介")
    @TableField("intro")
    private String intro;

    @Schema(description = "讲师资历,一句话说明讲师")
    @TableField("career")
    private String career;

    @Schema(description = "头衔 1高级讲师 2首席讲师")
    @TableField("level")
    @NotEmpty(message = "讲师等级{userinfo.notEmpty}")
    @Range(min = 0,max = 4,message = "讲师等级{userinfo.level}" )
    private Integer level;

    @Schema(description = "讲师头像")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    // 开启逻辑删除
    @TableLogic
    private Boolean deleted;


}
