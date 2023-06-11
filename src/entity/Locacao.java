/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dudam
 */
@Entity
@Table(name = "locacao")
@NamedQueries({
    @NamedQuery(name = "Locacao.findAll", query = "SELECT l FROM Locacao l"),
    @NamedQuery(name = "Locacao.findByIdLocacao", query = "SELECT l FROM Locacao l WHERE l.idLocacao = :idLocacao"),
    @NamedQuery(name = "Locacao.findByValorDesconto", query = "SELECT l FROM Locacao l WHERE l.valorDesconto = :valorDesconto"),
    @NamedQuery(name = "Locacao.findByDataInicio", query = "SELECT l FROM Locacao l WHERE l.dataInicio = :dataInicio"),
    @NamedQuery(name = "Locacao.findByDataTermino", query = "SELECT l FROM Locacao l WHERE l.dataTermino = :dataTermino"),
    @NamedQuery(name = "Locacao.findByEncerrado", query = "SELECT l FROM Locacao l WHERE l.encerrado = :encerrado"),
    @NamedQuery(name = "Locacao.findByFormaPgto", query = "SELECT l FROM Locacao l WHERE l.formaPgto = :formaPgto")})
public class Locacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLocacao")
    private Integer idLocacao;
    @Basic(optional = false)
    @Column(name = "valorDesconto")
    private double valorDesconto;
    @Basic(optional = false)
    @Column(name = "dataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Basic(optional = false)
    @Column(name = "dataTermino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    @Basic(optional = false)
    @Column(name = "encerrado")
    private boolean encerrado;
    @Basic(optional = false)
    @Column(name = "formaPgto")
    private int formaPgto;
    @JoinColumn(name = "idImovel", referencedColumnName = "idImovel")
    @ManyToOne(optional = false)
    private Imovel idImovel;
    @JoinColumn(name = "idLocatario", referencedColumnName = "idLocatario")
    @ManyToOne(optional = false)
    private Locatario idLocatario;

    public Locacao() {
    }

    public Locacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Locacao(Integer idLocacao, double valorDesconto, Date dataInicio, Date dataTermino, boolean encerrado, int formaPgto) {
        this.idLocacao = idLocacao;
        this.valorDesconto = valorDesconto;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.encerrado = encerrado;
        this.formaPgto = formaPgto;
    }

    public Integer getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public boolean getEncerrado() {
        return encerrado;
    }

    public void setEncerrado(boolean encerrado) {
        this.encerrado = encerrado;
    }

    public int getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(int formaPgto) {
        this.formaPgto = formaPgto;
    }

    public Imovel getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Imovel idImovel) {
        this.idImovel = idImovel;
    }

    public Locatario getIdLocatario() {
        return idLocatario;
    }

    public void setIdLocatario(Locatario idLocatario) {
        this.idLocatario = idLocatario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocacao != null ? idLocacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locacao)) {
            return false;
        }
        Locacao other = (Locacao) object;
        if ((this.idLocacao == null && other.idLocacao != null) || (this.idLocacao != null && !this.idLocacao.equals(other.idLocacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Locacao[ idLocacao=" + idLocacao + " ]";
    }
    
}
