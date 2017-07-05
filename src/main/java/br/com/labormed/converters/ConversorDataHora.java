package br.com.labormed.converters;

import java.sql.Timestamp;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.labormed.util.Util;

public class ConversorDataHora implements Converter {
	// decodificacao: CLIENTE -> SERVIDOR
	public Object getAsObject(FacesContext contexto, UIComponent componente, String novoValor) throws ConverterException {

		if (novoValor != null && novoValor.length() > 0) {
			if (Util.dataValida(novoValor)) {
				return Util.strToDateTimesTamp(novoValor);
			} else {
				return novoValor;
			}
		} else {
			return novoValor;
		}
	}

	// codificacao: SERVIDOR -> CLIENTE
	public String getAsString(FacesContext contexto, UIComponent componente,
			Object valor) throws ConverterException {
		return Util.TimeStampToStr((Timestamp) valor);
	}
}