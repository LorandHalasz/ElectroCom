package com.is.ElectroCom.config;

import com.is.ElectroCom.model.Order;
import com.is.ElectroCom.model.User;
import com.is.ElectroCom.model.Product;
import com.is.ElectroCom.repository.OrderRepository;
import com.is.ElectroCom.repository.UserRepository;
import com.is.ElectroCom.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@EnableMongoRepositories(basePackageClasses = {UserRepository.class, ProductRepository.class, OrderRepository.class})

// aceasta clasa se utilizeaza pentru configurarea bazei de date
@Configuration
public class DB {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
               /* userRepository.deleteAll();
                userRepository.save(new User("ad", "min", "Str. admin, nr. 1, jud. Cluj", "0731111111", "admin@yahoo.com", "a" , "Admin", new ArrayList<>()));
                userRepository.save(new User("us","er", "Str. user, nr. 2, jud. Cluj", "0742222222","user@yahoo.com", "u", "Client", new ArrayList<>()));
                productRepository.deleteAll();
                productRepository.save(new Product("Laptop", "Laptop Lenovo", "https://s12emagst.akamaized.net/products/5350/5349811/images/res_611ceaae5e690d0a600b56681540de05_450x450_sl8q.jpg", 3499.00, 10, 4.59, "Electronice"));
                productRepository.save(new Product("Smartphone", "Samsung S9", "https://s12emagst.akamaized.net/products/13571/13570812/images/res_659fc224a512612671183b735069348e_450x450_72op.jpg",2899.00,15, 4.79, "Electronice"));
                productRepository.save(new Product("Combina Frigorifica", "Samsung", "https://s12emagst.akamaized.net/products/5708/5707231/images/res_21aa024131c347d121cce8743b9bc3af_450x450_lhd0.jpg", 3299.99, 23, 5.00, "Electrocasnice"));
                productRepository.save(new Product("Aspirator", "Myria", "https://s12emagst.akamaized.net/products/18226/18225744/images/res_3a1cb3e43f4cb3205a983146600d1793_450x450_uks5.jpg", 149.99, 100, 2.00, "Electrocasnice"));
                orderRepository.deleteAll();
                orderRepository.save(new Order(1, "user@yahoo.com", "Smartphone", String.valueOf("2018-06-21")));
*/
            }
        };
    }
}

