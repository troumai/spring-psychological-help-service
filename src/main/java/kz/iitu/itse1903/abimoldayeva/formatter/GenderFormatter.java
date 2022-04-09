package kz.iitu.itse1903.abimoldayeva.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class GenderFormatter implements Formatter<String> {
    @Override
    public String parse(String text, Locale locale) throws ParseException {
        String gender = text;
        if(gender.equals("man"))gender = "male";
        else if(gender.equals("woman"))gender = "female";
        return gender;
    }

    @Override
    public String print(String object, Locale locale) {
        return null;
    }
}
