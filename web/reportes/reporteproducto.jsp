<%-- 
    Document   : reportecl
    Created on : 13-nov-2015, 4:58:38
    Author     : An
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import=" bean.btipoproducto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Reporte Cliente</title>
    </head>
    <body>
        <form id="rportecliente" method="post" action="../rproductotipo">
            <select   id="tipop" name="tipop"  >
                <option >Seleccionar opcion</option>
                <%
                    ResultSet rst = btipoproducto.getCliente();
                    while (rst.next()) {%>

                <option value="<%= rst.getString("idctgTipoProducto")%>"><%= rst.getString("nombre") %></option>

                <%}
                %>

            </select>
              
            <input name="cmdguardar"   type="submit" id="cmdguardar" value="Crear">
        </form>

    </body>
</html>
