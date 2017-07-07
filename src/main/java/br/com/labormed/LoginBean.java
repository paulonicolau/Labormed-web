package br.com.labormed;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import br.com.labormed.model.Usuario;
import br.com.labormed.services.UsuarioService;
import br.com.labormed.services.impl.UsuarioServiceImpl;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean extends AplicacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private UsuarioService usuarioService;

	private List<Usuario> Usuarios;
	private Usuario usuario;

	public LoginBean(){}
	
	public String login(){
		try {
			usuarioService = new UsuarioServiceImpl();
			usuario = this.usuarioService.findByLogin(usuario.getLogin(), usuario.getSenha());
			if(usuario == null){
				setMessageError("msg.loginInvalido");
			} else {
				setUsuarioLogado(usuario);
				return "login";
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}	
		
		return "logout";
	}

	public String logout(){
		setUsuarioLogado(null);
		return "logout";
	}
	
	public List<Usuario> getUsuarios() {
		try {
			usuarioService = new UsuarioServiceImpl();
			Usuarios = this.usuarioService.findAll();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage());
		}		
		
		return Usuarios;
	}

	public void setUsuarios(List<Usuario> Usuarios) {
		this.Usuarios = Usuarios;
	}

	public Usuario getUsuario() {
		if (this.usuario == null) {  
	        this.usuario = new Usuario();  
	    }
		return usuario;
	}

	public void setUsuario(Usuario Usuario) {
		this.usuario = Usuario;
	}
}