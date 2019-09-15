package com.carros.api;

import com.carros.api.domain.Carro;
import com.carros.api.domain.CarroService;
import com.carros.api.domain.dto.CarroDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosApplicationTests {
	@Autowired
	private CarroService service;

	@Test
	public void testSaveNewCar(){
		Carro carro = new Carro();
		carro.setTipo("esportivo");
		carro.setNome("Ferrari");

		//salvando carro
		CarroDTO carroDto = service.saveCarro(carro);
		assertNotNull(carroDto);

		//buscando carro pelo id
		Long id = carro.getId();
		assertNotNull(id);
		Optional<CarroDTO> op = service.getCarrosById(id);
		assertTrue(op.isPresent());

		carroDto = op.get();
		assertEquals(carro.getNome(),carroDto.getNome());
		assertEquals(carro.getTipo(),carroDto.getTipo());

		// deletando carro
		service.deleteCarro(id);
		assertFalse(service.getCarrosById(id).isPresent());

	}

	@Test
	public void contextLoads() {
	}

}
