package br.com.alura.gerenciador2.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//@WebFilter("/entrada")
public class MonitoramentoFilter implements Filter {

	public void init() throws ServletException {}
       
	@Override
	public void destroy() {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Monitoramento Filter");
		
		long antes = System.currentTimeMillis();
		
		String acao = request.getParameter("acao");
		
		//executa a acao
		chain.doFilter(request, response);

		
		long depois = System.currentTimeMillis();
		
		System.out.println("Tempo de execu??o da a??o " + acao + ": " + (depois - antes));
		
	}

}
