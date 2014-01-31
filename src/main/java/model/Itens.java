package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Itens implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;
    @Column(unique = true)
    private String descricao;
    private double valor;
    private String unid;
    @Lob
    private byte[] imagemItem;

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public byte[] getImagemItem() {
        return imagemItem;
    }

    public void setImagemItem(byte[] imagemItem) {
        this.imagemItem = imagemItem;
    }

    private String toSTring() {
        return "itens{idItem=" + idItem + ", descricao=" + descricao + ", valor=" + valor + ", unid=" + unid + "}\n";
    }
}
