package org.yoeltecleab.infinity.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
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
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking_info")
@SequenceGenerator(name = "info_sequence", schema = "no_schema", initialValue = 1001, allocationSize = 1)
public class Information implements Serializable {
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
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_sequence")
    private Integer bookingId;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Services.class)
    @JoinColumn(name = "service_id")
    private Services services;

    @Column(name = "location")
    private String location;

    @Column(name = "occasion")
    private String occasion;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "people")
    private Integer people;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "digital_deliverable")
    private Integer digital;

    @Column(name = "printed_deliverable")
    private Integer printed;

    @Column(name = "booking_date")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private String bookingDate;

    @Column(name = "service_date")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private String serviceDate;

    @Column(name = "service_time")
    @DateTimeFormat(pattern = "HH:mm")
    private String serviceTime;

    @Column(name = "confirmation")
    private String confirmation;

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
        Information that = (Information) o;
        return user != null && Objects.equals(user, that.user);
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
