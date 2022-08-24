package org.yoeltecleab.infinity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
@Table(name = "service")
public class Services implements Serializable {
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
    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "occasion")
    private String occasion;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "base_price")
    private Double price;

    @Column(name = "digital_deliverable")
    private Integer digital;

    @Column(name = "printed_deliverable")
    private Integer printed;

    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Information> information;

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
        if (!(o instanceof Services services)) return false;
        return Objects.equals(getDuration(), services.getDuration()) && Double.compare(services.getPrice(), getPrice()) == 0 && Objects.equals(getDigital(), services.getDigital()) && Objects.equals(getPrinted(), services.getPrinted()) && getServiceId().equals(services.getServiceId()) && getOccasion().equals(services.getOccasion());
    }

    /**
     * <pre>
     *  This method overrides the original "hashcode" method
     * @return an integer value of hashcode of the class
     * </pre>
     */
    @Override
    public int hashCode() {
        return Objects.hash(getServiceId(), getOccasion(), getDuration(), getPrice(), getDigital(), getPrinted());
    }

    /**
     * This method overrides the default toString method to be implemented in a desired way
     *
     * @return String format of the Services
     */
    @Override
    public String toString() {
        return "Services{" +
                "serviceId=" + serviceId +
                ", occasion='" + occasion + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", digital=" + digital +
                ", printed=" + printed +
                '}';
    }

}
