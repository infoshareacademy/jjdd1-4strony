$(document).ready(function () {
    $('.date-picker').datepicker()
        .on('dp.change', function (e) {
            $.ajax({
                type: 'Post',
                url: '/4analysis/test',
                data: {
                    start: $('#date-picker-start').val(),
                    end: $('#date-picker-end').val()
                },
                success: function (response) {
                    $('#date-picker-start').val(response);
                }
            });
        });

    // $('.datepicker').datepicker()
    //     .on(changeDate, function () {
    //         $.ajax({
    //             type: 'Post',
    //             url: '/4analysis/notowania',
    //             data: {
    //                 start: $('#date-picker-start').val(),
    //                 end: $('#date-picker-end').val()
    //             },
    //             success: function (response) {
    //                 $('#date-picker-start').val(response);
    //             }
    //         });
    //     });
    //
    //
    // $('.date-picker').datepicker({
    //     onSelect: function() {
    //         $.ajax({
    //             type: 'Post',
    //             url: '/4analysis/notowania',
    //             data: {
    //                 start: $('#date-picker-start').val(),
    //                 end: $('#date-picker-end').val()
    //             },
    //             success: function (response) {
    //                 $('#date-picker-start').val(response);
    //             }
    //         });
    //     }
    // });

    // $('.date-picker').blur(function () {
    //     $.ajax({
    //         type: 'Post',
    //         url: '/4analysis/test',
    //         data: {
    //             start: $('#date-picker-start').val(),
    //             end: $('#date-picker-end').val()
    //         },
    //         success: function (response) {
    //             $('#date-picker-start').val(response);
    //         }
    //     });
    // });
});