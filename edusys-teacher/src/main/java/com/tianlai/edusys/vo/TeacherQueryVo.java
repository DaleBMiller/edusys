package com.tianlai.edusys.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeacherQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name; // 讲师名称
    private Integer level; // 讲师等级
    private String joinDateBegin; // 加入的开始日期
    private String joinDateEnd; // 加入的结束日期
}
