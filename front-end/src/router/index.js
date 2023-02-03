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
    path:'/search',
    name:'search',
    component:SearchView,
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
        path: 'mission',
        // path: 'mission',
        component: MyPLMission,
      }
    ]
  },
  {
    path: '/playlist',
    name: 'playlist',
    component: playlistView,
    redirect:'/playlist/main',
    children: [
      {
        path:'main',
        name:'playlistMain',
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
