/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Potato Power
 */

@Entity
public class Tarea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id_tarea;
    
    @Getter @Setter
    private String titulo;
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private String estado;    
    @Getter @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created_at = new Date();
    @Getter @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updated_at = new Date();
    
    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_persona", nullable = false, referencedColumnName = "id_persona")
    private Persona persona;
    
    @Getter @Setter
    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();
    
    @JsonbTransient
    public Persona getPersona() {
        return persona;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_tarea != null ? id_tarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarea)) {
            return false;
        }
        Tarea other = (Tarea) object;
        if ((this.id_tarea == null && other.id_tarea != null) || (this.id_tarea != null && !this.id_tarea.equals(other.id_tarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.models.Tarea[ id=" + id_tarea + " ]";
    }
    
}
