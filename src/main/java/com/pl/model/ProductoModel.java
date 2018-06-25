package com.pl.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
        name = "producto"
)
public class ProductoModel {
    /*DEBE TENER UN FLAG PARA VER QUE
    ES UN BIEN FISCALIZADO*/
    @Transient
    private Integer rows;
    @Transient
    private Integer resume;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String descripcion;
    private Boolean fiscalizado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idformula")
    private FormulaModel formula;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto", cascade = CascadeType.ALL)
    private Collection<PedidoModel> productoPedidos = new ArrayList<PedidoModel>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "producto")

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getResume() {
        return resume;
    }

    public void setResume(Integer resume) {
        this.resume = resume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getFiscalizado() {
        return fiscalizado;
    }

    public void setFiscalizado(Boolean fiscalizado) {
        this.fiscalizado = fiscalizado;
    }

    public FormulaModel getFormula() {
        return formula;
    }

    public void setFormula(FormulaModel formula) {
        this.formula = formula;
    }

    @JsonIgnore
    public Collection<PedidoModel> getProductoPedidos() {
        return productoPedidos;
    }

    public void setProductoPedidos(Collection<PedidoModel> productoPedidos) {
        this.productoPedidos = productoPedidos;
    }

}
