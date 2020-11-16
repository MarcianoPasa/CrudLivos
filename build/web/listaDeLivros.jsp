<%@ page language="java" contentType="text/html; charset=ISO8859-1" pageEncoding="ISO8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO8859-1">
        
        <link href="resources/css/geral.css" rel="stylesheet" type="text/css" />
        
        <title>Livros</title>
    </head>
    <body>
        <form name="formLivros">
            <label class="labelTitulo">Livros</label>
            <br />
            
            <a href="LivroController?action=insert">
                <input type="button" 
                       value="Novo" 
                       name="btnNovo" 
                       class="botaoNovo"
                       onclick="location.=''; return true;"/>                                        
            </a>
            
            <table border=1>
                <thead>
                    <tr>
                        <th style="width: 150px;">Código</th>
                        <th style="width: 300px;">Nome</th>
                        <th style="width: 300px;">Autos</th>
                        <th style="width: 150px; text-align: center">Lançamento</th>                
                        <th colspan=2>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${livros}" var="livro">
                        <tr>
                            <td><c:out value="${livro.id}" /></td>
                            <td><c:out value="${livro.nome}" /></td>
                            <td><c:out value="${livro.autor}" /></td>
                            
                            <td style="text-align: center"">
                                <fmt:formatDate pattern="dd/MM/yyyy" value="${livro.datalancamento}" />
                            </td>

                            <td>
                                <a href="LivroController?action=editar&id=<c:out value="${livro.id}"/>">
                                    <input type="button" 
                                           value="Atualizar" 
                                           name="btnAtualizar" 
                                           class="botaoAtualizar"
                                           onclick="location.=''; return true;"/>                                        
                                </a>                                
                            </td>
                            
                            <td>
                                <a href="LivroController?action=apagar&id=<c:out value="${livro.id}"/>">
                                    <input type="button" 
                                           value="Excluir" 
                                           name="btnExcluir" 
                                           class="botaoExcluir"
                                           onclick="return confirm('Deseja excluir o registro?')"/>                                    
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>                       
        </form>
    </body>
</html>
