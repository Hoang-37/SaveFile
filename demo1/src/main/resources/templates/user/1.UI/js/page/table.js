$(document).ready(function () { 
    $("#listEmployee tbody").delegate('click', 'tr', function () {
        console.log('click');
    });    
})