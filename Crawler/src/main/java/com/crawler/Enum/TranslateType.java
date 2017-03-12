package com.crawler.Enum;
/**
 * 枚举工具类，用于选择翻译的种类
 * @author Administrator
 *仅能用于谷歌翻译
 */
public enum TranslateType {
    ChineseToEnglish("sl=zh-CN&tl=en"),EnglishToChinese("sl=en&tl=zh-CN");
    private String parameter;
    private TranslateType(String parameter) {
        this.parameter=parameter;
    }
    public String getParameter() {
        return parameter;
    }
    
}
