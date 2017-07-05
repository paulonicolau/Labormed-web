package br.com.labormed;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.labormed.impl.UsuarioServiceImpl;
import br.com.labormed.model.Usuario;
import br.com.labormed.services.UsuarioService;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean extends AplicacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios;
	private Usuario usuario;
	
	public UsuarioBean(){
		System.out.println(1);
	}

	public Usuario getUsuario(Object pk) {
		try {
			UsuarioService usuarioService = new UsuarioServiceImpl();
			usuario = usuarioService.findById(pk);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}		
		
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<Usuario> getUsuarios() {
		try {
			UsuarioService usuarioService = new UsuarioServiceImpl();
			usuarios = usuarioService.findAll();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}		
		
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}