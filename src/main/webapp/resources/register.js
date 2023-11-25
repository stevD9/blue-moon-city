$(document).ready(function () {
	$('form').submit(function(event) {
		register(event);
	});

//	$(":password").keyup(function(){
//		if($("#password").val() != $("#matchPassword").val()){
//	        $("#globalError").show().html(/*[[#{PasswordMatches.user}]]*/);
//	    }else{
//	    	$("#globalError").html("").hide();
//	    }
//	});
//
//	options = {
//		    common: {minChar:8},
//		    ui: {
//		    	showVerdictsInsideProgressBar:true,
//		    	showErrors:true,
//		    	errorMessages:{
//		    		  wordLength: /*[[#{error.wordLength}]]*/,
//		    		  wordNotEmail: /*[[#{error.wordNotEmail}]]*/,
//		    		  wordSequences: /*[[#{error.wordSequences}]]*/,
//		    		  wordLowercase: /*[[#{error.wordLowercase}]]*/,
//		    		  wordUppercase: /*[[#{error.wordUppercase}]]*/,
//		    	          wordOneNumber: /*[[#{error.wordOneNumber}]]*/,
//		    		  wordOneSpecialChar: /*[[#{error.wordOneSpecialChar}]]*/
//		    		}
//		    	}
//		};
//	 $('#password').pwstrength(options);
});

function register(event){
	event.preventDefault();
    $(".alert").html("").hide();
    $(".error-list").html("");
    if ($("#password").val() != $("#matchPassword").val()){
    	$("#globalError").show().html(/*[[#{PasswordMatches.user}]]*/);
    	return;
    }
        const $uname = $("#un").val();
        const $pw = $("#pw").val();
        const $email = $("#email").val();

        let user = new Object();

        user.username = $uname;
        user.password = $pw;
        user.email = $email;

        let req = JSON.stringify(user);

            $.ajax({
                url: "http://localhost:8080/users",
                type: "POST",
                data: req,
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success: function(response) {
                      window.location.href = "/registration/success";
                }});
//    .fail(function(data) {
//        if(data.responseJSON.error.indexOf("MailError") > -1)
//        {
//            window.location.href = serverContext + "emailError.html";
//        }
//        else if(data.responseJSON.error == "UserAlreadyExist"){
//            $("#emailError").show().html(data.responseJSON.message);
//        }
//        else if(data.responseJSON.error.indexOf("InternalError") > -1){
//            window.location.href = serverContext + "login?message=" + data.responseJSON.message;
//        }
//        else{
//        	var errors = $.parseJSON(data.responseJSON.message);
//            $.each( errors, function( index,item ){
//            	if (item.field){
//            		$("#"+item.field+"Error").show().append(item.defaultMessage+"<br/>");
//            	}
//            	else {
//            		$("#globalError").show().append(item.defaultMessage+"<br/>");
//            	}
//
//            });
//        }
//    });
}