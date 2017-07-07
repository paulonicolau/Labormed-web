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
			StringBuffer lSql = new StringBuffer("select p from Produto p where (1=1) ");
			if(subGrupo != null){
				lSql.append("and p.subGrupo.id = :idSubGrupo");
			}
			if(produto != null){
				if(produto.getCodigo() != null && !produto.getCodigo().isEmpty()){
					lSql.append("and p.codigo = :codigo ");
				}
				if(produto.getDescricao() != null){
					lSql.append("and p.descricao = :descricao ");
				}
			}
			
			Query query = entityManager.createQuery(lSql.toString());
			if(subGrupo != null){
				query.setParameter("idSubGrupo", subGrupo.getId());
			}
			if(produto != null){
				if(produto.getCodigo() != null && !produto.getCodigo().isEmpty()){
					query.setParameter("codigo", produto.getCodigo());
				}
				if(produto.getDescricao() != null){
					query.setParameter("descricao", produto.getDescricao());
				}
			}
			return (List<Produto>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}