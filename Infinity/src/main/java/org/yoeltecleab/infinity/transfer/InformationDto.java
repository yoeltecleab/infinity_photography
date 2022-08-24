package org.yoeltecleab.infinity.transfer;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class InformationDto {
    @NotEmpty(message = "Please select your location")
    private String location;

    @NotEmpty(message = "Please select your occasion")
    private String occasion;

    @NotNull(message = "Please enter how long you are booking the session for")
    @Positive(message = "Number must be greater than zero")
    private Double duration;

    @NotNull(message = "Please put how many people including you are going to be in the session")
    @Positive(message = "Number must be greater than zero")
    private Integer people;

    @NotNull(message = "Please select how many digital images you want")
    @PositiveOrZero(message = "Number must be greater than or equal to zero")
    private Integer digital;

    @NotNull(message = "Please select how many printed images you want")
    @PositiveOrZero(message = "Number must be greater than or equal to zero")
    private Integer printed;

    //@DateTimeFormat(pattern = "MM-dd-yyyy")
    @NotEmpty(message = "Please select your desired date")
    private String serviceDate;

    //@DateTimeFormat(pattern = "HH:mm")
    @NotEmpty(message = "Please select your desired time")
    private String serviceTime;
}
