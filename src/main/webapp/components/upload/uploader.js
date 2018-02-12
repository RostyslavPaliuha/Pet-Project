$("#btnSubmit").on("submit",function (event) {
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
