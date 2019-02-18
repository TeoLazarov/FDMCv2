package fdmcv2.repository;

import fdmcv2.domain.entities.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CatRepositoryImpl implements CatRepository {

    private final EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cat save(Cat entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Cat> findAll() {
        this.entityManager.getTransaction().begin();
        List<Cat> cats = this.entityManager
                .createQuery("SELECT c FROM Cat c", Cat.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return cats;
    }
}
