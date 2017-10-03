/**
 * 
 */

const BASE_PATH = "";

/* Convierte los parametros de la URL en
 * parte de un objeto manipulable */
function getUrlValue() {

    var vars = {};
    var parts = window.location.href.replace(
        /[?&]+([^=&]+)=([^&]*)/gi,
        function (m, key, value) {
            vars[key] = value;
            //console.log(value);
        }
    );

    return vars;

}

function getData(url, data){
	
	//console.log(data);	

	var token = document.querySelector("meta[name='_csrf']").getAttribute("content");
	var header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");	
	var xhr = new XMLHttpRequest();
	
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.setRequestHeader(header, token);
	xhr.send(JSON.stringify(data));
	
	return xhr;
	
}
function postData(url, data){
	
	//console.log(data);	

	var token = document.querySelector("meta[name='_csrf']").getAttribute("content");
	var header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");	
	var xhr = new XMLHttpRequest();
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.setRequestHeader(header, token);
	xhr.send(JSON.stringify(data));
	
	return xhr;
	
}
function setupTable(pages, actual) {
    console.log("Total de paginas: " + pages);
    var ul = document.createElement("ul");
    var li = document.createElement("li");
    var span = document.createElement("span");
    var text = (pages != 0) ? document.createTextNode("Página actual: ") : document.createTextNode("No se encontraron registros");
    span.appendChild(text);
    li.appendChild(span);
    ul.appendChild(li);
    var select = document.createElement("select");
    for (var i = 1; i <= pages; i++) {
        var option = document.createElement("option");
        var optionText = document.createTextNode(i);
        option.setAttribute("value", i);
        option.appendChild(optionText);
        select.appendChild(option);
    }
    var selectLi = document.createElement("li")
    selectLi.appendChild(select);
    ul.appendChild(selectLi);
    (actual != null) ? select.value = actual : null;
    document.querySelector("table tfoot tr td").innerHTML = "";
    document.querySelector("table tfoot tr td").appendChild(ul);
    var paging = document.querySelector("table tfoot li select");
    paging.addEventListener("change", function (event) {
        event.preventDefault();
        makeRequest(event.srcElement.value);
    });
    if (actual != null) {
        if (actual > 1 && pages > 1) {
            var prevLi = document.createElement("li");
            var prevA = document.createElement("a");
            var prevSpanIcon = document.createElement("span");
            prevSpanIcon.setAttribute("class", "icon-previous2");
            prevA.append(prevSpanIcon);
            prevA.setAttribute("href", "#" + (parseInt(actual) - 1));
            prevLi.append(prevA);
            selectLi.parentNode.insertBefore(prevLi, selectLi);
            prevA.onclick = function (event) {
                event.preventDefault();
                makeRequest(event.currentTarget.getAttribute("href").replace("#", ""));
            }
        }
        if (actual != pages && pages > 1) {
            var nextLi = document.createElement("li");
            var nextA = document.createElement("a");
            var nextSpanIcon = document.createElement("span");
            nextSpanIcon.setAttribute("class", "icon-next2");
            nextA.append(nextSpanIcon);
            nextA.setAttribute("href", "#" + (parseInt(actual) + 1));
            nextLi.append(nextA);
            selectLi.parentNode.insertBefore(nextLi, selectLi.nextSibling);
            nextA.onclick = function (event) {
                event.preventDefault();
                makeRequest(event.currentTarget.getAttribute("href").replace("#", ""));
            }
        }
    } else {
        if (pages > 1) {
            var nextLi = document.createElement("li");
            var nextA = document.createElement("a");
            var nextSpanIcon = document.createElement("span");
            nextSpanIcon.setAttribute("class", "icon-next2");
            nextA.append(nextSpanIcon);
            nextA.setAttribute("href", "#2");
            nextLi.append(nextA);
            selectLi.parentNode.insertBefore(nextLi, selectLi.nextSibling);
            nextA.onclick = function (event) {
                event.preventDefault();
                makeRequest(event.currentTarget.getAttribute("href").replace("#", ""));
            }
        }
    }
}

function handleHttpStatus(response) {
    var status = false;
    if (response.status != null) {
        if (response.status == 500) {
            alert(response.error + ": " + response.exception + ", " + response.message);
            status = false;
        } else {
            status = true;
        }
    } else {
        status = true;
    }
    return status;
}