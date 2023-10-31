function updateBarcosTable(){
    $.ajax({
        type: "GET",
        url: "/barco/getAll",
        success: function(result){
            var barcos = result;
            var barcosTable = $("#barcosTable");
            barcosTable.empty();
            for(var i=0; i<barcos.length; i++){
                var barco = barcos[i];
                let tr = "<tr class='bg-white border-b dark:bg-gray-900 dark:border-gray-700'><td class='py-4 px-6'>"+barco.nome+"</td><td class='py-4 px-6'>"+barco.tipoBarco+"</td><td class='py-4 px-6' >"+barco.tamanho+"</td><td class='py-4 px-6'>"+barco.qtdPassageiros+"</td><td class='py-4 px-6'>"+barco.valorDiaria+"</td>";
                if(isLoggedIn()){
                    tr = tr + "<td class='operacoes' id='"+barco.id+"'><a href='#' onclick='edit(event)' data-modal-toggle='barcoModal' type='button' data-modal-toggle='barcoModal' class='edit font-medium text-blue-600 dark:text-blue-500 hover:underline' ><i class='fa-solid fa-lg fa-pen-to-square'></i></a><a href='#' onclick='remove(event)' class='font-medium text-red-600 ml-3 dark:text-red-500 hover:underline'><i class='fa-solid fa-lg fa-trash'></i></a></td></tr>";
                }
                barcosTable.append(tr);

            }
        }
    });
}

function removeModalBackdrop(){
    $("body > div").remove();
}

function create(barco){
    $.ajax({
        url:'/barco/save',
        method: 'POST',
        data: JSON.stringify(barco),
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function(){
            Swal.fire({
                title: 'Sucesso!',
                text: 'Barco cadastrado com sucesso!',
                icon: 'success'
            })
            updateBarcosTable();
            $("#modal-form")[0].reset();
        }
    })
}
function update(barco,id){
    $.ajax({
        url:'/barco/update/'+id,
        method: 'PUT',
        data: JSON.stringify(barco),
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function(){
            updateBarcosTable();
            $("#modal-form")[0].reset();
            const modal = new Modal(document.getElementById('modalBarco'));
            modal.hide();
        }
    })
}
function edit(event)
{
    event.preventDefault();
    let id = $(event.currentTarget.parentNode).attr('id');
    $.ajax({
        url: 'http://localhost:8080/barco/findBy/' + id,
        method: 'GET',
        success: function (data) {
            $("#nome").val(data.nome);
            $("#tipoBarco").val(data.tipoBarco);
            $("#tamanho").val(data.tamanho);
            $("#qtdPassageiros").val(data.qtdPassageiros);
            $("#valorDiaria").val(data.valorDiaria);
            $("#typeOfOperation").val("UPDATE");
            $("#idBarco").val(id);
            const modal = new Modal(document.getElementById('modalBarco'));
            modal.show();
        }
    })
}
function remove(event){
    let id = $(event.currentTarget.parentNode).attr('id');
    $.ajax({
        url:'http://localhost:8080/barco/delete/'+ id,
        method: 'DELETE',
        success: function(){
            updateBarcosTable();
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

$("#btnAddBarco").click(function(){
    $("#typeOfOperation").val("CREATE");
});

function displayLoggedInOptions(){
    // aqui manda mostrar a coluna de editar e excluir
    $("#btnAddBarco").show();
    $("#colOperacao").show();
}

function hideLoggedInOptions(){
    $("#btnAddBarco").addClass("hidden");
    $("#colOperacao").hide();

}


$(document).ready(function(){
    isLoggedIn() ? displayLoggedInOptions() : hideLoggedInOptions();
    updateBarcosTable();
});


$("#modal-form").submit(function(event){
    event.preventDefault();
    let barco = {
        nome: $("#nome").val(),
        tipoBarco: $("#tipoBarco").val(),
        tamanho: $("#tamanho").val(),
        qtdPassageiros: $("#qtdPassageiros").val(),
        valorDiaria: $("#valorDiaria").val()
    }
    let typeOfOperation = $("#typeOfOperation").val();
    if(typeOfOperation === 'CREATE')
        create(barco);
    else if(typeOfOperation === 'UPDATE')
        update(barco, $("#idBarco").val());
    else
        alert('Invalid operation');

    const modal = new Modal(document.getElementById('modalBarco'));
    modal.hide();
    removeModalBackdrop();
});
