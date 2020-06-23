package org.clxmm.validator;

import org.clxmm.web.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author clx
 * @date 2020-06-23 21:29
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {


        System.out.println("MyConstraintValidator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String test = helloService.greeting("test");
        System.out.println(test);

        System.out.println(value);


        return false;
    }
}
