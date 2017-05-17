function AjaxTake() {
    var request = new XMLHttpRequest();
    request.open('GET','URL');
    request.send;
    
    request.onreadystatechange = function(){
        if(request.readyState == 4 && request.status == 200)
            processResult(request.responseText);
    }
}

function processResult (output) {
    var div = document.getElementById('divResult');
    div.innerHTML = output;
}

window.onload = function(){
    /*var inpunt = document.getElementById('btnTake');
    AjaxTake(input.value);*/
    AjaxTake();
}