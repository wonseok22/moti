import Vue from 'vue'
import VueRouter from 'vue-router'
import SignupView from '@/views/SignupView'
import LoginView from '@/views/LoginView'
import MyPLView from '@/views/MyPLView'
import SignupForm from '@/components/SignupForm'
import SignupAuth from '@/components/SignupAuth'
import SignupNickname from '@/components/SignupNickname'
import FeedView from '@/views/FeedView'
import ProfileView from '@/views/ProfileView'
// import SearchPage from '@/views/SearchView'
import UserFlowerList from '@/views/UserFLList'
import LoginForm from '@/components/LoginForm'
// import MyMissionMain from '@/components/MyMissionMain'
import FeedComment from '@/components/FeedComment'
import AuthCompleteView from '@/views/AuthCompleteView'
// import FeedPage from '@/views/FeedPage'
import SearchView from '@/views/SearchView'
import MyPLMain from '@/components/MyPLMain'
import MyPLMission from '@/components/MyPLMission'

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
    ],
  },
  {
    path:'/feed',
    name:'feed',
    component: FeedView,
  },
  {
    path:'/comment/:feedId',
    name:'comment',
    component:FeedComment,
  },
  {
    path:'/profile',
    name:'profile',
    component: ProfileView,
  },
  {
    path:'/search',
    name:'search',
    component:SearchView,
  },
  {
    path:'/userflowerlist',
    name:'userflowerlist',
    component:UserFlowerList,
  },
  {
    path: '/my-pl',
    name: 'my-pl',
    component: MyPLView,
    redirect: '/my-pl/main',
    children: [
      {
        path: 'main',
        component: MyPLMain,
      },
      {
        path: 'mission/:pl-id',
        // path: 'mission',
        component: MyPLMission,
      }
    ]
  },
  {
    // 이메일 인증 성공 페이지
    path:'/authcomplete',
    name:'authcomplete',
    component:AuthCompleteView,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
