package jsf.filter;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JpaFilter implements Filter {

    private EntityManagerFactory factory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.factory = Persistence.createEntityManagerFactory("ListaComprasPU");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        EntityManager manager = this.factory.createEntityManager();

        request.setAttribute("EntityManager", manager);
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            chain.doFilter(request, response);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ServletException(e);
        } finally {
            manager.close();
        }
    }

    @Override
    public void destroy() {
        this.factory.close();
    }
}
