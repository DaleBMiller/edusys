package com.tianlai.edusys.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageVo<T> {
    // 数据列表
    private List<T> dataList;
    // 当前页面
    private Integer currentPage;
    // 每页条数
    private Integer pageSize;
    // 总页数
    private Integer pages;
    // 总条数
    private Integer total;
}