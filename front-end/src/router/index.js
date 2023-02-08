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
import UserModifyView from "@/views/UserModifyView"
import LoginForm from '@/components/LoginForm'
import playlistMain from '@/components/CategoryMain'
import playlistSelect from '@/components/PlaylistMain'
import playlistDetail from '@/components/PlaylistDetail'
import playlistView from '@/views/PlaylistView'
import FeedComment from '@/components/FeedComment'
import AuthCompleteView from '@/views/AuthCompleteView'
import SearchView from '@/views/SearchView'
import MyPLMain from '@/components/MyPLMain'
import MyPLMission from '@/components/MyPLMission'
import NotFoundView from '@/views/NotFoundView'
import FeedCreateView from '@/views/FeedCreateView'
import SessionExpiredView from '@/views/SessionExpiredView'
import store from '@/store'
import axios from 'axios'


Vue.use(VueRouter)

const baseUrl = 'https://moti.today/api'

const checkAccessToken = (to, from, next) => {
  console.log('access token 체크 시작')
  const userId = store.state.id
  // api 요청(현재 로그인 유저 회원정보 조회)

  axios({
    method: 'get',
    url: `${baseUrl}/users/${userId}`,
    headers: {
      'access-token' : store.state.accessToken,
    }
  })
  // accessToken 유효
    .then(() => {
      console.log('access token이 유효합니다.')
      next()
    })
    .catch((error) => {
      // accessToken 만료
      if (error.response.status == 401) {
        console.log('access token이 만료되었습니다.')
        // accessToken 재발급 요청
        const regenResult = store.dispatch('tokenRegeneration')
        regenResult.then(() => {
          // 재발급 성공 시 작업 그대로 진행
          next()
        })
      } else {
        console.log(error.response.status)
      }
    })
}

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
    beforeEnter: checkAccessToken,
    component: FeedView,
  },
  {
    path: '/feed/create',
    name: 'feedcreate',
    beforeEnter: checkAccessToken,
    component: FeedCreateView,
  },
  {
    path:'/comment/:feedId',
    name:'comment',
    beforeEnter: checkAccessToken,
    component:FeedComment,
  },
  {
    path:'/profile',
    name:'profile',
    beforeEnter: checkAccessToken,
    component: ProfileView,
  },
  {
    path:"/profile/modify",
    name:"profileModifyView",
    beforeEnter: checkAccessToken,
    component:ProfileModifyView,
  },
  {
    path:"/user/modify",
    name:"userModifyView",
    beforeEnter: checkAccessToken,
    component:UserModifyView,
  },
  {
    path:'/search',
    name:'search',
    beforeEnter: checkAccessToken,
    component:SearchView,
  },
  {
    path: '/my-pl',
    component: MyPLView,
    children: [
      {
        path: '',
        name: 'my-pl',
        beforeEnter: checkAccessToken,
        component: MyPLMain,
      },
      {
        path: 'mission',
        beforeEnter: checkAccessToken,
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
        beforeEnter: checkAccessToken,
        component: playlistMain,
      },
      {
        path: 'select',
        name: 'playlistSelect',
        beforeEnter: checkAccessToken,
        component: playlistSelect,
      },
      {
        path: 'detail',
        name: 'playlistDetail',
        beforeEnter: checkAccessToken,
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
    beforeEnter: checkAccessToken,
    component: SearchView,
  },
  {
    path: '/session-expired/:error',
    name: 'sessionExpired',
    component: SessionExpiredView,
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