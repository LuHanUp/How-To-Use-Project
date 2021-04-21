# SpringBoot整合dubbo的三种方式

## 方式一:基于dubbo的注解
1. 导入`dubbo-starter`依赖
2. 使用`@com.alibaba.dubbo.config.annotation.Service`来暴露服务
3. 使用`@com.alibaba.dubbo.config.annotation.Reference`来使用服务

## 方式二:保留dubbo.xml配置文件
1. 导入`dubbo-starter`依赖
2. 使用`@ImportResource`导入`dubbo.xml`配置文件即可

## 方式三:使用配置类的方式
其实就是按照springboot的配置类的方式创建xxxConfig配置的bean即可

# dubbo的高可用

## zookeeper宕机的情况
即使注册中心zookeeper宕机了,消费者仍能调用服务者,这是因为在第一次调用之后会将服务提供者的地址信息
进行本地缓存起来,这样就不需要经过zookeeper了,所以还是可以调用

## dubbo直连
直接写上服务提供者的url信息
~~~xml
    <dubbo:reference interface="top.luhancc.use.framework.dubbo.service.UserService"
                     id="userService"
                     url="服务提供者ip:服务提供者port" />
~~~