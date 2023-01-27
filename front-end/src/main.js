import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false

Vue.prototype.$axios = axios
store.$axios = axios

// const baseUrl = 'http://i8a509.p.ssafy.io:8080'
const baseUrl = 'http://moti.today/api'
Vue.prototype.$baseUrl = baseUrl
store.$baseUrl = baseUrl

new Vue({
  store,
  router,
  axios,
  render: h => h(App)
}).$mount('#app')