//https://stackoverflow.com/questions/6294587/java-string-validation-using-enum-values-and-annotation/6360458

package za.co.wethinkcode.swingy.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {TypeValidator.class})
public @interface ValidateType
{
    String[] types();

    String message() default "The  character type is not valid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
