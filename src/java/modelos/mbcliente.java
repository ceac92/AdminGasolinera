/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entity.Cliente;
import entity.Ctgmunicipio;
import entity.Ctgtipocliente;
import hibernateutil.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author An
 */
@ViewScoped
@Named("mbcliente")
public class mbcliente implements Serializable {

    Session session;
    Transaction transaction;
    java.util.Date fecha = new Date();
    private Date fecharegistro = fecha;
    private int tipocliente;
    private int municipio;
    private int valor;

    public int getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(int tipocliente) {
        this.tipocliente = tipocliente;
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    /**
     * Creates a new instance of mbcliente
     */
    public mbcliente() {
    }

    @Inject
    private Cliente cliente;

    public void registrarCliente() {
        this.session = null;
        this.transaction = null;
        Cliente cl = new Cliente();
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            cl.setCodigo(this.cliente.getCodigo());
            cl.setPrimerNombre(this.cliente.getPrimerNombre());
            cl.setSegundoNombre(this.cliente.getSegundoNombre());
            cl.setPrimerApellido(this.cliente.getPrimerApellido());
            cl.setSegundoApellido(this.cliente.getSegundoApellido());
            cl.setCtgmunicipio((Ctgmunicipio) session.get(Ctgmunicipio.class, this.municipio));
            cl.setCtgtipocliente((Ctgtipocliente) session.get(Ctgtipocliente.class, this.tipocliente));
            cl.setFechaRegistro(this.fecharegistro);
            cl.setTipoDocumento(this.cliente.getTipoDocumento());
            cl.setNumeroDocumento(this.cliente.getNumeroDocumento());
            cl.setTelefonoFijo(this.cliente.getTelefonoFijo());
            cl.setTelefonoMovil(this.cliente.getTelefonoMovil());
            cl.setMail(this.cliente.getMail());
            cl.setDireccion(this.cliente.getDireccion());
            cl.setEstado("a");
          this.valor=(int)  this.session.save(cl);
            transaction.commit();

         
            if (this.valor>0) {
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente", "Registrado"));
                  FacesContext.getCurrentInstance().getExternalContext().redirect("registro.xhtml");
            }
          
        } catch (Exception e) {
            transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo agregar, revisar valores"));
        } finally {
            this.session.close();
        }

    }

}
