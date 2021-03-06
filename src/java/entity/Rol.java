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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Rol generated by hbm2java
 */
@Entity
@Table(name="rol"
    ,catalog="bdgasolinera"
)
public class Rol  implements java.io.Serializable {


     private Integer idrol;
     private String nombre;
     private Set empleados = new HashSet(0);

    public Rol() {
    }

	
    public Rol(String nombre) {
        this.nombre = nombre;
    }
    public Rol(String nombre, Set empleados) {
       this.nombre = nombre;
       this.empleados = empleados;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idrol", unique=true, nullable=false)
    public Integer getIdrol() {
        return this.idrol;
    }
    
    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    
    @Column(name="nombre", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="rol")
    public Set getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Set empleados) {
        this.empleados = empleados;
    }




}


