/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dudam
 */
@Entity
@Table(name = "tipoimovel")
@NamedQueries({
    @NamedQuery(name = "Tipoimovel.findAll", query = "SELECT t FROM Tipoimovel t"),
    @NamedQuery(name = "Tipoimovel.findByIdTipoImovel", query = "SELECT t FROM Tipoimovel t WHERE t.idTipoImovel = :idTipoImovel"),
    @NamedQuery(name = "Tipoimovel.findByDescricao", query = "SELECT t FROM Tipoimovel t WHERE t.descricao = :descricao")})
public class Tipoimovel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoImovel")
    private Integer idTipoImovel;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "idTipoImovel")
    private Collection<Imovel> imovelCollection;

    public Tipoimovel() {
    }

    public Tipoimovel(Integer idTipoImovel) {
        this.idTipoImovel = idTipoImovel;
    }

    public Tipoimovel(Integer idTipoImovel, String descricao) {
        this.idTipoImovel = idTipoImovel;
        this.descricao = descricao;
    }

    public Integer getIdTipoImovel() {
        return idTipoImovel;
    }

    public void setIdTipoImovel(Integer idTipoImovel) {
        this.idTipoImovel = idTipoImovel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<Imovel> getImovelCollection() {
        return imovelCollection;
    }

    public void setImovelCollection(Collection<Imovel> imovelCollection) {
        this.imovelCollection = imovelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoImovel != null ? idTipoImovel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoimovel)) {
            return false;
        }
        Tipoimovel other = (Tipoimovel) object;
        if ((this.idTipoImovel == null && other.idTipoImovel != null) || (this.idTipoImovel != null && !this.idTipoImovel.equals(other.idTipoImovel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
    
}
