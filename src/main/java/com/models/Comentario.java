/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.io.Serializable;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Potato Power
 */

@Entity
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id_comentario;
    
    @Getter @Setter
    private String contenido;
    @Getter @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date commented_at = new Date();
    
    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_tarea", nullable = false, referencedColumnName = "id_tarea")
    private Tarea tarea;
    
    @JsonbTransient
    public Tarea getTarea() {
        return tarea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_comentario != null ? id_comentario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.id_comentario == null && other.id_comentario != null) || (this.id_comentario != null && !this.id_comentario.equals(other.id_comentario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.models.Comentario[ id=" + id_comentario + " ]";
    }
    
}
