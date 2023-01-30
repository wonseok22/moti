import Vue from 'vue'
import VueRouter from 'vue-router'
import SignupView from '@/views/SignupView'
import LoginView from '@/views/LoginView'
import SignupForm from '@/components/SignupForm'
import SignupAuth from '@/components/SignupAuth'
import SignupNickname from '@/components/SignupNickname'
import FeedView from '@/views/FeedView'
import ProfileMain from '@/views/ProfileView'
import SearchPage from '@/views/SearchView'
import UserFlowerList from '@/views/UserFLList'
import LoginForm from '@/components/LoginForm'

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
  {
    path: '/login',
    name: 'login',
    component: LoginView,
    redirect: 'login/main',
    children: [
      {
        path: 'main',
        component: LoginForm
      },
    ],
  },
  {
    path:'/feed',
    name:'feed',
    component: FeedView,
  },
  {
    path:'/profile',
    name:'profile',
    component: ProfileMain,
  },
  {
    path:'/search',
    name:'search',
    component:SearchPage,
  },
  {
    path:'/userflowerlist',
    name:'userflowerlist',
    component:UserFlowerList,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
