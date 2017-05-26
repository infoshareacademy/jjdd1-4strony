package isacademy.jjdd1.czterystrony.webapp.persistence.repositories;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.FinancialInstrument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class InstrumentRepository<T extends FinancialInstrument> {

    private static final Logger log = LoggerFactory.getLogger(InstrumentRepository.class);

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> entityClass;

    InstrumentRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void add(T instrument) {
        entityManager.persist(instrument);
        log.info("Added new {}: {}", entityClass.getName(), instrument.getId());
    }

    public T getById(String id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> getAll() {
        return entityManager
                .createQuery("SELECT i FROM " + entityClass.getSimpleName() + " i", entityClass)
                .getResultList();
    }
}
