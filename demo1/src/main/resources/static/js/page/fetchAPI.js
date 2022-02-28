import MyFunc from '../common/function.js';
import Api from '../service/Api.js';
import FormatData from '../common/formatData.js';

const func = new MyFunc();
/**
 * get list employee and show in table
 * author: nlanh
 */
function initTableData() {
    var listEmployee = Api.getAPI("http://cukcuk.manhnv.net/v1/Employees");
    if(listEmployee){
        listEmployee.then(
            data => {  
                bindingToTable(data);
            }
        )
    }
}

/**
 * format data and bind into table
 * author: nlanh
 */
function bindingToTable(data){
    let tbody = $("#listEmployee tbody");
    tbody.empty();
    let ths = $("table thead th");                            
    Array.from(data).forEach(element => {
        let tr = $(`<tr row-id=${element.EmployeeId}></tr>`);
        tr.append(`<td class="checkbox">
                        <span class="custom-checkbox"><i class="fas fa-check"></i></span>
                    </td>`)
        $.each(ths, (idx, item) => {
            let tdData = $(item).attr("td-data");
            if(tdData){
                let data = element[tdData];
                data = FormatData.formatNull(data);
                switch($(item).attr("format")){
                    case "date": {
                        data = FormatData.formatDate(data);
                        tr.append(`<td style="text-align:center">${data}</td>`);
                        break;
                    }
                    case "salary": {
                        data = FormatData.formatSalary(data);
                        tr.append(`<td style="text-align:right">${data}</td>`);
                        break;
                    }
                    case "work-status": {
                        data = FormatData.formatWorkStatus(data);
                        tr.append(`<td>${data}</td>`);
                        break;
                    }
                    default: {
                        tr.append(`<td>${data}</td>`);
                    }
                }
            }                        
        }) 
        tbody.append(tr);
    });
}

/**
 * action on click , dblclick in table row, click checkbox
 * author: nlanh
 */
function actionTable() {
    var tbody = $("#listEmployee tbody");

    tbody.on('click', 'tr', function(){
        // $(this).siblings().removeClass("tr-active");
        // $(this).siblings().find(".custom-checkbox").removeClass("active");
        $(this).toggleClass("tr-active");
        $(this).find(".custom-checkbox").toggleClass("active");
    })
    tbody.on("dblclick", 'tr', function(){
        let id = $(this).attr("row-id");
        showDetail(id);
    })

    let selectAll = $("#select-all");
    selectAll.click(() => {
        selectAll.toggleClass("active");
        if(selectAll[0].classList.contains("active")){
            $(".custom-checkbox").addClass("active");
            $("#listEmployee tbody tr").addClass("tr-active");
        } else {
            $(".custom-checkbox").removeClass("active");
            $("#listEmployee tbody tr").removeClass("tr-active");
        }
    })
}

/**
 * function show modal employee detail and update employee
 * author: nlanh
 */
function showDetail(id){
    let employee = Api.getAPI("http://cukcuk.manhnv.net/v1/Employees/" + id);
    employee.then(
        data => {
            func.showModal(true);
            let inputs = $("#addEmployeeForm").find("input");
            $.each(inputs, (idx, item) => {
                let name = $(item).attr("name");
                if($(item).attr("type") == "date"){
                    data[name] = FormatData.formatDateOutput(data[name]);
                } else if($(item).attr("format") == 'salary'){
                    data[name] = FormatData.formatSalary(data[name]);
                }
                if($(item).attr("select") !== undefined){
                    $(item).parent().parent().data("setValue")(data[name]);
                    if(data[name] === "" || data[name] == undefined || data[name] == null){
                        $(item).val(data[name]);
                        $(item).parent().next().find("div").removeClass("same-as-selected");
                    } else{
                        $(item).parent().next().children().each((idx, ele) => {
                            if($(ele).attr("value") == data[name]){
                                $(ele).click();
                            }
                        })
                    }                    
                }else 
                    $(item).val(data[name]);
                
            })
        }
    )
}


/**
 * function resolve some action with form in dialog like: cancle, submit,...
 * author: nlanh
 */
function formAction(){
    let inputs = $("#addEmployeeForm").find("input").not(':input[type=file]');
    $.each(inputs, (idx, item) => {
        $(item).on("input", () => {
            $(item).parent().removeClass("error");
        })
        $(item).on("blur", () => {
            validateInput(item);
        })
    })
    $("#cancelBtn").click(() => {
        func.closeModal();
    })
    $("#submitBtn").click(() => {
        let validate = -1;
        let formData = new FormData();
        $.each(inputs, (idx, item) => {
            if($(item).attr("type") !== "hidden"){
                let val = $(item).val();
                val = validateInput(item);
                if(val === false){
                    $(item).focus();
                    if(validate == -1){
                        validate = idx;
                    }
                    return false;
                } else if(val != ""){
                    if($(item).attr("select") == undefined)
                        formData[$(item).attr("id")] = val;
                    else {
                        formData[$(item).attr("id")] = $(item).parent().parent().data("getValue")();
                    }
                }
            }
        })
        if(validate == -1){
            let res;
            let isDetail = $("#addEmployeeForm").attr("is-detail");
            let info = "Thêm nhân viên thành công!";
            if(isDetail == "true"){
                let id = $("#employeeId").val();
                res = Api.putApi("http://cukcuk.manhnv.net/v1/Employees/" + id, formData);
                info = "Đã lưu thay đổi!";
            }else {
                res = Api.postAPI("http://cukcuk.manhnv.net/v1/Employees", formData);
            } 
            res.then(
                response => {
                    if(response == 1){
                        window.alert(info);
                        func.closeModal();
                        initTableData();
                    }
                },
                error => {
                    window.alert("Đã có lỗi xảy ra!");
                }
            )
        }        
    })

    $("#submitBtn").on("keydown", function(e){
        if(e.keyCode == 13) {
            $(this).click();
        }
    })
}

/**
 * onclick clearBtn in input field
 * author: nlanh
 */
function inputClearBtn(){
    //show clear button on keyup input
    var listInput = Array.from(document.getElementsByClassName("search-box"));
    listInput.forEach(item => {
        var input = item.querySelector("input");
        var clearBtn = item.querySelector(".clearBtn");
        if(input && input.type == 'number'){
            clearBtn.style.right = '25px';
        }
        if(input && (input.type == 'text' || input.type == 'number') && clearBtn){   
            //on input
            input.addEventListener("input", () => {            
                clearBtn.style.display = 'block';
                if(input.value == "")
                    clearBtn.style.display = "none";
            })

            //onclick clearBtn
            clearBtn.addEventListener("click", () => {
                input.value = "";
                clearBtn.style.display = "none";
            })
        }        
    })
}

/** 
 * function validate input with required, some format and combobox;
 */
function validateInput(item){
    let validate = true;
    let val = $(item).val();
    let required = $(item).attr("required");
    if($(item).attr("type") !== "hidden"){
        if(required && !val){                
            validate = false;
        } else if(val) {
            let check = "";
            switch($(item).attr("format")){
                case "phoneNumber": {
                    check = FormatData.validatePhoneNumber(val);
                    break;
                }
                case "salary": {
                    val = val.replaceAll(".", "");
                    check = FormatData.validateMoney(val);
                    break;
                }
                case "identity": {
                    check = FormatData.validateIdentityNumber(val);
                    break;
                }
                case "taxCode": {
                    check = FormatData.validateTaxCode(val);
                    break;
                }
                case "email": {
                    check = FormatData.validateEmail(val);
                    break;
                }
            }
            if($(item).parent().parent()[0].classList.contains("error")){
                check = "Dữ liệu không tồn tại trong hệ thống!";
            }
            if(check){
                $(item).parent().find(".inputMessage").find("p").text(check);
                validate = false;
            }
        }
        if(!validate){
            $(item).parent().addClass("error");
            return false;
        } else {
            return val;
        }
    }
    return true;
}

/**
 * reset all input in form: hidden input, custom select input
 * author: nlanh
 */
function formReset(form){
    form.reset();
    $(form).find("input[type=hidden]").val("");
    $(form).find("input").each((idx, item) => {
        $(item).parent().removeClass("error");
        $(item).parent().parent().removeClass("error");
    })
    $(form).find(".select-selected").each((idx, item) => {
        $(item).parent().data("setValue")("");
        $(item).next().find("div").first().click();
    });

}

/**
 * check selected row to delete
 * author: nlanh
 */
function deleteEmployee(){
    let list = $("#listEmployee tbody").find(".checkbox .active");
    $.each(list, function (idx, item) { 
        let employeeId = $(item).parent().parent().attr("row-id");
        let res = Api.deleteData("http://cukcuk.manhnv.net/v1/Employees/" + employeeId);
        res.then(
            response => {
                if(response == 1){
                    console.log("Xoa thanh cong");
                    $("[row-id=" + employeeId + "]").remove();
                } else {
                    console.log("response khong bang 1");
                }
            },
            error => {
                console.log("co loi xay ra");
            }
        )
    });
}

/**
 * onclick button delete show confirm dialog and call deleteEmployee()
 * author: nlanh
 */
function onClickButtonDelete(){
    $("#deleteBtn").on("click", function() {
        if(!$("#listEmployee tbody").find(".checkbox .active").length){
            window.alert("Bạn chưa chọn nhân viên nào để xóa!");
        } else {            
            $("#confirm-delete").css({display: "block"});
            $("#confirm-delete").find(".confirm-text").text("Bạn có chắc chắn muốn xóa các nhân viên đã chọn không?");
        }
    })

    $("#confirm-delete").find(".cancel").on("click", function() {
        $("#confirm-delete").css({display: "none"});
        $("#confirm-delete").find(".confirm-text").text("");
    })
    $("#confirm-delete").find(".confirm").on("click", function() {
        deleteEmployee();
        $("#confirm-delete").css({display: "none"});
    })
    $("#confirm-delete").find(".closeBtn").click(function(){
        $("#confirm-delete").css({display: "none"});
    })
}

/**
 * init list page number in paging when callApi
 * author: nlanh
 */
function initPaging(currPage, totalPages){
    let tmp = Math.floor(Number(currPage)/4);
    let start = tmp * 4 + 1;
    let end = start + 3;
    if(end > totalPages){
        end = totalPages;
    }
    let pageNums = $(".pageNums");
    pageNums.empty();
    for(let i = 1; i<= totalPages; i++){
        let pageNum = $(`<div class="pageNum">${i}</div>`);
        if(i < start || i > end){
            $(pageNum).addClass("hide");
        }
        if(i == (currPage + 1)){
            $(pageNum).addClass("active");
        }
        pageNums.append(pageNum);
    }
}

/**
 * filter employee by searchkey, department, postion and by page
 * author: nlanh
 */
function filterEmployee(formData){
    let start = Number(formData["pageNumber"]) + 1;
    let end  = start + Number(formData["pageSize"]) - 1;
    let listEmployee = Api.getApiWithParams("http://cukcuk.manhnv.net/v1/Employees/employeeFilter", formData);
    if(listEmployee){
        listEmployee.then(
            data => {
                if(data){
                    bindingToTable(data.Data);
                    $(".showing b").text(start + "-" + end + "/" + data.TotalRecord);
                    let currentPage = Math.floor(Number(formData["pageNumber"])/Number(formData["pageSize"]));
                    initPaging(currentPage, data.TotalPage);
                }                
            },
            error => {
                console.log(error);
            }
        )
    }
}

/**
 * call filterEmployee() on change combobox ...
 * author: nlanh
 */
function formFilter(){
    let formData = {
        pageSize: 10,
        pageNumber: 0,
        employeeFilter: "nv",
        departmentId: "",
        positionId: ""
    };
    let employeeFilter = $(".search-form .search-box input");
    let departmentFiter = $("#filterDepartment");
    let positionFilter = $("#filterPosition");
    let pageSize = $(".pagesize input");
    employeeFilter.on("input", () => {
        if(employeeFilter.val() !== ""){
            formData["employeeFilter"] = employeeFilter.val();
        } else {
            formData["employeeFilter"] = "nv";
        }       
        formData["pageNumber"] = 0;
        filterEmployee(formData);
    })
    $(departmentFiter).on("selected-change", function(){
        let val = $(this).data("getValue")();
        if(val != "0"){
            formData["departmentId"] = val;
        } else {
            formData["departmentId"] = "";
        }
        formData["pageNumber"] = 0;
        filterEmployee(formData);
    })
    $(positionFilter).on("selected-change", function(){
        let val = $(this).data("getValue")();
        if(val != "0"){
            formData["positionId"] = val;
        } else {
            formData["positionId"] = "";
        }
        formData["pageNumber"] = 0;
        filterEmployee(formData);
    })

    $(pageSize).on("change", function(){
        if($(this).val() != ""){
            formData["pageSize"] = $(this).val();
        } else {
            $(this).val("10");
            formData["pageSize"] = 10;
        }
        formData["pageNumber"] = 0;
        filterEmployee(formData);
    })
    $(".pageNums").on("click", ".pageNum", function(){
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
        formData["pageNumber"] = (Number($(this).text()) - 1) * Number(formData["pageSize"]);
        filterEmployee(formData);
    })

    $("#nextPage").on("click", function(){
        $(".pageNums .active").next().click();
    })

    $("#prevPage").on("click", function(){
        $(".pageNums .active").prev().click();
    })

    $("#lastPage").on("click", function(){
        $(".pageNums .pageNum").last().click();
    })

    $("#firstPage").on("click", function(){
        $(".pageNums .pageNum").first().click();
    })
}

document.addEventListener("DOMContentLoaded", function(){
    initTableData();
    actionTable();
    formAction();
    inputClearBtn();
    onClickButtonDelete();
    formFilter();

    //create dialog input form with jquery-ui
    let dialog = $("#m-dialog-employee").dialog({
        autoOpen: false,
        width: 1200,
        modal: true,
        close: function() {
            formReset( $("#addEmployeeForm")[0]);
        }
    });
    $(document).on("click", (e) => {
        let outside = $(".ui-widget-overlay.ui-front")[0];
        if(e.target == outside){
            func.closeModal();
        }
    })
    //event on click refresh button
    $("#refresh").on("click", () => {
        initTableData();
    })
}, false)
