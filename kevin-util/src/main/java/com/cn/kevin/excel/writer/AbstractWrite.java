package com.cn.kevin.excel.writer;

import com.cn.kevin.Constant;
import com.cn.kevin.excel.ExcelEnum;
import com.cn.kevin.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 写入 excle 抽象类
 * @author wj
 * @date 2015-12-16
 * @Description 写入 excle 抽象类
 */
public abstract class AbstractWrite {
    /**
     * 默认为 xlsx 格式
     */
    protected ExcelEnum excelEnum = ExcelEnum.XLSX;
    /**
     * sheetName
     */
    protected String sheetName;
    /**
     * Excel 标题
     */
    protected LinkedHashMap<String,String> header;
    /**
     * 写入的数据
     */
    protected List<Map<String,Object>> data;
    /**
     * 文件名称
     */
    protected String fileName;
    /**
     * 序号
     */
    protected boolean serialNumber;
    /**
     * 单元格宽度数组
     */
    protected int[] columnWidth;
    /**
     * workbook
     */
    protected Workbook workbook;
    /**
     *  抽象类定义整个流程骨架
     */
    public final Workbook write(){
        this.workbook = ExcelUtil.getWorkbook(this.excelEnum);
        Sheet sheet = workbook.createSheet();
        if(this.header != null && !this.header.isEmpty()){
            writeHeader(sheet);
            if(this.data != null && !this.data.isEmpty()){
                writeBody(sheet);
            }
        }
        return this.workbook;
    }
    /**
     * 写入标题
     */
    protected void writeHeader(Sheet sheet){
        Row titleRow = sheet.createRow(0);
        Cell[] cells = ExcelUtil.getCell(header.size(),this.excelEnum);

        int i = 0;
        for(String title : header.values()){
            if(serialNumber && i == 0){
                //序号5 可以写死
                sheet.setColumnWidth(i, 5 * ExcelUtil.WIDTH_UNIT);
                cells[i] = titleRow.createCell(i);
                cells[i].setCellValue(Constant.EMPTY);
                i++;
                continue;
            }
            if(columnWidth != null && i < columnWidth.length){
                sheet.setColumnWidth(i, columnWidth[i] * ExcelUtil.WIDTH_UNIT);
            }
            cells[i] = titleRow.createCell(i);
            cells[i].setCellValue(new XSSFRichTextString(title));
            cells[i].getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);
            i++;
        }
    }

    /**
     * 主体写入（必要时子类可以重写）
     */
    protected void writeBody(Sheet sheet){
        int row = 1;
        for (Map<String,Object> map : this.data){
            //创建行
            Row titleRow = sheet.createRow(row);
            //创建单元格
            Cell[] cells = ExcelUtil.getCell(header.size(),this.excelEnum);
            int i = 0;
            for(String title : header.keySet()){
                if(serialNumber && i == 0){
                    cells[i] = titleRow.createCell(i);
                    cells[i].setCellValue(row);
                }else{
                    cells[i] = titleRow.createCell(i);
                    if(map.get(title) instanceof Date){
                        cells[i].setCellStyle(ExcelUtil.getCellDateStyle(this.workbook,"yyyy-MM-dd HH:mm:ss"));
                        cells[i].setCellValue((Date) map.get(title));
                    }else {
                        cells[i].setCellValue(writeCell(title,map.get(title)));
                    }
                }
                i++;
            }
            row++;
        }
    }

    /**
     * 写入单元格（业务特殊时可以重写）
     * @param key
     * @param value
     * @return
     */
    protected RichTextString writeCell(String key,Object value){
        return new XSSFRichTextString(String.valueOf(value));
    }

    public ExcelEnum getExcelEnum() {
        return excelEnum;
    }

    public void setExcelEnum(ExcelEnum excelEnum) {
        this.excelEnum = excelEnum;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public LinkedHashMap<String, String> getHeader() {
        return header;
    }

    public void setHeader(LinkedHashMap<String, String> header) {
        this.header = header;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(boolean serialNumber) {
        this.serialNumber = serialNumber;
    }
}


