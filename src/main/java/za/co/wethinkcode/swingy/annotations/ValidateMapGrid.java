package za.co.wethinkcode.swingy.annotations;


import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {MapValidator.class})

public @interface ValidateMapGrid
{

    String message() default "The  map grid is not valid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

