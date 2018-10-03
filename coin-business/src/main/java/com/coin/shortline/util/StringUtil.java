package com.coin.shortline.util;

public class StringUtil {

    public static int getInt(String i){
        try {
            return Integer.parseInt(i);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * @param str
     *            is null or ""
     * @return true is not null
     */
    public static final boolean isNotEmpty(String str) {
        return !(str == null || str.equals(""));
    }

    /**
     *
     * @param str
     *            is null or ""
     * @return true is not null
     */
    public static final boolean isEmpty(String str) {
        return (str == null || str.equals(""));
    }

}
