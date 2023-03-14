import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import lineClamp from 'vue-line-clamp'
import axios from 'axios'
import VModal from 'vue-js-modal'
import VueGtag from 'vue-gtag'

import NavigationBar from './components/Common/NavigationBar.vue'


Vue.component(NavigationBar.name, NavigationBar)

Vue.config.productionTip = false

Vue.use(lineClamp,{
  importCss: true,
})

Vue.use(VModal, {dialog: true})

Vue.prototype.$axios = axios
store.$axios = axios

// const baseUrl = 'http://i8a509.p.ssafy.io:8080'
const baseUrl = 'https://moti.today/api'
// const baseUrl = 'http://localhost:8080/api'

Vue.prototype.$baseUrl = baseUrl
store.$baseUrl = baseUrl

window.Kakao.init("332addc510b9cc23dc54269380c84107");


// store에서 router 사용하기
store.$router = router

// Google Analytics setup
Vue.use(VueGtag, {
  config: { id: 'G-D2267Q4M3G'}
})

new Vue({
  store,
  router,
  axios,
  render: h => h(App)
}).$mount('#app')