package jsf.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import model.Itens;
import model.ItensListas;
import model.ItensListasRepository;
import model.Listas;
import model.ListasRepository;

@ManagedBean
public class CadastroItensListasBean {

    private ItensListas itensListas = new ItensListas();
    private Itens itens = new Itens();
    private Listas listas = new Listas();

    public ItensListas getItensListas() {
        return itensListas;
    }

    public void setItensListas(ItensListas itensListas) {
        this.itensListas = itensListas;
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

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String idILParam = facesContext.getExternalContext().getRequestParameterMap().get("idItemLista");
        String idLParam = facesContext.getExternalContext().getRequestParameterMap().get("idLista");

        if (idILParam != null) {
            EntityManager manager = getEntityManager();
            this.itensListas = new ItensListasRepository(manager).consultaItensListasPorId(Long.valueOf(idILParam));
        } else {
            if (idLParam != null) {
                EntityManager manager = getEntityManager();
                this.listas = new ListasRepository(manager).consultaListaPorId(Long.valueOf(idLParam));
            }
        }
    }

    public String grava() {
        if (itens.getIdItem() != null) {
            this.itensListas.setItens(itens);
        } else {
            throw new RuntimeException("O item não foi informado!");
        }

        if (listas.getIdLista() != null) {
            this.itensListas.setListas(listas);
        } else {
            throw new RuntimeException("A lista não foi informado!");
        }

        if (itensListas.getQtd() <= 0) {
            throw new RuntimeException("Quantidade inválida!");
        }

        if (itensListas.getVisivel() == null) {
            itensListas.setVisivel("S");
        }

        EntityManager manager = getEntityManager();

        new ItensListasRepository(manager).adicionaItensListas(itensListas);

        return "itens-lista?faces-redirect=true&idLista=" + itensListas.getListas().getIdLista();
    }

    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }
}
