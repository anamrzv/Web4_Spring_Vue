import Vue from 'vue'
import VueRouter from 'vue-router'

import Main from "./views/Main.vue";
import Login from "./views/Login.vue";

Vue.use(VueRouter);

const routes = [
    {path: '/', name:'def', component: Login},
    {path: '/login', name:'login', component: Login},
    {path: '/main', name:'main', component: Main}
]

export default new VueRouter({
    routes
});




