package br.com.labormed.services.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.labormed.model.SubGrupo;
import br.com.labormed.services.SubGrupoService;

public class SubGrupoServiceImpl extends ServiceImpl<SubGrupo> implements SubGrupoService {

	@SuppressWarnings("unchecked")
	@Override
	public List<SubGrupo> findSubGrupoByGrupo(long id) {
		try{
			Query query = entityManager.createQuery("select p from SubGrupo p where p.grupo.id = :id");
			query.setParameter("id", id);
			return (List<SubGrupo>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}