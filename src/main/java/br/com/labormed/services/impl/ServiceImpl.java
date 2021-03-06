package br.com.labormed.services.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.labormed.EMF;
import br.com.labormed.services.Service;

public abstract class ServiceImpl<T> implements Service<T> {

	protected EntityManager entityManager;
	
	public ServiceImpl() {
		entityManager = EMF.createEntityManager();
	}
	
	public void update(Object object) throws Exception {
		try	{
			entityManager.getTransaction().begin();
			entityManager.merge(object);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
	
	public void save(Object object) throws Exception {
		try	{
			entityManager.getTransaction().begin();
			entityManager.persist(object);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		String tb = getEntityClass().getName();
		return entityManager.createQuery("Select x From "+tb+" x").getResultList();
	}
	
	@Override
	public T findById(Object pk) {
		return entityManager.find(getEntityClass(), pk);
	}

	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
		Type type = getClass().getGenericSuperclass();
		if (!(type instanceof ParameterizedType)) {
			type = getClass().getSuperclass().getGenericSuperclass();
		}
		if (type instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) type;
			if (paramType.getActualTypeArguments().length < 2)
				return (Class<T>) paramType.getActualTypeArguments()[0];
			else
				return (Class<T>) paramType.getActualTypeArguments()[1];
		} else {
			throw new IllegalArgumentException("Could not guess entity class by reflection");
		}
	}
}