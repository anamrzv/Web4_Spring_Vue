window.onload = function () {
    prepareClock();
    setInterval(prepareClock, 9000);
};

function prepareClock(){
    const now = new Date();
    const dayNo = now.getDay();
    const day = now.getDate();
    const year = now.getFullYear();
    const month = now.getMonth();
    const months = ["Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря"];
    const dayOfWeek = ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"];
    const clock = document.getElementById("clock");
    const date = document.getElementById("date");
    const result = dayOfWeek[dayNo] + ", " + day + " " + months[month] + " " + year;
    clock.innerHTML = now.toLocaleTimeString();
    date.innerHTML = result;
}