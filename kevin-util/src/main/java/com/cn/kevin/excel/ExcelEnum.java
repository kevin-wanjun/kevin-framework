package com.cn.kevin.excel;

/**
 * excel 枚举
 * @author wj
 * @date 2015-12-16
 * @Description excel 枚举
 */
public enum  ExcelEnum {

    XLS("xls"),
    XLSX("xlsx");

    ExcelEnum(String format) {
        this.format = format;
    }

    /**
     * 文件格式
     */
    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
