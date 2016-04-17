package com.cjqyj.study.sys.spring;

import com.cjqyj.study.utils.DateUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yuanyouz on 2016/3/24.
 */
public class StringToDateConverter implements GenericConverter {
    private static final Set<ConvertiblePair> convertiblePair = new HashSet(1);

    static {
        ConvertiblePair stringDatePair = new ConvertiblePair(String.class, Date.class);
        convertiblePair.add(stringDatePair);
    }

    public StringToDateConverter() {
    }

    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Date result = null;
        if(source != null) {
            String realSource = (String)source;
            int len = realSource.trim().length();
            if(len == 10) {         //"yyyy-MM-dd"
                result = DateUtils.getDate(realSource, "yyyy-MM-dd");
            } else if(len == 19) {      //"yyyy-MM-dd HH:mm:ss"
                result = DateUtils.getDate(realSource, "yyyy-MM-dd HH:mm:ss");
            }
        }

        return result;
    }

    public Set<ConvertiblePair> getConvertibleTypes() {
        return convertiblePair;
    }
}
