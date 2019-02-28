/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Potato Power
 */
@Entity
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id_persona;
    @Getter @Setter
    @Column(length = 15,unique = true)
    private String dni;
    @Getter @Setter
    @Column(length = 60)
    private String nombres;
    @Getter @Setter
    @Column(length = 60)
    private String apellidos;
    @Getter @Setter
    @Column(length = 60)
    private String pais;
    @Getter @Setter
    @Column(length = 60)
    private String ciudad;
    @Getter @Setter
    @Column(length = 9)
    private String sexo;
    @Getter @Setter
    private String telefono;
    
    @Getter @Setter
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tarea> tareas = new ArrayList<>();

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_persona != null ? id_persona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id_persona == null && other.id_persona != null) || (this.id_persona != null && !this.id_persona.equals(other.id_persona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.models.Persona[ id=" + id_persona + " ]";
    }
    
}
