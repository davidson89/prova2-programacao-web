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
	<form:errors path="item.descricao" cssStyle="color:red" />
	<form action="adicionaItem" method="POST">
		<div id="wrap">
			<div id="menu">
				<%@ include file="/menu.jsp"%>
			</div>
			<div id="main">
				<div id="body">
					<h1>Cadastro de ítens</h1>
					<table width="1000px" class="consulta">
						<tr style="border: 1px solid #000000;">
							<td class="consulta">Id:<input type="text" id="id" name="id" readonly/ size="3"></td>
							<td class="consulta">Descrição: <input type="text" id="descricao" name="descricao" size="20"/></td>
							<td class="consulta">Preço Unitário: <input type="text" id="preco" name="preco" size="10"/></td>
							<td align="center" class="consulta"><input type="submit" value="Salvar" style="width: 100px;height: 30px"/></td>
							<td align="center" class="consulta"><input type="submit" value="Pesquisar" style="width: 100px;height: 30px"/></td>
						</tr>
					</table>
					<table align="center" width="1000px">
						<tr>
							<td width="3%" align="center"><b>Selecione</b></td>
							<td width="3%" align="center"><b>Id</b></td>
							<td width="25%"><b>Descrição</b></td>
							<td width="10%" align="center"><b>Preço</b></td>
							<td width="5%" align="center"><b>Ação</b></td>
						</tr>
						<c:forEach items="${itens}" var="item" varStatus="contador">
							<tr id="row_${item.id}" bgcolor="#${contador.count % 2 == 0 ? '99FFFF' : 'FFFF99' }">
								<td align="center"><input type="radio" name="group1" value="${item.id}" onClick="checaRadio(value)"></td>
								<td align="center">${item.id}</td>
								<td id="descricao_${item.id}">${item.descricao}</td>
								<td id="preco_${item.id}" align="right">${item.preco}<br></td>
								<td align="center"><a href="remove?id=${item.id}"><img src="<c:url value="/resources/imagens/delete.png"/>"
										style="height: 21px; width: 24px;" /></a> 
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