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
@Table(name = "Relatorio")
@NamedQueries({
    @NamedQuery(name = "Relatorio.findAll", query = "SELECT r FROM Relatorio r"),
    @NamedQuery(name = "Relatorio.findByIdImovel", query = "SELECT r FROM Relatorio r WHERE r.idImovel = :idImovel"),
    @NamedQuery(name = "Relatorio.findByIdLocatario", query = "SELECT r FROM Relatorio r WHERE r.idLocatario = :idLocatario"),
    @NamedQuery(name = "Relatorio.findByDataTermino", query = "SELECT r FROM Relatorio r WHERE r.dataTermino = :dataTermino"),
    @NamedQuery(name = "Relatorio.findByValorLocacao", query = "SELECT r FROM Relatorio r WHERE r.valorLocacao = :valorLocacao")})
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
    @Column(name = "dataTermino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    @Basic(optional = false)
    @Column(name = "valorLocacao")
    private double valorLocacao;

    public Relatorio() {
    }
    
    public Relatorio(Integer idLocatario, Integer idImovel, Date dataTermino, double valorLocacao, Integer idRelatorio) {
        this.idLocatario = idLocatario;
        this.idImovel = idImovel;
        this.dataTermino = dataTermino;
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

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
    
}
