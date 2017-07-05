package br.com.labormed.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;

import br.com.labormed.model.Usuario;
import br.com.labormed.services.UsuarioService;

public class UsuarioConverter implements Converter{
	
	public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()){
			return null;
		}
		try { 
			Usuario c = ((UsuarioService) (new InitialContext().lookup("usuarioService"))).findById(Integer.valueOf(value));
			return c;
		} catch (Exception e) { return null; }
	}
 
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null || object.equals("")) return null;
		Usuario Usuario = (Usuario) object;
		if(Usuario == null || Usuario.getId() == 0){
			return null;
		}
		return String.valueOf(Usuario.getId());
	}

}