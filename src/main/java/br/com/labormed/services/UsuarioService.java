package br.com.labormed.services;

import javax.persistence.NoResultException;

import br.com.labormed.model.Usuario;

public interface UsuarioService extends Service<Usuario>{
	public Usuario findByLogin(String pLogin, String pSenha) throws NoResultException;
}
