package org.yoeltecleab.infinity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <pre>
 * This class is the model class for Customers.
 * for this project, a "User" is considered as a "Customer"
 * when they finish the booking process and then enter the payment information.
 * It is responsible for creating the table "Customer"
 * in Database and making the columns
 *
 * These annotations replace need of writing codes for the variables
 * {@code @Getter} replaces all the getter methods
 * {@code @Setter} replaces all the setter methods
 * {@code @ToString} replaces the toString method generated
 * {@code @AllArgsConstructor} replaces the constructor that contains all the arguments
 * {@code @NoArgsConstructor} replaces the constructor that contains no arguments
 * </pre>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@SecondaryTable(name = "customer_info", pkJoinColumns = @PrimaryKeyJoinColumn(name = "customer_id"))
@SequenceGenerator(name = "user_sequence", schema = "no_schema", initialValue = 101, allocationSize = 1)
public class User implements Serializable {

    /**
     * <pre>
     * These are the variables for the entity class "Customer"
     * They are responsible for creating the columns
     * in the database
     *
     * {@code @OneToOne} maps the relationship with the "User" class.
     * {@code @Id} identifies the primary key of the table
     * {@code @Column} adds more details to the column
     * {@code @PrimaryKeyJoinColumn} indicates that the primary key of this table
     * is also the primary key of the "User" class
     * </pre>
     */
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Information> information;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Customer.class)
    private Customer customer;

    /**
     * <pre>
     * This method overrides the original "equals" method
     * @param o takes an object "o" as an argument and compares
     * @return a boolean value whether the compared values are equal or not
     * </pre>
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return email != null && Objects.equals(email, user.email);
    }

    /**
     * <pre>
     *  This method overrides the original "hashcode" method
     * @return an integer value of hashcode of the class
     * </pre>
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * This method overrides the default toString method to be implemented in a desired way
     *
     * @return String format of the Services
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "email = " + email + ", " +
                "userId = " + userId + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "password = " + password + ")";
    }
}