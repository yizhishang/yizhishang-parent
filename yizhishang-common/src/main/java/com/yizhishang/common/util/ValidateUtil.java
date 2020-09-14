package com.yizhishang.common.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yizhishang
 */
public class ValidateUtil<T> {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = validatorFactory.getValidator();

    /**
     * 验证请求参数(单个错误)
     *
     * @return null 则说明验证成功，如果非null 则说明验证失败
     */
    public String validateOne(T model) {
        Set<ConstraintViolation<T>> violations = validator.validate(model);
        if (null == violations || violations.isEmpty()) {
            return null;
        }
        for (ConstraintViolation<T> violation : violations) {
            return violation.getMessage();
        }
        return null;
    }

    /**
     * 对象是否不为空(新增)
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * 对象是否为空
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            if (StringUtil.isBlank(o.toString())) {
                return true;
            }
        } else if (o instanceof List) {
            if (((List) o).isEmpty()) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Set) {
            if (((Set) o).isEmpty()) {
                return true;
            }
        } else if (o instanceof Object[]) {
            if (((Object[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof int[]) {
            if (((int[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof long[]) {
            if (((long[]) o).length == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对象组中是否存在空对象
     */
    public static boolean isOneEmpty(Object... os) {
        for (Object o : os) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对象组中是否全是空对象
     */
    public static boolean isAllEmpty(Object... os) {
        for (Object o : os) {
            if (!isEmpty(o)) {
                return false;
            }
        }
        return true;
    }
}