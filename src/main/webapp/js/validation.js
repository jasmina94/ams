/**
 * Created by Jasmina on 23/02/2018.
 */

function ToastrValidator() {
    this.checkFieldEmpty = function (field){
        if(field.val() == ""){
            field.focus();
            return false;
        }
        return true;
    },

    this.validateLoginForm = function () {
        var self = this;
        if(!self.checkFieldEmpty($("#username"))){
            toastr.error("Username is required.");
            return false;
        }
        if(!self.checkFieldEmpty($("#password"))){
            toastr.error("Password is required.");
            return false;
        }
        return true;
    }
}