package com.example.thuctaptotnghiep.donghohanquoc.Model.Output;

import java.io.Serializable;

public class Pagination implements Serializable {
    private static final long serialVersionUID = 1L;
    private int page;
    private int pageSize;
    private long totalItem;
    @SuppressWarnings("unused")
    private long totalPage;
    public long getTotalPage() {
        return totalItem % pageSize == 0 ? totalItem / pageSize : (totalItem / pageSize + 1);
    }
}
