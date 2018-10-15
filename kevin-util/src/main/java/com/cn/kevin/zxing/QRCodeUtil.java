package com.cn.kevin.zxing;



import com.cn.kevin.zxing.extend.QRCodeWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import sun.font.FontDesignMetrics;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 简单二维码生成工具类
 * @author wj
 * @date 2018-07-31
 */
public class QRCodeUtil {

    /**用于设置图案的颜色*/
    private static final int BLACK = 0xFF000000;
    /**用于背景色*/
    private static final int WHITE = 0xFFFFFFFF;
    /**logo默认边框颜色*/
    public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
    /**logo默认边框宽度*/
    public static final int DEFAULT_LOGO_BORDER = 2;
    /**logo大小默认为二维码照片的1/6*/
    public static final int DEFAULT_LOGOPART = 6;

    /**
     * 二维码写入文件
     * @param config
     * @param file
     * @throws IOException
     */
    public static void writeToFile(Config config, File file)
            throws IOException, WriterException {
        BufferedImage image = toBufferedImage(config);
        if (!ImageIO.write(image, config.getFormat(), file)) {
            throw new IOException("Could not write an image of format " + config.getFormat() + " to " + file);
        }
    }

    /***
     * 二维码放入输出流
     * @param config
     * @param stream
     * @throws IOException
     */
    public static BufferedImage writeToStream(Config config, OutputStream stream) throws IOException, WriterException {
        BufferedImage image = toBufferedImage(config);
        if (!ImageIO.write(image, config.getFormat(), stream)) {
            throw new IOException("Could not write an image of format " + config.getFormat());
        }
        return image;
    }


    /**
     * 生成二维码
     * @param config 配置
     * @return
     */
    public static BufferedImage toBufferedImage(Config config) throws WriterException {
        //宽度
        int width = config.getWidth();
        //长度
        int height = config.getHeight();

        /*BitMatrix matrix = new MultiFormatWriter().encode(config.getDeviceQrcode(), BarcodeFormat.QR_CODE,
                width, height, config.getHints());*/
        BitMatrix matrix =new QRCodeWriter().encode(config.getDeviceQrcode(), BarcodeFormat.QR_CODE,width, height, config.getHints());

        //生成二维码
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y ) ? BLACK :0xFFFFF666);
            }
        }
        //不需要生成底部描述文字直接返回
        if(config.getDeviceInfo() == null || config.getDeviceInfo().trim().length() == 0){
            return image;
        }

        BufferedImage bufferedImage = toBufferedImage(image,config.getDeviceInfo(),config.getDeviceInfoFontSize());
        return bufferedImage;
    }

    /**
     * 加入底部文字
     * @param image
     * @param deviceInfo
     * @param fontSize
     * @return
     */
    public static BufferedImage toBufferedImage(BufferedImage image, String deviceInfo, int fontSize) {
        //字体
        Font font = new Font("黑体", Font.BOLD, fontSize);
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);

        //二维码宽度
        int width = image.getWidth();
        //二维码长度
        int height = image.getHeight();
        //二维码和文字描述之间的间隙
        int bottomBlankHeight = 20;
        //底部文字多出来的高度
        int outputImageHeight = bottomBlankHeight / 2 + fontSize;
        //整张图片的高度(二维码的高度 + 底部文字多出来的高度)
        int imgHeight = height + outputImageHeight;

        //建立整张图片
        BufferedImage outputImage = new BufferedImage(width,imgHeight,BufferedImage.TYPE_INT_RGB);
        Graphics g = outputImage.getGraphics();
        g.setColor(Color.red);
        g.fillRect(0,0, width, imgHeight);
        g.dispose();

        //添加二维码
        int[] imageNewArray = new int[width * height];
        imageNewArray = image.getRGB(0,0,width,height,imageNewArray,0,width);
        outputImage.setRGB(0,0,width,height,imageNewArray,0,width);

        //计算文字开始的位置
        // x
        int startX = (width - getWordWidth(font,deviceInfo)) >> 1;
        //y开始的位置
        int startY = height + metrics.getAscent();
        Graphics gText = outputImage.createGraphics();
        gText.setColor(Color.BLUE);
        gText.setFont(font);
        gText.drawString(deviceInfo, startX, startY);
        gText.dispose();

        return outputImage;
    }


    public static int getWordWidth(Font font, String content) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int width = 0;
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return width;
    }

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.setWidth(700);
        config.setHeight(700);
        config.setDeviceInfoFontSize(120);
        config.setMargin(0);
        config.setDeviceInfo("10号");
        config.setDeviceQrcode("http://sitpay.xsycloud.com.cn/api/order/jsPayRoute?appid=wx5111111111111111111df03ac48b490198&merchantCode=1001001008&storeId=d3fa1765-29ed-447d-bd59-7cd1bdbecf85&redirect_uri=http://10.32.4.99/wap/&code=tiger0724&channel=FOOD&tableCode=1/jsPayRoute?appid=wx5111111111111111111df03ac48b490198&merchantCode=1001001008&storeId=d3fa1765-29ed-447d-bd59-7cd1bdbecf85&redirect_uri=http://10.32.4.99/wap/&code=tiger0724&channel=FOOD&tableCode=1");

        // 生成二维码
        File outputFile = new File("d:" + File.separator + "二维码.jpg");
        writeToFile(config,outputFile);

    }


}
