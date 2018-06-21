package com.cn.kevin.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel 工具类
 * @author wj
 * @date 2015-12-16
 * @Description excel 工具类
 */
public abstract class ExcelUtil {

    /**
     * 单元格基础宽度
     */
    public static final int WIDTH_UNIT = 256;
    /**
     * 创建何种格式 Workbook
     * @param excelEnum
     * @return
     */
    public static Workbook getWorkbook(ExcelEnum excelEnum) {
        switch (excelEnum) {
            case XLS:
                return new HSSFWorkbook();
            case XLSX:
                return new XSSFWorkbook();
            default:
                return new XSSFWorkbook();
        }
    }

    /**
     * 得到 cell 数组
     * @param length
     * @param excelEnum
     * @return
     */
    public static Cell[] getCell(int length, ExcelEnum excelEnum){
        switch (excelEnum) {
            case XLS:
                return new HSSFCell[length];
            case XLSX:
                return new XSSFCell[length];
            default:
                return new XSSFCell[length];
        }
    }


    /**
     * 得到红色色字体样式
     */
    public static CellStyle getRedCellStyle(Workbook book){
      return getCellStyle(book,HSSFColor.RED.index);
    }

    /**
     * 获取指定颜色的字体样式
     * @param book
     * @param index
     */
    public static CellStyle getCellStyle(Workbook book,short index){
        CellStyle cellStyle = book.createCellStyle();
        Font font = book.createFont();
        font.setColor(index);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 获取日期格式
     * @param book
     * @param format
     */
    public static CellStyle getCellDateStyle(Workbook book,String format){
        CellStyle cellStyle = book.createCellStyle();
        CreationHelper creationHelper = book.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(format));
        return cellStyle;
    }

    /**
     * 设置单元格水平对齐的样式
     * @param style
     * @param setting
     * @return
     */
    public static CellStyle setCellStyleAlignment(CellStyle style, short setting){
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(setting);
        return style;
    }


    /**
     * 设置垂直对齐的样式为居中对齐的样式
     * @param style
     * @param setting
     * @return
     */
    public static CellStyle setCellDateStyleVerticalAlignment(CellStyle style, short setting){
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(setting);
        return style;
    }

    /**
     * 获取水平垂直居中样式
     * @param book
     * @return
     */
    public static CellStyle getAllAlignmentStyle(Workbook book){
        CellStyle style = book.createCellStyle();
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(CellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        return style;
    }


}
