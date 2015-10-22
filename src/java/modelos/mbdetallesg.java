/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entity.Compra;
import entity.Ctgtipoproducto;
import entity.Detallecompra;
import entity.Detalleventa;
import entity.Proveedor;
import hibernateutil.HibernateUtil;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author An
 */
@ManagedBean
@SessionScoped
public class mbdetallesg implements Serializable {

    Session session;

    public int getIddetallecompra() {
        return iddetallecompra;
    }

    public void setIddetallecompra(int iddetallecompra) {
        this.iddetallecompra = iddetallecompra;
    }
    private int iddetallecompra;
    private int idetalleventa;
    private int idtipoproducto;
    private int idproveedor;
    private int iddetalleproveedor;

    public int getIddetalleproveedor() {
        return iddetalleproveedor;
    }

    public void setIddetalleproveedor(int iddetalleproveedor) {
        this.iddetalleproveedor = iddetalleproveedor;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public int getIdtipoproducto() {
        return idtipoproducto;
    }

    public void setIdtipoproducto(int idtipoproducto) {
        this.idtipoproducto = idtipoproducto;
    }

    public int getIdetalleventa() {
        return idetalleventa;
    }

    public void setIdetalleventa(int idetalleventa) {
        this.idetalleventa = idetalleventa;
    }

    /**
     * Creates a new instance of mbdetallesg
     */
    public mbdetallesg() {
    }

    public void verDetalles(ActionEvent miActionEvent, int iddetacompra) throws IOException {
        if (iddetacompra != 0) {
            this.iddetallecompra = iddetacompra;
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrador/detallecompra.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo procesar la peticion"));
        }
    }

    public List<Detallecompra> detallecompr(int valor) {
        session = null;
        Query query = null;
        try {
            valor = this.iddetallecompra;
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select dc.cantidad, p.precioCosto,p.nombre from Detallecompra as dc inner join dc.producto as p inner join dc.compra as c where c.idcompra=:iddetalle";
            query = session.createQuery(hql);
            query.setParameter("iddetalle", valor);
            return query.list();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    public void verDetallesventa(ActionEvent miActionEvent, int iddetaventa) throws IOException {
        if (iddetaventa != 0) {
            this.idetalleventa = iddetaventa;
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrador/detalleventa.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo procesar la peticion"));
        }
    }

    public List<Detalleventa> detalleventa(int valores) {
        session = null;
        Query query = null;
        try {
            valores = this.idetalleventa;
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select p.codigo, p.nombre, p.precioVenta, dv.cantidad, dv.monto, dv.descuento, dv.subtotal from Detalleventa as dv inner join dv.venta as v inner join dv.producto as p where v.idventa=:iddetalleventa";
            query = session.createQuery(hql);
            query.setParameter("iddetalleventa", valores);
            return query.list();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    public void vercataloproducto(ActionEvent miActionEvent, int idcataloproducto) throws IOException {
        if (idcataloproducto != 0) {
            this.idtipoproducto = idcataloproducto;
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrador/detalletipoproducto.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo procesar la peticion"));
        }
    }

    public List<Ctgtipoproducto> getAlltipoproductoc() {
        session = null;
        Query query = null;
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select p.codigo,p.bodega.codigo, p.nombre, p.cantidadExistencia, p.cantidadMinima, p. precioVenta, p.precioCosto from Producto as p where p.ctgtipoproducto.idctgTipoProducto=:idctgtipop";
            query = session.createQuery(hql);
            query.setParameter("idctgtipop",  this.idtipoproducto);
            return query.list();
        } catch (Exception e) {
        }
        return null;
    }
     public  List<Proveedor> getAllproveedor(){
        session=null;
        Query query=null;
        
        try {
            session=HibernateUtil.getSessionFactory().openSession();
            String hql="from Proveedor p where p.idproveedor=:valor";
            query=session.createQuery(hql);
            query.setParameter("valor", this.idproveedor);
            return query.list();
        } catch (Exception e) {
        }finally{
         session.close();
        }
    return null;
    }
     
    public void verDetallesproveedor(ActionEvent miActionEvent, int iddetaproveedor) throws IOException {
        if ( iddetaproveedor != 0) {
            this.iddetalleproveedor =  iddetaproveedor;
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrador/detalleproveedor.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo procesar la peticion"));
        }
    }
    
    public List<Compra> getAllvercompraproveedor(int valorproveedor){
        session=null;
        Query query=null;
       valorproveedor=this.iddetalleproveedor;
        try {
            session=HibernateUtil.getSessionFactory().openSession();
            String hql="select dc.cantidad, p.precioCosto,p.nombre, c.fecha from Detallecompra as dc inner join dc.producto as p inner join dc.compra as c where c.proveedor.idproveedor=valorp";
            query=session.createQuery(hql);
            query.setParameter("valorp", valorproveedor);
            return query.list();
        } catch (Exception e) {
        }finally{
        session.close();
        }
    return null;
    }
}
