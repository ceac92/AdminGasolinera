/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.productodao;
import entity.Bodega;
import entity.Ctgtipoproducto;
import entity.Empleado;
import entity.Producto;
import hibernateutil.HibernateUtil;
import java.io.Serializable;
import java.util.List;
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
@Named("mbproducto")
@ViewScoped
public class mbproducto implements Serializable {      
    Session session;
    Transaction transaction;
    private int bodegav;
    private int tipoproductov;
    private Producto productou;
    private String estadov = "a";
    private List<Producto> productoD;

    public List<Producto> getProductoD() {
        return productoD;
    }

    public void setProductoD(List<Producto> productoD) {
        this.productoD = productoD;
    }

    public String getEstadov() {
        return estadov;
    }

    public void setEstadov(String estadov) {
        this.estadov = estadov;
    }

    public List<Producto> getProduct() {
        return Product;
    }

    public void setProduct(List<Producto> Product) {
        this.Product = Product;
    }
    private List<Producto> Product;

    public Producto getProductou() {
        return productou;
    }

    public void setProductou(Producto productou) {
        this.productou = productou;
    }

    public int getBodegav() {
        return bodegav;
    }

    public void setBodegav(int bodegav) {
        this.bodegav = bodegav;
    }

    public int getTipoproductov() {
        return tipoproductov;
    }

    public void setTipoproductov(int tipoproductov) {
        this.tipoproductov = tipoproductov;
    }

    /**
     * Creates a new instance of mbproducto
     */
    public mbproducto() {
        this.productou = new Producto();
    }
    @Inject
    private Producto producto;

    public void registrarProducto( int idempleado) {
        this.session = null;
        this.transaction = null;

        try {

            int valor = producto.getPrecioCosto().compareTo(producto.getPrecioVenta());
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            if (valor < 0) {
                if (producto.getCantidadExistencia() <= 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existencia", "Debe se mayor a cero"));
                    return;
                }
                if (producto.getCantidadMinima() <= 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cantidad minima", "Debe se mayor a cero"));
                    return;
                }
                Producto pr = new Producto();
                pr.setBodega((Bodega) session.get(Bodega.class, this.bodegav));
                pr.setCtgtipoproducto((Ctgtipoproducto) session.get(Ctgtipoproducto.class, this.tipoproductov));
                pr.setEmpleado((Empleado) session.get(Empleado.class,idempleado));
                pr.setCodigo("");
                pr.setNombre(producto.getNombre());
                pr.setDescripcion(producto.getDescripcion());
                pr.setCantidadExistencia(producto.getCantidadExistencia());
                pr.setCantidadMinima(producto.getCantidadMinima());
                pr.setEstado("a");
                pr.setPrecioVenta(producto.getPrecioVenta());
                pr.setPrecioCosto(producto.getPrecioCosto());
                session.save(pr);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto", "Registrado"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto", " Valor venta debe ser mayor al de compra"));
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto", "No registrado"));
        } finally {
            this.session.close();
        }
    }

    public void updateproducto() {
        this.session = null;
        this.transaction = null;

        try {
            if (productou.getCantidadExistencia() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existencia", "Debe se mayor a cero"));
                return;
            }
            if (productou.getCantidadMinima() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cantidad minima", "Debe se mayor a cero"));
                return;
            }
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            int valor2 = productou.getPrecioCosto().compareTo(productou.getPrecioVenta());
            if (valor2 < 0) {
                this.productou.setEstado(estadov);

               this.session.update(this.productou);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto", "Actualizado"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto", " Valor venta debe ser mayor al de compra"));
            }
            this.transaction.commit();
            this.productou = new Producto();
        } catch (Exception e) {
            transaction.rollback();
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto", e.getMessage()));
        } finally {
            this.session.close();

        }

    }

  

    public List<Producto> getAllproducto() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();

            productodao daop = new productodao();
            this.Product = daop.getAll(session);

            return this.Product;
        } catch (Exception e) {
        } finally {
            this.session.close();
        }
        return null;
    }

    public List<Producto> getAlldesactivado() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.productoD = productodao.getALLproductosd(session);

            return this.productoD;
        } catch (Exception e) {
           this.transaction.rollback();
        }finally{
         this.session.close();
        }
        return null;
    }
}
