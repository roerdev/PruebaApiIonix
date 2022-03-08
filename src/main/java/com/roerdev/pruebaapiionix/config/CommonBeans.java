package com.roerdev.pruebaapiionix.config;

import com.roerdev.pruebaapiionix.consumers.IonixAPI;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class CommonBeans {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(3600);
            }
        };
    }

    @Bean(name = "ionixApiConsumer")
    public IonixAPI getSunatApi(){
        var retrofit = new Retrofit.Builder()
                .baseUrl("https://stoplight.io/mocks/spbusiness/test-tecnico/11631269/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(IonixAPI.class);
    }


}
