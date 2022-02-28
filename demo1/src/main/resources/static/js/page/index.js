
import MyFunc from '../common/function.js';
const func = new MyFunc();

document.addEventListener('DOMContentLoaded', function(){
    // change menu side bar 
    var sidelist = document.querySelector(".list-menu").childNodes;
    sidelist.forEach(item => {
        if(item.classList && item.classList.contains('menu-item')){
            item.addEventListener('click', () => {
                sidelist.forEach(child => {
                    if(child.classList && child.classList.contains('active'))
                        child.classList.remove('active');
                })
                item.classList.add('active');
            })
        }
    })
    //change page
    // var pageNum = Array.from(document.getElementsByClassName("pageNum"));
    // pageNum.forEach(item => {
    //     item.addEventListener("click", () => {
    //         pageNum.forEach(item1 => {
    //             if(item1.classList && item1.classList.contains("active"))
    //                 item1.classList.remove("active");
    //         })
    //         item.classList.add("active");
    //     })
    // })
    document.querySelector(".toggle-icon").addEventListener("click", () => {
        func.toMiniSize();
        func.changeIcon();
    });
    var miniButton = document.querySelector(".buttonMinisize");
    
    miniButton.addEventListener("click", () => {
        func.toMiniSize();
        func.changeIcon();
    });

    //onclick table row show modal 
    var mymodal = $(".ui-widget-overlay.ui-front");
    var row = Array.from(document.querySelector("tbody").getElementsByTagName("tr"));
    row.forEach(item => {
        item.addEventListener('click', () => {
            func.showModal();
        })
    })
    window.onclick = function(event) {
        if (event.target == mymodal[0]) {
            func.closeModal();
        }
    }
    //action in modal
    var fileInput = document.getElementById("avatar");
    var imgavatar = document.getElementById("imgavatar");
    imgavatar.addEventListener("click", () => {
        fileInput.click();
    })

    //onclick button them nhan vien show modal
    document.getElementById("addEmployee").addEventListener("click", () => func.showModal());

    var inputSalary = document.getElementById('salary');
    inputSalary.oninput = () => {
        func.updateTextView(inputSalary);
    }

    $('input[input-format="number"]').keypress(function(e){
        return func.validateNumber(e);
    })

    $(".pagesize input").keypress(function (evt) {
        evt.preventDefault();
    });
    
    //catch event onchange select
    // document.getElementById("gender").addEventListener("change", (e) => {
    //     console.log(e.target.value);
    // })
    // document.getElementById("position").addEventListener("change", (e) => {
    //     console.log(e.target.value);
    // })
}, false)