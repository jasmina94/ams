/**
 * Created by Jasmina on 23/02/2018.
 */

var validator = new ToastrValidator();
var formBuilder = new FormBuilder();

$(document).on("click", "#registerBtn", function () {
    $.ajax({
        url : '/api/registration/start',
        type : 'GET',
        success : function(formDTO) {
            if(formDTO.formProperties.length != 0){
                $("#modalRegister").modal("toggle");
                var $registerForm = $("#registerUserForm").empty();
                $registerForm.append("<fieldset>");

                for(i=0; i<formDTO.formProperties.length; i++){
                    var formProperty = formDTO.formProperties[i];
                    var $content = formBuilder.buildFormInput(formProperty, formDTO);
                    console.log($content);
                    $registerForm.append($content);
                }

                var $button = formBuilder.buildFormButton("success", "registerConfirmBtn", "Potvrdi");
                $registerForm.append($button);
            }else {
                toastr.info(formDTO.message);
            }
        },
        error : function(xhr, textStatus, errorThrown) {
            toastr.error('Error!  Status = ' + xhr.status);
        }
    })
});