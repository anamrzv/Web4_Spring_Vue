<template>
  <div id="body-table">

    <div class="header">
      <div>
        <p>Морозова Анастасия Александровна P3230</p>
        <p>Вариант 5547</p>
      </div>
    </div>

    <div>
      <form id="login">
        <div class="login">
          <input type="text" name="username" v-model="input.username" placeholder="Username">
          <input type="password" v-model="input.password" placeholder="Password">
          <button type="submit" class="gradient-button" v-on:click="login()">Войти</button>
        </div>
      </form>
    </div>

  </div>
</template>

<script>
export default {
  name: 'Login',
  data() { //this page has a login form so we need to initialize the variables that will bind it
    return {
      input: {
        username: "",
        password: ""
      }
    }
  },
  methods: {
    login() {
      if(this.input.username != "" && this.input.password != "") {
        if(this.input.username == "ana" && this.input.password == "123") {
          this.$emit("authenticated", true);
        } else {
          alert("The username and / or password is incorrect");
        }
      } else {
        //window.location.href = 'main';
        this.$router.push("/main");
        alert("A username and password must be present");
      }
    }
  }
}
</script>

<style>
@import '../assets/css/header.css';
@import '../assets/css/body.css';
</style>