/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    var data = new Date();
    $(document).ready(function () {
        $('.datepicker').datepicker({
            minDate: data,
            format: 'dd/mm/yyyy'

        });
        $('select').formSelect();
        $('.tabs').tabs();
    });
});

