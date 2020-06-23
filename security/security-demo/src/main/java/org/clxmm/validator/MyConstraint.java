package org.clxmm.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author clx
 * @date 2020-06-23 21:27
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)  // 运行时的注解
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {



    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
