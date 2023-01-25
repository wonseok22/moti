import Vue from 'vue'
import VueRouter from 'vue-router'
import SignupView from '@/views/SignupView'
import SignupForm from '@/components/SignupForm'
import SignupAuth from '@/components/SignupAuth'
import SignupNickname from '@/components/SignupNickname'

Vue.use(VueRouter)

const routes = [
  {
    path: '/signup',
    name: 'signup',
    component: SignupView,
    redirect: 'signup/main',
    children: [
      {
        path: 'main',
        component: SignupForm
      },
      {
        path: 'auth',
        component: SignupAuth
      },
      {
        path: 'nickname',
        component: SignupNickname
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
