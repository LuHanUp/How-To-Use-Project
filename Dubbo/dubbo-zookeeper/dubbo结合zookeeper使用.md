# SpringBoot整合dubbo的三种方式

## 方式一:基于dubbo的注解
1. 导入dubbo-starter依赖
2. 使用@Service来暴露服务
3. 使用@Reference来使用服务

## 方式二:保留dubbo.xml配置文件
1. 导入dubbo-starter依赖
2. 使用@ImportResource导入dubbo.xml配置文件即可

## 方式三:使用配置类的方式
其实就是按照springboot的配置类的方式创建xxxConfig配置的bean即可