package work.twgj.itext7_velocity_pdf.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * @author weijie.zhu
 * @date 2022/9/1
 */
public class VelocityUtils {

    /**
     *  得到模板引擎渲染数据后的写入器
     * @param templatePath 模板所在位置
     * @param paramMap 模板数据k-y
     * @return
     */
    public static Writer getWriterByTemplate(String templatePath,String templateFilePath, Map paramMap) {
        VelocityEngine velocityEngine = new VelocityEngine();
        // 这是模板所在路径
        velocityEngine.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, templatePath);
        velocityEngine.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        //指定编码格式，避免生成模板就造成乱码，影响到转pdf后的文件
        velocityEngine.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        velocityEngine.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        velocityEngine.init();

        Writer writer = new StringWriter();
        Template template = velocityEngine.getTemplate(templateFilePath, "utf-8");
        VelocityContext context = new VelocityContext(paramMap);
        template.merge(context, writer);
        return writer;
    }
}
