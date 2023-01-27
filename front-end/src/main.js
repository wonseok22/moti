import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import lineClamp from 'vue-line-clamp'

import NavigationBar from './components/NavigationBar.vue'

Vue.component(NavigationBar.name, NavigationBar)

Vue.config.productionTip = false

Vue.use(lineClamp,{
  importCss: true,
})

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
