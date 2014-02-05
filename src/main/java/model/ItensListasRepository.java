package model;

import java.util.List;
import javax.persistence.EntityManager;

public class ItensListasRepository {

    private EntityManager em;

    public ItensListasRepository(EntityManager em) {
        this.em = em;
    }

    public void adicionaItensListas(ItensListas itensListas) {
        if (itensListas.getIdItemLista() == null) {
            em.persist(itensListas);
        } else {
            em.merge(itensListas);
        }
    }

    public void removeItensListas(ItensListas itensListas) {
        itensListas = em.find(ItensListas.class, itensListas.getIdItemLista());
        em.remove(itensListas);
    }

    public ItensListas consultaItensListasPorId(Long idItemLista) {
        return em.find(ItensListas.class, idItemLista);
    }

    public List<ItensListas> consultaItensListas(Long idLista) {
        String sql = "FROM ItensListas AS IL WHERE IL.visivel <> 'N' AND IL.listas.idLista = :idLista";

        return em.createQuery(sql, ItensListas.class).setParameter("idLista", idLista).getResultList();
    }

    public void alteraItensListasOculto(ItensListas itensListas) {
        itensListas = em.find(ItensListas.class, itensListas.getIdItemLista());
        em.merge(itensListas).setVisivel("N");
    }
}
