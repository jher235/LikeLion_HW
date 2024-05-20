package org.mjulikelion.likelion12thhw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing// JPA Auditing 활성화
public class Likelion12thHwApplication {

    public static void main(String[] args) {
        SpringApplication.run(Likelion12thHwApplication.class, args);
    }

}
