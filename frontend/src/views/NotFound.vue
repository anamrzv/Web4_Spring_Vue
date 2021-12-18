<template>
  <div id="body-table">

    <div class="header">
      <div>
        <p>Морозова Анастасия Александровна P3230</p>
        <p>Вариант 5547</p>
      </div>
    </div>

    <div class="errors">
      <h1>Ошибка {{errorCode}}:</h1>
      <h2>{{errorMsg}}</h2>
    </div>

  </div>
</template>

<style>
@import "../../public/header.css";
@import "../../public/body.css";

.errors{
  color: #000000;
  text-align: center;
  margin-top: 200px;
}
</style>

<script>
export default {
  name: "NotFound",
  props: {
    errorCode: String,
    errorMsg: String
  }
}
</script>