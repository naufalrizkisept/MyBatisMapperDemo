package org.example;

import org.example.controller.CompanyController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.Assert.assertNull;

@SpringBootTest
class MainTest {

    @Test
    void applicationStarts() {
        Main.main(new String[] {});
        CompanyController companyControllerTest = new CompanyController();
        assertNull(companyControllerTest.deleteCompany(1L));
    }
}
