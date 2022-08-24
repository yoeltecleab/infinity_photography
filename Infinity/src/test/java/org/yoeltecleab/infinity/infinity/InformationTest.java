package org.yoeltecleab.infinity.infinity;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.yoeltecleab.infinity.model.Information;
import org.yoeltecleab.infinity.model.User;
import org.yoeltecleab.infinity.repository.InformationRepository;
import org.yoeltecleab.infinity.repository.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InformationTest {

    @Autowired
    private InformationRepository information;

    @Autowired
    private UserRepository userRepository;


    @Order(1)
    @Test
    @Rollback(false)
    @DisplayName("Creating User Test")
    public void createUser() {
        System.out.println("Testing .... testing creating user");
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@email.com");
        user.setPassword("12345");
        userRepository.save(user);
        Assertions.assertEquals(user.getFirstName(), "John", "Assertion to equal works!");
        Assertions.assertEquals(user.getLastName(), "Doe", "Assertion to equal works!");
        System.out.println("All assertions to equal works!");
        Assertions.assertNotEquals(user.getEmail(), "wrongemail@email.com", "Assertion to not equal works!");
        Assertions.assertNotEquals(user.getPassword(), "54321", "Assertion to not equal works!");
        System.out.println("All assertions to not equal works!");
    }


    @Order(2)
    @Test
    @Rollback(false)
    @DisplayName("Creating Booking Test")
    public void testCreateBooking() {
        System.out.println("Testing .... testing creating booking");

        Information info = new Information();
        info.setBookingId(9999);
        info.setDigital(99);
        info.setDuration(99.0);
        info.setOccasion("Graduation");
        info.setLocation("Online");
        info.setPeople(22);
        info.setServiceDate("2022-08-26");
        info.setServiceTime("10:00");
        info.setPrinted(0);
        info.setBookingDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        info.setUser(userRepository.findByEmail("johndoe@email.com"));
        information.save(info);

        /* These next assure that it is true */
        Assertions.assertEquals(info.getBookingId(), 9999, "Assertion to equal works!");
        Assertions.assertEquals(info.getDigital(), 99, "Assertion to equal works!");
        Assertions.assertEquals(info.getDuration(), 99, "Assertion to equal works!");
        Assertions.assertEquals(info.getLocation(), "Online", "Assertion to equal works!");
        Assertions.assertEquals(info.getOccasion(), "Graduation", "Assertion to equal works!");
        Assertions.assertEquals(info.getPeople(), 22, "Assertion to equal works!");
        /* These next assure that it is false*/
        Assertions.assertNotEquals(info.getServiceDate(), "2022-12-23", "Assertion to false works!");
        Assertions.assertNotEquals(info.getServiceTime(), "15:56", "Assertion to not equal works!");
        Assertions.assertNotEquals(info.getPrinted(), 999, "Assertion to bot equal works!");
        Assertions.assertNotEquals(info.getBookingDate(), LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("MM-dd-yyyy")), "Assertion to not equal works!");
    }


    @Order(3)
    @Test
    @Rollback(false)
    @DisplayName("Finding Booking Test")
    public void testFindBookingByUserEmail() {
        System.out.println("Testing .... testing reading / finding booking");

        Information info = information.findByUser_Email("johndoe@email.com");
        Assertions.assertEquals(info.getLocation(), ("Online"));
    }

    @Order(4)
    @Test
    @Rollback(false)
    @DisplayName("Updating Booking Test")
    public void testUpdateBooking() {
        System.out.println("Testing .... testing updating booking");

        Information info = information.findByUser_Email("johndoe@email.com");
        info.setDuration(30.0);
        information.save(info);
        Information updated = information.findByUser_Email("johndoe@email.com");
        Assertions.assertEquals(info.getDuration(), updated.getDuration(), "Assertion to equal works!");
    }

    @Order(5)
    @Test
    @Rollback(false)
    @DisplayName("Deleting Booking Test")
    public void testDeleteBooking() {
        System.out.println("Testing deleting operation...");
        System.out.println("As \"Rollback\" is enables, test will not affect original data...");

        Information info = information.findByUser_Email("johndoe@email.com");

        information.deleteById(info.getBookingId());

        Assertions.assertNull(information.findByUser_Email("johndoe@email.com"), "Assertion to not null works!");

    }
}
