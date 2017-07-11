package br.com.labormed;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.labormed.model.Fabricante;
import br.com.labormed.services.FabricanteService;
import br.com.labormed.services.impl.FabricanteServiceImpl;

@ManagedBean(name = "fabricanteBean")
@ViewScoped
public class FabricanteBean extends AplicacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Fabricante> fabricantes;
	private Fabricante fabricante;
	
	public FabricanteBean(){
		setFabricante(new Fabricante());
	}

	public void cadastrar(){
		try {
			FabricanteService produtoService = new FabricanteServiceImpl();
			fabricante.setDataReg(new java.sql.Date(new Date().getTime()));
			produtoService.save(fabricante);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}
	}
	public void onChangeFabricante(){
	} 

	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		try {
			FabricanteService fabricanteService = new FabricanteServiceImpl();
			fabricantes = fabricanteService.findAll();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}		
		
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
}