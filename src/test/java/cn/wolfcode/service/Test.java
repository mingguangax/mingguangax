package cn.wolfcode.service;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {
        // 创建 Freemarker 配置对象
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        // 指定模板文件从何处加载的数据源，这里设置文件目录位置。
        cfg.setDirectoryForTemplateLoading(new File("templates"));
        // 设置默认编码类型
        cfg.setDefaultEncoding("utf-8");
        // 提供数据
        Map<String, Object> data = new HashMap<>();
        data.put("user", "admin");

        List<String> list = new ArrayList<>();
        list.add("武当");
        list.add("云梦");
        list.add("暗香");
        list.add("云梦");
        list.add("少林");
        Boolean boo = true;
        data.put("boo",boo);
        data.put("list",list);
        // 获取模板文件
        Template temp = cfg.getTemplate("test.ftl");
        // 设置输出为新的文件
        Writer out = new OutputStreamWriter(new FileOutputStream("test.html"));
        // 执行输出
        temp.process(data, out);
        out.flush();
        out.close();
    }
}
