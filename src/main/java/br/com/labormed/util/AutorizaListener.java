package br.com.labormed.util;
 
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
 
public class AutorizaListener implements PhaseListener {
 
 	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
        // Obtem o contexto atual
        FacesContext contexto = event.getFacesContext();
        
        // Obtem a pagina que atualmente esta interagindo com o ciclo
        String paginaAtual = contexto.getViewRoot().getViewId();
        
        // Se for a pagina 'login.jsp' seta a variavel como true
        boolean isLoginPage = paginaAtual.contains("login");
        
        // Obtem a sessao atual
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
	        
		try {
	        // Restaga o nome do usuario logado
	        Object usuario = sessao.getAttribute("usuarioLogado");

	        // Verifica se o usuario esta logado e se nao esta na pagina de login
	        if (!isLoginPage && usuario == null) {
	            // Redireciona o fluxo para a pagina de login
	            NavigationHandler nh = contexto.getApplication().getNavigationHandler();
	            nh.handleNavigation(contexto, null, "logout");
	        } else if (isLoginPage && usuario != null) {
	            // Se o usuario logado tentar acessar a pagina de login ele e
	            // redirecionado para a pagina inicial
	            NavigationHandler nh = contexto.getApplication().getNavigationHandler();
	            nh.handleNavigation(contexto, null, "login");
	        }
		} catch (Exception e) {
            NavigationHandler nh = contexto.getApplication().getNavigationHandler();
            nh.handleNavigation(contexto, null, "logout");
		}
    }
 
    public void beforePhase(PhaseEvent event) {
    }
 
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}

