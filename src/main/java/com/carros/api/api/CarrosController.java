package com.carros.api.api;

import com.carros.api.domain.Carro;
import com.carros.api.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;


    @GetMapping
    public ResponseEntity<Iterable<Carro> >get(){
        return  ResponseEntity.ok(service.getCarros()); //resumid version
//        return new ResponseEntity<>(service.getCarros(), HttpStatus.OK); // full version
    }


  @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id){
        Optional<Carro> carro  = service.getCarrosById(id);
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
        List<Carro> carros =  service.getCarrosByTipo(tipo);
        return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);

    }

    @PostMapping
    public String postCarro(@RequestBody Carro carro){
        Carro retorno = service.saveCarro(carro);
        return "Carro salvo com sucesso: " + retorno.getId();
    }

    @PutMapping("/{id}")
    public String putCarro(@PathVariable("id") Long id,@RequestBody Carro carro){
        Carro retorno = service.update(carro,id);
        return "Carro Atualizado com sucesso: " + retorno.getId();
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
         service.deleteCarro(id);
         return "Carro deletado com sucesso";
    }


}

