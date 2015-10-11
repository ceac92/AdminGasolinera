/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.detalledocumentodao;
import dao.documentodao;
import dao.empleadodao;
import entity.Ctgtipodocumento;
import entity.Detalledocumento;
import entity.Empleado;
import entity.Rol;
import hibernateutil.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author An
 */
@Named("mbempleado")
@ViewScoped
public class mbempleado implements Serializable {

    Session session;
    Transaction transaction;
    private List<Detalledocumento> detalledocumen;
    private List<Empleado> allempleado;
    private Empleado emple;

    public List<Empleado> getAllempleado() {
        return allempleado;
    }

    public void setAllempleado(List<Empleado> allempleado) {
        this.allempleado = allempleado;
    }

    public Empleado getEmple() {
        return emple;
    }

    public void setEmple(Empleado emple) {
        this.emple = emple;
    }
    private String valordocumento;
    private Ctgtipodocumento tipodocumento;
    private int valortipodocu;
    private int idrol;
    private String estadocivil="soltero";
    private String generovalor="m";

    public int getValortipodocu() {
        return valortipodocu;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getGenerovalor() {
        return generovalor;
    }

    public void setGenerovalor(String generovalor) {
        this.generovalor = generovalor;
    }

    public void setValortipodocu(int valortipodocu) {
        this.valortipodocu = valortipodocu;
    }

    public Ctgtipodocumento getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(Ctgtipodocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getValordocumento() {
        return valordocumento;
    }

    public void setValordocumento(String valordocumento) {
        this.valordocumento = valordocumento;
    }

    public List<Detalledocumento> getDetalledocumen() {
        return detalledocumen;
    }

    public void setDetalledocumen(List<Detalledocumento> detalledocumen) {
        this.detalledocumen = detalledocumen;
    }

    /**
     * Creates a new instance of mbempleado
     */
    public mbempleado() {
        this.detalledocumen = new ArrayList<>();
        this.tipodocumento = new Ctgtipodocumento();
    }

    public void agregarListaDetalleDocumento() {
        this.session = null;
        this.transaction = null;

        try {
            boolean comparar = false;
            for (Detalledocumento item : this.detalledocumen) {
                if (item.getCtgtipodocumento().getIdctgTipoDocumento().equals(this.valortipodocu)) {

                    comparar = true;
                    break;
                }
            }
            if (comparar == true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", "ya esta el producto seleccionado"));
                RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
            } else {
                this.session = HibernateUtil.getSessionFactory().openSession();
                documentodao daoTProducto = new documentodao();
                this.transaction = this.session.beginTransaction();
                this.tipodocumento = daoTProducto.getByIdProducto(this.session, this.valortipodocu);

                this.detalledocumen.add(new Detalledocumento(this.tipodocumento, null, this.valordocumento));

                this.transaction.commit();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Documento agregado"));
                RequestContext.getCurrentInstance().update("frmRealizarVentas:msg");

            }

        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }

        }
    }

    public void retirarListaVentaDetalle(int idtipodocumento) {

        try {
            int i = 0;
            for (Detalledocumento item : this.detalledocumen) {
                if (item.getCtgtipodocumento().getIdctgTipoDocumento().equals(idtipodocumento)) {
                    this.detalledocumen.remove(i);
                    break;
                }
                i++;
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Documento retirado"));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    @Inject
    private Empleado emp;

    public void regisTraempleado() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            documentodao docu = new documentodao();
            detalledocumentodao ddocudao = new detalledocumentodao();
            Empleado emple = new Empleado();
            emple.setRol((Rol) session.get(Rol.class, this.idrol));
            emple.setPrimerNombre(emp.getPrimerNombre());
            emple.setSegundoNombre(emp.getSegundoNombre());
            emple.setPrimerApellido(emp.getPrimerApellido());
            emple.setSegundoApellido(emp.getSegundoApellido());
            emple.setFechaNacimiento(emp.getFechaNacimiento());
            emple.setFechaContratacion(emp.getFechaContratacion());
            emple.setGenero(this.generovalor);
            emple.setEstadoCivil(this.estadocivil);
            emple.setMail(emp.getMail());
            emple.setPassword(emp.getPassword());
            emple.setTelefonoFijo(emp.getTelefonoFijo());
            emple.setTelefonoMovil(emp.getTelefonoMovil());
            emple.setDireccion(emp.getDireccion());
            emple.setEstado("a");
            this.session.save(emple);
            for (Detalledocumento detdocu : detalledocumen) {
                this.tipodocumento = docu.getByIdProducto(session, detdocu.getCtgtipodocumento().getIdctgTipoDocumento());
                detdocu.setEmpleado(emple);
                detdocu.setCtgtipodocumento(tipodocumento);
                ddocudao.insert(session, detdocu);
            }
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Empleado registrado correctamente."));
            this.detalledocumen = new ArrayList<>();
            this.tipodocumento = new Ctgtipodocumento();
        
            this.idrol = 0;
            this.generovalor = "";

        } catch (Exception e) {
            this.transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } finally {
            this.session.close();
        }
    }
    public List<Empleado> getEmpleado(){
        this.session=null;
        try {
            session=HibernateUtil.getSessionFactory().openSession();
            this.allempleado=empleadodao.getAllempleado(session);
            return  this.allempleado;
            
        } catch (Exception e) {
        }finally{
        session.close();
        }
       return null;
       
    }
}
