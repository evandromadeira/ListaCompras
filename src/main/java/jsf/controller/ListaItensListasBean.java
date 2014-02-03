package jsf.controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import model.ItensListas;
import model.ItensListasRepository;

@ManagedBean
public class ListaItensListasBean {

    public List<ItensListas> getListaItensListas(Long idLista) {
        EntityManager entityManager = getEntityManager();

        return new ItensListasRepository(entityManager).consultaItensListas(idLista);
    }

    public void setItensListasInvisivel(ItensListas itensListas) {
        EntityManager entityManager = getEntityManager();

        new ItensListasRepository(entityManager).alteraItensListasOculto(itensListas);
    }

    public void removeItensListas(ItensListas itensListas) {
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
