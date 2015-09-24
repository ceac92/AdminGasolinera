/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;



import dao.productodao;
import entity.Detalleventa;
import entity.Producto;
import entity.Venta;
import hibernateutil.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author An
 */
@Named("mbventa")
@ViewScoped
public class mbventa implements  Serializable{
    Session session;
    Transaction transaction;
    private Producto producto;
    private List<Producto> listaproducto;
    private Venta venta;
    java.util.Date fech=new Date();
    private Date fecha=fech;
    private List<Detalleventa> detalleventa;

    public List<Detalleventa> getDetalleventa() {
        return detalleventa;
    }

    public void setDetalleventa(List<Detalleventa> detalleventa) {
        this.detalleventa = detalleventa;
    }

   
    /**
     * Creates a new instance of mbventa
     */
    public mbventa() {
        this.producto=new Producto();
       this.detalleventa=new ArrayList<>();
        this.venta=new Venta();
    }
    
     public List<Producto> getAllProducto() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            productodao daoTProducto = new productodao();
            this.transaction = this.session.beginTransaction();
            this.listaproducto = daoTProducto.getAll(this.session);
            this.transaction.commit();
            return this.listaproducto;
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
    
        public void agregarListaVentaDetalle(int idProducto) {
        this.session = null;
        this.transaction = null;

        try {
            boolean item_duplicado = false;
            if (this.producto!=null) {
                 for (Detalleventa item : this.detalleventa) {
                if (item.getProducto().getIdproducto().equals(idProducto)) {

                    item_duplicado = true;
                    break;
                }
            }
            }
           
            if (item_duplicado == true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", "ya esta el producto seleccionado"));
                RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
            } else {
                this.session = HibernateUtil.getSessionFactory().openSession();
                productodao daoTProducto = new productodao();
                this.transaction = this.session.beginTransaction();
              

                this.detalleventa.add(new Detalleventa(producto, venta, idProducto, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

                this.transaction.commit();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "se agrego"));
                RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
                RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");

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

    
    
    
    
    
    
    
     public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListaproducto() {
        return listaproducto;
    }

    public void setListaproducto(List<Producto> listaproducto) {
        this.listaproducto = listaproducto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    

}
