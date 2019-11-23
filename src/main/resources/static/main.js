
$(document).ready(function(){
    $('#btn-login').click(function(event) {
        event.preventDefault();
        window.location.href = '/loginpage';
    })
})


//function onSubmit() {
//    e.stopImmediatePropagation();
//    e.preventDefault();
//    $('.form-submit').attr('disabled', 'disabled');
//    return true;
//}

//$("#btn-register").click(function(e) {
//    e.stopImmediatePropagation();
//    e.preventDefault();
//    $('.form-submit').attr('disabled', 'disabled');
//});