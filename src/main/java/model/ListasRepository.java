package model;

import java.util.List;
import javax.persistence.EntityManager;

public class ListasRepository {

    private EntityManager em;

    public ListasRepository(EntityManager em) {
        this.em = em;
    }

    public void adicionaLista(Listas listas) {
        if (listas.getIdLista() == null) {
            em.persist(listas);
        } else {
            em.merge(listas);
        }
    }

    public void removeLista(Listas listas) {
        listas = em.find(Listas.class, listas.getIdLista());
        em.remove(listas);
    }

    public Listas consultaListaPorId(Long idLista) {
        return em.find(Listas.class, idLista);
    }

    public List<Listas> consultaTodas() {
        return em.createQuery("FROM Listas l", Listas.class).getResultList();
    }
}
