<%@ page language="java" contentType="text/html; charset=ISO8859-1" pageEncoding="ISO8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO8859-1">        
        
        <link href="resources/css/geral.css" rel="stylesheet" type="text/css" />        
        
        <title>Cadastro de livro</title>
    </head>
    <body>        
        <form method="POST" action='LivroController' name="formLivro">
            <label class="labelTitulo">Cadastro de livro</label>
            <br />
            
            
            <c:if test="${livro.id != null}">             
                <label>Código:</label>
                <br />
                <input type="text" 
                       readonly="readonly" 
                       name="id"                    
                       value="<c:out value="${livro.id}" />" 
                       class="inputTexto"
                       style="width: 50%;"/> 
            </c:if>
            
            <br /> 

            <label>Nome:</label>
            <input type="text" name="nome" value="<c:out value="${livro.nome}"/>" class="inputTexto" /> 

            <br /> 
            
            <label>Autor:</label>
            <input type="text" name="autor" value="<c:out value="${livro.autor}"/>" class="inputTexto" /> 
            
            <br />

            <label>Data de lançamento:</label>
            <br />
            <input type="date" name="dataLancamento" value="${livro.datalancamento}" class="inputData"> 
           
            <br />
            
            <input type="submit" value="Salvar" class="botaoSalvar" />
            <input type="button" value="Cancelar" onclick="history.go(-1)" class="botaoCancelar" />
        </form>
    </body>
</html>
