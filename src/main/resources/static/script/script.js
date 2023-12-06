function verificarLogin() {
    var usuario = document.getElementById('usuario').value;
    var senha = document.getElementById('senha').value;

    if(usuario === "admin" && senha === "admin"){
        alert("Usuário e senha estão corretos!");
        window.location.replace("prescricao");
        return false; 
    } else {
        alert("Usuário ou senha incorretos!");
        return false;
    }
}
 
