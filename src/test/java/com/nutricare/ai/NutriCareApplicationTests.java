package com.nutricare.ai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:mysql://localhost:3306/nutricare_ai?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
    "spring.datasource.username=root",
    "spring.datasource.password=akshaya111"
})
class NutriCareApplicationTests {

    @Test
    void contextLoads() {
    }
}
