package org.example;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainTest {

    @Test
    public void applicationStarts() {
        Main.main(new String[] {});
    }
}
