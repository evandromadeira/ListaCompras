package model;

import java.util.List;
import javax.persistence.EntityManager;

public class ItensRepository {

    private EntityManager em;

    public ItensRepository(EntityManager em) {
        this.em = em;
    }

    public void adicionaItem(Itens itens) {
        if (itens.getIdItem() == null) {
            em.persist(itens);
        } else {
            em.merge(itens);
        }
    }

    public void removeItem(Itens itens) {
        itens = em.find(Itens.class, itens.getIdItem());
        em.remove(itens);
    }

    public Itens consultaItemPorId(Long idItem) {
        return em.find(Itens.class, idItem);
    }

    public List<Itens> consultaTodos() {
        return em.createQuery("FROM Itens i", Itens.class).getResultList();
    }
}
