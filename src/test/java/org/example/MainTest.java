package org.example;


import org.example.controller.CompanyControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.Assert.assertNull;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MainTest {

    @Test
    void applicationStarts() {
        Main.main(new String[] {});
        CompanyControllerTest companyControllerTest = new CompanyControllerTest();
        companyControllerTest.testFindAllCompanies();
    }
}
