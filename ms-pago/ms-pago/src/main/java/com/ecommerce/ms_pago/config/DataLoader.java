package com.ecommerce.ms_pago.config;

import com.ecommerce.ms_pago.model.Pago;
import com.ecommerce.ms_pago.repository.PagoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es"));
        for (int i = 1; i <= 10; i++) {
            Pago pago = new Pago();
            pago.setMontoPago(faker.number().randomDouble(2, 1000, 50000));
            pago.setFechaPago(LocalDate.now().minusDays(faker.number().numberBetween(1, 30)));
            pago.setPedidoId((long) i);
            pagoRepository.save(pago);
        }
        System.out.println("Base de datos poblada");
    }
}