/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mrgnwatson
 */
public class validation {
    Pattern regex = null;
    Matcher match = null;
    
    //validate string
    public void validate(String text){
        text = text.trim();
        match = regex.matcher(text);
    }
    
    //text only validation
    public boolean validateTextOnly(String text){
        text = text.replaceAll("\\.", "");
        text = text.replaceAll("\\'", "");
        text = text.replaceAll(" ", "");
        regex = Pattern.compile("[a-zA-Z]{1,35}");
        validate(text);
        return match.matches();
    }
    
    //number only validation
    public boolean validateNumberOnly(String text){
        regex = Pattern.compile("\\d");
        text = text.replaceAll(" ", "");
        validate(text);
        return match.matches();
    }
    
    //date validation
    public boolean validateDate(String text){
        regex = Pattern.compile("/(0[1-9]|1[012])[- \\/.](0[1-9]|[12][0-9]|3[01])[- \\/.](19|20)\\d\\d/");
        text = text.replaceAll(" ", "");
        validate(text);
        return match.matches();
    }
    
    //phone number validation
    public boolean validatePhoneNumber(String text){
        text = text.replaceAll(" ", "");
        regex = Pattern.compile("(.\\d{3}).\\d{3}-\\d{4}");
        validate(text);
        return match.matches();
    }
    
    //zip code validation
    public boolean validateZipCode(String text){
        regex = Pattern.compile("\\d{5,6}");
        validate(text);
        return match.matches();
    }
}
