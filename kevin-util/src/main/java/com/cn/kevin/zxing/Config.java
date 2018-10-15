package com.cn.kevin.zxing;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生成配置类
 * @author wj
 * @date 2018-07-31
 */
public class Config {
    /**生成二维码*/
    private String deviceQrcode;
    /**二维码宽度*/
    private int width;
    /**二维码高度*/
    private int height;
    /**底部描述文字*/
    private String deviceInfo;
    /**描述文字大小*/
    private int deviceInfoFontSize;
    /***二维码边框大小*/
    private int margin;
    /**二维码的图片格式*/
    private String format;
    /**二维码图片logo地址*/
    private String logoUrl;
    /**设置二维码的参数*/
    private Map<EncodeHintType,Object> hints;
    /**左右边框的宽度*/
    private Integer leftPadding;
    /**上下边框的宽度*/
    private Integer topPadding;

    /**
     * 默认初始参数
     */
    public Config(){
        this.width = 300;
        this.height = 300;
        this.deviceInfoFontSize = 16;
        this.margin = 0;
        this.format = "jpg";
        this.hints = new HashMap<>(16);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //容错级别 H->30%
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置二维码边的空度，非负数
        hints.put(EncodeHintType.MARGIN,this.margin);
    }

    /**
     * 获取margin的宽度,这里主要
     * @return
     */
    public Config getMarginWidth() throws WriterException {
        QRCode code = Encoder.encode(this.getDeviceQrcode(),
                (ErrorCorrectionLevel)hints.get(EncodeHintType.ERROR_CORRECTION), hints);
        //code.set

        return this;
    }

    public String getDeviceQrcode() {
        return deviceQrcode;
    }

    public void setDeviceQrcode(String deviceQrcode) {
        this.deviceQrcode = deviceQrcode;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public int getDeviceInfoFontSize() {
        return deviceInfoFontSize;
    }

    public void setDeviceInfoFontSize(int deviceInfoFontSize) {
        this.deviceInfoFontSize = deviceInfoFontSize;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
        //设置二维码边的空度，非负数
        hints.put(EncodeHintType.MARGIN,this.margin);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Map<EncodeHintType, Object> getHints() {
        return hints;
    }
}
