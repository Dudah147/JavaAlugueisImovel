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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author henri
 */
@Entity
@Table(name = "relatorio")
@NamedQueries({
    @NamedQuery(name = "Relatorio.findAll", query = "SELECT r FROM Relatorio r"),
    @NamedQuery(name = "Relatorio.findByIdImovel", query = "SELECT r FROM Relatorio r WHERE r.idImovel = :idImovel"),
    @NamedQuery(name = "Relatorio.findByIdLocatario", query = "SELECT r FROM Relatorio r WHERE r.idLocatario = :idLocatario"),
    @NamedQuery(name = "Relatorio.findByNome", query = "SELECT r FROM Relatorio r WHERE r.nome = :nome"),
    @NamedQuery(name = "Relatorio.findByDataTermino", query = "SELECT r FROM Relatorio r WHERE r.dataTermino = :dataTermino"),
    @NamedQuery(name = "Relatorio.findByValorLocacao", query = "SELECT r FROM Relatorio r WHERE r.valorLocacao = :valorLocacao"),
    @NamedQuery(name = "Relatorio.findByValorDesconto", query = "SELECT r FROM Relatorio r WHERE r.valorDesconto = :valorDesconto")})
public class Relatorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idImovel")
    @Id
    private int idImovel;
    @Basic(optional = false)
    @Column(name = "idLocatario")
    private int idLocatario;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "dataTermino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    @Basic(optional = false)
    @Column(name = "valorLocacao")
    private double valorLocacao;
    @Basic(optional = false)
    @Column(name = "valorDesconto")
    private double valorDesconto;

    public Relatorio() {
    }

    public Relatorio(int idImovel, int idLocatario, String nome, Date dataTermino, double valorLocacao, double valorDesconto) {
        this.idImovel = idImovel;
        this.idLocatario = idLocatario;
        this.nome = nome;
        this.dataTermino = dataTermino;
        this.valorDesconto = valorDesconto;
        this.valorLocacao = valorLocacao;
    }

    public int getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(int idImovel) {
        this.idImovel = idImovel;
    }

    public int getIdLocatario() {
        return idLocatario;
    }

    public void setIdLocatario(int idLocatario) {
        this.idLocatario = idLocatario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public double getValorLocacao() {
        return (valorLocacao -  this.valorDesconto);
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
    
}
