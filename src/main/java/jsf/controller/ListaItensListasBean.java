package jsf.controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import model.ItensListas;
import model.ItensListasRepository;

@ManagedBean
public class ListaItensListasBean {
    
    @ManagedProperty("#{param.idLista}")
    private Long idLista;
    @ManagedProperty("#{param.idItemLista}")
    private Long idItemLista;

    public Long getIdLista() {
        return idLista;
    }

    public void setIdLista(Long idLista) {
        this.idLista = idLista;
    }

    public Long getIdItemLista() {
        return idItemLista;
    }

    public void setIdItemLista(Long idItemLista) {
        this.idItemLista = idItemLista;
    }

    public List<ItensListas> getListaItensListas() {
        EntityManager entityManager = getEntityManager();

        return new ItensListasRepository(entityManager).consultaItensListas(idLista);
    }

    public void remove(ItensListas itensListas) {
        EntityManager entityManager = getEntityManager();

        new ItensListasRepository(entityManager).removeItensListas(itensListas);
    }

    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }
}
