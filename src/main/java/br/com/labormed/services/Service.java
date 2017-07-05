package br.com.labormed.services;

import java.util.List;

public interface Service<T> {
	
	public void salvar(Object obj);
	
	public List<T> findAll();

	public T findById(Object pk);

}
