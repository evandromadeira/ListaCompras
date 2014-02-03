package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Listas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLista;
    @Column(unique = true)
    private String descricaoLista;
    @Transient
    private String dataLista;

    public Long getIdLista() {
        return idLista;
    }

    public void setIdLista(Long idLista) {
        this.idLista = idLista;
    }

    public String getDescricaoLista() {
        return descricaoLista;
    }

    public void setDescricaoLista(String descricaoLista) {
        this.descricaoLista = descricaoLista;
    }

    public String getDataLista() {
        return dataLista;
    }

    public void setDataLista(String dataLista) {
        this.dataLista = dataLista;
    }

    @Override
    public String toString() {
        return "listas{" + "idLista=" + idLista + ", descricaoLista=" + descricaoLista + "}\n";
    }
}
