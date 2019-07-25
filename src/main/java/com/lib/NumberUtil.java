package com.lib;

import org.omg.CORBA.SystemException;

import java.util.HashMap;
import java.util.Map;

public class NumberUtil {
    public static final Map<String, String> NUMBER_MAP = new HashMap<>();

    static {
        NUMBER_MAP.put("0", "零");
        NUMBER_MAP.put("1", "一");
        NUMBER_MAP.put("2", "二");
        NUMBER_MAP.put("3", "三");
        NUMBER_MAP.put("4", "四");
        NUMBER_MAP.put("5", "五");
        NUMBER_MAP.put("6", "六");
        NUMBER_MAP.put("7", "七");
        NUMBER_MAP.put("8", "八");
        NUMBER_MAP.put("9", "九");
        NUMBER_MAP.put("10", "十");
    }

    public static final Map<String, String> BIT_WORK = new HashMap<>();

    static {
        BIT_WORK.put("1", "个");
        BIT_WORK.put("2", "十");
        BIT_WORK.put("3", "百");
        BIT_WORK.put("4", "千");
        BIT_WORK.put("5", "万");
        BIT_WORK.put("6", "百");
        BIT_WORK.put("7", "千");
        BIT_WORK.put("8", "亿");
    }

    /**
     * 先定个小目标，比如说，先转换它一个亿
     *
     * @param number
     * @return
     */
    public static String numberConverter(int number) {
        String src = number+"";
        return "";
    }

    /**
     * 该方法只能支持十万以下的数字做转化。
     * 要考虑做分割，还是原有基础上修修改改
     * @param number
     * @return
     */
    public static String lowLevelConverter(String number) {
        //拆分数字的每一位
        char[] bit = number.toCharArray();
        StringBuilder sb = new StringBuilder();
        int size = bit.length;
        for (int index = 0; index < bit.length; index++) {
            String bitValue = bit[index] + "";
            //如果最低位是0，且参数不是个数，则不要转成中文，如310->不要变成了三百一十零
            if (isSkin(index, bitValue, size)) {
                continue;
            }
            if (isWriteLevelNum(index, bitValue, size)) {
                sb.append(NUMBER_MAP.get(bitValue));
            }
            if (!isWriteLevel(index, bitValue, size)) {
                continue;
            }
            sb.append(BIT_WORK.get((bit.length - index) + ""));
        }
        return afterProcess(sb);
    }

    /**
     * 是否跳过数字转中文
     *
     * @param level
     * @param num
     * @param totalLevel
     * @return
     */
    public static boolean isSkin(int level, String num, int totalLevel) {
        //如果最低位是0，且参数不是个数，则不要转成中文，如310->不要变成了三百一十零
        if (level == totalLevel - 1 && "0".equals(num) && totalLevel > 1) {
            return true;
        }
        return false;
    }

    /**
     * 是否写级数上面的数字，比如三百的三
     *
     * @param level
     * @param num
     * @param totalLevel
     * @return
     */
    public static boolean isWriteLevelNum(int level, String num, int totalLevel) {
        //如果是10-19位数，则不要给
        if (level == totalLevel - 2 && num.equals("1") && totalLevel == 2) {
            return false;
        }
        return true;
    }

    /**
     * 是否写级数，比如三百的百
     *
     * @param level
     * @param num
     * @param totalLevel
     * @return
     */
    public static boolean isWriteLevel(int level, String num, int totalLevel) {
        //如果位数上是0，则不要显示位数，如301-->不要是三百零十一
        if (num.equals("0")) {
            return false;
        }
        //低位不要显示位数，虽然把个置为空串也行吧，个是不显示的
        if (level == totalLevel - 1) {
            return false;
        }
        return true;
    }

    /**
     * 将字符串首尾，符合条件的字符去掉
     * @param src
     * @param c
     * @return
     */
    public static String trim(String src, char c) {
        char[] value = src.toCharArray();
        int len = value.length;
        int st = 0;
        char[] val = value;    /* avoid getfield opcode */

        while ((st < len) && (val[st] == c)) {
            st++;
        }
        while ((st < len) && (val[len - 1] == c)) {
            len--;
        }
        return ((st > 0) || (len < value.length)) ? src.substring(st, len) : src;
    }

    public static String afterProcess(StringBuilder sb) {

        //只保留一个零
        holeOnlyWord(sb, '零');
        String target = "";
        char last = sb.charAt(sb.length() - 1);
        if (last == '零' && sb.length() > 1) {
            target = trim(sb.toString(), '零');
        } else {
            target = sb.toString();
        }
        return target;
    }

    /**
     * 将字符串里面重复的字段，缩减到只保留1位
     * @param sb
     * @param world
     */
    private static void holeOnlyWord(StringBuilder sb, char world) {
        int findCount = 0;
        for (int i = 0; i < sb.length(); i++) {
            char element = sb.charAt(i);
            if (element == world) {
                if (findCount >= 1) {
                    sb.deleteCharAt(i);
                    i--;
                } else {
                    findCount++;
                }
            }
        }
        return;
    }
}

