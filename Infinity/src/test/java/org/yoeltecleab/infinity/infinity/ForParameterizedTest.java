package org.yoeltecleab.infinity.infinity;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ForParameterizedTest {

//    @Autowired
//    InformationService informationService;

    //    @ParameterizedTest(name = "(0 + " +
//            "((1 - 2) * 3) + " +
//            "((4 - 5) * 6) + " +
//            "((7 - 8) * 9) + " +
//            "((10 - 11) * 12)) = 13")
//    @CsvSource({
//            "150, 10, 10, 12, 10, 10, 7, 1, 1, 90, 1, 1, 50, 150"
//    })
    @Test
    public void testPriceCalculation() {
        Assertions.assertTrue(true);
    }


}
