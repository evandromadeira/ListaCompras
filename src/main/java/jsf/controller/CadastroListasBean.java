package jsf.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import model.Listas;
import model.ListasRepository;

@ManagedBean
public class CadastroListasBean {

    private Listas listas = new Listas();

    @ManagedProperty("#{param.idLista}")
    private Long idLista;

    public Listas getLista() {
        return listas;
    }

    public void setLista(Listas lista) {
        this.listas = lista;
    }

    public Long getIdLista() {
        return idLista;
    }

    public void setIdLista(Long idLista) {
        this.idLista = idLista;
    }

    @PostConstruct
    public void init() {
        if (idLista != null) {
            EntityManager manager = getEntityManager();
            this.listas = new ListasRepository(manager).consultaListaPorId(idLista);
        }
    }

    public String grava() {
        EntityManager manager = getEntityManager();

        new ListasRepository(manager).adicionaLista(listas);

        return "listas?faces-redirect=true";
    }

    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }
}
