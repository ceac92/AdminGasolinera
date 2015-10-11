/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.loginDao;
import entity.Empleado;
import hibernateutil.MyUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.Serializable;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Erick
 */
@ManagedBean
@SessionScoped
public class loginBean implements Serializable {

    /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
    }

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(ActionEvent event) {

        Session session = hibernateutil.HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.beginTransaction();
        loginDao userLogin = new loginDao();

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn=false;
        String ruta = "";
        String refreshLogin = MyUtil.baseUrl();

        try {
            Empleado usuario = userLogin.getEmpLogin(session, this.username);

            if (username != null && username.equals(usuario.getMail()) && password != null && password.equals(usuario.getPassword())) {
                loggedIn = true;
                int rolUsuario = usuario.getRol().getIdrol();
                String estado = usuario.getEstado();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario.getMail());
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuario.getMail());

                if (rolUsuario == 1 && estado.equals("a")) 
                {
                    ruta = MyUtil.basepathlogin() + "/administrador/inicio.xhtml";
                } else if (rolUsuario ==2 && estado.equals("a") ) 
                {
                    ruta = MyUtil.basepathlogin() + "/empleado/inicio.xhtml";
                }else{
                    ruta = MyUtil.basepathlogin() + "/usuarioInactivo.xhtml";
                }

            } else {
                loggedIn = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de Login", "Credenciales Invalidas");
                ruta = MyUtil.baseUrl();
            }
            txn.commit();

            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", loggedIn);
            context.addCallbackParam("ruta", ruta);
        } catch (Exception ex) {
            Logger.getLogger(loginBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();
        }
    }
}
