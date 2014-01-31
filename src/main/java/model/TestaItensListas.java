package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jsf.controller.CadastroItensListasBean;

public class TestaItensListas {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ListaComprasJoaozinhoPU");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

        Listas listas = manager.find(Listas.class, 3L);

        Itens itens = manager.find(Itens.class, 11L);

        ItensListas itensListas = new ItensListas();
        itensListas.setListas(listas);
        itensListas.setItens(itens);
        itensListas.setQtd(Double.valueOf(99));
        itensListas.setVisivel("S");

        ItensListasRepository itensListasRepository = new ItensListasRepository(manager);

        itensListasRepository.adicionaItensListas(itensListas);

        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }
}
