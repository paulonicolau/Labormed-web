package br.com.labormed.services;

import java.util.List;

import br.com.labormed.model.SubGrupo;

public interface SubGrupoService extends Service<SubGrupo>{
	public abstract List<SubGrupo> findSubGrupoByGrupo(long id);
}
