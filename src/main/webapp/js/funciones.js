function refreshIframe() { // recarga el iframe de la página
	frames[0].location.reload(true);
}

// Valida que no esté vacio
function validateNickname(elemento) {
	var nickname = document.getElementById(elemento).value;
	if (nickname.length == 0 || !nickname.trim() || nickname == null) {
		alert("Introduce un nombre de usuario");
	} else {
		document.getElementById("nicknameForm").submit();
	}
}
