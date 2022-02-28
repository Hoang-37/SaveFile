import Api from '../service/Api.js'

export default class MyFunc {
    genNewCodeInForm(){
        var newCode = Api.getAPI("http://cukcuk.manhnv.net/v1/Employees/NewEmployeeCode");
        newCode.then(
            response => {
                $("#employeeCode").val(response);
            },
            error => {
                console.log(error);
            }
        )
    }
    //function resolve modal event
    showModal(isDetail) {
        $("#m-dialog-employee").dialog("open");
        if(!isDetail){
            $("#addEmployeeForm").attr("is-detail", false);
            this.genNewCodeInForm();
        } else {
            $("#addEmployeeForm").attr("is-detail", true);
        }
        $("#employeeCode").focus();
        
    }
    closeModal() {
        $("#m-dialog-employee").dialog("close");
        // $("#addEmployeeForm")[0].reset();
        // $("#addEmployeeForm").find("input").each((idx, item) => {
        //     item.parent().removeClass("error");
        // })
    }

    //function with minisize
    //change icon minisize
    changeIcon() {
        var miniButton = document.querySelector(".buttonMinisize");
        var i = miniButton.querySelector("i");
        if(i.classList.contains("fa-chevron-left")){
            i.classList.remove("fa-chevron-left");
            i.classList.add("fa-chevron-right");
        } else {
            i.classList.remove("fa-chevron-right");
            i.classList.add("fa-chevron-left");
        }
    }
    // toggle minisize
    toMiniSize(){
        var leftbar = document.querySelector(".left-bar");
        leftbar.classList.toggle("minisize");
        var listmenu = Array.from(document.getElementsByClassName("menu-item"));
        if(listmenu){
            listmenu.forEach(item => {
                item.querySelector("div").classList.toggle("hide");
            })
        }
        document.querySelector(".right-content").classList.toggle("changeMargin");
    }

    // validate salary
    getNumber(str){
        var arr = str.split('');
        var res = new Array();
        for(var i = 0; i < arr.length; i++){
            if(isNaN(arr[i]) === false){
                res.push(arr[i]);
            }
        }
        return Number(res.join(''));
    }
    updateTextView(obj){
        var num = this.getNumber(obj.value);
        if(num == 0){
            obj.value = '';
        } else {
            obj.value = num.toLocaleString('it-IT');
        }
    }

    validateNumber(e){
        const pattern = /^[0-9]$/;
        return pattern.test(e.key);
    }
}
