package com.tianlai.edusys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
@Data
@Accessors(chain = true)
public class baseEntity {
    @Schema(description = "编号")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @Schema(description = "更新时间")
    @TableField(fill =  FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
