$(document).ready(function () {
    var formWasEnable = false;
    $("#login-enable").on("click", function () {
        if (!formWasEnable) {
            var loginForm = $("#login-form");
            loginForm.show();
            $("#login-enable").attr('id','login-close');
            $("#login-close").text("Close");

            $("[name='email']").focus();
            formEnable = true;
        }

    });

  $("#login-close").on("click",function(){
      if(formWasEnable){
          $("#login-form").hide();
          $("#login-close").text("LogIn");
          $("#login-close").attr('login-close','login-enable')
      }
  });


    $("#login-form").submit( function (event) {
        event.preventDefault();
        var email=$("[name='email']").val();
        var pass=$("[name='password']").val();
        var creds=JSON.stringify({email:email,password:pass});
        $.ajax({
            method: "POST",
            url: "/auth/login",
            dataType: "json",
            ContentType:"application/json; charset=utf-8",
            data:creds,
            headers: {'Content-Type': 'application/json; charset=utf-8'},
        complete: function (jqXHR,textStatus) {
            alert( "success" );
                var token = jqXHR.getResponseHeader("Authentication");
                window.sessionStorage.setItem("token", token);
                if (token !== null) {
                    $("#login-form, #login-enable").hide();
                }
                $("#main-container").load("./components/upload/uploader.html");
            }});



    });

    $("#btnSubmit").on("click",function (event) {
        event.preventDefault();
        var form = $('#fileUploadForm')[0];
        var data = new FormData(form);
        debugger

        alert( event.isDefaultPrevented() ); // true
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/api/upload",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            headers: {'Authentication': sessionStorage.getItem('token')},
            success: function (data) {
                alert("SUCCESS");

            },
            error: function (e) {
                alert("ERROR : ", e);


            }
        });

    });

});