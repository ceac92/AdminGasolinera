/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.detallecompradao;
import dao.productodao;
import entity.Compra;
import entity.Ctgformapago;
import entity.Detallecompra;
import entity.Empleado;
import entity.Producto;
import entity.Proveedor;
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
@Named(value = "mbcompra")
@ViewScoped
public class mbcompra implements Serializable {

    Session session;
    Transaction transaction;
    private Producto producto;
    private List<Producto> listaproducto;
    private Compra compra;
    private BigDecimal cantidad;
    private int comparar;

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
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

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public List<Detallecompra> getDetallecomp() {
        return detallecomp;
    }

    public void setDetallecomp(List<Detallecompra> detallecomp) {
        this.detallecomp = detallecomp;
    }
    private List<Detallecompra> detallecomp;

    /**
     * Creates a new instance of mbcompra
     */
    public mbcompra() {
        this.compra = new Compra();
        this.detallecomp = new ArrayList<>();
        this.producto = new Producto();

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
            for (Detallecompra item : this.detallecomp) {
                if (item.getProducto().getIdproducto().equals(idProducto)) {

                    item_duplicado = true;
                    break;
                }
            }
            if (item_duplicado==true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", "ya esta el producto seleccionado"));
                 RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
            } else {
                this.session = HibernateUtil.getSessionFactory().openSession();
                productodao daoTProducto = new productodao();
                this.transaction = this.session.beginTransaction();
                this.producto = daoTProducto.getByIdProducto(this.session, idProducto);
               
                 this.detallecomp.add(new Detallecompra(null, this.producto, this.cantidad));

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

    public void retirarListaVentaDetalle(int codigoBarras) {
        try {
            int i = 0;
            for (Detallecompra item : this.detallecomp) {
                if (item.getProducto().getIdproducto().equals(codigoBarras)) {
                    this.detallecomp.remove(i);
                    break;
                }
                i++;
            }
            BigDecimal totalVenta = new BigDecimal("0");
            for (Detallecompra item : this.detallecomp) {
                BigDecimal totalVentaPorProducto = item.getProducto().getPrecioVenta().multiply(item.getCantidad());
                totalVenta = totalVenta.add(totalVentaPorProducto);
            }
            this.compra.setValorcompra(totalVenta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Producto retirado de la lista de venta"));
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    public void calcularCostos() {
        try {
            BigDecimal totalVenta = new BigDecimal("0");
            for (Detallecompra item : this.detallecomp) {
                BigDecimal totalVentaPorProducto = item.getProducto().getPrecioVenta().multiply(item.getCantidad());
                totalVenta = totalVenta.add(totalVentaPorProducto);
            }
            this.compra.setValorcompra(totalVenta);
            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "verificar valores"));
        }
    }

    public void realizarVenta() {
        session = null;
        transaction = null;

  
        try {
            java.util.Date fecha = new Date();
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            detallecompradao detallec = new detallecompradao();
            productodao pdao = new productodao();
            Compra Cp = new Compra();
            Cp.setCtgformapago((Ctgformapago) session.get(Ctgformapago.class, 1));
            Cp.setEmpleado((Empleado) session.get(Empleado.class, 1));
            Cp.setFecha(fecha);
            Cp.setProveedor((Proveedor) session.get(Proveedor.class, 1));
            Cp.setValorcompra(this.compra.getValorcompra());
            session.save(Cp);

            for (Detallecompra dcompra : this.detallecomp) {
                this.producto = pdao.getByIdProducto(session, dcompra.getProducto().getIdproducto());
                dcompra.setCompra(Cp);
                dcompra.setProducto(producto);
                detallec.insert(session, dcompra);

            }
            transaction.commit();
            this.detallecomp = new ArrayList<>();
            this.compra = new Compra();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Venta realizada correctamente"));
        } catch (Exception e) {
            transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","Debe de agregar un valor a Cantidad"));
            System.err.println(e.getMessage());
        } finally {
            session.close();
        }

    }

}
