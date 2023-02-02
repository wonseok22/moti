import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import lineClamp from 'vue-line-clamp'
import axios from 'axios'
import VModal from 'vue-js-modal'

import NavigationBar from './components/NavigationBar.vue'


// 202 응답 에러 처리
axios.interceptors.response.use(
  function(response) {
    if (response.status == 202) {
      const response = {
        status: 202
      }
      const error = {
        response
      }
      return Promise.reject(error)
    }
    return response
  },
  function(error) {
    return Promise.reject(error)
  }
)


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