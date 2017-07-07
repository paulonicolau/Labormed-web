package br.com.labormed.converters;

import java.sql.Date;
import java.sql.Timestamp;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.labormed.util.Util;

@FacesConverter("conversorData")
public class DataConveter implements Converter {
	public Object getAsObject(FacesContext contexto, UIComponent componente, String novoValor) throws ConverterException {

		if (novoValor != null && novoValor.length() > 0) {
			if (Util.dataValida(novoValor)) {
				return Util.strToDateSQL(novoValor);
			} else {
				return novoValor;
			}
		} else {
			return novoValor;
		}
	}

	public String getAsString(FacesContext contexto, UIComponent componente, Object valor) throws ConverterException {
		Date data = new Date(((Timestamp)valor).getTime());
		return Util.dateSQLToStr(data);
	}
}