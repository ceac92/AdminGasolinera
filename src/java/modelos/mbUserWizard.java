/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import entity.Empleado;
 
@ManagedBean
@ViewScoped
public class mbUserWizard implements Serializable {
 
    private Empleado emp = new Empleado();
     
    private boolean skip;
     
    public Empleado getEmpleado() {
        return emp;
    }
 
    public void setEmpleado(Empleado emp) {
        this.emp = emp;
    }
     
    public void save() {        
        FacesMessage msg = new FacesMessage("Exitoso!", "Bienvenido :" + emp.getPrimerNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
}