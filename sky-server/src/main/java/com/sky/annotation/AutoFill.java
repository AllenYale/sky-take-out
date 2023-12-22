package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: AutoFill
 * PackageName: com.sky.annotation
 * Description: 自定义注解，用于标识某个方法是新增还是修改
 * 需要进行功能字段自动填充处理，eg：creatTime、updateTime、createUser、updateUser
 *
 * @Author: Hanyu
 * @Date: 23/12/22 - 12:42
 * @Version: v1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    OperationType value();
}
