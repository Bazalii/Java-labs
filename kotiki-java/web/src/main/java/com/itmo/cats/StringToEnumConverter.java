package com.itmo.cats;

import com.itmo.cats.domains.Color;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Color> {
    @Override
    public Color convert(String source) {
        try {
            return Color.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }
}