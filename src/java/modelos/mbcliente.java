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
    private String nombreuno;
    private String nombredos;
    private String apellidouno;
    private String apellido;
    private String tipodocu;
    private int numerodocum;
    private String direccion;

    public String getNombreuno() {
        return nombreuno;
    }

    public void setNombreuno(String nombreuno) {
        this.nombreuno = nombreuno;
    }

    public String getNombredos() {
        return nombredos;
    }

    public void setNombredos(String nombredos) {
        this.nombredos = nombredos;
    }

    public String getApellidouno() {
        return apellidouno;
    }

    public void setApellidouno(String apellidouno) {
        this.apellidouno = apellidouno;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipodocu() {
        return tipodocu;
    }

    public void setTipodocu(String tipodocu) {
        this.tipodocu = tipodocu;
    }

    public int getNumerodocum() {
        return numerodocum;
    }

    public void setNumerodocum(int numerodocum) {
        this.numerodocum = numerodocum;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

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
            cl.setPrimerNombre(this.nombreuno);
            cl.setSegundoNombre(this.nombredos);
            cl.setPrimerApellido(this.apellidouno);
            cl.setSegundoApellido(this.apellido);
            cl.setCtgmunicipio((Ctgmunicipio) session.get(Ctgmunicipio.class, this.municipio));
            cl.setCtgtipocliente((Ctgtipocliente) session.get(Ctgtipocliente.class, this.tipocliente));
            cl.setFechaRegistro(this.fecharegistro);
            cl.setTipoDocumento(this.tipodocu);
            cl.setNumeroDocumento(String.valueOf(this.numerodocum));
            cl.setTelefonoFijo(this.cliente.getTelefonoFijo());
            cl.setTelefonoMovil(this.cliente.getTelefonoMovil());
            cl.setMail(this.cliente.getMail());
            cl.setDireccion(this.direccion);
            cl.setEstado("a");
            this.valor = (int) this.session.save(cl);
            transaction.commit();

            if (this.valor > 0) {
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
