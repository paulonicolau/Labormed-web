package br.com.labormed.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.labormed.util.Util;

public class ConversorSenha implements Converter {
	// decodificacao: CLIENTE -> SERVIDOR
	public Object getAsObject(FacesContext contexto, UIComponent componente, String pSenha) throws ConverterException {
		Integer lResult = 0;
		if (pSenha != null && pSenha.length() > 0) {
			lResult = Util.converterSenhaUsuario(pSenha);
		}
		return lResult;
	}

	// codificacao: SERVIDOR -> CLIENTE
	public String getAsString(FacesContext contexto, UIComponent componente, Object valor) throws ConverterException {
		return "";
	}
}