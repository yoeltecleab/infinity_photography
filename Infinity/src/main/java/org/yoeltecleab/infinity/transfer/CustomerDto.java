package org.yoeltecleab.infinity.transfer;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CustomerDto {

    @NotEmpty(message = "First Name can not be empty")
//    @Pattern(regexp = "^[a-zA-Z0-9$]+\s+[a-zA-Z0-9$]", message = "There must be at least one space between first and last name")
    private String fullName;

    @NotEmpty(message = "Email can not be empty")
//    @Pattern(regexp = "^[A-Za-z0-9+_.-]+ @(.+)$", message = "Please put email in the correct format")
    private String email;

    @NotEmpty(message = "Address must not be empty")
    private String address;

    @NotEmpty(message = "City must not be empty")
    private String city;

    @NotEmpty(message = "State must not be empty")
    private String state;

    @NotNull(message = "Zip Code can not be null")
    @Min(value = 10000, message = "Zip code must at least be 5 digits ")
    @Max(value = 99999, message = "Zip code can not be more than 5 digits")
    private Integer zipCode;

    @NotEmpty(message = "Please put your name as is on the card")
    private String nameCard;

    @NotNull(message = "Card number can not be empty")
//    @Pattern(regexp = """
//            ^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|
//            \t\t(?<mastercard>5[1-5][0-9]{14})|
//            \t\t(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|
//            \t\t(?<amex>3[47][0-9]{13})|
//            \t\t(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|
//            \t\t(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$
//            """, message = "Your card number should be separated using spaces after each four digits")

    private Long cardNumber;


    @NotNull(message = "Expiration Month can not be empty")
    @Min(value = 1, message = "Months should start from 1")
    @Max(value = 12, message = "When did we get a new Calendar")
    private Integer expMonth;

    @NotNull(message = "Expiration Year can not be empty")
    @Min(value = 2022, message = "Your card might be expired")
    @Max(value = 9999, message = "Seriously????")
    private Integer expYear;

    @NotNull(message = "CVV code can not be empty")
    @Min(value = 100, message = "Must at least be 3 digits")
    @Max(value = 9999, message = "Can not be more than 4 digits")
    private Integer cvv;
}
