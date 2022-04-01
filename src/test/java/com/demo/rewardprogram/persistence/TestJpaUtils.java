package com.demo.rewardprogram.persistence;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;


@Component
public class TestJpaUtils {

	private EntityManager entityManager;

	private TransactionalExecutor transactionalExecutor;

	public TestJpaUtils(EntityManager entityManager, TransactionalExecutor transactionalExecutor) {
		super();
		this.entityManager = entityManager;
		this.transactionalExecutor = transactionalExecutor;
	}

	public <T> T saveEntity(final T entity) {
		return transactionalExecutor.execute(() -> entityManager.merge(entity));
	}

	public <T> void refreshEntity(final T entity) {
		entityManager.refresh(entity);
	}

	public <T> T findEntity(Class<T> entityClass, Serializable primaryKey) {
		return entityManager.find(entityClass, primaryKey);
	}

	public <T> T findSingleResult(String sql, Class<T> entityClass) {
		TypedQuery<T> query = entityManager.createQuery(sql, entityClass);
		return query.getSingleResult();
	}

	public <T> List<T> findResultList(String sql, Class<T> entityClass) {
		TypedQuery<T> query = entityManager.createQuery(sql, entityClass);
		return query.getResultList();
	}

	@Deprecated
	public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
		return entityManager.createQuery(qlString, resultClass);
	}
}