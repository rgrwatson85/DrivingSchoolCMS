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
        regex = Pattern.compile("\\d{3,4}");
        validate(text);
        return match.matches();
    }
    
    //number and letter address
    public boolean validateAddress(String text){
        regex = Pattern.compile("[a-zA-Z0-9]{1,150}");
        text = text.replaceAll(" ", "");
        text = text.replaceAll("\\.", "");
        validate(text);
        return match.matches();
    }
    
    //email address
    public boolean validateEmail(String text){
        regex = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        validate(text);
        return match.matches();
    }
    
    //date validation
    public boolean validateDate(String text){
        regex = Pattern.compile("^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|(?:(?:0?[1,3-9]"
                + "|1[0-2])(\\/|-|\\.)(?:29|30)\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|"
                + "-|\\.)29\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|"
                + "[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1"
                + "\\d|2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
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
