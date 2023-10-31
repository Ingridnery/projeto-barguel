$("#login").submit(function (event) {
    event.preventDefault();

    let user = {
        "username": $("#username").val(),
        "password": $("#password").val()
    }

    $.ajax({
        url: '/atendente/login',
        method: 'POST',
        data: JSON.stringify(user),
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (resp) {
            sessionStorage.setItem("uuid", resp);
            let timerInterval
            Swal.fire({
                title: 'Autenticado com sucesso!!',
                html: 'Redirecionando para Barcos em <b></b> milissegundos.',
                timer: 3000,
                timerProgressBar: true,
                didOpen: () => {
                    Swal.showLoading()
                    const b = Swal.getHtmlContainer().querySelector('b')
                    timerInterval = setInterval(() => {
                        b.textContent = Swal.getTimerLeft()
                    }, 100)
                },
                willClose: () => {
                    clearInterval(timerInterval)
                }
            }).then((result) => {
                window.location.href = "index.html";
            })
        },
        error: function (resp) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Usuário ou senha incorretos!',
            })
        }
    })
});

function checkIfPasswordsMatches() {
    return ($("#passwordRegister").val() == $("#confirmaSenha").val());
}

$("#register").submit(function (event) {
    event.preventDefault();

    if (checkIfPasswordsMatches()) {
        let user = {
            "username": $("#username").val(),
            "password": $("#password").val()
        }

        $.ajax({
            url: '/atendente/register',
            method: 'POST',
            data: JSON.stringify(user),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (resp) {
                sessionStorage.setItem("uuid", resp.id);
                let timerInterval
                Swal.fire({
                    title: 'Registrado com sucesso!!',
                    html: 'Redirecionando para Barcos em <b></b> milissegundos.',
                    timer: 3000,
                    timerProgressBar: true,
                    didOpen: () => {
                        Swal.showLoading()
                        const b = Swal.getHtmlContainer().querySelector('b')
                        timerInterval = setInterval(() => {
                            b.textContent = Swal.getTimerLeft()
                        }, 100)
                    },
                    willClose: () => {
                        clearInterval(timerInterval)
                    }
                }).then((result) => {
                    window.location.href = "index.html";
                })
            },
            error: function (resp) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Usuário ou senha incorretos!',
                })
            }
        })
    } else{
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'As senhas não conferem!',
        })
    }


});

$(document).ready(function () {
    if (isLoggedIn()) {
        let timerInterval
        Swal.fire({
            title: 'Você já está logado!',
            html: 'Redirecionando para Barcos em <b></b> milissegundos.',
            timer: 3000,
            timerProgressBar: true,
            didOpen: () => {
                Swal.showLoading()
                const b = Swal.getHtmlContainer().querySelector('b')
                timerInterval = setInterval(() => {
                    b.textContent = Swal.getTimerLeft()
                }, 100)
            },
            willClose: () => {
                clearInterval(timerInterval)
            }
        }).then((result) => {
            window.location.href = "index.html";
        })
    }
});