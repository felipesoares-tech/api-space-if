package br.com.felipesoarestech.api.cliente.config;

import br.com.felipesoarestech.api.cliente.domain.util.ByteArrayDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(byte[].class, new ByteArrayDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
