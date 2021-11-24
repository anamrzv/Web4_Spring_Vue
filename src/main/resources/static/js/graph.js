let x, yCoord;
let r;
let svg;

//For points from table
let Xs = [];
let Ys = [];
let results = [];

let graph;
const svgSize = 320;

window.onload = function () {
    graph = document.getElementById("graph_pic");
    graph.addEventListener("click", processCoordinates);
    clearDots();
    loadDotsFromTable();
    if (Xs.length) {
        console.log(Xs);
        drawDots();
    }
}

function processCoordinates(event) {
    const domRect = graph.getBoundingClientRect();
    const rowX = event.clientX - domRect.x;
    const rowY = event.clientY - domRect.y;
    r = document.getElementById("coordinates-form:rVal").value.trim();
    x = ((r * (svgSize / 2 - rowX) * -1) / (320/3)).toFixed(2);
    yCoord = ((r * (svgSize / 2 - rowY)) / (320/3)).toFixed(2);
    sendRequest(x, yCoord, r);
}

function sendRequest(xC, yC, rC) {
    console.log(xC, yC, rC);
    document.getElementById('hidden-form:pointX').value = xC;
    document.getElementById('hidden-form:pointY').value = yC;
    document.getElementById('hidden-form:pointR').value = rC;
    document.getElementById('hidden-form:hidden-btn').click();
}

function drawDots() {
    svg = document.querySelector("svg");
    r = document.getElementById("coordinates-form:rVal").value.trim();
    const svgns = "http://www.w3.org/2000/svg";
    for (let i = 0; i < Xs.length; i++) {
        let thisX = svgSize / 2 + (Xs[i] / r) * 100;
        let thisY = svgSize / 2 - (Ys[i] / r) * 100;
        let newDot = document.createElementNS(svgns, "circle");
        if (results[i] == 'Попадание') {
            newDot.setAttribute("fill", "green");
        } else {
            newDot.setAttribute("fill", "red");
        }
        if (isNaN(thisX) || isNaN(thisY)) {
            newDot.setAttribute("fill", "#c6d9e8");
        }
        newDot.setAttribute("cx", thisX.toString());
        newDot.setAttribute("cy", thisY.toString());
        newDot.setAttribute("r", "5");
        svg.appendChild(newDot);
    }

}

function loadDotsFromTable() {
    let table = document.getElementById("main-table");
    let tRows = table.tBodies[0].rows;
    for (let i = 0; i < tRows.length; ++i) {
        Xs.push(parseFloat(tRows[i].cells[0].innerText));
        Ys.push(parseFloat(tRows[i].cells[1].innerText));
        results.push(tRows[i].cells[4].innerText.trim());
    }
}

function clearDots() {
    Xs = [];
    Ys = [];
    results = [];
}

function redraw() {
    clearDots();
    loadDotsFromTable();
    drawDots();
}

$("input[name=\"coordinates-form:rVal\"]").on('input', function (){
    clearDots();
    loadDotsFromTable();
    drawDots();
})

