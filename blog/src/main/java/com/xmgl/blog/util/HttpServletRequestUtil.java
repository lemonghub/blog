package com.xmgl.blog.util;

import javax.servlet.http.HttpServletRequest;


public final class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request, String key){
        try{
            return Integer.parseInt(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static Long getLong(HttpServletRequest request, String key){
        try{
            return Long.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1L;
        }
    }

    public static Double getDouble(HttpServletRequest request, String key){
        try{
            return Double.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1d;
        }
    }

    public static Boolean getBoolean(HttpServletRequest request, String key){
        try{
            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key){
        try{
            String val = request.getParameter(key);
            if(val != null){
                val = val.trim();
            }
            if("".equals(val)){
                val = null;
            }
            return val;
        }catch (Exception e){
            return null;
        }
    }

}
