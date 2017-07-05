package br.com.labormed.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.labormed.model.Usuario;
import br.com.labormed.services.UsuarioService;

	public class UsuarioServiceImpl extends ServiceImpl<Usuario> implements UsuarioService {

	public Usuario findByLogin(String pLogin, String pSenha) throws NoResultException {
		try{
			Query query = entityManager.createQuery("select u from Usuario u where upper(u.login) = :login and u.senha = :senha");
			query.setParameter("login", pLogin.toUpperCase());
			query.setParameter("senha", pSenha);
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}