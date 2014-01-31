package jsf.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import model.Itens;
import model.ItensListas;
import model.ItensListasRepository;
import model.ItensRepository;
import model.Listas;
import model.ListasRepository;

@ManagedBean
@ViewScoped
public class CadastroItensListasBean {

    private Itens itens = new Itens();
    private Listas listas = new Listas();
    private ItensListas itensListas = new ItensListas();

    private Long idItem;
    private Long idLista;
    private Long idItemLista;

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

    public ItensListas getItensListas() {
        return itensListas;
    }

    public void setItensListas(ItensListas itensListas) {
        this.itensListas = itensListas;
    }

    public Long getIdItem() {
        idItem = itensListas.getItens().getIdItem();
        return idItem;
    }

    public void setIdItem(Long idItem) {
        EntityManager manager = getEntityManager();
        this.itens = new ItensRepository(manager).consultaItemPorId(idItem);
        this.itensListas.setItens(itens);
    }

    public Long getIdLista() {
        return idLista;
    }

    public void setIdLista(Long idLista) {
        this.listas.setIdLista(idLista);
    }

    public Long getIdItemLista() {
        return idItemLista;
    }

    public void setIdItemLista(Long idItemLista) {
        this.idItemLista = idItemLista;
    }

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String idILParam = facesContext.getExternalContext().getRequestParameterMap().get("idItemLista");
        String idLParam = facesContext.getExternalContext().getRequestParameterMap().get("idLista");

        if (idILParam != null) {
            idItemLista = Long.valueOf(idILParam);
            EntityManager manager = getEntityManager();
            this.itensListas = new ItensListasRepository(manager).consultaItensListasPorId(idItemLista);
        } else {
            if (idLParam != null) {
                idLista = Long.valueOf(idLParam);
                EntityManager manager = getEntityManager();
                this.listas = new ListasRepository(manager).consultaListaPorId(idLista);
                this.itensListas.setListas(listas);
            }
        }
    }

    public String grava() {
        EntityManager manager = getEntityManager();

        new ItensListasRepository(manager).adicionaItensListas(itensListas);

        return "itens-lista?faces-redirect=true";
    }

    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }
}
