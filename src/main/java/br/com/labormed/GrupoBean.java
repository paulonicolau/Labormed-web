package br.com.labormed;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.labormed.model.Grupo;
import br.com.labormed.model.SubGrupo;
import br.com.labormed.services.GrupoService;
import br.com.labormed.services.SubGrupoService;
import br.com.labormed.services.impl.GrupoServiceImpl;
import br.com.labormed.services.impl.SubGrupoServiceImpl;

@ManagedBean(name = "grupoBean")
@ViewScoped
public class GrupoBean extends AplicacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Grupo> grupos;
	private List<SubGrupo> subGrupos;
	private Grupo grupo;
	private SubGrupo subGrupo;
	private Date dataInicial;
	private Date dataFinal;
	
	public GrupoBean(){}

	public void onChangeGrupo(){
		getSubGrupos();
	} 

	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public SubGrupo getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupo subGrupo) {
		this.subGrupo = subGrupo;
	}

	public List<Grupo> getGrupos() {
		try {
			GrupoService grupoService = new GrupoServiceImpl();
			grupos = grupoService.findAll();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}		
		
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<SubGrupo> getSubGrupos() {
		try {
			if(grupo != null){
				SubGrupoService subGrupoService = new SubGrupoServiceImpl();
				subGrupos = subGrupoService.findSubGrupoByGrupo(grupo.getId());
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}		
		
		return subGrupos;
	}

	public void setSubGrupos(List<SubGrupo> subGrupos) {
		this.subGrupos = subGrupos;
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
}