package com.carros.api;

import com.carros.api.domain.Carro;
import com.carros.api.domain.CarroService;
import com.carros.api.domain.dto.CarroDTO;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosServiceTests {
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
		carroDto = service.getCarrosById(id);
		assertNotNull(carroDto);
		assertEquals(carro.getNome(),carroDto.getNome());
		assertEquals(carro.getTipo(),carroDto.getTipo());

		// deletando carro
		service.deleteCarro(id);

		try{
			assertNull(service.getCarrosById(id));
			fail("O carro não foi excluido");
		}catch (ObjectNotFoundException e){

		}

	}

	@Test
	public void testList() {
		List<CarroDTO> carros = service.getCarros();
		assertEquals(30,carros.size());

	}

	@Test
	public void testCarro() {

		CarroDTO c = service.getCarrosById(11L);
		assertNotNull(c);
		assertEquals("Ferrari FF",c.getNome());

	}

}
