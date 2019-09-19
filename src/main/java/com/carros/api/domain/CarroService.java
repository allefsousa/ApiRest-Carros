package com.carros.api.domain;


import com.carros.api.api.exception.ObjectNotFoundException;
import com.carros.api.domain.dto.CarroDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {
    private Logger logger = LoggerFactory.getLogger(CarroService.class);

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> getCarros(){
        return carroRepository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO getCarrosById(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {

        return carroRepository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO saveCarro(Carro carro) {
        Assert.isNull(carro.getId(),"Não Possivel Inserir o registro");

        return CarroDTO.create(carroRepository.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro");
        Optional<Carro> optionalCarro = carroRepository.findById(id);
        if (optionalCarro.isPresent()){
            Carro db = optionalCarro.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());
            carroRepository.save(db);
            return CarroDTO.create(db);
        }else {
            return null;
        }
    }

    public void deleteCarro(Long id) {
        carroRepository.deleteById(id);
    }
}
