
$("#btnSubmit").click(function (event) {
    event.preventDefault();
    debugger
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    $("#btnSubmit").prop("disabled", true);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/upload",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            alert("SUCCESS");
            $("#btnSubmit").prop("disabled", false);
        },
        error: function (e) {
            alert("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

});
