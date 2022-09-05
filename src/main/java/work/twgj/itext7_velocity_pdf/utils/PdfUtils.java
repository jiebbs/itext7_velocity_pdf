package work.twgj.itext7_velocity_pdf.utils;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Map;

/**
 * @author weijie.zhu
 * @date 2022/9/1
 */
public class PdfUtils {

    private static Logger log = Logger.getLogger(PdfUtils.class);

    public static void createPdfFile(String templatePath, String templateFilePath, String outputFilePath,Map<String, Object> paramValue){
        //输入流
        Writer writer = null;
        InputStream in = null;
        //字节输出流
        PdfDocument pdfDocument = null;
        try {
            writer = VelocityUtils.getWriterByTemplate(templatePath,templateFilePath,paramValue);
            in = new ByteArrayInputStream(writer.toString().getBytes(Charset.defaultCharset()));
            pdfDocument = new PdfDocument(new PdfWriter(new FileOutputStream(outputFilePath)));
            //html -> pdf
            HtmlConverter.convertToPdf(in, pdfDocument);
        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
            try {
                if (pdfDocument != null){pdfDocument.close();}
                if (in != null){in.close();}
                if (writer != null){writer.close();}
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
    }

    public static String createPdf2Base64Str(String templatePath, String templateFilePath, Map<String,Object> paramValue){
        //输入流
        Writer writer = null;
        InputStream in = null;
        //字节输出流
        ByteArrayOutputStream swapStream = null;
        PdfDocument pdfDocument = null;
        try {
            writer = VelocityUtils.getWriterByTemplate(templatePath,templateFilePath,paramValue);
            in = new ByteArrayInputStream(writer.toString().getBytes(Charset.defaultCharset()));
            swapStream = new ByteArrayOutputStream();
            pdfDocument = new PdfDocument(new PdfWriter(swapStream));
            //html -> pdf
            HtmlConverter.convertToPdf(in, pdfDocument);
            //转化为base64字符串
            return Base64.getEncoder().encodeToString(swapStream.toByteArray());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }finally {
            try{
                if (pdfDocument != null){pdfDocument.close();}
                if (swapStream != null){swapStream.close();}
                if (in != null){in.close();}
                if (writer != null){writer.close();}
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
    }
}
