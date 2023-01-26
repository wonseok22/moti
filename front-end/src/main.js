import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false

Vue.prototype.$axios = axios

new Vue({
  store,
  router,
  axios,
  render: h => h(App)
}).$mount('#app')