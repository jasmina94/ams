$(document).ready(function(){
	//Sakriti sve divove koji su višak
	$("#divZahtevAukcija").hide();
	$("#divOdlukaManjiBr").hide();
});


$(document).on("click", "#pokreniAukcije", function(){
	$.ajax({
		url: '/auction/start',
		type : 'GET',
	    success: function(data){
	    	getAllTasksForLoggedUser();
	    },
	    error : function(xhr, textStatus, errorThrown) {
            toastr.error('Error authenitication user!  Status = ' + xhr.status);
        }
	})
});

$(document).on("click", "#getZadaciLogovani", function(){
	getAllTasksForLoggedUser();
});


$(document).on("click", "#podnesiZahtev", function(){
	var params = pripremiParametreZahteva();
	var taskId = $("#podnesiZahtevTaskId").val();
	$.ajax({
		url : '/auction/confirm/' + taskId,
        type : 'POST',
        data: params,
        contentType: "application/json",
        async: false,
        success: function(status){
        	if(status == "ok"){
        		toastr.info("Proverite vaš email ili dostupne zadatke.");
        	}else{
        		toastr.info("Došlo je do greške prilikom unosa podataka. Pogledajte ponovo dostupne zadatke.");
        	}
        },
	    error : function(xhr, textStatus, errorThrown) {
	        toastr.error('Error!  Status = ' + xhr.status);
	    }
	});
});


$(document).on("click", "#potvrdiOdlukuManjiBr", function(){
	var params = pripremiParametreOdlukeManjiBr();
	var taskId = $("#potvrdiOdlukuManjiBrId").val();
	$.ajax({
		url : '/auction/confirmGen/' + taskId,
        type : 'POST',
        data: params,
        contentType: "application/json",
        async: false,
        success: function(status){
        	if(status == "ok"){
        		alert("Proverite vaš email ili dostupne zadatke.");
        	}else{
        		alert("Došlo je do greške prilikom unosa podataka. Pogledajte ponovo dostupne zadatke.");
        	}
        },
	    error : function(xhr, textStatus, errorThrown) {
	        toastr.error('Error!  Status = ' + xhr.status);
	    }
	});
});


function getAllTasksForLoggedUser(){
	$.ajax({
		url:'/auction/tasks',
		type: 'GET',
		success: function(tasks){
        	if(tasks.length != 0){
        		var task = tasks[0];
        		var id = task.id;
        		var taskName = task.name;
        		getFormForTask(id, taskName);
        	}else{
        		toastr.info("Trenutno nema zadataka za vas.");
        	}
        },
        error : function(xhr, textStatus, errorThrown) {
            toastr.error('Error!  Status = ' + xhr.status);
        }
	});
}

function getFormForTask(id, taskName){
	$.ajax({
		url : '/auction/task/' + id,
        type : 'GET',
        async: false,
        success: function(formDTO){
        	var taskId = id;
        	var formProperties = formDTO.formProperties;
        	if(formProperties.length != 0){
        		if(taskName == "Prijava zahteva"){
        			prikaziFormuZahteva(taskId, formProperties, formDTO);
        		}else {
        			prikaziFormuOdlukaManjiBroj(taskId,formProperties, formDTO)
        		}
        	}else{
        		toastr.info("Potvrdili ste task.");
        	}
        	
        },
        error : function(xhr, textStatus, errorThrown) {
            toastr.error('Error!  Status = ' + xhr.status);
        }
	}); 
}

function prikaziFormuZahteva(taskId, formProperties, form){
	var $divZahtev = $("#divZahtevAukcija").show();
    var $zahtevForm = $("#formaZahtevAukcija").empty();
    $zahtevForm.append("<fieldset>");
    
    for(i=0; i<formProperties.length; i++){
        var formProperty = formProperties[i];
        var $content = globalFormBuilder.buildFormInput(formProperty, form);
        $zahtevForm.append($content);
    }
    
    $zahtevForm.append("<input hidden type='text' value='" + taskId + "' id='podnesiZahtevTaskId'/>")
    getKategorijePoslova();
	var $button = globalFormBuilder.buildFormButton("success", "podnesiZahtev", "Potvrdi");
    $zahtevForm.append($button);
    
    var dateToday = new Date();
    $("#rokZaPonude").datepicker({dateFormat: 'yy-mm-dd', changeYear: true, changeMonth: true, minDate : dateToday});
    $("#rokIzvrsenja").datepicker({dateFormat: 'yy-mm-dd', changeYear: true, changeMonth: true, minDate : dateToday});
    
    //Sakrij sve drugo
    $("#divOdlukaManjiBr").hide();
}

function prikaziFormuOdlukaManjiBroj(taskId, formProperties, form){
	var $div = $("#divOdlukaManjiBr").show();
    var $forma = $("#formaOdlukaManjiBr").empty();
    $forma.append("<fieldset>");
    
    for(i=0; i<formProperties.length; i++){
        var formProperty = formProperties[i];
        var $content = globalFormBuilder.buildFormInput(formProperty, form);
        $forma.append($content);
    }
    
    $forma.append("<input hidden type='text' value='" + taskId + "'id='potvrdiOdlukuManjiBrId'/>")

	var $button = globalFormBuilder.buildFormButton("success", "potvrdiOdlukuManjiBr", "Potvrdi");
    $forma.append($button);
    
    //Sakrij sve drugo
    $("#divZahtevAukcija").hide();
}

function pripremiParametreZahteva(){
	var params = {};
	var kategorija = $("#kategorijaPosla option:selected").text();
	var opis = $("#opis").val();
	var procenjenaVrednost = $("#procenjenaVrednost").val();
	console.log($("#rokZaPonude").val());
	var rokZaPonude = $("#rokZaPonude").val();
	var maxPonuda = $("#maxPonuda").val();
	var minPonuda = $("#minPonuda").val();
	var rokIzvrsenja = $("#rokIzvrsenja").val();
	
	params["kategorijaPosla"] = kategorija;
	params["opis"] = opis;
	params["procenjenaVrednost"] = procenjenaVrednost;
	params["rokZaPonude"] = rokZaPonude;
	params["maxPonuda"] = maxPonuda;
	params["minPonuda"] = minPonuda;
	params["rokIzvrsenja"] = rokIzvrsenja;
	
	var jparams = JSON.stringify(params);
	return jparams;
}

function pripremiParametreOdlukeManjiBr(){
	var params = {};
	var odlukaManjiBroj = $("#odlukaManjiBroj option:selected").val();
	params["odlukaManjiBroj"] = odlukaManjiBroj;
	
	var jparams = JSON.stringify(params);
	console.log(jparams);
	return jparams;
}

function getKategorijePoslova(){
	$.ajax({
		url : '/api/categories',
        type : 'GET',
        async: false,
        success: function(kategorije){
        	if(kategorije.length != 0){
        		var $selectKat = $("#kategorijaPosla");
        		$selectKat.empty();
        		for(var i=0; i<kategorije.length; i++){
        			var kat = kategorije[i];
        			var $option = $("<option>");
        			$option.attr("value", kat.id);
        			$option.text(kat.name);
        			$selectKat.append($option);
        		}
        	}
        },
        error : function(xhr, textStatus, errorThrown) {
            toastr.error('Error!  Status = ' + xhr.status);
        }
	});
}