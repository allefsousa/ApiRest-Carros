package com.carros.api.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarroService {
    private Logger logger = LoggerFactory.getLogger(CarroService.class);
    public List<Carro> getCarros(){
        List<Carro> carros = new ArrayList<>();

        carros.add(new Carro(1L,"Fusca"));
        carros.add(new Carro(2L,"Corsa"));
        carros.add(new Carro(3L,"I30"));
        logger.info("Retornando carros");
        return carros;
    }
}
