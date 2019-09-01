package com.carros.api.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    private Logger logger = LoggerFactory.getLogger(CarroService.class);

    @Autowired
    private CarroRepository carroRepository;

    public Iterable<Carro> getCarros(){
        return carroRepository.findAll();
    }


    public List<Carro> getCarrosFake(){
        List<Carro> carros = new ArrayList<>();

        carros.add(new Carro(1L,"Fusca"));
        carros.add(new Carro(2L,"Corsa"));
        carros.add(new Carro(3L,"I30"));
        logger.info("Retornando carros");
        return carros;
    }

    public Optional<Carro> getCarrosById(Long id) {
        return carroRepository.findById(id);
    }
}
