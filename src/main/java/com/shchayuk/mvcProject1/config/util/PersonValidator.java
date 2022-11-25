package com.shchayuk.mvcProject1.config.util;

import com.shchayuk.mvcProject1.config.dao.PersonDAO;
import com.shchayuk.mvcProject1.config.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "", "No");

        if (personDAO.showPersonByName(person.getName()).isPresent()){
            errors.rejectValue("name", "","This name already exists");
        }
        if (String.valueOf(person.getYear()).isEmpty()){
            errors.rejectValue("year", "org.springframework.web.util.NestedServletException","This field shouldn't be empty");
        }

    }
}
