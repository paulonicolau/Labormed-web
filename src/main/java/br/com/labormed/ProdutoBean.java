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
	private Produto produto;
	private SubGrupo subGrupo;
	private Date dataInicial;
	private Date dataFinal;
	private String lote;
	private String dataValidade;
	
	public ProdutoBean(){
		setProduto(new Produto());
	}
	
	public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
	
	public void cadastrarProdutos(){
		
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
}