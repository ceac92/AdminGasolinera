/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entity.Bodega;
import entity.Cliente;
import entity.Ctgdepartamento;
import entity.Ctgformapago;
import entity.Ctgmunicipio;
import entity.Ctgpaisproveedor;
import entity.Ctgtipocliente;
import entity.Ctgtipodocumento;
import entity.Ctgtipofactura;
import entity.Ctgtipoproducto;
import entity.Detallecaja;
import entity.Producto;
import entity.Proveedor;
import entity.Rol;
import hibernateutil.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.Session;

/**
 *
 * @author An
 */
@Named("combos")
@ViewScoped
public class combos implements Serializable {

    private List<SelectItem> itemproveedor;
    private List<SelectItem> itemrol;
    private List<SelectItem> itemCliente;
    private List<SelectItem> itemdetallecaja;
    private List<SelectItem> itemdepartamento;
    private List<SelectItem> itemformapago;
    private List<SelectItem> itemmunicipio;
    private List<SelectItem> itempaisproveedor;
    private List<SelectItem> itemtipocliente;
    private List<SelectItem> itemtipodocumento;
    private List<SelectItem> itemfactuta;
    private List<SelectItem> itemtipoproducto;
    private List<SelectItem> itembodega;
     private List<SelectItem> itemproducto;
    private List<Rol> roles;
    private List<Ctgdepartamento> departamentos;
    private List<Cliente> cliente;
    private List<Detallecaja> detallecaja;
    private List<Ctgformapago> formapagos;
    private List<Ctgmunicipio> municipio;
    private List<Ctgpaisproveedor> paisproveedors;
    private List<Ctgtipocliente> tipoclientes;
    private List<Ctgtipodocumento> tipodocumento;
    private List<Ctgtipofactura> tipofactura;
    private List<Ctgtipoproducto> tipoproductos;
    private List<Bodega> bodegas;
    private List<Proveedor> proveedor;
     private List<Producto> produc; 
    private int valor;
    private int valorempleado;

    public List<SelectItem> getItemrol() {
        return itemrol;
    }

    public void setItemrol(List<SelectItem> itemrol) {
        this.itemrol = itemrol;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Creates a new instance of combos
     */
    public combos() {

        this.itemdepartamento = new ArrayList<>();
        this.itemfactuta = new ArrayList<>();
        this.itemformapago = new ArrayList<>();
        this.itemmunicipio = new ArrayList<>();
        this.itempaisproveedor = new ArrayList<>();
        this.itemtipocliente = new ArrayList<>();
        this.itemtipodocumento = new ArrayList<>();
        this.itemtipoproducto = new ArrayList<>();
        this.itembodega = new ArrayList<>();
        this.itemCliente = new ArrayList<>();
        this.itemdetallecaja = new ArrayList<>();
        this.itemproveedor = new ArrayList<>();
        this.itemrol = new ArrayList<>();
        this.itemproducto= new ArrayList<>();
        this.bodegas = new ArrayList<>();
        this.departamentos = new ArrayList<>();
        this.formapagos = new ArrayList<>();
        this.municipio = new ArrayList<>();
        this.paisproveedors = new ArrayList<>();
        this.tipoclientes = new ArrayList<>();
        this.tipodocumento = new ArrayList<>();
        this.tipofactura = new ArrayList<>();
        this.tipoproductos = new ArrayList<>();
        this.proveedor = new ArrayList<>();
        this.cliente = new ArrayList<>();
        this.detallecaja = new ArrayList<>();
        this.roles = new ArrayList<>();
        this.produc= new ArrayList<>();
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }

    public List<Detallecaja> getDetallecaja() {
        return detallecaja;
    }

    public void setDetallecaja(List<Detallecaja> detallecaja) {
        this.detallecaja = detallecaja;
    }

    public List<SelectItem> getItemproveedor() {
        return itemproveedor;
    }

    public void setItemproveedor(List<SelectItem> itemproveedor) {
        this.itemproveedor = itemproveedor;
    }

    public List<Proveedor> getProveedor() {
        return proveedor;
    }

    public void setProveedor(List<Proveedor> proveedor) {
        this.proveedor = proveedor;
    }

    public void valorempleado(int idempleado) {
        if (idempleado > 0) {
            this.valorempleado = idempleado;
        }
    }
 public List<SelectItem> getPRODUCT() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = " from Producto ";
            this.produc = session.createQuery(hql).list();
            for (Producto item : this.produc) {
                SelectItem selectItem = new SelectItem(item.getIdproducto(),item.getNombre());
                this.itemproducto.add(selectItem);
            }
            return this.itemproducto;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }
    public List<SelectItem> getCLIENT() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = " from Cliente ";
            this.cliente = session.createQuery(hql).list();
            for (Cliente item : this.cliente) {
                SelectItem selectItem = new SelectItem(item.getIdcliente(), item.getPrimerNombre().concat(" ").concat(item.getSegundoNombre()).concat(" ").concat(item.getPrimerApellido()).concat(" ").concat(item.getSegundoApellido()));
                this.itemCliente.add(selectItem);
            }
            return this.itemCliente;
        } catch (Exception ex) {
            System.err.print(ex);
        } 

        return null;
    }

    public List<SelectItem> getRoll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = " from Rol";
            this.roles = session.createQuery(hql).list();
            for (Rol item : this.roles) {
                SelectItem selectItem = new SelectItem(item.getIdrol(), item.getNombre());
                this.itemrol.add(selectItem);
            }
            return this.itemrol;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }

    public List<SelectItem> getDETALLECAJ() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from Detallecaja as d where d.empleado.idempleado=" + this.valorempleado;
            this.detallecaja = session.createQuery(hql).list();
            for (Detallecaja item : this.detallecaja) {
                SelectItem selectItem = new SelectItem(item.getIddetalleCaja(), item.getTurno());
                this.itemdetallecaja.add(selectItem);
            }
            return this.itemdetallecaja;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }

    public List<SelectItem> getPROVEEDOR() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = " from Proveedor ";
            this.proveedor = session.createQuery(hql).list();
            for (Proveedor item : this.proveedor) {
                SelectItem selectItem = new SelectItem(item.getIdproveedor(), item.getNombre());
                this.itemproveedor.add(selectItem);
            }
            return this.itemproveedor;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }
        return null;
    }

    public List<SelectItem> getFORMAP() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = " from Ctgformapago ";
            this.formapagos = session.createQuery(hql).list();
            for (Ctgformapago item : this.formapagos) {
                SelectItem selectItem = new SelectItem(item.getIdctgFormaPago(), item.getNombre());
                this.itemformapago.add(selectItem);
            }
            return this.itemformapago;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }

    public List<SelectItem> getDEPA() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select p from Ctgdepartamento p";
            this.departamentos = session.createQuery(hql).list();
            for (Ctgdepartamento item : this.departamentos) {
                SelectItem selectItem = new SelectItem(item.getIdctgDepartamento(), item.getNombre());
                this.itemdepartamento.add(selectItem);
            }
            return this.itemdepartamento;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }

    public List<SelectItem> getMUNICIPIO() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select m from Ctgmunicipio m";
            this.municipio = session.createQuery(hql).list();
            for (Ctgmunicipio item : this.municipio) {
                SelectItem selectItem = new SelectItem(item.getIdctgMunicipio(), item.getNombre());
                this.itemmunicipio.add(selectItem);
            }
            return this.itemmunicipio;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }

    public List<SelectItem> getPAISPROVEEDOR() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select pl from Ctgpaisproveedor pl";
            this.paisproveedors = session.createQuery(hql).list();
            for (Ctgpaisproveedor item : this.paisproveedors) {
                SelectItem selectItem = new SelectItem(item.getIdctgPaisProveedor(), item.getNombre());
                this.itempaisproveedor.add(selectItem);
            }
            return this.itempaisproveedor;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }

    public List<SelectItem> getTTIPOCLIENTE() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select tp from Ctgtipocliente tp";
            this.tipoclientes = session.createQuery(hql).list();
            for (Ctgtipocliente item : this.tipoclientes) {
                SelectItem selectItem = new SelectItem(item.getIdctgTipoCliente(), item.getNombre());
                this.itemtipocliente.add(selectItem);
            }
            return this.itemtipocliente;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }

    public List<SelectItem> getTIPODOCUMENTO() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select pd from Ctgtipodocumento pd";
            this.tipodocumento = session.createQuery(hql).list();
            for (Ctgtipodocumento item : this.tipodocumento) {
                SelectItem selectItem = new SelectItem(item.getIdctgTipoDocumento(), item.getNombre());
                this.itemtipodocumento.add(selectItem);
            }
            return this.itemtipodocumento;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }

    public List<SelectItem> getTIPOFACTURA() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select tf from Ctgtipofactura tf";
            this.tipofactura = session.createQuery(hql).list();
            for (Ctgtipofactura item : this.tipofactura) {
                SelectItem selectItem = new SelectItem(item.getIdctgTipoFactura(), item.getNombre());
                this.itemfactuta.add(selectItem);
            }
            return this.itemfactuta;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }

    public List<SelectItem> getTIPOPRODUCTO() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select tp from Ctgtipoproducto tp";
            this.tipoproductos = session.createQuery(hql).list();
            for (Ctgtipoproducto item : this.tipoproductos) {
                SelectItem selectItem = new SelectItem(item.getIdctgTipoProducto(), item.getNombre());
                this.itemtipoproducto.add(selectItem);
            }
            return this.itemtipoproducto;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }

    public List<SelectItem> getBODEGA() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select b from Bodega b";
            this.bodegas = session.createQuery(hql).list();
            for (Bodega item : this.bodegas) {
                SelectItem selectItem = new SelectItem(item.getIdbodega(), item.getUbicacion());
                this.itembodega.add(selectItem);
            }
            return this.itembodega;
        } catch (Exception ex) {
            System.err.print(ex);
        } finally {
            session.close();
        }

        return null;
    }

    public List<SelectItem> getItemdepartamento() {
        return itemdepartamento;
    }

    public void setItemdepartamento(List<SelectItem> itemdepartamento) {
        this.itemdepartamento = itemdepartamento;
    }

    public List<SelectItem> getItemformapago() {
        return itemformapago;
    }

    public void setItemformapago(List<SelectItem> itemformapago) {
        this.itemformapago = itemformapago;
    }

    public List<SelectItem> getItemmunicipio() {
        return itemmunicipio;
    }

    public void setItemmunicipio(List<SelectItem> itemmunicipio) {
        this.itemmunicipio = itemmunicipio;
    }

    public List<SelectItem> getItempaisproveedor() {
        return itempaisproveedor;
    }

    public void setItempaisproveedor(List<SelectItem> itempaisproveedor) {
        this.itempaisproveedor = itempaisproveedor;
    }

    public List<SelectItem> getItemtipocliente() {
        return itemtipocliente;
    }

    public void setItemtipocliente(List<SelectItem> itemtipocliente) {
        this.itemtipocliente = itemtipocliente;
    }

    public List<SelectItem> getItemtipodocumento() {
        return itemtipodocumento;
    }

    public void setItemtipodocumento(List<SelectItem> itemtipodocumento) {
        this.itemtipodocumento = itemtipodocumento;
    }

    public List<SelectItem> getItemfactuta() {
        return itemfactuta;
    }

    public void setItemfactuta(List<SelectItem> itemfactuta) {
        this.itemfactuta = itemfactuta;
    }

    public List<SelectItem> getItemtipoproducto() {
        return itemtipoproducto;
    }

    public void setItemtipoproducto(List<SelectItem> itemtipoproducto) {
        this.itemtipoproducto = itemtipoproducto;
    }

    public List<SelectItem> getItembodega() {
        return itembodega;
    }

    public void setItembodega(List<SelectItem> itembodega) {
        this.itembodega = itembodega;
    }

    public List<Ctgdepartamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Ctgdepartamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<Ctgformapago> getFormapagos() {
        return formapagos;
    }

    public void setFormapagos(List<Ctgformapago> formapagos) {
        this.formapagos = formapagos;
    }

    public List<Ctgmunicipio> getMunicipio() {
        return municipio;
    }

    public void setMunicipio(List<Ctgmunicipio> municipio) {
        this.municipio = municipio;
    }

    public List<Ctgpaisproveedor> getPaisproveedors() {
        return paisproveedors;
    }

    public void setPaisproveedors(List<Ctgpaisproveedor> paisproveedors) {
        this.paisproveedors = paisproveedors;
    }

    public List<Ctgtipocliente> getTipoclientes() {
        return tipoclientes;
    }

    public void setTipoclientes(List<Ctgtipocliente> tipoclientes) {
        this.tipoclientes = tipoclientes;
    }

    public List<Ctgtipodocumento> getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(List<Ctgtipodocumento> tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public List<Ctgtipofactura> getTipofactura() {
        return tipofactura;
    }

    public void setTipofactura(List<Ctgtipofactura> tipofactura) {
        this.tipofactura = tipofactura;
    }

    public List<Ctgtipoproducto> getTipoproductos() {
        return tipoproductos;
    }

    public void setTipoproductos(List<Ctgtipoproducto> tipoproductos) {
        this.tipoproductos = tipoproductos;
    }

    public List<Bodega> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

}
