import MyFunc from '../common/function.js';
import Api from '../service/Api.js';

const func = new MyFunc();
const api = new Api();
/**
 * get list employee and show in table
 * author: nlanh
 */
function initTableData() {
    var listEmployee = api.getAPI("http://cukcuk.manhnv.net/v1/Employees");
    var tbody = $("#listEmployee tbody");
    if(listEmployee){
        tbody.empty();
        listEmployee.then(
            data => {                
                Array.from(data).forEach(element => {
                    let birthDate = formatDate(element.DateOfBirth);
                    let salary = formatSalary(element.Salary);
                    let phoneNum = element.PhoneNumber;
                    if(!phoneNum){
                        phoneNum = '';
                    }
                    let email = element.Email;
                    if(!email){
                        email = '';
                    }
                    let tr = document.createElement("tr");
                    tr.innerHTML = "<td>" + element.EmployeeCode +"</td>" +
                    "<td>" + element.FullName +"</td>" +
                    "<td>" + element.Gender + "</td>" +
                    "<td class=\"text-center\">" + birthDate +"</td>" +
                    "<td>" + phoneNum +"</td>" +
                    "<td>" + email +"</td>" +
                    "<td>" + element.PositionId +"</td>" +
                    "<td>" + element.DepartmentId +"</td>" +
                    "<td class=\"text-right\">" + salary +"</td>" +
                    "<td>" + element.WorkStatus +"</td>"
                    tbody.append(tr);
                });
            }
        )
    }
    tbody.on('click', 'tr', function(){
        $(this).siblings().removeClass("tr-active");
        $(this).toggleClass("tr-active");
    })
    tbody.on("dblclick", 'tr', function(){
        func.showModal();
    })
}


/**
 * excute API to get list department and append to select option
 * author: nlanh
 */
function initSelectDepartment(){
    let listDepartment = api.getAPI("http://cukcuk.manhnv.net/api/Department");
    let select = $("#filterDepartment").parent().next();
    let select1 = $("#department").parent().next();
    select.empty();
    select1.empty();
    select.append(`<div class="same-as-selected" value="0">Tất cả phòng ban</div>`);
    select1.append(`<div class="same-as-selected" value="0">Tất cả phòng ban</div>`);
    if(listDepartment){
        listDepartment.then(
            data => {
                Array.from(data).forEach(item => {
                    var option = `<div value="${item.DepartmentId}">${item.DepartmentName}</div>`;
                    select.append(option);
                    select1.append(option);
                })
            }
        )
    }
}

/**
 * ---5/7/2021---
 * function format birth date
 * Author: nlanh
 */
function formatDate(date){
    if(!date){
        date = '';
    } else {
        date = new Date(date).toLocaleDateString("en-GB");
    }
    return date;
}
/**
 * function format salary
 */
function formatSalary(salary) {
    if(!salary || !Number(salary)){
        return '';
    } else {
        return Number(salary).toLocaleString('it-IT');
    }
}

/**
 * handle some event in custom select: onclick select show options, ...
 * author: nlanh
 */
function initCustomSelect(){
    $(".select-items").each((index, item) => {
        let input = $(item).prev().children("input");
        let seletor = $(item).prev();
        input.val($(item).children().first().text());
        $(seletor).click(function(e){
            e.stopPropagation();
            if(e.target == seletor[0]){
                closeAllSelect(seletor);
                $(item).toggleClass("select-hide"); 
                $(this).toggleClass("select-arrow-active");  
            }                 
            
        })
        $(seletor).children("input").on("focus", function(e){
            e.stopPropagation();
            closeAllSelect(seletor);
            $(item).removeClass("select-hide"); 
            $(seletor).addClass("select-arrow-active");
        })
        $(item).on("click", "div", function(){
            input.val($(this).text());
            input.attr("selected-value", $(this).attr("value"));
            $(this).siblings().removeClass("same-as-selected");
            $(this).addClass("same-as-selected");
            $(item).addClass("select-hide");
            // $(seletor).children("input").focus();
        })
    })
    $(".select-selected").on("keydown", function (e) { 
        var focus = $(this).next().find(".same-as-selected");
        let input = $(this).children("input");
        switch(e.keyCode){
            case 40: {   
                // $(selector).children().removeClass("same-as-selected");             
                // focus.next().addClass("same-as-selected");
                focus.next().click();
                input.focus();
                break;
            }
            case 38: {
                // $(selector).children().removeClass("same-as-selected");   
                // focus.prev().addClass("same-as-selected");
                focus.prev().click();
                input.focus();
                break;
            } 
            case 13: {
                focus.click();
            }
        }
    });
    $(document).click((e) => {
        closeAllSelect();
    })
    $("input").focus(function (e) {
        if(!e.target.parentNode.classList.contains("select-selected")){
            closeAllSelect();
        }
    })
}

function closeAllSelect(seletor){
    let item;
    if(seletor){
        item = seletor.next();
    }
    $(".select-items").not($(item)).addClass("select-hide");
    $(".select-selected").not($(seletor)).removeClass("select-arrow-active");
}



function formAction(){
    $("#cancelBtn").click(() => {
        func.closeModal();
    })
    $("#submitBtn").click(() => {
        let validate = true;
        let formData = new FormData();
        formData["employeeCode"] = $("#employeeCode").val();
        formData["fullName"] = $("#fullname").val();
        formData["dateOfBirth"] = $("#birthDate").val();
        formData["gender"] = $("#gender").attr("selected-value");
        formData["identityNumber"] = $("#identityNumber").val();
        formData["identityDate"] = $("#identityDate").val();
        formData["identityPlace"] = $("#identityPlace").val();
        formData["email"] = $("#email").val();
        formData["phoneNumber"] = $("#phoneNumber").val();
        formData["departmentId"] = $("#departmentId").attr("selected-value");
        formData["personalTaxCode"] = $("#personalTaxCode").val();
        formData["salary"] = $("#salary").val().replaceAll('.', '');;
        formData["joinDate"] = $("#joinDate").val();
        formData["workStatus"] = $("#workStatus").attr("selected-value");
        if(validate){
            // let res = api.postAPI("http://cukcuk.manhnv.net/v1/Employees", formData);
            // res.then(
            //     response => {
            //         if(response == 1){
            //             window.alert("Thêm nhân viên thành công");
            //             func.closeModal();
            //         }
            //     },
            //     error => {
            //         window.alert("Đã có lỗi xảy ra!");
            //     }
            // )
        }        
    })
}

document.addEventListener("DOMContentLoaded", function(){
    initTableData();
    initSelectDepartment();
    initCustomSelect();
    formAction();
    console.log($("#state").attr("selected-value"));
}, false)
