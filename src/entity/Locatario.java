/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dudam
 */
@Entity
@Table(name = "locatario")
@NamedQueries({
    @NamedQuery(name = "Locatario.findAll", query = "SELECT l FROM Locatario l"),
    @NamedQuery(name = "Locatario.findByIdLocatario", query = "SELECT l FROM Locatario l WHERE l.idLocatario = :idLocatario"),
    @NamedQuery(name = "Locatario.findByCpf", query = "SELECT l FROM Locatario l WHERE l.cpf = :cpf"),
    @NamedQuery(name = "Locatario.findByNome", query = "SELECT l FROM Locatario l WHERE l.nome = :nome"),
    @NamedQuery(name = "Locatario.findByEstadoCivil", query = "SELECT l FROM Locatario l WHERE l.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Locatario.findByDataNascimento", query = "SELECT l FROM Locatario l WHERE l.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "Locatario.findByContaBancaria", query = "SELECT l FROM Locatario l WHERE l.contaBancaria = :contaBancaria")})
public class Locatario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLocatario")
    private Integer idLocatario;
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "estadoCivil")
    private String estadoCivil;
    @Basic(optional = false)
    @Column(name = "dataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Basic(optional = false)
    @Column(name = "contaBancaria")
    private String contaBancaria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLocatario")
    private Collection<Locacao> locacaoCollection;

    public Locatario() {
    }

    public Locatario(Integer idLocatario) {
        this.idLocatario = idLocatario;
    }

    public Locatario(Integer idLocatario, String cpf, String nome, String estadoCivil, Date dataNascimento, String contaBancaria) {
        this.idLocatario = idLocatario;
        this.cpf = cpf;
        this.nome = nome;
        this.estadoCivil = estadoCivil;
        this.dataNascimento = dataNascimento;
        this.contaBancaria = contaBancaria;
    }

    public Integer getIdLocatario() {
        return idLocatario;
    }

    public void setIdLocatario(Integer idLocatario) {
        this.idLocatario = idLocatario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public Collection<Locacao> getLocacaoCollection() {
        return locacaoCollection;
    }

    public void setLocacaoCollection(Collection<Locacao> locacaoCollection) {
        this.locacaoCollection = locacaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocatario != null ? idLocatario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locatario)) {
            return false;
        }
        Locatario other = (Locatario) object;
        if ((this.idLocatario == null && other.idLocatario != null) || (this.idLocatario != null && !this.idLocatario.equals(other.idLocatario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Locatario[ idLocatario=" + idLocatario + " ]";
    }
    
}
