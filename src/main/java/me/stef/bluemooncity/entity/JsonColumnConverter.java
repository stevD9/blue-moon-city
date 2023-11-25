package me.stef.bluemooncity.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import me.stef.bluemooncity.MyErrorCode;
import me.stef.bluemooncity.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

@Converter
public class JsonColumnConverter<T> implements AttributeConverter<T, String> {

    private final Class<T> clazz;

    @Autowired
    private ObjectMapper mapper;

    public JsonColumnConverter() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public String convertToDatabaseColumn(T t) {
        try {
            return mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new MyException(MyErrorCode.JSON_IO);
        }
    }

    @Override
    public T convertToEntityAttribute(String s) {
        try {
            return s == null ? null : mapper.readValue(s, clazz);
        } catch (JsonProcessingException e) {
            throw new MyException(MyErrorCode.JSON_IO);
        }
    }
}
