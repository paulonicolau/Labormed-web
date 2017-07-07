package br.com.labormed.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.labormed.impl.FabricanteServiceImpl;
import br.com.labormed.model.Fabricante;

@FacesConverter("conversorFabricante")
public class FabricanteConverter implements Converter{
	
	public Fabricante getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()){
			return null;
		}
		try { 
			Fabricante c = new FabricanteServiceImpl().findById(Integer.valueOf(value));
			return c;
		} catch (Exception e) { return null; }
	}
 
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null || object.equals("")) return null;
		Fabricante fabricante = (Fabricante) object;
		if(fabricante == null || fabricante.getId() == 0){
			return null;
		}
		return String.valueOf(fabricante.getId());
	}

}