/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entity.Ctgpaisproveedor;
import hibernateutil.HibernateUtil;
import java.io.Serializable;
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
@Named("mbpais")
@ViewScoped
public class mbpais implements Serializable{
    Session session;
    Transaction transaction;
    private Ctgpaisproveedor ctgpais;

    public Ctgpaisproveedor getCtgpais() {
        return ctgpais;
    }

    public void setCtgpais(Ctgpaisproveedor ctgpais) {
        this.ctgpais = ctgpais;
    }
    

    /**
     * Creates a new instance of mbpais
     */
    public mbpais() {
    }
    
    @Inject
    private Ctgpaisproveedor pais;
    public void agregar(){
    this.session=null;
    this.transaction=null;
    
        try {
            Ctgpaisproveedor cp=new Ctgpaisproveedor();
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=session.beginTransaction();
            cp.setNombre(pais.getNombre());
            cp.setCodigo(pais.getCodigo());
            session.save( cp);
            this.transaction.commit();
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais", "agregado"));
        } catch (Exception e) {
            this.transaction.rollback();
        }finally{
        session.close();
        }
    }
    
    public void  actualizar(){
    this.session=null;
    this.transaction=null;
        try {
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=session.beginTransaction();
            this.session.update(this.ctgpais);
            this.transaction.commit();

        } catch (Exception e) {
            this.transaction.rollback();
        }finally{
        this.session.close();
        }
    
    }
}
