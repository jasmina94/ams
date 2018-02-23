/**
 * Created by Jasmina on 23/02/2018.
 */

var validator = new ToastrValidator();

$(document).on("click", "#loginBtn", function(e) {
    if(!validator.validateLoginForm()){
        return;
    }
    e.preventDefault();
    var newUser = new Object();
    newUser["username"] = $("#username").val();
    newUser["password"] = $("#password").val();
    loginUser(newUser);
});

function loginUser(user) {
    strUser = format(user);
    $.ajax({
        url : '/api/users/login',
        type : 'POST',
        contentType : 'application/x-www-form-urlencoded',
        data : strUser,
        success : function() {
            setUser();
        },
        error : function(xhr, textStatus, errorThrown) {
            toastr.error('Error!  Status = ' + xhr.status);
        }
    });
}

function setUser() {
    $.ajax({
        url : '/api/users/me',
        type : 'GET',
        success : function(userDTO) {
            console.log(userDTO)
            //redirektuj se na home page sa parametrima tog usera
        },
        error : function(xhr, textStatus, errorThrown) {
            toastr.error('Error authenitication user!  Status = ' + xhr.status);
        }
    })
}

function format(obj) {
    var str = [];
    for (var p in obj)
        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
    return str.join("&");
}