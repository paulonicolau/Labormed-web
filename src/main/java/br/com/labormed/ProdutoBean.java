package br.com.labormed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.labormed.model.Produto;
import br.com.labormed.model.SubGrupo;
import br.com.labormed.services.ProdutoService;
import br.com.labormed.services.impl.ProdutoServiceImpl;

@ManagedBean(name = "produtoBean")
@ViewScoped
public class ProdutoBean extends AplicacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Produto> produtos;
	private List<Produto> listaProduto;
	private Produto produto;
	private SubGrupo subGrupo;
	private Date dataInicial;
	private Date dataFinal;
	private String lote;
	private String dataValidade;
	
	public ProdutoBean(){
		setProduto(new Produto());
	}
	
	public List<Produto> completeText(String query) {
        List<Produto> results = new ArrayList<Produto>();
		try {
			ProdutoService produtoService = new ProdutoServiceImpl();
			results = produtoService.findByDescricao(query);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}

        return results;
    }
	
	public void cadastrarProdutos(){
		try {
			ProdutoService produtoService = new ProdutoServiceImpl();
			produto.setUsuario(getUsuarioLogado());
			produto.setDataReg(new java.sql.Date(new Date().getTime()));
			produtoService.save(produto);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}
    }
	
	public void buscarProdutos(){
		try {
			ProdutoService produtoService = new ProdutoServiceImpl();
			List<Produto> lista = produtoService.findByFilters(subGrupo, produto);
			setProdutos(lista);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
			setProdutos(new ArrayList<Produto>());
		}
	}
	public Produto getProduto(Object pk) {
		try {
			ProdutoService produtoService = new ProdutoServiceImpl();
			produto = produtoService.findById(pk);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
			setProdutos(new ArrayList<Produto>());
		}		
		
		return produto;
	}

	public void onChangeProduto(){
		try {
			ProdutoService produtoService = new ProdutoServiceImpl();
			Produto lista = produtoService.findById(produto.getId());
			setProduto(lista);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}
	} 
	public void onChangeSubGrupo(){
		try {
			ProdutoService produtoService = new ProdutoServiceImpl();
			List<Produto> lista = produtoService.findByFilters(subGrupo, produto);
			setProdutos(lista);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}
	} 

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public SubGrupo getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupo subGrupo) {
		this.subGrupo = subGrupo;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public List<Produto> getListaProduto() {
		listaProduto = new ProdutoServiceImpl().findAll();
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
}