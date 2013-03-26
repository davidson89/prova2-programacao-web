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
<title>Atualizar</title>
</head>
<body>
	<form action="adicionaPedido" method="POST">
		<div id="wrap">
			<div id="menu">
				<%@ include file="/menu.jsp"%>
			</div>
			<div id="main">
				<div id="body">
					<table width="1000px">
						<tr>
							<td>Id: <input type="text" id="id" name="id" readonly/></td>
							<td>Numero do Pedido: <input type="text" id="descricao" name="descricao" /></td>
							<td>Pre�o Unit�rio: <input type="text" id="preco" name="preco"/></td>
							<td><input type="submit" value="Salvar" /></td>
						</tr>
					</table>
					<table align="center" width="1000px">
						<tr>
							<td width="3%"><b>Selecione</b></td>
							<td width="3%"><b>Id</b></td>
							<td width="5%"><b>No.Pedido</b></td>
							<td width="20%"><b>Nome do solicitante</b></td>
							<td width="10%"><b>Status</b></td>
							<td width="10%"><b>Dt pedido</b></td>
							<td width="10%"><b>Dt fechamento</b></td>
						</tr>
						<c:forEach items="${pedidos}" var="pedido" varStatus="contador">
							<tr id="row_${pedido.id}" bgcolor="#${contador.count % 2 == 0 ? '99FFFF' : 'FFFF99' }">
								<td align="center"><input type="radio" name="group1" value="${item.id}" onClick="checaRadio(value)"></td>
								<td align="center">${pedido.id}</td>
								<td id="descricao_${pedido.id}">${pedido.descricao}</td>
								<td id="preco_${pedido.id}">${item.preco}<br></td>
								<td>
									<a href="remove?id=${pedido.id}"><img src="<c:url value="/resources/imagens/delete.png"/>"
										style="height: 21px; width: 24px;" /></a> 
									<a href="remove?id=${pedido.id}"><img src="<c:url value="/resources/imagens/edit.png"/>" 
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
			document.getElementById('descricao').value=elTableCells[2].textContent;
			document.getElementById('preco').value=elTableCells[3].textContent;
		}
	
	</script>
</body>
</html>