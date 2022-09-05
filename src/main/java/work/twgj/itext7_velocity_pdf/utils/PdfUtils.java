package work.twgj.itext7_velocity_pdf.utils;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.apache.commons.collections.CollectionUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Map;

/**
 * @author weijie.zhu
 * @date 2022/9/1
 */
public class PdfUtils {

    public static void createPdfFile(String templatePath, String templateFilePath, String outputFilePath,Map<String, Object> paramValue) throws Exception{
        //输入流
        Writer writer = VelocityUtils.getWriterByTemplate(templatePath,templateFilePath,paramValue);
        InputStream in = new ByteArrayInputStream(writer.toString().getBytes(Charset.defaultCharset()));
        //字节输出流
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(new FileOutputStream(outputFilePath)));
        //html -> pdf
        HtmlConverter.convertToPdf(in, pdfDocument);
    }

    public static String createPdf2Base64Str(String templatePath, String templateFilePath, Map<String,Object> paramValue) throws Exception{
        //输入流
        Writer writer = VelocityUtils.getWriterByTemplate(templatePath,templateFilePath,paramValue);
        InputStream in = new ByteArrayInputStream(writer.toString().getBytes(Charset.defaultCharset()));
        //字节输出流
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(swapStream));
        //html -> pdf
        HtmlConverter.convertToPdf(in, pdfDocument);
        //转化为base64字符串
        return Base64.getEncoder().encodeToString(swapStream.toByteArray());
    }
}
