package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItensListas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemLista;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDITEM")
    private Itens itens;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDLISTA")
    private Listas listas;

    private Double qtd;
    private String visivel;

    public Long getIdItemLista() {
        return idItemLista;
    }

    public void setIdItemLista(Long idItemLista) {
        this.idItemLista = idItemLista;
    }

    public Itens getItens() {
        return itens;
    }

    public void setItens(Itens itens) {
        this.itens = itens;
    }

    public Listas getListas() {
        return listas;
    }

    public void setListas(Listas listas) {
        this.listas = listas;
    }

    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }

    public String getVisivel() {
        return visivel;
    }

    public void setVisivel(String visivel) {
        this.visivel = visivel;
    }

    @Override
    public String toString() {
        return "ItensListas{idItemLista=" + idItemLista + ", idLista=" + listas.getIdLista() + ", descricaoLista=" + listas.getDescricaoLista()
                + ", idItens=" + itens.getIdItem() + ", descricao=" + itens.getDescricao() + ", unid=" + itens.getUnid()
                + ", valor=" + itens.getValor() + ", qtd=" + qtd + ", vlrTotal=" + qtd * itens.getValor() + ", visivel=" + visivel + "}\n";
    }
}
