package com.Hanium.Farm.Farm.Service;

import com.Hanium.Farm.Farm.Domain.Fruit;
import com.Hanium.Farm.Farm.Repository.FruitRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class FarmApplicationTests {

	@Autowired
	FruitRepositoryInterface fruitRepository;

	@Test
	public void FuitTest(){
		// given
		String name = "사과";
		Fruit info = fruitRepository.getFruitInfo(name);

		assertThat(info.getFruit_name()).isEqualTo("사과");
		assertThat(info.getCalories()).isEqualTo("57.0");
	}
}
