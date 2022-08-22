package br.com.alura.gerenciador2.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador2.acao.Acao;

//@WebFilter("/entrada")   //Configurando atraves do web.xml
public class ControladorFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		System.out.println("Controlador Filter");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String paramAcao = request.getParameter("acao");
		
		String nomeDaClasse = "br.com.alura.gerenciador2.acao." + paramAcao; //Nome completo da classe
		String nome;
		try {
			Class classe = Class.forName(nomeDaClasse); // Cria a classe
			Object obj = classe.newInstance(); // Cria um objeto da classe
			Acao acao = (Acao) obj; // Cast necess�rio para que executa possa ser chamado.
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
	}

}
