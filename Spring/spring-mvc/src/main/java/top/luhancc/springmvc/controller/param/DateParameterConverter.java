package top.luhancc.springmvc.controller.param;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期类型的转换器
 * <p>
 * {@code Converter<S,T>}
 * S:source,源类型
 * T:target,目标类型
 * </p>
 * <p>
 * 注册方式:
 * <pre>{@code
 *     <!-- 自动注册最合适的处理器映射器、处理器适配器 -->
 *     <mvc:annotation-driven conversion-service="conversionServiceFactoryBean"/>
 *     <!--注册自定义类型转换器-->
 *     <bean id="conversionServiceFactoryBean"
 *           class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
 *         <property name="converters">
 *             <set>
 *                 <!--注册自定义日期类型转换器-->
 *                 <bean class="top.luhancc.springmvc.controller.param.DateParameterConverter"/>
 *             </set>
 *         </property>
 *     </bean>
 * }
 * </pre>
 *
 * @author luHan
 * @create 2021/5/11 15:46
 * @since 1.0.0
 */
public class DateParameterConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(source);
        } catch (ParseException e) {
            return null;
        }
    }
}
