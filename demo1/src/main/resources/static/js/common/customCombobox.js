import Api from '../service/Api.js';

function customCombobox(){
    let comboboxes = $(".my-custom-select");
   
    $.each(comboboxes, function (idx, item) { 
        $(item).data({getValue: function(){
            return $(item).data("value");
        }})
        $(item).data({setValue: function(value){
            $(item).data({value: value});
            $(item).trigger("selected-change");
        }})
        $(item).data({getText: function(){
            return $(item).find("input").val();
        }})
        $(item).data({setText: function(value){
            $(item).find(".clearBtn").css({display: "block"});
            $(item).find("input").val(value);
        }})
        $(item).data({reset: function(){
            let data = $(item).data("selectOptions");
            let nameValue = $(item).attr("name-value");
            let nameText = $(item).attr("name-text");
            $(item).data("setValue")(data[0][nameValue]);
            $(item).data("setText")(data[0][nameText]);
        }})
        $(item).data({initOption: function(){
            initOption(item);
        }})
    });

    /**
     * function append options to combobox from data of element
     * author: nlanh
     */
    function initOption(combobox){
        let data = $(combobox).data("selectOptions");
        let select = $(combobox).find(".select-items");
        let input = $(combobox).find("input");
        let nameValue = $(combobox).attr("name-value");
        let nameText = $(combobox).attr("name-text");
        $(select).empty();
        if(data){
            Array.from(data).forEach((item, idx) => {
                var option;                
                if(idx == 0){
                    option = `<div class="same-as-selected" value="${item[nameValue]}">${item[nameText]}</div>`;
                    $(combobox).data("setText")(item[nameText]);
                    $(combobox).data("setValue")(item[nameValue]);
                } else {
                    option = `<div value="${item[nameValue]}">${item[nameText]}</div>`;
                }            
                select.append(option);
            })
        }        
        $(input).on("input focus", function(){
            let value = $(this).val().toLowerCase();
            let first = -1;
            $(select).removeClass("select-hide"); 
            $(select).parent().addClass("select-arrow-active");
            if(value === ""){
                $(combobox).find(".clearBtn").css({display: "none"});
            }
            $.each(data, (indexInArray, valueOfElement) => { 
                if(!valueOfElement[nameText].toLowerCase().startsWith(value)){
                    $(select).find("div")[indexInArray].classList.add("hide");
                } else {
                    $(select).find("div")[indexInArray].classList.remove("hide");
                    if(first == -1){
                        first = indexInArray;
                    }
                }
            });
            if(first == -1){
                $(select).parent().addClass("error");
                $(select).prev().attr("title", "Dữ liệu không tồn tại trong hệ thống");
            } else {
                $(select).parent().removeClass("error");
                $(select).prev().removeAttr("title");
                $(select).find("div").removeClass("hover");
                $(select).find("div")[first].classList.add("hover");
                $(select).find("div")[first].classList.remove("same-as-selected");
            }
        })
        $(input).on("blur", function(e){
            let value = $(this).val().toLowerCase();
            $.each(data, (indexInArray, valueOfElement) => {
                if(valueOfElement[nameText].toLowerCase() == value){
                    $(select).find("div")[indexInArray].click();
                    return false;
                }
            })
        })
    }
}

/**
 * handle some event in custom select: onclick select show options, ...
 * author: nlanh
 */
function initCustomSelect(){
    $(document).click((e) => {
        closeAllSelect();
    })
    $(".select-items").each((index, item) => {
        let parent = $(item).parent();
        let input = $(item).prev().children("input");
        let seletor = $(item).prev();
        let selectIcon = $(parent).find("span");
        input.val($(item).children().first().text());
        $(selectIcon).click(function(e){
            e.stopPropagation();
            closeAllSelect(seletor);
            $(item).toggleClass("select-hide"); 
            $(parent).toggleClass("select-arrow-active");  
            $(item).find("div").each((idx, e) => {
                e.classList.remove("hide");
                e.classList.remove("hover");
            })            
            
        })
        $(seletor).children("input").on("focus click", function(e){
            e.stopPropagation();
            closeAllSelect(seletor);
            $(item).removeClass("select-hide"); 
            $(seletor).parent().addClass("select-arrow-active");
            $(item).find("div").removeClass("same-as-selected");
        })
        $(item).on("click", "div", function(){
            $(parent).data("setText")($(this).text());
            $(parent).data("setValue")($(this).attr("value"));
            $(this).siblings().removeClass("same-as-selected");
            $(this).addClass("same-as-selected");
            $(item).addClass("select-hide");
            // $(seletor).children("input").focus();
        })
        $(seletor).find(".clearBtn").on("click", function(){
            $(parent).data("reset")();
        })
    })
    $(".select-selected").on("keydown", function (e) { 
        var focus = $(this).next().find("div.hover");
        let input = $(this).children("input");
        switch(e.keyCode){
            case 40: {   
                // $(selector).children().removeClass("same-as-selected");             
                // focus.next().addClass("same-as-selected");
                if(focus.next()[0] && !focus.next()[0].classList.contains("hide")){
                    focus.removeClass("hover");
                    focus.next().addClass("hover")
                }        
                break;
            }
            case 38: {
                console.log("sao khong vao day?");
                // $(selector).children().removeClass("same-as-selected");   
                // focus.prev().addClass("same-as-selected");
                if(focus.prev()[0] && !focus.prev()[0].classList.contains("hide")){
                    focus.prev().addClass("hover")
                    focus.removeClass("hover");
                }
                break;
            } 
            case 13: {
                focus.click();
            }
        }
    });
    
    $("input").focus(function (e) {
        if(!e.target.parentNode.classList.contains("select-selected")){
            closeAllSelect();
        }
    })
}

/** 
 * function close all select when click outside ỏ another input
 * author: nlanh
 */
 function closeAllSelect(seletor){
    let item;
    if(seletor){
        item = seletor.next();
    }
    $(".select-items").not($(item)).addClass("select-hide");
    $(".select-selected").not($(seletor)).parent().removeClass("select-arrow-active");
}

/**
 * function get data from server and bind into combobox
 * author: nlanh
 */
function bindingDataToSelect(){
    let comboboxes = $(".my-custom-select");
    $.each(comboboxes, function (idx, item) { 
        let url = $(item).attr("url");
        let defaultVal = $(item).attr("default-value");
        let defaultText = $(item).attr("default-text");
        let nameValue = $(item).attr("name-value");
        let nameText = $(item).attr("name-text");
        if(url){
            let res = Api.getAPI(url);
            res.then(
                data => {
                    if(defaultVal){
                        let tmp = {};
                        tmp[nameValue] = defaultVal;
                        tmp[nameText] = defaultText;
                        data.unshift(tmp);
                    }
                    $(item).data({selectOptions: data});
                    $(item).data("initOption")();
                }
            )
        }
    });
    let listWorkStatus = [
        {
            WorkStatus: "1",
            WorkStatusText: "Đang làm việc"
        },
        {
            WorkStatus: "2",
            WorkStatusText: "Đang học việc"
        },
        {
            WorkStatus: "3",
            WorkStatusText: "Đã nghỉ việc"
        }
    ]
    $("#selectWorkStatus").data({selectOptions: listWorkStatus});
    $("#selectWorkStatus").data("initOption")();

    let listGender = [
        {
            Gender: "1",
            GenderName: "Nam"
        },
        {
            Gender: "0",
            GenderName: "Nữ"
        },
        {
            Gender: "2",
            GenderName: "Không xác định"
        }
    ]
    $("#selectGender").data({selectOptions: listGender});
    $("#selectGender").data("initOption")();
}

$(document).ready(function(){
    customCombobox();
    initCustomSelect();
    bindingDataToSelect();
})