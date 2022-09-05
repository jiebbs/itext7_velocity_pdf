package work.twgj.itext7_velocity_pdf;

import org.junit.Test;
import work.twgj.itext7_velocity_pdf.utils.PdfUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weijie.zhu
 * @date 2022/9/1
 */
public class Itext7pdfTest {

    @Test
    public void test(){
        Map<String,Object> paramValue = new HashMap<>();

        List<String> equations = new ArrayList<>();
        for(int i = 0;i<10;i++){
            equations.add("24 * 12 =");
        }
        paramValue.put("equations",equations);
        PdfUtils.createPdfFile("src/main/resources/template","hello.vm","E:\\demo\\itext7_velocity_pdf\\hello.pdf",paramValue);
    }
}
