<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css" media="screen">
@import url("<c:url value="/resources/styles/style.css"/>");
</style>
<title>Pedido</title>
</head>
<body >
	<form action="adicionaPedido" method="POST">
		<div id="wrap">
			<div id="menu">
				<%@ include file="/menu.jsp"%>
			</div>
			<div id="main">
				<div id="body">
					<h1>Cadastro de Pedidos</h1>
					<table width="1000px" class="consulta" style="border: 1px solid #000000;">
						<tr>
							<td width="10%" class="consulta">Id:&nbsp;</td>
							<td width="20%"class="consulta"><input type="text" id="id" name="id" readonly size="3" /></td>
							<td width="10%" class="consulta">No.Pedido:&nbsp;</td>
							<td width="20%" class="consulta"><input type="text" id="numeroPedido" name="numeroPedido" size="6" />&nbsp;</td>

							<td width="10%" class="consulta">Status:&nbsp;</td>
							<td width="15%" class="consulta">
								<select id="status" name="status" style="width:100%;" >
									<option value="true">Finalizado</option>
									<option value="false">Pendente</option>
								</select></td>
							<td width="13%" class="consulta">&nbsp;</td>
						</tr>
						<tr>
							<td class="consulta">Solicitante:&nbsp;</td>
							<td class="consulta"><input type="text" id="solicitante" name="solicitante" size="20"/></td>
							
							<td class="consulta">Email:&nbsp;</td>
							<td class="consulta"><input type="text" id="emailSolicitante" name="emailSolicitante" size="20"/></td>
							
							<td class="consulta">Valor Total:&nbsp;</td>
							<td class="consulta" align="right"><input type="text" id="valor_total" name="valor_total" size="10"/></td>
							<td class="consulta" align="right"><input type="submit" value="Salvar" /></td>
						</tr>
					</table>
					<table align="center" width="1000px">
						<tr>
							<td width="3%" align="center"><b>Selecione</b></td>
							<td width="3%" align="center"><b>Id</b></td>
							<td width="5%" align="center"><b>No.Pedido</b></td>
							<td width="24%"><b>Nome do solicitante</b></td>
							<td width="20%"><b>Email</b></td>
							<td width="8%" align="center"><b>Status</b></td>
							<td width="10%" align="center"><b>Dt pedido</b></td>
							<td width="10%" align="center"><b>Dt fechamento</b></td>
							<td width="10%" align="center"><b>Valor Total</b></td>
							<td width="7%" align="center"><b>Ação</b></td>
						</tr>
						<c:forEach items="${pedidos}" var="pedido" varStatus="contador">
							<tr id="row_${pedido.id}" bgcolor="#${contador.count % 2 == 0 ? '99FFFF' : 'FFFF99' }">
								<td align="center"><input type="radio" name="group1" value="${pedido.id}" onClick="checaRadio(value)"></td>
								<td align="center">${pedido.id}</td>
								<td id="numero_${pedido.id}">${pedido.numeroPedido}</td>
								<td id="solicitante_${pedido.id}">${pedido.solicitante}<br></td>
								<td id="email_${pedido.emailSolicitante}">${pedido.emailSolicitante}<br></td>
								<td id="status_${pedido.status}"><c:choose><c:when test="${pedido.status}">Finalizado</c:when><c:otherwise>Pendente</c:otherwise></c:choose> <br></td>
								<td id="dtPedido_${pedido.dtPedido}">${pedido.dtPedido}<br></td>
								<td id="dtFechamento_${pedido.dtFechamento}">${pedido.dtFechamento}<br></td>
								<td id="valorTotal_"><br></td>
								<td align="center"><a href="removePedido?id=${pedido.id}"><img src="<c:url value="/resources/imagens/delete.png"/>"
										style="height: 21px; width: 24px;" /></a>
										<a href="novoItemPedido?id=${pedido.id}"><img src="<c:url value="/resources/imagens/edit.png"/>"
										style="height: 21px; width: 24px;" /></a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</form>
	<script type="application/javascript">
		
		function checaRadio(name){
			document.getElementById('id').value=name;
			var elTableRow = document.getElementById('row_'+name);
			var elTableCells = elTableRow.getElementsByTagName("td");
			document.getElementById('numeroPedido').value=elTableCells[2].textContent;
			document.getElementById('solicitante').value=elTableCells[3].textContent;
			document.getElementById('emailSolicitante').value=elTableCells[4].textContent;
			document.getElementById('status').value=elTableCells[5].textContent;
		}
	
	</script>
</body>
</html>