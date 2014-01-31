package jsf.controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import model.Itens;
import model.ItensRepository;

@ManagedBean
public class ListaItensBean {

    public List<Itens> getListaItens() {
        EntityManager entityManager = getEntityManager();

        return new ItensRepository(entityManager).consultaTodos();
    }

    public void removeItens(Itens itens) {
        EntityManager entityManager = getEntityManager();

        new ItensRepository(entityManager).removeItem(itens);
    }

    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }
}
