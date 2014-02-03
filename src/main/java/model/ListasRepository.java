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

    public List<Itens> consultaItensNaoListados(Long idLista) {
        String sql = "SELECT I FROM Itens I"
                + "    WHERE NOT EXISTS (SELECT 1 FROM ItensListas IL"
                + "                       WHERE IL.listas.idLista = :idLista"
                + "                         AND IL.itens.idItem = I.idItem)"
                + " ORDER BY I.descricao";

        return em.createQuery(sql, Itens.class).setParameter("idLista", idLista).getResultList();
    }

    public Double consultaValorTotalLista(Long idLista) {
        String sql = "SELECT SUM(I.valor * IL.qtd) FROM ItensListas IL, Itens I"
                + "    WHERE IL.itens.idItem = I.idItem"
                + "      AND IL.listas.idLista = :idLista";

        return (Double) (em.createQuery(sql).setParameter("idLista", idLista).getSingleResult());
    }

    public Long consultaQuantidadeItensLista(Long idLista) {
        String sql = "SELECT COUNT(IL) FROM ItensListas IL"
                + "    WHERE IL.listas.idLista = :idLista";

        return (Long) (em.createQuery(sql).setParameter("idLista", idLista).getSingleResult());
    }
}
