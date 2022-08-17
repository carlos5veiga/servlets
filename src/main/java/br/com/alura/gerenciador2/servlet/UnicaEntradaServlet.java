package br.com.alura.gerenciador2.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.grenciador2.acao.Acao;

@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramAcao = request.getParameter("acao");
		
		//Para não usar os if-else para determinar qual classe foi passada em ação
		//Cria´se este modelo genérico
		
		String nomeDaClasse = "br.com.alura.grenciador2.acao." + paramAcao; //Nome completo da classe
		
		
		String nome;
		try {
			Class classe = Class.forName(nomeDaClasse); // Cria a classe
			Object obj = classe.newInstance(); // Cria um objeto da classe
			Acao acao = (Acao) obj; // Cast necessário para que executa possa ser chamado.
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException
				| IOException e) {
			throw new ServletException(e);
		}
		
		
		String[] splitRetorno = nome.split(":");
		if(splitRetorno[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + splitRetorno[1]);
			rd.forward(request, response);
		}
		else {
			response.sendRedirect(splitRetorno[1]);
		}	
		
//		Solução antiga testando cada classe passada em Acao
//		if (paramAcao.equals("ListaEmpresas")) {
//			
//			ListaEmpresas acao = new ListaEmpresas();
//			nome = acao.executa(request, response);
//			
//		} else if (paramAcao.equals("RemoveEmpresa")) {
//			
//			RemoveEmpresa acao = new RemoveEmpresa();
//			nome = acao.executa(request, response);
//			
//		} else if (paramAcao.equals("MostraEmpresa")) {
//			
//			MostraEmpresa acao = new MostraEmpresa();
//			nome = acao.executa(request, response);
//			
//		} else if (paramAcao.equals("AlteraEmpresa")) {
//			
//			AlteraEmpresa acao = new AlteraEmpresa();
//			nome = acao.executa(request, response);
//			
//		} else if (paramAcao.equals("NovaEmpresa")) {
//			
//			NovaEmpresa acao = new NovaEmpresa();
//			nome = acao.executa(request, response);
//			
//		} else if (paramAcao.equals("NovaEmpresaForm")) {
//			
//			NovaEmpresaForm acao = new NovaEmpresaForm();
//			nome = acao.executa(request, response);
//			
//		}
		
	}

}
