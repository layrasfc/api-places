package com.api.lugares.config;


import com.api.lugares.domain.PlaceRepository;
import com.api.lugares.service.PlaceService;
import com.api.lugares.controller.PlaceController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@Configuration
@EnableR2dbcAuditing // fazer com que os campos de data sejam automaticos
public class PlaceServiceConfig {

    @Bean
    PlaceService placeService(PlaceRepository placeRepository){
        return new PlaceService(placeRepository);
    }

    @Bean
    PlaceController placeController(PlaceService placeService){
        return new PlaceController(placeService);
    }
}
