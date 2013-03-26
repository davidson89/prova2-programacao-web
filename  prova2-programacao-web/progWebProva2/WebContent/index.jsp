<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/styles/style.css"/>" rel="stylesheet" type="text/css" />

<title>Principal</title>
</head>
<body>
	<div id="wrap">
		<div id="menu">
			<%@ include file="/menu.jsp"%>
		</div>
		<div id="main">
			<div id="body">
				<dl>
					<dt><b>Prova 2 da Disciplina de Programação Web</b></dt>
					<dt><br><br></dt>
					<dt><b>Professora:</b><dt>
					<dd>Letícia Bueno</dd>
					<dt><br><br></dt>
					<dt><b>Alunos:</b></dt>
					<dd>Davidson Oliveira Rodrigues<br>Felipe Toshio Hassunuma Saito<br></dd>
				</dl>

			</div>
		</div>
	</div>
</body>
</html>