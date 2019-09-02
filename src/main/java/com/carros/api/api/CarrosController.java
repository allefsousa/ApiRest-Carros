package com.carros.api.api;

import com.carros.api.domain.Carro;
import com.carros.api.domain.CarroService;
import com.carros.api.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;


    @GetMapping
    public ResponseEntity<List<CarroDTO> >get(){
        return  ResponseEntity.ok(service.getCarros()); //resumid version
//        return new ResponseEntity<>(service.getCarros(), HttpStatus.OK); // full version
    }


  @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id){
        Optional<CarroDTO> carro  = service.getCarrosById(id);
//        if (carro.isPresent()){
//            return ResponseEntity.ok(carro.get());
//        }else {
//            return ResponseEntity.notFound().build();
//        }

//      //Ternario
//      return carro.isPresent() ? ResponseEntity.ok(carro.get()) : ResponseEntity.notFound().build();
      return carro.map(car ->ResponseEntity.ok(car))
              .orElse(ResponseEntity.notFound().build());


    }


    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo){
        List<CarroDTO> carros =  service.getCarrosByTipo(tipo);
        return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);

    }

    @PostMapping
    public ResponseEntity postCarro(@RequestBody Carro carro){
        try{
            CarroDTO retorno = service.saveCarro(carro);
            URI localtion = getUri(retorno.getId());
            return ResponseEntity.created(localtion).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity putCarro(@PathVariable("id") Long id,@RequestBody Carro carro){
       carro.setId(id);
        CarroDTO retorno = service.update(carro,id);

        return  retorno != null ?
                ResponseEntity.ok(retorno) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
         service.deleteCarro(id);
         return "Carro deletado com sucesso";
    }


}

