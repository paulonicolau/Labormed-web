package br.com.labormed.converters;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
@FacesConverter("senhaConverter")
public class SenhaConverter implements Converter {
	// decodificacao: CLIENTE -> SERVIDOR
	public Object getAsObject(FacesContext contexto, UIComponent componente, String pSenha) throws ConverterException {
		try {
			String lResult = null;
			if (pSenha != null && pSenha.length() > 0) {
				lResult = converterSenhaUsuario(pSenha);
			}
			return lResult;
		} catch (Exception e) {
			throw new ConverterException(new FacesMessage(e.getMessage()));
		}
	}

	private String converterSenhaUsuario(String pSenha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(pSenha.getBytes("UTF-8"));
		 
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		return hexString.toString();
	}

	// codificacao: SERVIDOR -> CLIENTE
	public String getAsString(FacesContext contexto, UIComponent componente, Object valor) throws ConverterException {
		return "";
	}
	
}