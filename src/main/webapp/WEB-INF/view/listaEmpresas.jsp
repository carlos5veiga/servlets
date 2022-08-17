<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List,br.com.alura.gerenciador2.modelo.Empresa" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Java Standard Taglib</title>
</head>
<body>
	<c:if test="${ not empty empresa }">
		Empresa ${ empresa } cadastrada com sucesso!!
	</c:if>
	<p>Lista de Empresas</p>
	<ul>
		<c:forEach items = "${ empresas }" var = "empresa" >
			<li>
				${ empresa.nome } - <fmt:formatDate value="${empresa.dataAbertura }" pattern="dd/MM/yyyy"/>
				<a href="/gerenciador2/entrada?acao=MostraEmpresa&id=${ empresa.id }">edita</a>
				<a href="/gerenciador2/entrada?acao=RemoveEmpresa&id=${ empresa.id }">remove</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>