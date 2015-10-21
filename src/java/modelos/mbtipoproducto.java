/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dao.tipoproductodao;
import entity.Ctgtipoproducto;
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
@Named(value = "mbtipoproducto")
@ViewScoped
public class mbtipoproducto implements Serializable {
//se debe de crear implements Serializable en las managed bean para cargar

    Session session;
    Transaction transaction;
    private int valor;
    private Ctgtipoproducto tipopro;
    private Ctgtipoproducto listipoproducto;
    private List<Ctgtipoproducto> tiposproductos;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Ctgtipoproducto getTipopro() {
        return tipopro;
    }

    public void setTipopro(Ctgtipoproducto tipopro) {
        this.tipopro = tipopro;
    }

    public Ctgtipoproducto getListipoproducto() {
        return listipoproducto;
    }

    public void setListipoproducto(Ctgtipoproducto listipoproducto) {
        this.listipoproducto = listipoproducto;
    }

    public List<Ctgtipoproducto> getTiposproductos() {
        return tiposproductos;
    }

    public void setTiposproductos(List<Ctgtipoproducto> tiposproductos) {
        this.tiposproductos = tiposproductos;
    }

    /**
     * Creates a new instance of mbtipoproducto
     */
    public mbtipoproducto() {
        this.tipopro = new Ctgtipoproducto();
    }
    @Inject// esta es para poder inyectar los valores por get desde entidad a las metodo
    private Ctgtipoproducto tipoproducto;//esta variable no se a creado los set ni get solo se ocupa de pibote

    public void agregar() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Ctgtipoproducto cp = new Ctgtipoproducto();
        cp.setNombre(this.tipoproducto.getNombre());
        cp.setDescripcion(this.tipoproducto.getDescripcion());
        session.save(cp);
        transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "se agrego"));

        try {

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }

    }
//trae todos los valores de la tabla desde el dao revisar el dao para otras recomendaciones, 
    //El nombre del metodo debe llevar getALL segudio de cualquier nombre sino no se lo reconoce en la vista .

    public List<Ctgtipoproducto> getAlltipopro() {
        this.tiposproductos = tipoproductodao.alltiproductos();
        return this.tiposproductos;
    }

    // esta funcion lo que hacer redireccionar a otra vista, el valor que pasa es para ocuparla 
   /* public void redirecc(ActionEvent miActionEvent, int idtipoproducto) throws IOException {

        if (idtipoproducto != 0) {
            this.valor = idtipoproducto;
            this.tipopro = tipoproductodao.tipoproducto(this.valor);

            FacesContext.getCurrentInstance().getExternalContext().redirect("detalletipoproducto.xhtml");
        } else {
            System.err.println(idtipoproducto);
        }

    }*/
// la forma de actualizar aun no encuentro como hacerla.

    public void actualizar() {
        this.session = null;
        this.transaction = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            this.session.update(tipopro);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Actualizacion realizada"));
        } catch (Exception e) {
            this.transaction.rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } finally {
            this.session.close();
        }
    }

}
