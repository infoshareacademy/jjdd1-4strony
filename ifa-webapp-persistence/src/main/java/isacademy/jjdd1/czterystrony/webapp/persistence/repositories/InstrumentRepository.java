package isacademy.jjdd1.czterystrony.webapp.persistence.repositories;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.FinancialInstrument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Stateless
public class InstrumentRepository<T extends FinancialInstrument> {

    private static Logger LOG = LoggerFactory.getLogger(InstrumentRepository.class);

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> entityClass;

    public InstrumentRepository() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public void add(T instrument) {
        entityManager.persist(instrument);
        LOG.info("Added new invest fund: {}", instrument.getId());
    }

    public T getById(String id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> getAll() {
        return entityManager
                .createQuery("SELECT i FROM " + entityClass.getName() + " i", entityClass)
                .getResultList();
    }
}
