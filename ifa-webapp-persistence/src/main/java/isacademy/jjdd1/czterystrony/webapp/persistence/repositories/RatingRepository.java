package isacademy.jjdd1.czterystrony.webapp.persistence.repositories;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class RatingRepository<T extends Rating> {

    private static final Logger LOG = LoggerFactory.getLogger(RatingRepository.class);

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> entityClass;

    RatingRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


}
