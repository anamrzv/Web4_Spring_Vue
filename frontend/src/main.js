import Vue from 'vue'
import App from './App.vue'
import router from "./router"
import VueResource from "vue-resource"
import Notifications from 'vue-notification';

Vue.use(VueResource);
Vue.use(Notifications);

new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
