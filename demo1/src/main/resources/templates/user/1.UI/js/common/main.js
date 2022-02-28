// custom select js 
document.addEventListener("DOMContentLoaded", function(){
    var x = document.getElementsByClassName("my-custom-select");
    var l = x.length;
    for(var i = 0; i<l; i++){
        let selectEle = x[i].getElementsByTagName("select")[0]; //select
        let ll = selectEle.length; //number of option
        // create new custom select 
        let a = document.createElement("div");
        a.setAttribute("class", "select-selected");
        a.innerHTML = selectEle.options[selectEle.selectedIndex].innerHTML;
        x[i].appendChild(a);

        // create options list 
        let b = document.createElement("div");
        b.setAttribute("class", "select-items select-hide");
        for(let j = 0; j < ll; j++){
            // create option 
            let c = document.createElement("div");
            c.innerHTML = selectEle.options[j].innerHTML;
            if(j == selectEle.selectedIndex)
                c.setAttribute("class", "same-as-selected");
            c.addEventListener("click", (e) => {
                a.innerHTML = c.innerHTML;
                for(let m = 0; m < ll; m++){
                    if(c.innerHTML == selectEle.options[m].innerHTML){
                        selectEle.selectedIndex = m;
                        let event = new Event('change');
                        selectEle.dispatchEvent(event);
                    }
                }
                Array.from(b.getElementsByClassName("same-as-selected")).forEach(item => {
                    item.removeAttribute("class");
                })
                c.setAttribute("class", "same-as-selected");
                a.click();
            })
            b.appendChild(c);
        }
        x[i].appendChild(b);

        a.addEventListener("click", (e) => {
            e.stopPropagation();
            closeAllSelect(a);  
            a.nextSibling.classList.toggle("select-hide");
            a.classList.toggle("select-arrow-active");          
        })
    }
    function closeAllSelect(elmnt) {
        let x, y, i, xl, yl, arrNo = [];
        x = document.getElementsByClassName("select-items");
        y = document.getElementsByClassName("select-selected");
        xl = x.length;
        yl = y.length;
        for (i = 0; i < yl; i++) {
            if (elmnt == y[i]) {
            arrNo.push(i)
            } else {
            y[i].classList.remove("select-arrow-active");
            }
        }
        for (i = 0; i < xl; i++) {
            if (arrNo.indexOf(i)) {
            x[i].classList.add("select-hide");
            }
        }
    }
    document.addEventListener("click", closeAllSelect);
}, false);