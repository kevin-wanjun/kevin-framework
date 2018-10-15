package com.cn.kevin.zxing.extend;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Map;

/**
 * 重写 zxing 包的QRCodeWriter 类，由于 zxing 包中的QRCodeWriter是用 final 修饰的，所以这里只能重写全部的方法。
 * @author wj
 * @date 2018-08-25
 */
public final class QRCodeWriter implements Writer {

    private static final int QUIET_ZONE_SIZE = 4;

    public QRCodeWriter() {
    }

    @Override
    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height) throws WriterException {
        return this.encode(contents, format, width, height, (Map)null);
    }

    @Override
    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (contents.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (format != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + format);
        } else if (width >= 0 && height >= 0) {
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            // 默认二维码边框宽度
            int quietZone = 4;

            if (hints != null) {
                //容错级别
                ErrorCorrectionLevel requestedECLevel = (ErrorCorrectionLevel)hints.get(EncodeHintType.ERROR_CORRECTION);
                if (requestedECLevel != null) {
                    errorCorrectionLevel = requestedECLevel;
                }
                //获取设置的边框宽度
                Integer quietZoneInt = (Integer)hints.get(EncodeHintType.MARGIN);
                if (quietZoneInt != null) {
                    quietZone = quietZoneInt.intValue();
                }
            }
            // 二维码生成
            QRCode code = Encoder.encode(contents, errorCorrectionLevel, hints);
            // 输出渲染
            return renderResult(code, width, height, quietZone);
        } else {
            throw new IllegalArgumentException("Requested dimensions are too small: " + width + 'x' + height);
        }
    }

    public static void main(String[] args) {
        System.out.println(1 >> 1);
    }

    /**
     * 对 zxing 的 QRCodeWriter 进行扩展, 解决白边不固定的问题
     * 具体参考 {@link com.google.zxing.qrcode.QRCodeWriter#renderResult(QRCode, int, int, int)}
     * @param code
     * @param width
     * @param height
     * @param quietZone 二维码边框宽度
     * @return
     */
    private static BitMatrix renderResult(QRCode code, int width, int height, int quietZone) {
        ByteMatrix input = code.getMatrix();
        if (input == null) {
            throw new IllegalStateException();
        }
        //二维码高度宽度
        int inputWidth = input.getWidth();
        int inputHeight = input.getHeight();

        int qrWidth = inputWidth + (quietZone << 1);
        int qrHeight = inputHeight + (quietZone << 1);

        int outputWidth = Math.max(width, qrWidth);
        int outputHeight = Math.max(height, qrHeight);

        int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);

        int leftPadding = (outputWidth - inputWidth * multiple) / 2;
        int topPadding = (outputHeight - inputHeight * multiple) / 2;

        BitMatrix output = new BitMatrix(outputWidth, outputHeight);
        int inputY = 0;

        for(int outputY = topPadding; inputY < inputHeight; outputY += multiple) {
            int inputX = 0;

            for(int outputX = leftPadding; inputX < inputWidth; outputX += multiple) {
                if (input.get(inputX, inputY) == 1) {
                    output.setRegion(outputX, outputY, multiple, multiple);
                }

                ++inputX;
            }

            ++inputY;
        }

        return output;
    }
}
