import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import lineClamp from 'vue-line-clamp'
import axios from 'axios'

import NavigationBar from './components/NavigationBar.vue'

Vue.component(NavigationBar.name, NavigationBar)

Vue.config.productionTip = false

Vue.use(lineClamp,{
  importCss: true,
})

Vue.prototype.$axios = axios
store.$axios = axios

// const baseUrl = 'http://i8a509.p.ssafy.io:8080'
const baseUrl = 'https://moti.today/api'
Vue.prototype.$baseUrl = baseUrl
store.$baseUrl = baseUrl

// store에서 router 사용하기
store.$router = router

new Vue({
  store,
  router,
  axios,
  render: h => h(App)
}).$mount('#app')