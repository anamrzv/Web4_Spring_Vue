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
              <div class="row">
                <input class="ordinary-btn" id="-5" type="button" value="-5" name="x"
                       @click="rememberX(-5)">
                <input class="ordinary-btn" id="-4" type="button" value="-4" name="x"
                       @click="rememberX(-4)">
                <input class="ordinary-btn" id="-3" type="button" value="-3" name="x"
                       @click="rememberX(-3)">
              </div>
              <div class="row">
                <input class="ordinary-btn" id="-2" type="button" value="-2" name="x"
                       @click="rememberX(-2)">
                <input class="ordinary-btn" id="-1" type="button" value="-1" name="x"
                       @click="rememberX(-1)">
                <input class="ordinary-btn" id="0" type="button" value="0" name="x" @click="rememberX(0)">
              </div>
              <div class="row">
                <input class="ordinary-btn" id="1" type="button" value="1" name="x" @click="rememberX(1)">
                <input class="ordinary-btn" id="2" type="button" value="2" name="x" @click="rememberX(2)">
                <input class="ordinary-btn" id="3" type="button" value="3" name="x" @click="rememberX(3)">
              </div>

              <p>Выберите Y:</p>
              <div>
                <input id="yField" type="text" name="y" required="" placeholder="Число в интервале (-5;5)">
              </div>

              <p>Выберите R:</p>
              <div>
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
                  <input class="ordinary-btn" id="r1" type="button" value="1" name="r"
                         @click="rememberR(1)">
                  <input class="ordinary-btn" id="r2" type="button" value="2" name="r"
                         @click="rememberR(2)">
                  <input class="ordinary-btn" id="r3" type="button" value="3" name="r"
                         @click="rememberR(3)">
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
import Vue from "vue";

new Vue({
  el: '#graph',
  template: `<svg id="graph_pic" height="320" width="320" xmlns="http://www.w3.org/2000/svg">

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

        <polygon fill="blue"
        fill-opacity="0.2"
        stroke="blue"
        points="50,150 150,150 150,50 50,50"/>

        <path fill="blue"
        fill-opacity="0.2"
        stroke="blue"
        d="M 100 150 A 100 100, 90, 0, 0, 150 200 L 150 150"/>

        <polygon fill="blue"
        fill-opacity="0.2"
        stroke="blue"
        points="150,150 200,150 150,200"/>
        </svg>`
});

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
    rememberX(value) {
      document.getElementById(value).setAttribute("style", "color: blue");
      document.getElementById('hidden-form:pointX').value = value;
    },
    rememberR(value) {
      let id;
      if (value == 1) id = "r1";
      else if (value == 2) id = "r2";
      else if (value == 3) id = "r3";
      document.getElementById(id).setAttribute("style", "color: blue");
      document.getElementById('hidden-form:pointR').value = value;
    },
    validateInput() {
      let y;
      let yText = document.getElementById("coordinates-form:y");
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
    }
  }
}
</script>

<style>
@import '../assets/css/header.css';
@import '../assets/css/body.css';
</style>