package br.com.tqi.analiseemprestimo.configurations;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfiguration {

	private DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
	
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(getLocalDateConverter());
        modelMapper.addConverter(getLocalDateToStringConverter());

        return modelMapper;
    }

    private AbstractConverter<String, LocalDate> getLocalDateConverter() {
        return new AbstractConverter<String, LocalDate>() {

            @Override
            protected LocalDate convert(String source) {
                return LocalDate.parse(source, localDateFormatter);
            }
        };
    }
    
    private AbstractConverter<LocalDate, String> getLocalDateToStringConverter() {
        return new AbstractConverter<LocalDate, String>() {

            @Override
            protected String convert(LocalDate source) {
                if (source == null) {
                    return null;
                }

                return  localDateFormatter.format(source);
            }
        };
    }
}
