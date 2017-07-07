package br.com.labormed.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.labormed.model.SubGrupo;
import br.com.labormed.services.impl.SubGrupoServiceImpl;

@FacesConverter("conversorSubGrupo")
public class SubGrupoConverter implements Converter{
	
	public SubGrupo getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()){
			return null;
		}
		try {
			SubGrupo c = new SubGrupoServiceImpl().findById(Long.parseLong(value));
			return c;
		} catch (Exception e) { return null; }
	}
 
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null || object.equals("")) return null;
		SubGrupo subGrupo = (SubGrupo) object;
		if(subGrupo == null || subGrupo.getId() == 0){
			return null;
		}
		return String.valueOf(subGrupo.getId());
	}

}