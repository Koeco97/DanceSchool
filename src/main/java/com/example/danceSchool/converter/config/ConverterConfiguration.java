package com.example.danceSchool.converter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConverterConfiguration implements ApplicationListener<ContextRefreshedEvent> {
    private final Set<Converter<?, ?>> converters;
    private final ConfigurableConversionService conversionService;

    @Autowired
    public ConverterConfiguration(
            Set<Converter<?, ?>> converters,
            ConfigurableConversionService conversionService
    ) {
        this.converters = converters;
        this.conversionService = conversionService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        converters.forEach(conversionService::addConverter);
    }

}
