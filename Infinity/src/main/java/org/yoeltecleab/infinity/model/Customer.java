package org.yoeltecleab.infinity.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;

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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_info")
public class Customer implements Serializable {
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
    @Id
    @Column(name = "customer_id")
    private Integer customerId;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @PrimaryKeyJoinColumn(name = "customer_id")
    private User user;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private Integer zipCode;

    @Column(name = "name_card")
    private String nameCard;

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "exp_month")
    private Integer expMonth;

    @Column(name = "exp_year")
    private Integer expYear;

    @Column(name = "cvv")
    private Integer cvv;

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
        return o != null && Hibernate.getClass(this) == Hibernate.getClass(o);
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
}
