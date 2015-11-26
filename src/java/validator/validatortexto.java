/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author An
 */
@FacesValidator("validatortexto")
public class validatortexto implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
         String label;
        
        HtmlInputText htmlInputText=(HtmlInputText) uic;
        
        if(htmlInputText.getLabel()==null || htmlInputText.getLabel().trim().equals(""))
        {
            label=htmlInputText.getId();
        }
        else
        {
            label=htmlInputText.getLabel();
        }
        
        Pattern pattern=Pattern.compile("([a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ']{2,25})");
        Matcher matcher=pattern.matcher((CharSequence) o);
        
        if(!matcher.matches())
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", label+": Solo texto"));
        }
    }
    
}
