/**
 * Created by Jasmina on 23/02/2018.
 */

var validator = new ToastrValidator();
var loggedInUser = null;

$(document).on("click", "#loginBtn", function(e) {
    if(!validator.validateLoginForm()){
        return;
    }
    e.preventDefault();
    var newUser = new Object();
    newUser["username"] = $("#logusername").val();
    newUser["password"] = $("#logpassword").val();
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
            getUser();
        },
        error : function(xhr, textStatus, errorThrown) {
            toastr.error('Error!  Status = ' + xhr.status);
        }
    });
}

function getUser() {
    $.ajax({
        url : '/api/users/me',
        type : 'GET',
        async: 'false',
        success : function(userDTO) {
            if(userDTO != null){
            	var name = userDTO.firstname + " " + userDTO.lastname;
            	$(".mainNavbar").show();
            	$("#loginDiv").hide();
            	$("#mainDiv").show();
            	$("#userLabel").text(name);
            	loggedInUser = userDTO;
            }else {
            	loggedInUser = null;
            }
        },
        error : function(xhr, textStatus, errorThrown) {
            toastr.error('Error authenitication user!  Status = ' + xhr.status);
        }
    })
}

$(document).on("click", "#logoutBtn", function(){
	 if(loggedInUser != null) {
		 var name = loggedInUser.firstname + " " + loggedInUser.lastname; 
		 $.ajax({
			url: '/api/logout',
			type : 'POST',
		    data: loggedInUser.username,
		    success: function(data){
		    	console.log("izlogovan");
		    	setLoggedOutView();
		    },
		    error: function(data){
		    	console.log("pogresno");
		    	setLoggedInView(name);
		    }
		 });
	 }
});

function setLoggedInView(){
	$(".mainNavbar").show();
	$("#loginDiv").hide();
	$("#mainDiv").show();
	$("#userLabel").text(name);
}

function setLoggedOutView(){
	$(".mainNavbar").hide();
	$("#loginDiv").show();
	$("#loginForm").find("input").val("");
	$("#mainDiv").hide();
	$("#userLabel").text("");
}
function format(obj) {
    var str = [];
    for (var p in obj)
        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
    return str.join("&");
}