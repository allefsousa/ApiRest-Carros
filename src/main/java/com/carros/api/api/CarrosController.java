package com.carros.api.api;

import com.carros.api.domain.Carro;
import com.carros.api.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    private CarroService service;


    @GetMapping
    public Iterable<Carro> get(){
        return service.getCarros();
    }


  @GetMapping("/{id}")
    public Optional<Carro> get(@PathVariable Long id){
        return service.getCarrosById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo){
        return service.getCarrosByTipo(tipo);
    }


}

