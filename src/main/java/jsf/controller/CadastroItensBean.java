package jsf.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import model.Itens;
import model.ItensRepository;

@ManagedBean
public class CadastroItensBean {

    private Itens itens = new Itens();

    @ManagedProperty("#{param.idItem}")
    private Long idItem;

    public Itens getItens() {
        return itens;
    }

    public void setItens(Itens itens) {
        this.itens = itens;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    @PostConstruct
    public void init() {
        if (idItem != null) {
            EntityManager manager = getEntityManager();
            this.itens = new ItensRepository(manager).consultaItemPorId(idItem);
        }
    }

    public String grava() {
        EntityManager manager = getEntityManager();

        new ItensRepository(manager).adicionaItem(itens);

        return "itens?faces-redirect=true";
    }

    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }
}
