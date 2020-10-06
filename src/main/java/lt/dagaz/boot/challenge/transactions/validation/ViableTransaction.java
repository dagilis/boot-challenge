package lt.dagaz.boot.challenge.transactions.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ViableTransactionValidator.class)
@Documented
public @interface ViableTransaction {

    String message() default "{Transaction is not viable to be executed}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}