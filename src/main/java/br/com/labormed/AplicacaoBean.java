package br.com.labormed;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import br.com.labormed.model.Usuario;

public class AplicacaoBean {
	
	private Usuario usuarioLogado;
	
	public Usuario getUsuarioLogado() {
		if(usuarioLogado == null){
			usuarioLogado = (Usuario) getParameterSessao("usuarioLogado");
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
		setParameterSessao("usuarioLogado", usuarioLogado);
	}

	public Object getParameterSessao(String param){
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(param);
	}

	public void setParameterSessao(String param, Object object){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(param, object);
	}
	
	public void setMessageError(String key){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceBundle(key), key));
	}

	public void setMessageWarn(String key){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, getMessageResourceBundle(key), key));
	}
	
	public String getMessageBundle(String chave){
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = context.getViewRoot();
		Locale locale = viewRoot.getLocale();
				
		Application app = context.getApplication();
		String appBundleName = app.getMessageBundle();
				
		ResourceBundle bundle = ResourceBundle.getBundle(appBundleName, locale);
		
		String texto = bundle.getString(chave);
		
		return texto;
	}
	
	public String getMessageResourceBundle(String key){
		FacesContext contexto = FacesContext.getCurrentInstance();
		Application app = contexto.getApplication();
		String appBundleName = app.getMessageBundle();
		ResourceBundle bundle = ResourceBundle.getBundle(appBundleName);
		
		return bundle.getString(key);
	}

}
