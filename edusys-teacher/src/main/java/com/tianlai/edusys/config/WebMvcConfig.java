package com.tianlai.edusys.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.tianlai.edusys.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    // 从配置文件中获取日期格式化参数，如果没有就使用默认格式化字符串 ‘: yyyy-MM-dd HH:mm:ss’
    /**
     * 注意：因为要从配置文件拿取格式配置，所以不能为 static final 类型！
     */
    @Value("${spring.mvc.format.date-time:yyyy-MM-dd HH:mm:ss}")
    private String DEFAULT_DATETIME_PATTERN;
    @Value("${spring.mvc.format.date:yyyy-MM-dd}")
    private String DEFAULT_DATE_FORMAT;
    @Value("${spring.mvc.format.time:HH:mm:ss}")
    private String DEFAULT_TIME_FORMAT;
    private static final String TIME_ZONE = "GMT+8";


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器
        registry.addInterceptor(new LoginHandlerInterceptor())
                //设置拦截路径
                .addPathPatterns("/**")
                //设置排除资源路径
                .excludePathPatterns("/api/login", "/api/captcha")
                //为swagger设置排除
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v3/**", "/swagger-ui.html", "/swagger-ui/**", "/h5/**", "/mgr/**");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 这里不能直接创建新的MappingJackson2HttpMessageConverter对象添加，会影响swagger-ui使用
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper objectMapper = new ObjectMapper();
                // 指定时区
                objectMapper.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
                // 日期类型字符串处理
                objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATETIME_PATTERN));
                // 设置参数为null时不序列化
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                // Java8日期日期处理
                JavaTimeModule javaTimeModule = new JavaTimeModule();
                javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN)));
                javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
                javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
                javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN)));
                javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
                javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
                // 解决JavaScript无法表示Long类型的问题，因为Long超出了JavaScript中number类型的范围
                // javaTimeModule.addSerializer(Long.class, ToStringSerializer.instance);
                objectMapper.registerModule(javaTimeModule);
                ((MappingJackson2HttpMessageConverter) converter).setObjectMapper(objectMapper);
            }
        }
    }
}
