package br.com.labormed.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.labormed.model.Produto;
import br.com.labormed.model.SubGrupo;
import br.com.labormed.services.ProdutoService;

public class ProdutoServiceImpl extends ServiceImpl<Produto> implements ProdutoService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> findByCliente(Integer id) {
		try{
			Query query = entityManager.createQuery("select p from Produtos p where p.cliente.produtoPK.cliente.id = :id");
			query.setParameter("id", id);
			return (List<Produto>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> findByFilters(SubGrupo subGrupo, Produto produto) {
		try{
			Query query = entityManager.createQuery("select p from Produtos p where p.subGrupo.id = :id");
			query.setParameter("id", subGrupo.getId());
			return (List<Produto>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}