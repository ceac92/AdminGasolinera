/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.loginDao;
import entity.Empleado;
import hibernateutil.MyUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
    private int valorempleado;
    private String username;

    public int getValorempleado() {
        return valorempleado;
    }

    public void setValorempleado(int valorempleado) {
        this.valorempleado = valorempleado;
    }

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

    private Empleado empleadoAutenticado;

    public Empleado getEmpleadoAutenticado() {
        return empleadoAutenticado;
    }

    public void setEmpleadoAutenticado(Empleado empleadoAutenticado) {
        this.empleadoAutenticado = empleadoAutenticado;
    }

    public void login(ActionEvent event) {

        Session session = hibernateutil.HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.beginTransaction();
        loginDao userLogin = new loginDao();

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn = false;
        String ruta = "";
        String refreshLogin = MyUtil.baseUrl();

        try {
            empleadoAutenticado = userLogin.getEmpLogin(session, this.username);
            if (empleadoAutenticado == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, loginBean.class.getCanonicalName(), "load: Credenciales Invalidas"));
                return;
            }

            if (username != null && username.equals(empleadoAutenticado.getMail()) && password != null && password.equals(empleadoAutenticado.getPassword())) {
                loggedIn = true;
                int rolUsuario = empleadoAutenticado.getRol().getIdrol();
                String estado = empleadoAutenticado.getEstado();
                this.valorempleado = empleadoAutenticado.getIdempleado();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", empleadoAutenticado.getMail());
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", empleadoAutenticado.getMail());

                if (rolUsuario == 1 && estado.equals("a")) {
                    ruta = MyUtil.basepathlogin() + "/administrador/inicio.xhtml?faces-redirect=true";
                } else if (rolUsuario == 2 && estado.equals("a")) {
                    ruta = MyUtil.basepathlogin() + "empleado/compra.xhtml?faces-redirect=true";
                } else {
                    ruta = MyUtil.basepathlogin() + "/usuarioInactivo.xhtml?faces-redirect=true";
                }

            } else {
                loggedIn = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, loginBean.class.getCanonicalName(), "Credenciales Invalidas");
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

    public void autenticarEmpleado(int idEmpleadoAuth) throws IOException {
        if (idEmpleadoAuth != 0) {

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Autenticacion Invalida", "El usuario autenticado no es valido"));
            RequestContext.getCurrentInstance().addCallbackParam("ruta", MyUtil.baseUrl());
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml?faces-redirect=true");
        }
    }

    public void cerrarSesion() throws IOException {
        this.password = null;
        this.username = null;

        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml?faces-redirect=true");

    }
}
