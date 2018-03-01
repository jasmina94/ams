/**
 * Created by Jasmina on 23/02/2018.
 */

$(document).on("click", "#loginBtn", function(e) {
    if(!globalValidator.validateLoginForm()){
        return;
    }
    e.preventDefault();
    var newUser = new Object();
    newUser["username"] = $("#logusername").val();
    newUser["password"] = $("#logpassword").val();
    globalAuthenticator.login(newUser);
});


$(document).on("click", "#logoutBtn", function(){
	globalAuthenticator.logout();
});