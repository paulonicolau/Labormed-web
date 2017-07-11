package br.com.labormed.services;

import java.util.List;

import br.com.labormed.model.Produto;
import br.com.labormed.model.SubGrupo;

public interface ProdutoService extends Service<Produto>{

	List<Produto> findByCliente(Integer id);

	List<Produto> findByFilters(SubGrupo subGrupo, Produto produto);

	List<Produto> findByDescricao(String query);
	
}
