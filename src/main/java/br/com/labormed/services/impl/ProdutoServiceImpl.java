package br.com.labormed.services.impl;

import java.sql.Date;
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
				lSql.append("and p.subGrupo.id = :idSubGrupo ");
			}
			if(produto != null){
				if(produto.getCodigo() != null && !produto.getCodigo().isEmpty()){
					lSql.append("and p.codigo = :codigo ");
				}
				if(produto.getDescricao() != null && !produto.getDescricao().isEmpty()){
					lSql.append("and p.descricao = :descricao ");
				}
				if(produto.getDataReg() != null){
					lSql.append("and p.dataReg = :dataReg ");
				}
				if(produto.getId() != null && produto.getId() > 0){
					lSql.append("and p.id = :idProduto ");
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
				if(produto.getDescricao() != null && !produto.getDescricao().isEmpty()){
					query.setParameter("descricao", produto.getDescricao());
				}
				if(produto.getDataReg() != null){
					query.setParameter("dataReg", new Date(produto.getDataReg().getTime()));
				}
				if(produto.getId() != null && produto.getId() > 0){
					query.setParameter("idProduto", produto.getId());
				}
			}
			return (List<Produto>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> findByDescricao(String queryDescricao) {
		try{
			StringBuffer lSql = new StringBuffer("select p from Produto p where (1=1) ");
			lSql.append("and p.descricao like :descricao ");
			
			Query query = entityManager.createQuery(lSql.toString());
			query.setParameter("descricao", queryDescricao + "%");

			return (List<Produto>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}