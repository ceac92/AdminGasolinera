/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entity.Bodega;
import entity.Ctgtipoproducto;
import entity.Empleado;
import entity.Producto;
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
@Named("mbproducto")
@ViewScoped
public class mbproducto implements Serializable {

    Session session;
    Transaction transaction;
    private int bodegav;
    private int tipoproductov;

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
    }
    @Inject
    private Producto producto;

    public void registrarProducto() {
        this.session = null;
        this.transaction = null;

        try {
            int valor = producto.getPrecioCosto().compareTo(producto.getPrecioVenta());
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            if (valor < 0) {

                Producto pr = new Producto();
                pr.setBodega((Bodega) session.get(Bodega.class, this.bodegav));
                pr.setCtgtipoproducto((Ctgtipoproducto) session.get(Ctgtipoproducto.class, this.tipoproductov));
                pr.setEmpleado((Empleado) session.get(Empleado.class, 1));
                pr.setCodigo(producto.getCodigo());
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
}
