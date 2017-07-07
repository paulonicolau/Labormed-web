package br.com.labormed.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.labormed.model.Grupo;
import br.com.labormed.services.impl.GrupoServiceImpl;

@FacesConverter("conversorGrupo")
public class GrupoConverter implements Converter{
	
	public Grupo getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()){
			return null;
		}
		try {
			Grupo c = new GrupoServiceImpl().findById(Long.parseLong(value));
			return c;
		} catch (Exception e) { return null; }
	}
 
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null || object.equals("")) return null;
		Grupo Grupo = (Grupo) object;
		if(Grupo == null || Grupo.getId() == 0){
			return null;
		}
		return String.valueOf(Grupo.getId());
	}

}