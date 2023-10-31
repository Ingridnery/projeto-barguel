function updateClientesTable(){
    $.ajax({
        type: "GET",
        url: "/cliente/getAll",
        success: function(result){
            var clientes = result;
            var clientesTable = $("#clientesTable");
            clientesTable.empty();
            for(var i=0; i<clientes.length; i++){
                var cliente = clientes[i];
                clientesTable.append("<tr class='bg-white border-b dark:bg-gray-900 dark:border-gray-700'><td class='py-4 px-6'>"+cliente.nome+"</td><td class='py-4 px-6'>"+cliente.cpf+"</td><td>"+cliente.email+"</td><td class='py-4 px-6'>"+cliente.arraisAmador+"</td><td id='"+cliente.id+"'><a href='#' onclick='edit(event)' data-modal-toggle='modalCliente' type='button' data-modal-toggle='modalCliente' class='edit font-medium text-blue-600 dark:text-blue-500 hover:underline' ><i class='fa-solid fa-lg fa-pen-to-square'></i></a><a href='#' onclick='remove(event)' class='font-medium text-red-600 ml-3 dark:text-red-500 hover:underline'><i class='fa-solid fa-lg fa-trash'></i></a></td></tr>");
            }
        },
    });
}

function removeModalBackdrop(){
    $("body > div").remove();
}

function create(cliente){
    $.ajax({
        url:'/cliente/save',
        method: 'POST',
        data: JSON.stringify(cliente),
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function(){
            Swal.fire({
                title: 'Sucesso!',
                text: 'Cliente cadastrado com sucesso!',
                icon: 'success'
            })
            updateClientesTable();
            $("#modal-form")[0].reset();
        }
    })
}
function update(cliente,id){
    $.ajax({
        url:'/cliente/update/'+id,
        method: 'PUT',
        data: JSON.stringify(cliente),
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function(){
            updateClientesTable();
            $("#modal-form")[0].reset();
            const modal = new Modal(document.getElementById('modalCliente'));
            modal.hide();
        }
    })
}
function remove(event){
    let id = $(event.currentTarget.parentNode).attr('id');
    $.ajax({
        url:'http://localhost:8080/cliente/delete/'+ id,
        method: 'DELETE',
        success: function(){
            updateClientesTable();
        },
        error : function (resp){
            Swal.fire({
                title: 'Erro!',
                text: resp.responseText,
                icon: 'error'
            })
        }
    })
}

$("#btnAddCliente").click(function(){
    $("#typeOfOperation").val("CREATE");
});
$(document).ready(isLoggedIn() ? updateClientesTable() : window.location.href = "login.html");

$("#modal-form").submit(function(event){
    event.preventDefault();
    let cliente = {
        nome: $("#nome").val(),
        cpf: $("#cpf").val(),
        email: $("#email").val(),
        arraisAmador: $("#arrais").val()
    }
    let typeOfOperation = $("#typeOfOperation").val();
    if(typeOfOperation === 'CREATE')
        create(cliente);
    else if(typeOfOperation === 'UPDATE')
        update(cliente, $("#clienteId").val());
    else
        alert('Invalid operation');

    const modal = new Modal(document.getElementById('modalCliente'));
    modal.hide();
    removeModalBackdrop();
});

function edit(event)
{
    event.preventDefault();
    let id = $(event.currentTarget.parentNode).attr('id');
    $.ajax({
        url: 'http://localhost:8080/cliente/findBy/' + id,
        method: 'GET',
        success: function (data) {
            $("#nome").val(data.nome);
            $("#cpf").val(data.cpf);
            $("#email").val(data.email);
            $("#arrais").val(data.arraisAmador);
            $("#typeOfOperation").val("UPDATE");
            $("#clienteId").val(id);
            const modal = new Modal(document.getElementById('modalCliente'));
            modal.show();
        }
    })
}