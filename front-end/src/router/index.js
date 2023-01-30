import Vue from 'vue'
import VueRouter from 'vue-router'
import SignupView from '@/views/SignupView'
import LoginView from '@/views/LoginView'
import MyMissionView from '@/views/MyMissionView'
import SignupForm from '@/components/SignupForm'
import SignupAuth from '@/components/SignupAuth'
import SignupNickname from '@/components/SignupNickname'
import LoginForm from '@/components/LoginForm'
import MyMissionMain from '@/components/MyMissionMain'

Vue.use(VueRouter)

const routes = [
  {
    path: '/signup',
    name: 'signup',
    component: SignupView,
    redirect: '/signup/main',
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
  {
    path: '/login',
    name: 'login',
    component: LoginView,
    redirect: '/login/main',
    children: [
      {
        path: 'main',
        component: LoginForm
      },
    ]
  },
  {
    path: '/my-mission',
    name: 'my-mission',
    component: MyMissionView,
    redirect: '/my-mission/main',
    children: [
      {
        path: 'main',
        component: MyMissionMain,
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
