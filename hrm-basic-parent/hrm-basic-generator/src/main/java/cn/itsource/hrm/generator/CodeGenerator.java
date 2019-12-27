package cn.itsource.hrm.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.*;

public class CodeGenerator {

  public static void main(String[] args) {

    ResourceBundle rb = ResourceBundle.getBundle("course-generator");
    AutoGenerator mpg = new AutoGenerator();

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    gc.setOutputDir(rb.getString("servicePath") + "/src/main/java");
    gc.setAuthor(rb.getString("author"));
    gc.setFileOverride(true);
    gc.setActiveRecord(true);//开启activeRecord模式
    gc.setEnableCache(false);/// xml二级缓存
    gc.setBaseResultMap(true);
    gc.setBaseColumnList(false);//xml 公共sql 所有的列
    gc.setOpen(false);
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl(rb.getString("jdbc.url"));
    dsc.setDriverName(rb.getString("jdbc.driver"));
    dsc.setUsername(rb.getString("jdbc.username"));
    dsc.setPassword(rb.getString("jdbc.password"));
    mpg.setDataSource(dsc);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setParent(rb.getString("parent"));
    pc.setController("web.controller");
    pc.setEntity("domain");
    mpg.setPackageInfo(pc);

    // 自定义配置
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        // ${cfg.parent} 获取到自定义的属性
        map.put("parent", rb.getString("parent"));
        this.setMap(map);
      }
    };



    // 自定义输出配置
    List<FileOutConfig> focList = new ArrayList<>();

    //自定义controller生成
    String controllerTemplatePath = "/templates/mycontroller.java.vm";
    focList.add(new FileOutConfig(controllerTemplatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return rb.getString("servicePath") + rb.getString("controller")
          + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
      }
    });

    //自定义query生成
    String queryTemplatePath = "/templates/query.java.vm";
    focList.add(new FileOutConfig(queryTemplatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return rb.getString("commonPath") + rb.getString("query")
          + tableInfo.getEntityName() + "Query" + StringPool.DOT_JAVA;
      }
    });

    //自定义domain生成
    String domainTemplatePath = "/templates/entity.java.vm";
    focList.add(new FileOutConfig(domainTemplatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return rb.getString("commonPath") + rb.getString("domain")
          + tableInfo.getEntityName() + StringPool.DOT_JAVA;
      }
    });

    //自定义Mapper生成
    String mapperTemplatePath = "/templates/mapper.xml.vm";
    focList.add(new FileOutConfig(mapperTemplatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return rb.getString("servicePath") + rb.getString("mapper")
          + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
      }
    });

    cfg.setFileOutConfigList(focList);
    mpg.setCfg(cfg);

    // 配置模板
    TemplateConfig templateConfig = new TemplateConfig();
    templateConfig.setController(null);
    templateConfig.setEntity(null);
    templateConfig.setXml(null);
    mpg.setTemplate(templateConfig);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    strategy.setEntityLombokModel(true);
    strategy.setInclude("t_course_type");//生成的表
    strategy.setTablePrefix("t_");
    mpg.setStrategy(strategy);
    mpg.setTemplateEngine(new VelocityTemplateEngine());
    mpg.execute();
  }
}
