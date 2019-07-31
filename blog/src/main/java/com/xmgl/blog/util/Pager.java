package com.xmgl.blog.util;

import java.util.List;

public class Pager<T> {

    private int totalPage; // 总页数
    private int totalCount; // 总记录数
    private int currentPage; // 当前页
    private int pageSize; // 每页的数量
    private int firstPage; // 首页
    private int prePage; // 上一页
    private int nextPage; // 下一页

    private int lastPage; // 最后一页
    private boolean isFirst; // 是否是第一页
    private boolean isLast; // 是否是最后一页

    //数据
    private List<T> datas;

    /**
     *
     */
    public Pager(int currentPage, int pageSize, int totalCount, List<T> datas) {

        if (currentPage > 0) {
            this.currentPage = currentPage;
        } else {
            this.currentPage = 1;
        }
        if (pageSize > 0) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = 10;
        }

        if (totalCount > 0) {
            this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
            this.totalCount = totalCount;
        } else {
            this.totalPage = 1;
            this.totalCount = 0;
        }

        this.currentPage = this.currentPage > this.totalPage?1:this.currentPage;

        this.nextPage = (this.currentPage + 1 > this.totalPage) ? this.totalPage
                : this.currentPage + 1;
        this.prePage = (this.currentPage - 1 < 1) ? 1 : this.currentPage - 1;

        this.firstPage = 1;
        this.lastPage = this.totalPage;

        this.isFirst = this.currentPage == 1 ? true : false;
        this.isLast = this.currentPage == this.totalPage ? true : false;
        this.datas = datas;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
