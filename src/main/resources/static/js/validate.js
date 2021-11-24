let y;

if ( window.history.replaceState ) {
    window.history.replaceState( null, null, window.location.href );
}

function validate() {
    if (!validateY()||!validateXR());
}

function validateY() {
    let yText = document.getElementById("coordinates-form:y");
    y = yText.value.trim();
    if (/^-?\d+\.?\d*$/.test(y)) {
        if (y >= 5 || y <= -5) {
            yText.setCustomValidity("Число вне допустимого диапазона");
            return false;
        } else {
            yText.setCustomValidity("")
            return true;
        }
    } else {
        yText.setCustomValidity("Некорректный вид числа");
        return false;
    }
}

function validateXR() {
    let xText = document.getElementById("coordinates-form:x");
    let rText = document.getElementById("coordinates-form:rVal");
    const x = xText.value;
    const r = rText.value.trim();
    const rValues = [1, 1.25, 1.5, 1.75, 2, 2.25, 2.5, 2.75, 3, 3.25, 3.5, 3.75, 4];
    if (rValues.indexOf(r)==-1) {
        rText.setCustomValidity("Число вне допустимого диапазона");
    }
}