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
import ProfileModifyView from "@/views/ProfileModifyView"
//import SearchPage from '@/views/SearchView'
import LoginForm from '@/components/LoginForm'
//import MyMissionMain from '@/components/MyMissionMain'
// import SearchPage from '@/views/SearchView'
import playlistMain from '@/components/CategoryMain'
import playlistSelect from '@/components/PlaylistMain'
import playlistDetail from '@/components/PlaylistDetail'
import playlistView from '@/views/PlaylistView'
// import MyMissionMain from '@/components/MyMissionMain'
import FeedComment from '@/components/FeedComment'
import AuthCompleteView from '@/views/AuthCompleteView'
// import FeedPage from '@/views/FeedPage'
import SearchView from '@/views/SearchView'
import MyPLMain from '@/components/MyPLMain'
import MyPLMission from '@/components/MyPLMission'
import NotFoundView from '@/views/NotFoundView'
import FeedCreateView from '@/views/FeedCreateView'


Vue.use(VueRouter)

const routes = [
  {
    path: '/signup',
    component: SignupView,
    children: [
      {
        path: '',
        name: 'signup',
        component: SignupForm
      },
      {
        path: 'auth',
        name: 'auth',
        component: SignupAuth
      },
      {
        path: 'nickname',
        name: 'nickname',
        component: SignupNickname
      },
    ]
  },
  {
    path: '/login',
    component: LoginView,
    children: [
      {
        path: '',
        name: 'login',
        component: LoginForm
      },
    ],
  },
  {
    path: '/feed',
    name: 'feed',
    component: FeedView,
  },
  {
    path: '/feed/create',
    name: 'feedcreate',
    component: FeedCreateView,
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
    path:"/profile/modify",
    name:"profileModifyView",
    component:ProfileModifyView,

  },
  {
    path:'/search',
    name:'search',
    component:SearchView,
  },
  {
    path: '/my-pl',
    component: MyPLView,
    children: [
      {
        path: '',
        name: 'my-pl',
        component: MyPLMain,
      },
      {
        path: 'mission',
        component: MyPLMission,
      }
    ]
  },
  {
    path: '/playlist',
    component: playlistView,
    children: [
      {
        path: '',
        name: 'playlist',
        component: playlistMain,
      },
      {
        path: 'select',
        name: 'playlistSelect',
        component: playlistSelect,
      },
      {
        path: 'detail',
        name: 'playlistDetail',
        component: playlistDetail,
      }
    ]
  },
  {
    // 이메일 인증 성공 페이지
    path:'/authcomplete',
    name:'authcomplete',
    component: AuthCompleteView,
  },
  
  {
    // 랜딩페이지: 로그인 페이지
    path: '/',
    name: 'landing',
    redirect: 'login',
    component: LoginView,
  },
  {
    path: '/search',
    name: 'searchView',
    component: SearchView,
  },
  {
    path: '*',
    name: 'notfound',
    component: NotFoundView,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
