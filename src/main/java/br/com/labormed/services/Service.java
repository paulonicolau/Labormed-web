package br.com.labormed.services;

import java.util.List;

public interface Service<T> {
	
	public void save(Object obj) throws Exception;
	
	public List<T> findAll();

	public T findById(Object pk);

}
