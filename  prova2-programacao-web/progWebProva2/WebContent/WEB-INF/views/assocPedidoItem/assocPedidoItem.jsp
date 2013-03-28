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
		<div id="wrap">
			<div id="menu">
				<%@ include file="/menu.jsp"%>
			</div>
			<div id="main">
				<div id="body">
					<h1>Associação pedido-ítem</h1>
						<form action="adicionaAssoc" method="POST">
						<table width="1000px" class="consulta" style="border: 1px solid #000000;">
						<tr>
							<td width="10%" class="consulta">Id:&nbsp;</td>
							<td width="20%"class="consulta"><input type="text" id="id_novo" name="id_novo" readonly size="3" value="${pedido.id}"/></td>
							
							<td width="10%" class="consulta">No.Pedido:&nbsp;</td>
							<td width="20%" class="consulta"><input type="text" id="numeroPedido_novo" name="numeroPedido_novo" readonly size="6" value="${pedido.numeroPedido}"/>&nbsp;</td>

							<td width="10%" class="consulta">Status:&nbsp;</td>
							<td width="15%" class="consulta">
								<select id="status" name="status_novo" style="width:100%;">
									<option value="${pedido.status}_novo"><c:choose><c:when test="${pedido.status}">Finalizado</c:when><c:otherwise>Pendente</c:otherwise></c:choose></option>
								</select></td>
							<td width="5%" class="consulta">&nbsp;</td>
							<td width="10%" class="consulta" align="right"><input type="button" value="Voltar" onclick="window.location.href='novoPedido'" style="width: 100px;height: 30px"/></td>
						</tr>
						<tr>
							<td class="consulta">Solicitante:&nbsp;</td>
							<td class="consulta"><input type="text" id="solicitante_2" name="solicitante_2" readonly size="20" value="${pedido.solicitante}"/></td>
							
							<td class="consulta">Email:&nbsp;</td>
							<td class="consulta"><input type="text" id="emailSolicitante_2" name="emailSolicitante_2" readonly size="20" value="${pedido.emailSolicitante}"/></td>
							
							<td class="consulta">Valor Total:&nbsp;</td>
							<td class="consulta" align="right"><input type="text" id="valorTotal" name="valorTotal_2" readonly size="10" value="${pedido.valorTotalCompra}"/></td>
							<td class="consulta">&nbsp;</td>
							<td class="consulta">&nbsp;</td>
						</tr>
					</table>
					<table width="1000px" class="consulta" style="border: 1px solid #000000;">
						<tr>
							<td><b>Tabela de ítens</b></td>
						</tr>
					</table>
					<table align="center" width="1000px">
						<tr>
							<td width="3%" align="center"><b>Id</b></td>
							<td width="25%"><b>Descrição</b></td>
							<td width="10%" align="center"><b>Preço</b></td>
							<td width="10%" align="center"><b>Quantidade</b></td>
							<td width="5%" align="center"><b>Ação</b></td>
						</tr>
						<c:forEach items="${itens}" var="item" varStatus="contador">
							<tr id="row_${item.id}" bgcolor="#${contador.count % 2 == 0 ? '99FFFF' : 'FFFF99' }">
								<td align="center">${item.id}</td>
								<td id="descricao_${item.id}">${item.descricao}</td>
								<td id="preco_${item.id}" align="right">${item.preco}<br></td>
								<td id="qtde_${item.id}" align="center" dir="rtl"><input type="text" id="quantidade_${item.id}" name="quantidade_${item.id}" onclick="insereValores(value)"></td>
								<td align="center">
									<input type="image" src="<c:url value="/resources/imagens/clean.png"/>" style="height: 21px; width: 24px;" value="${item.id}" onclick="insereValores(value)">
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td>
								<input type="hidden" id="id" name="id" value="">
								<input type="hidden" id="qtde" name="qtde" value="">
							</td>
						</tr>
					</table>
					<table width="1000px" class="consulta" style="border: 1px solid #000000;">
						<tr>
							<td><b>Tabela de associações</b></td>
						</tr>
					</table>
					</form>
					<!-- Lista Associações -->
					<table align="center" width="1000px">
						<tr>
							<td width="3%" align="center"><b>Id</b></td>
							<td width="25%"><b>Descrição</b></td>
							<td width="10%" align="center"><b>Preço</b></td>
							<td width="10%" align="center"><b>Quantidade</b></td>
							<td width="5%" align="center"><b>Ação</b></td>
						</tr>
						<c:forEach items="${assocs}" var="assoc" varStatus="contador">
							<tr id="rows_${assoc.id}" bgcolor="#${contador.count % 2 == 0 ? '99FFFF' : 'FFFF99' }">
								<td align="center">${assoc.id}</td>
								<td id="descricao_${assoc.id}">${assoc.item.descricao}</td>
								<td id="preco_${assoc.id}" align="right">${assoc.item.preco}<br></td>
								<td id="qtde_${assoc.id}" align="center" dir="rtl"><input type="text" id=quantidade name=quantidade readonly="readonly" value="${assoc.qtd}"></td>
								<td id="rem_${assoc.id}" align="center"><a href="removeAssoc?id=${assoc.id}"><img src="<c:url value="/resources/imagens/delete.png"/>"
										style="height: 21px; width: 24px;" /></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	
	<script type="application/javascript">
		function insereValores(name){
			document.getElementById('id').value=name;
			var elTableRow = document.getElementById('row_'+name);
			var elTableCells = elTableRow.getElementsByTagName("td");
			document.getElementById('qtde').value=elTableCells[3].childNodes[0].value;
		}	
	</script>
</body>
</html>