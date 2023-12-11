package com.example.obspringbootdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObSpringbootDataApplication {

	public static void main(String[] args) {
		// SpringApplication.run(ObSpringbootDataApplication.class, args);
		ApplicationContext context = SpringApplication.run(ObSpringbootDataApplication.class, args);
		CocheRepository repository = context.getBean(CocheRepository.class);
		System.out.println("hola");
		System.out.println(repository.count()); // dice que hay 0 registros en el repositorio.

		//Crear y almacenar un coche en la base de datos
		Coche toyota = new Coche(null, "Toyota", "Prius", 2023);
		repository.save(toyota);

		System.out.println("El número de coches en la base de datos es: "+repository.count());

		System.out.println(repository.findAll());
	}

}
