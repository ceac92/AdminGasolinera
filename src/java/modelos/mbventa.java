/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.detalleventadao;
import dao.productodao;
import entity.Cliente;
import entity.Ctgformapago;
import entity.Ctgtipofactura;
import entity.Detallecaja;
import entity.Detalleventa;
import entity.Empleado;
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
public class mbventa implements Serializable {

    Session session;
    Transaction transaction;
    private Producto producto;
    private List<Producto> listaproducto;
    private Venta venta;
    java.util.Date fech = new Date();
    private Date fecha = fech;
    private int cantidav=0;
    private BigDecimal montov;
    private BigDecimal descuentov=BigDecimal.ZERO;
    private BigDecimal subtotal;
    private int detacaja;
    private int client;
    private int formapago;
    private int tipofactura=1;

    public int getDetacaja() {
        return detacaja;
    }

    public void setDetacaja(int detacaja) {
        this.detacaja = detacaja;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getFormapago() {
        return formapago;
    }

    public void setFormapago(int formapago) {
        this.formapago = formapago;
    }

    public int getTipofactura() {
        return tipofactura;
    }

    public void setTipofactura(int tipofactura) {
        this.tipofactura = tipofactura;
    }

    public int getCantidav() {
        return cantidav;
    }

    public void setCantidav(int cantidav) {
        this.cantidav = cantidav;
    }

    public BigDecimal getMontov() {
        return montov;
    }

    public void setMontov(BigDecimal montov) {
        this.montov = montov;
    }

    public BigDecimal getDescuentov() {
        return descuentov;
    }

    public void setDescuentov(BigDecimal descuentov) {
        this.descuentov = descuentov;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
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
        this.producto = new Producto();
        this.detalleventa = new ArrayList<>();
        this.venta = new Venta();
        this.descuentov=new BigDecimal(0);
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

    public void agregarListaVentaDetalle() {
        this.session = null;
        this.transaction = null;

        try {
            if (cantidav <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La Cantidad debe se mayor a cero"));
                RequestContext.getCurrentInstance().update("formventa:msgs");

            } else {
                boolean coparar = false;
                if (this.producto != null) {
                    for (Detalleventa item : this.detalleventa) {
                        if (item.getProducto().getIdproducto().equals(this.producto.getIdproducto())) {

                            coparar = true;
                            break;
                        }
                    }
                }
                if (this.getProducto().getCantidadExistencia()<this.cantidav) {
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cantidad es mayor a la existencia disponible"));
                    return;
                }
                 
                if (coparar == true) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "ya esta el producto seleccionado"));
                    RequestContext.getCurrentInstance().update("formventa:msgs");
                } else {
                    this.session = HibernateUtil.getSessionFactory().openSession();
                    this.transaction = this.session.beginTransaction();
                    BigDecimal cambiarcantidad = new BigDecimal(this.cantidav);
                    this.subtotal = this.producto.getPrecioVenta().multiply(cambiarcantidad);
                    BigDecimal valordescuento = cambiarcantidad.multiply(producto.getPrecioVenta().multiply(descuentov));

                    this.detalleventa.add(new Detalleventa(producto, null, this.cantidav, this.producto.getPrecioVenta(), this.descuentov, this.subtotal.subtract(valordescuento)));

                    this.transaction.commit();

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "se agrego"));
                    RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
                    RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");

                }
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
            for (Detalleventa item : this.detalleventa) {
                if (item.getProducto().getIdproducto().equals(codigoBarras)) {
                    this.detalleventa.remove(i);
                    break;
                }
                i++;
            }
            BigDecimal totalVenta = new BigDecimal("0");
            for (Detalleventa item : this.detalleventa) {
                BigDecimal valorvalor = item.getProducto().getPrecioVenta().multiply(new BigDecimal(item.getCantidad()));
                BigDecimal totalVentaPorProducto = item.getProducto().getPrecioVenta().multiply(new BigDecimal(item.getCantidad()).multiply(item.getDescuento()));
                BigDecimal valorcambio = valorvalor.subtract(totalVentaPorProducto);
                item.setSubtotal(valorcambio);
                totalVenta = totalVenta.add(valorcambio);

            }
            this.venta.setMoto(totalVenta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Producto retirado de la lista de venta"));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    public void calcularCostos() {
        try {
            BigDecimal totalVenta = new BigDecimal("0");

            for (Detalleventa item : this.detalleventa) {
                BigDecimal valorvalor = item.getProducto().getPrecioVenta().multiply(new BigDecimal(item.getCantidad()));

                BigDecimal totalVentaPorProducto = item.getProducto().getPrecioVenta().multiply(new BigDecimal(item.getCantidad()).multiply(item.getDescuento()));
                BigDecimal valorcambio = valorvalor.subtract(totalVentaPorProducto);
                item.setSubtotal(valorcambio);
                totalVenta = totalVenta.add(valorcambio);

            }

            this.venta.setMoto(totalVenta);

            RequestContext.getCurrentInstance().update("formventa:panelFinalVenta");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    public void realizarVenta() {
        session = null;
        transaction = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            detalleventadao detallec = new detalleventadao();
            productodao pdao = new productodao();
            Venta venp = new Venta();
            venp.setCliente((Cliente) session.get(Cliente.class, this.client));
            venp.setCtgformapago((Ctgformapago) session.get(Ctgformapago.class, this.formapago));
            venp.setCtgtipofactura((Ctgtipofactura) session.get(Ctgtipofactura.class, this.tipofactura));
            venp.setDetallecaja((Detallecaja) session.get(Detallecaja.class, this.detacaja));
            venp.setEmpleado((Empleado) session.get(Empleado.class, 1));
            venp.setFecha(this.fecha);
            venp.setMoto(this.venta.getMoto());

            session.save(venp);

            for (Detalleventa dcompra : this.detalleventa) {
                this.producto = pdao.getByIdProducto(session, dcompra.getProducto().getIdproducto());
                dcompra.setVenta(venp);
                dcompra.setProducto(producto);
                detallec.insert(session, dcompra);

            }
            transaction.commit();
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Venta realizada correctamente."));
            this.detalleventa = new ArrayList<>();
            this.venta = new Venta();
            this.client=0;
            this.descuentov=null;
            this.detacaja=0;
            this.formapago=0;
            this.montov=null;
            this.producto=new Producto();
            this.subtotal=null;
            this.venta=new Venta();
             FacesContext.getCurrentInstance().getExternalContext().redirect("venta.xhtml");

           
        } catch (Exception e) {
            transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problemas a guardar."));
            System.err.println(e.getMessage());
        } finally {
            session.close();
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
