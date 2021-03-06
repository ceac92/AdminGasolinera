package entity;
// Generated 19-sep-2015 2:07:46 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Detallebombatc generated by hbm2java
 */
@Entity
@Table(name="detallebombatc"
    ,catalog="bdgasolinera"
)
public class Detallebombatc  implements java.io.Serializable {


     private Integer iddetallebombatc;
     private Bomba bomba;
     private Tipocombustible tipocombustible;
     private Set detalleventacs = new HashSet(0);

    public Detallebombatc() {
    }

	
    public Detallebombatc(Bomba bomba, Tipocombustible tipocombustible) {
        this.bomba = bomba;
        this.tipocombustible = tipocombustible;
    }
    public Detallebombatc(Bomba bomba, Tipocombustible tipocombustible, Set detalleventacs) {
       this.bomba = bomba;
       this.tipocombustible = tipocombustible;
       this.detalleventacs = detalleventacs;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="iddetallebombatc", unique=true, nullable=false)
    public Integer getIddetallebombatc() {
        return this.iddetallebombatc;
    }
    
    public void setIddetallebombatc(Integer iddetallebombatc) {
        this.iddetallebombatc = iddetallebombatc;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fkbomba", nullable=false)
    public Bomba getBomba() {
        return this.bomba;
    }
    
    public void setBomba(Bomba bomba) {
        this.bomba = bomba;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fktipocombustible", nullable=false)
    public Tipocombustible getTipocombustible() {
        return this.tipocombustible;
    }
    
    public void setTipocombustible(Tipocombustible tipocombustible) {
        this.tipocombustible = tipocombustible;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="detallebombatc")
    public Set getDetalleventacs() {
        return this.detalleventacs;
    }
    
    public void setDetalleventacs(Set detalleventacs) {
        this.detalleventacs = detalleventacs;
    }




}


