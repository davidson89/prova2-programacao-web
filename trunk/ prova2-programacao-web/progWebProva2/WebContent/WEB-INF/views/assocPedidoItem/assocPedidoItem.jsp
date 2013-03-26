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
<title>Associação</title>
</head>
<body>
	<form action="adicionaPedidoItem" method="POST">
		<div id="wrap">
			<div id="menu">
				<%@ include file="/menu.jsp"%>
			</div>
			<div id="main">
				<div id="body">
					<h1>Associação pedido-ítem</h1>
						<table width="1000px" class="consulta" style="border: 1px solid #000000;">
						<tr>
							<td width="10%" class="consulta">Id:&nbsp;</td>
							<td width="20%"class="consulta"><input type="text" id="id" name="id" readonly size="3" value="${pedido.id}"/></td>
							
							<td width="10%" class="consulta">No.Pedido:&nbsp;</td>
							<td width="20%" class="consulta"><input type="text" id="numeroPedido" name="numeroPedido" readonly size="6" value="${pedido.numeroPedido}"/>&nbsp;</td>

							<td width="10%" class="consulta">Status:&nbsp;</td>
							<td width="15%" class="consulta">
								<select id="status" name="status" style="width:100%;">
									<option value="${pedido.status}"><c:choose><c:when test="${pedido.status}">Finalizado</c:when><c:otherwise>Pendente</c:otherwise></c:choose></option>
								</select></td>
							<td width="5%" class="consulta">&nbsp;</td>
							<td width="10%" class="consulta" align="right"><input type="button" value="Voltar" onclick="window.location.href='novoPedido'" style="width: 100px;height: 30px"/></td>
						</tr>
						<tr>
							<td class="consulta">Solicitante:&nbsp;</td>
							<td class="consulta"><input type="text" id="solicitante" name="solicitante" readonly size="20" value="${pedido.solicitante}"/></td>
							
							<td class="consulta">Email:&nbsp;</td>
							<td class="consulta"><input type="text" id="emailSolicitante" name="emailSolicitante" readonly size="20" value="${pedido.emailSolicitante}"/></td>
							
							<td class="consulta">Valor Total:&nbsp;</td>
							<td class="consulta" align="right"><input type="text" id="valor_total" name="valor_total" readonly size="10"/></td>
							<td class="consulta">&nbsp;</td>
							<td class="consulta">&nbsp;</td>
						</tr>
					</table>
					<table align="center" width="1000px">
						<tr>
							<td width="3%" align="center"><b>Selecione</b></td>
							<td width="3%" align="center"><b>Id</b></td>
							<td width="25%"><b>Descrição</b></td>
							<td width="10%" align="center"><b>Preço</b></td>
							<td width="10%" align="center"><b>Quantidade</b></td>
							<td width="5%" align="center"><b>Ação</b></td>
						</tr>
						<c:forEach items="${itens}" var="item" varStatus="contador">
							<tr id="row_${item.id}" bgcolor="#${contador.count % 2 == 0 ? '99FFFF' : 'FFFF99' }">
								<td align="center"><input type="radio" name="group1" value="${item.id}" onClick="checaRadio(value)"></td>
								<td align="center">${item.id}</td>
								<td id="descricao_${item.id}">${item.descricao}</td>
								<td id="preco_${item.id}" align="right">${item.preco}<br></td>
								<td id="qtde_${item.id}" align="center" dir="rtl"><input type="text" id=quantidade name=quantidade></td>
								<td align="center"><a href="salvaAssocItemPedido?id=${item.id}?descricao=${item.descricao}"><img src="<c:url value="/resources/imagens/clean.png"/>"
										style="height: 21px; width: 24px;" /></a></td>
							</tr>
						</c:forEach>
					</table>
					<table align="center" width="1000px">
						<tr>
							<td align="right"><input type="submit" value="Solicitar"/></td>
						</tr>
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
			document.getElementById('descricao').value=elTableCells[2].textContent;
			document.getElementById('preco').value=elTableCells[3].textContent;
		}
	
	</script>
</body>
</html>