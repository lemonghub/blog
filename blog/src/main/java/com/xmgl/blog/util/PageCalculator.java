package com.xmgl.blog.util;


public final class PageCalculator {
    /**
     * 返回记录的开始即start
     * @param page
     * @param limit
     * @return
     */
    public static int calculateStart(int page, int limit){
        limit = limit <= 0 ? 10 : limit;
        return page > 0 ? (page - 1) * limit : 0;
    }

    public static int checkPage(int page) {
        return page <= 0 ? 1 : page;
    }

    public static int checkLimit(int limit) {
        return limit <= 0 ? 10 : limit;
    }

    public static int calculateStart(int page, int limit, int count) {
        limit = limit <= 0 ? 10 : limit;
        if(((page-1)*limit + limit)<count) {
            return (page - 1) * limit;
        }
        return page > 0 ? (page - 1) * limit : 0;
    }
}
