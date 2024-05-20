function showAlert(message, type = 'success') {
    var alertContainer = $('#alertContainer');
    var alertClass = 'alert-' + type;
    var alert = '<div class="alert ' + alertClass + ' alert-dismissible fade show" role="alert">' +
        message +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
        '<span aria-hidden="true">&times;</span>' +
        '</button>' +
        '</div>';
    alertContainer.empty().append(alert);

    setTimeout(function() {
        $('.alert').alert('close');
    }, 2000);
}