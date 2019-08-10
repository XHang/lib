package com.lib;

import com.lib.reg.RegUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Htmlutil {

    public static List<String> GetPicUrl(String content) {
        String[] matchs = RegUtil.getMatchText(content, "(http|https)://([a-z0-9A-Z]+\\.)+([a-z0-9A-Z]+/)+.+?.jpg");
        return new ArrayList<>(Arrays.asList(matchs));
    }
}
