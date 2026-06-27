package com.ecommerce.ms_cliente.config;

import com.ecommerce.ms_cliente.model.Cliente;
import com.ecommerce.ms_cliente.repository.ClienteRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es"));
        for (int i = 0; i < 10; i++) {
            Cliente c = new Cliente();
            c.setNombreCliente(faker.name().fullName());
            c.setCorreoCliente(faker.internet().emailAddress());
            c.setTelefonoCliente(faker.phoneNumber().cellPhone());
            repository.save(c);
        }
        System.out.println(">>> Base de datos poblada con DataFaker.");
    }
}