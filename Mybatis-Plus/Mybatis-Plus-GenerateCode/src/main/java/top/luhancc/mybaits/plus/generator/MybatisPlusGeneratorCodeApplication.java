package top.luhancc.mybaits.plus.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;

/**
 * @author luHan
 * @create 2021/4/9 15:06
 * @since 1.0.0
 */
public class MybatisPlusGeneratorCodeApplication {
    private GlobalConfig globalConfig = new GlobalConfig();
    private DataSourceConfig dataSourceConfig = new DataSourceConfig();
    private StrategyConfig strategyConfig = new StrategyConfig();
    private PackageConfig packageConfig = new PackageConfig();
    private AutoGenerator autoGenerator = new AutoGenerator();
    private Scanner scanner = new Scanner(System.in);

    public void config() {
        System.out.println("=============================开始配置全局设置======================================");

        System.out.println("是否支持AR模式?y/n");
        String isActiveRecord = scanner.next();
        if ("y".equals(isActiveRecord)) {
            globalConfig.setActiveRecord(true);
        }
        globalConfig.setAuthor("luHan");
        globalConfig.setOutputDir("/Users/luhan/学习/java/How-To-Use-Project/Mybatis-Plus/Mybatis-Plus-GenerateCode/src/main/java");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);
        System.out.println("是否开启 swagger2 模式?y/n");
        String isSwagger2 = scanner.next();
        if ("y".equals(isSwagger2)) {
            globalConfig.setSwagger2(true);
        }
        System.out.println("实体类的后缀名称");
        String entitySuffix = scanner.next();
        globalConfig.setEntityName("%s" + entitySuffix);
        globalConfig.setServiceName("%s");
        globalConfig.setServiceImplName("%sImpl");
        globalConfig.setMapperName("%sMapper");
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setControllerName("%sController");
    }

    public void dataSource() {
        System.out.println("=============================开始配置数据源======================================");
        dataSourceConfig.setDbType(DbType.MYSQL);
        String className = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            className = "com.mysql.jdbc.Driver";
        }
        dataSourceConfig.setDriverName(className);
        System.out.println("请输入数据库的IP");
        String ip = scanner.next();
        System.out.println("请输入数据库的端口");
        String port = scanner.next();
        System.out.println("请输入用户名");
        String userName = scanner.next();
        System.out.println("请输入密码");
        String password = scanner.next();
        System.out.println("请输入数据库名称");
        String datasourceName = scanner.next();
        dataSourceConfig.setUsername(userName);
        dataSourceConfig.setPassword(password);
        dataSourceConfig.setUrl("jdbc:mysql://" + ip + ":" + port + "/" + datasourceName);
    }

    public void strategyConfig() {
        System.out.println("=============================开始配置策略======================================");
        strategyConfig.setCapitalMode(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setEntityTableFieldAnnotationEnable(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setEntityLombokModel(true);
        System.out.println("输入要生成的表名称：多个使用,分割");
        String tableNames = scanner.next();
        strategyConfig.setInclude(tableNames.split(","));
    }

    public void packageConfig() {
        System.out.println("=============================开始配置包名策略======================================");
        System.out.println("输入包名前缀");
        String packageParentName = scanner.next();
        packageConfig.setParent(packageParentName);
        packageConfig.setController("controller");
        packageConfig.setEntity("domain.entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setXml("mapper");
    }

    public void mergeConfig() {
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setPackageInfo(packageConfig);
    }

    public void start() {
        config();
        dataSource();
        strategyConfig();
        packageConfig();
        mergeConfig();
        System.out.println("=============================开始执行代码生成======================================");
        autoGenerator.execute();
        System.out.println("=============================代码生成完成======================================");
    }

    public static void main(String[] args) {
        MybatisPlusGeneratorCodeApplication mybatisPlusGeneratorCodeApplication = new MybatisPlusGeneratorCodeApplication();
        mybatisPlusGeneratorCodeApplication.start();
    }
}
