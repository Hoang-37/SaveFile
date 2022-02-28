
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
    var pageNum = Array.from(document.getElementsByClassName("pageNum"));
    pageNum.forEach(item => {
        item.addEventListener("click", () => {
            pageNum.forEach(item1 => {
                if(item1.classList && item1.classList.contains("active"))
                    item1.classList.remove("active");
            })
            item.classList.add("active");
        })
    })

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
    var mymodal = document.getElementById("detail");
    var table = document.getElementById("mytable");
    var row = Array.from(document.querySelector("tbody").getElementsByTagName("tr"));
    row.forEach(item => {
        item.addEventListener('click', () => {
            func.showModal();
        })
    })
    window.onclick = function(event) {
        if (event.target == mymodal) {
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

    document.getElementById('closeBtn').onclick = func.closeModal;

    var inputSalary = document.getElementById('salary');
    inputSalary.oninput = () => {
        func.updateTextView(inputSalary);
    }

    //show clear button on keyup input
    var listInput = Array.from(document.getElementsByClassName("search-box"));
    listInput.forEach(item => {
        var input = item.querySelector("input");
        var clearBtn = item.querySelector(".clearBtn");
        if(input && input.type == 'number'){
            clearBtn.style.right = '25px';
        }
        if(input && (input.type == 'text' || input.type == 'number') && clearBtn){            
            input.addEventListener("input", () => {            
                clearBtn.style.display = 'block';
                if(input.value == "")
                    clearBtn.style.display = "none";
            })
            clearBtn.addEventListener("click", () => {
                input.value = "";
                clearBtn.style.display = "none";
            })
        }        
    })
    //catch event onchange select
    document.getElementById("gender").addEventListener("change", (e) => {
        console.log(e.target.value);
    })
    document.getElementById("position").addEventListener("change", (e) => {
        console.log(e.target.value);
    })
}, false)