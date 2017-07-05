package br.com.labormed.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.labormed.impl.ProdutoServiceImpl;
import br.com.labormed.model.Produto;

public class ProdutoConverter implements Converter{
	
	public Produto getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()){
			return null;
		}
		try {
			Produto c = new ProdutoServiceImpl().findById(value);
			return c;
		} catch (Exception e) { return null; }
	}
 
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null || object.equals("")) return null;
		Produto Produto = (Produto) object;
		if(Produto == null || Produto.getId() == 0){
			return null;
		}
		return String.valueOf(Produto.getId());
	}

}