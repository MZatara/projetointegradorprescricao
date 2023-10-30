function validarFormulario() {
    var nomeAluno = document.forms["formulario"]["nomeAluno"].value;
    var exercicio = document.forms["formulario"]["exercicio"].value;
    var cuidadosAluno = document.forms["formulario"]["cuidadosAluno"].value;
    var series = document.forms["formulario"]["series"].value;
    var repeticoes = document.forms["formulario"]["repeticoes"].value;
    
    var regexLetras = /^[A-Za-z\s]+$/;
    var regexLetrasNumeros = /^[A-Za-z0-9\s]+$/;
    var regexNumeros = /^\d+$/;
    
    if (!regexLetras.test(nomeAluno)) {
        alert("Nome do Aluno deve conter somente letras e espaços.");
        return false;
    }
    
    if (!regexLetrasNumeros.test(exercicio)) {
        alert("Exercício deve conter somente letras e números.");
        return false;
    }
    
    if (!regexLetrasNumeros.test(cuidadosAluno)) {
        alert("Cuidados do Aluno deve conter somente letras e números.");
        return false;
    }
    
    if (!regexNumeros.test(series)) {
        alert("Séries deve conter somente números.");
        return false;
    }
    
    if (!regexNumeros.test(repeticoes)) {
        alert("Repetições deve conter somente números.");
        return false;
    }
}

function verificarLogin(formId) {
    var usuario = document.getElementById('usuario').value;
    var senha = document.getElementById('senha').value;

    console.log(usuario);
    console.log(senha);
  
    if(usuario == "admin" && senha == "admin"){
        alert("Usuário e senha estão corretos!")
 window.location.replace = ("prescricao.html");
 return true;
    } else {
        alert("Usuário ou senha incorretos!")
        return false;
    }
}









