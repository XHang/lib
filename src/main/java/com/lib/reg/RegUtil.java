package com.lib.reg;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
    public static String[] getMatchText(String src,String reg){
        Pattern pattern= Pattern.compile(reg);
        Matcher matcher = pattern.matcher(src);
        List<String> matchers = new ArrayList<>();
        while (matcher.find()){
            matchers.add(matcher.group());
        }
        String[] arr = new String[matchers.size()];
        return matchers.toArray(arr);
    }
    public static String getFirstMatchText(String src,String reg){
        String[] matchers = getMatchText(src,reg);
        if (matchers == null || matchers.length == 0){
            return "";
        }else{
            return matchers[0];
        }
    }
}
