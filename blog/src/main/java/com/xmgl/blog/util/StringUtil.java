package com.xmgl.blog.util;


public final class StringUtil {

    public static boolean isEmpty(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String[] toSearchStrings(String s) {
        s = s.replaceAll("[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "");
        return s.split(" ");
    }
}
