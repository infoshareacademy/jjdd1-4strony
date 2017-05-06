$(document).ready(function() {
    $('#fund-id').change(function(){
        $.ajax({
            url : '/4analysis/investfundjson',
            data : {
                id : $('#fund-id').val()
            },
            dataType: 'json',
            success : function(response) {
                $('#fund-name').val(response.name);
                $('#fund-priority').val(response.priority);
            },
            error:function(response,status,er) {
                alert("error: "+response+" status: "+status+" er:"+er);
            }
        });
    });
});