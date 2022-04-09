package kz.iitu.itse1903.abimoldayeva.validation;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.AssertTrue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailCheckValidator implements ConstraintValidator<EmailCheck, Client> {
    @Override
    public void initialize(EmailCheck constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Client client, ConstraintValidatorContext constraintValidatorContext) {
        if( client == null) return false;
        return doEmailFormatCheck(client.getEmail());
    }

    private boolean doEmailFormatCheck(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        Matcher matcher = pattern.matcher(email);
        return (email.trim().length()>0) && matcher.find();
    }
}
