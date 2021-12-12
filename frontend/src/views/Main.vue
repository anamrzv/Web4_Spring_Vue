<template>
  <table id="body-table">

    <tr>
      <th class="header">
        <div>
          <p>Морозова Анастасия Александровна P3230</p>
          <p>Вариант 5547</p>
        </div>
      </th>
    </tr>

    <tr class="body">
      <th class="choose-area">
        <div id="graph">
          <svg id="graph_pic" height="320" width="320" xmlns="http://www.w3.org/2000/svg">

            <line stroke="black" x1="0" y1="150" x2="300" y2="150"/>
            <line stroke="black" x1="150" y1="0" x2="150" y2="300"/>
            <polygon fill="black" points="150,0 144,15 156,15" stroke="black"/>
            <polygon fill="black" points="300,150 285,156 285,144" stroke="black"/>

            <line stroke="black" x1="200" y1="155" x2="200" y2="145"/>
            <line stroke="black" x1="250" y1="155" x2="250" y2="145"/>

            <line stroke="black" x1="50" y1="155" x2="50" y2="145"/>
            <line stroke="black" x1="100" y1="155" x2="100" y2="145"/>

            <line stroke="black" x1="145" y1="100" x2="155" y2="100"/>
            <line stroke="black" x1="145" y1="50" x2="155" y2="50"/>

            <line stroke="black" x1="145" y1="200" x2="155" y2="200"/>
            <line stroke="black" x1="145" y1="250" x2="155" y2="250"/>

            <text fill="black" x="195" y="140">R/2</text>
            <text fill="black" x="250" y="140">R</text>

            <text fill="black" x="40" y="140">-R</text>
            <text fill="black" x="85" y="140">-R/2</text>

            <text fill="black" x="160" y="55">R</text>
            <text fill="black" x="160" y="105">R/2</text>

            <text fill="black" x="160" y="204">-R/2</text>
            <text fill="black" x="160" y="255">-R</text>

            <text fill="black" x="285" y="140">X</text>
            <text fill="black" x="160" y="15">Y</text>

            <polygon fill="black"
                     fill-opacity="0.5"
                     stroke="black"
                     points="50,150 150,150 150,50 50,50"/>

            <path fill="black"
                  fill-opacity="0.5"
                  stroke="black"
                  d="M 100 150 A 100 100, 90, 0, 0, 150 200 L 150 150"/>

            <polygon fill="black"
                     fill-opacity="0.5"
                     stroke="black"
                     points="150,150 200,150 150,200"/>
          </svg>
        </div>

        <form id="hidden-form" style="display: none">
          <input type="text" id="pointX"/>
          <input type="text" id="pointY"/>
          <input type="text" id="pointR"/>
          <button type="submit" id="hidden-btn"></button>
        </form>

        <div>
          <form id="coordinates-form">
            <div class="checkbox-area">
              <p>Выберите X:</p>
              <div id="x-group">
                <div class="row">
                  <input class="ordinary-btn" id="-5" type="button" value="-5" name="x"
                         @click="rememberX(-5); paintButtons(-5)">
                  <input class="ordinary-btn" id="-4" type="button" value="-4" name="x"
                         @click="rememberX(-4); paintButtons(-4)">
                  <input class="ordinary-btn" id="-3" type="button" value="-3" name="x"
                         @click="rememberX(-3); paintButtons(-3)">
                </div>
                <div class="row">
                  <input class="ordinary-btn" id="-2" type="button" value="-2" name="x"
                         @click="rememberX(-2); paintButtons(-2)">
                  <input class="ordinary-btn" id="-1" type="button" value="-1" name="x"
                         @click="rememberX(-1); paintButtons(-1)">
                  <input class="ordinary-btn" id="0" type="button" value="0" name="x"
                         @click="rememberX(0); paintButtons(0)">
                </div>
                <div class="row">
                  <input class="ordinary-btn" id="1" type="button" value="1" name="x"
                         @click="rememberX(1); paintButtons(1)">
                  <input class="ordinary-btn" id="2" type="button" value="2" name="x"
                         @click="rememberX(2); paintButtons(2)">
                  <input class="ordinary-btn" id="3" type="button" value="3" name="x"
                         @click="rememberX(3); paintButtons(3)">
                </div>
              </div>

              <p>Выберите Y:</p>
              <div>
                <input id="yField" type="text" name="y" required="" placeholder="Число в интервале (-5;5)">
              </div>

              <p>Выберите R:</p>
              <div class="y-group">
                <div class="row">
                  <input class="ordinary-btn" type="button" value="-5" name="r" disabled>
                  <input class="ordinary-btn" type="button" value="-4" name="r" disabled>
                  <input class="ordinary-btn" type="button" value="-3" name="r" disabled>
                </div>
                <div class="row">
                  <input class="ordinary-btn" type="button" value="-2" name="r" disabled>
                  <input class="ordinary-btn" type="button" value="-1" name="r" disabled>
                  <input class="ordinary-btn" type="button" value="0" name="r" disabled>
                </div>
                <div class="row">
                  <input class="ordinary-btn-R" id="r1" type="button" value="1" name="r"
                         @click="rememberR(1); paintButtonsR('r1')">
                  <input class="ordinary-btn-R" id="r2" type="button" value="2" name="r"
                         @click="rememberR(2); paintButtonsR('r2')">
                  <input class="ordinary-btn-R" id="r3" type="button" value="3" name="r"
                         @click="rememberR(3); ; paintButtonsR('r3')">
                </div>
              </div>

              <div class="buttons-area">
                <button type="submit" id="function-btn" class="gradient-button" @click="validateInput()">
                  Отправить
                </button>
              </div>
            </div>
          </form>
        </div>

      </th>
    </tr>

    <tr>
      <th>
        <div>
          <button type="button" class="gradient-button">На главную</button>
        </div>
        <div class="table-area">
          <table id="main-table">
            <caption>История выполнений</caption>
            <tr>
              <th class="col">X</th>
              <th class="col">Y</th>
              <th class="col">R</th>
              <th class="col">Текущее время</th>
              <th class="col">Результат</th>
            </tr>
          </table>
        </div>
      </th>
    </tr>

  </table>
</template>

<script>
export default {
  name: "main",
  data() {
    return {
      x: "",
      y: "",
      r: "3",
      xGraph: "",
      yGraph: "",
      dots: new Array(0)
    }
  },
  watch: { //для изменения картинки потом вставить
    r() {

    }
  },
  methods: {
    rememberX(value1) {
      document.getElementById('pointX').value = value1;
    },
    rememberR(value1) {
      document.getElementById('pointR').value = value1;
    },
    validateInput() {
      let y;
      let yText = document.getElementById("yField");
      y = yText.value.trim();
      if (/^-?\d+\.?\d*$/.test(y)) {
        if (y >= 5 || y <= -5) yText.setCustomValidity("Число вне допустимого диапазона");
        else {
          yText.setCustomValidity("");
          this.addDots();
        }
      } else yText.setCustomValidity("Некорректный вид числа");
    },
    addDots() {
      alert("Отправлено!");
    },
    paintButtons(id) {
      let selected = document.querySelectorAll(".ordinary-btn-selected");
      if (selected !== null) {
        document.querySelectorAll(".ordinary-btn-selected").forEach(function (i) {
              i.classList.toggle("ordinary-btn");
              i.classList.toggle("ordinary-btn-selected");
            }
        )
      }
      document.getElementById(id).classList.toggle("ordinary-btn"); //убрали обычный
      document.getElementById(id).classList.toggle("ordinary-btn-selected"); //добавили нажатый
    },
    paintButtonsR(id) {
      let selected = document.querySelectorAll(".ordinary-btn-selected-R");
      if (selected !== null) {
        document.querySelectorAll(".ordinary-btn-selected-R").forEach(function (i) {
              i.classList.toggle("ordinary-btn-R");
              i.classList.toggle("ordinary-btn-selected-R");
            }
        )
      }
      document.getElementById(id).classList.toggle("ordinary-btn-R"); //убрали обычный
      document.getElementById(id).classList.toggle("ordinary-btn-selected-R"); //добавили нажатый
    }
  }
}
</script>

<style>
@import "../assets/css/header.css";
@import "../assets/css/body.css";

.ordinary-btn, .ordinary-btn-R {
  background-color: #b8b4b8;
  display: inline-block;
  border-radius: 45px;
  font-family: "Century Gothic", sans-serif;
  text-transform: uppercase;
  color: black;
  margin: auto 30px;
  width: 40px;
}

.ordinary-btn-selected, .ordinary-btn-selected-R {
  background-color: #6f5976;
  display: inline-block;
  border-radius: 45px;
  font-family: "Century Gothic", sans-serif;
  text-transform: uppercase;
  color: black;
  margin: auto 30px;
  width: 40px;
}

#graph_pic {
  margin-top: 35px;
  justify-content: center;
}


</style>