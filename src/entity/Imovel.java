/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author dudam
 */
@Entity
@Table(name = "imovel")
@NamedQueries({
    @NamedQuery(name = "Imovel.findAll", query = "SELECT i FROM Imovel i"),
    @NamedQuery(name = "Imovel.findByIdImovel", query = "SELECT i FROM Imovel i WHERE i.idImovel = :idImovel"),
    @NamedQuery(name = "Imovel.findByEndereco", query = "SELECT i FROM Imovel i WHERE i.endereco = :endereco"),
    @NamedQuery(name = "Imovel.findByMetragem", query = "SELECT i FROM Imovel i WHERE i.metragem = :metragem"),
    @NamedQuery(name = "Imovel.findByQuantQuartos", query = "SELECT i FROM Imovel i WHERE i.quantQuartos = :quantQuartos"),
    @NamedQuery(name = "Imovel.findByQuantBanheiros", query = "SELECT i FROM Imovel i WHERE i.quantBanheiros = :quantBanheiros"),
    @NamedQuery(name = "Imovel.findByDescricaoDependencias", query = "SELECT i FROM Imovel i WHERE i.descricaoDependencias = :descricaoDependencias"),
    @NamedQuery(name = "Imovel.findByValorLocacao", query = "SELECT i FROM Imovel i WHERE i.valorLocacao = :valorLocacao"),
    @NamedQuery(name = "Imovel.findByFotoImovel", query = "SELECT i FROM Imovel i WHERE i.fotoImovel = :fotoImovel"),
    @NamedQuery(name = "Imovel.findByAlocado", query = "SELECT i FROM Imovel i WHERE i.alocado = :alocado")})
public class Imovel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idImovel")
    private Integer idImovel;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    @Basic(optional = false)
    @Column(name = "metragem")
    private double metragem;
    @Basic(optional = false)
    @Column(name = "quantQuartos")
    private int quantQuartos;
    @Basic(optional = false)
    @Column(name = "quantBanheiros")
    private int quantBanheiros;
    @Basic(optional = false)
    @Column(name = "descricaoDependencias")
    private String descricaoDependencias;
    @Basic(optional = false)
    @Column(name = "valorLocacao")
    private double valorLocacao;
    @Basic(optional = false)
    @Column(name = "fotoImovel")
    private String fotoImovel;
    @Column(name = "alocado")
    private Boolean alocado;
    @JoinColumn(name = "idTipoImovel", referencedColumnName = "idTipoImovel")
    @ManyToOne(optional = false)
    private Tipoimovel idTipoImovel;

    public Imovel() {
    }

    public Imovel(Integer idImovel) {
        this.idImovel = idImovel;
    }

    public Imovel(Integer idImovel, String endereco, double metragem, int quantQuartos, int quantBanheiros, String descricaoDependencias, double valorLocacao, String fotoImovel) {
        this.idImovel = idImovel;
        this.endereco = endereco;
        this.metragem = metragem;
        this.quantQuartos = quantQuartos;
        this.quantBanheiros = quantBanheiros;
        this.descricaoDependencias = descricaoDependencias;
        this.valorLocacao = valorLocacao;
        this.fotoImovel = fotoImovel;
    }

    public Integer getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Integer idImovel) {
        this.idImovel = idImovel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getMetragem() {
        return metragem;
    }

    public void setMetragem(double metragem) {
        this.metragem = metragem;
    }

    public int getQuantQuartos() {
        return quantQuartos;
    }

    public void setQuantQuartos(int quantQuartos) {
        this.quantQuartos = quantQuartos;
    }

    public int getQuantBanheiros() {
        return quantBanheiros;
    }

    public void setQuantBanheiros(int quantBanheiros) {
        this.quantBanheiros = quantBanheiros;
    }

    public String getDescricaoDependencias() {
        return descricaoDependencias;
    }

    public void setDescricaoDependencias(String descricaoDependencias) {
        this.descricaoDependencias = descricaoDependencias;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public String getFotoImovel() {
        return fotoImovel;
    }

    public void setFotoImovel(String fotoImovel) {
        this.fotoImovel = fotoImovel;
    }

    public Boolean getAlocado() {
        return alocado;
    }

    public void setAlocado(Boolean alocado) {
        this.alocado = alocado;
    }

    public Tipoimovel getIdTipoImovel() {
        return idTipoImovel;
    }

    public void setIdTipoImovel(Tipoimovel idTipoImovel) {
        this.idTipoImovel = idTipoImovel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImovel != null ? idImovel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imovel)) {
            return false;
        }
        Imovel other = (Imovel) object;
        if ((this.idImovel == null && other.idImovel != null) || (this.idImovel != null && !this.idImovel.equals(other.idImovel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Imovel[ idImovel=" + idImovel + " ]";
    }
    
}
