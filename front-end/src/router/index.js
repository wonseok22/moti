import Vue from 'vue'
import VueRouter from 'vue-router'
import SignupView from '@/views/SignupView'
import LoginView from '@/views/LoginView'
import MyPLView from '@/views/MyPLView'
import SignupForm from '@/components/LoginSignup/SignupForm'
import SignupAuth from '@/components/LoginSignup/SignupAuth'
import SignupNickname from '@/components/LoginSignup/SignupNickname'
import FeedView from '@/views/FeedView'
import ProfileView from '@/views/ProfileView'
import ProfileModifyView from "@/views/ProfileModifyView"
import UserModifyView from "@/views/UserModifyView"
import LoginForm from '@/components/LoginSignup/LoginForm'
import playlistMain from '@/components/Playlist/CategoryMain'
import playlistSelect from '@/components/Playlist/PlaylistMain'
import playlistDetail from '@/components/Playlist/PlaylistDetail'
import playlistView from '@/views/PlaylistView'
import FeedComment from '@/components/Comment/FeedComment'
import AuthCompleteView from '@/views/AuthCompleteView'
import SearchView from '@/views/SearchView'
import onBoarding from '@/components/Common/OnBoarding'
import MyPLMain from '@/components/Playlist/MyPLMain'
import MyPLMission from '@/components/Playlist/MyPLMission'
import MissionCreateView from '@/views/MissionCreateView'
import NotFoundView from '@/views/NotFoundView'
import FeedCreateView from '@/views/FeedCreateView'
import SessionExpiredView from '@/views/SessionExpiredView'
import FeedEditView from '@/views/FeedEditView.vue'
import LandingView from '@/views/LandingView'
import store from '@/store'
import axios from 'axios'
// Google Analytics
import VueGtag from 'vue-gtag'

Vue.use(VueRouter)

const baseUrl = 'https://moti.today/api'

const checkAccessToken = (to, from, next) => {
  const userId = store.state.id
  // 로그인이 안 되어 있는 경우
  if (!userId) {
    router.push({ name: "login" })
  } else {
    // api 요청(현재 로그인 유저 회원정보 조회)
    axios({
      method: 'post',
      url: `${baseUrl}/users/check`,
      headers: {
        'access-token' : store.state.accessToken,
      },
    })
    // accessToken 유효
      .then((response) => {
         // accessToken 만료
         if (response.status == 202) {
          // accessToken 재발급 요청
          const regenResult = store.dispatch('tokenRegeneration')
          regenResult.then(() => {
            // 재발급 성공 시 작업 그대로 진행
            next()
          })
        } else {
          next()
        }
      })
      .catch(() => {
      })
  }
}

const loginCheck = (to, from, next) => {
  if (store.state.id) {
    router.push({
      name: "feed",
    })
  } else {
    next()
  }
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
    // beforeEnter:loginCheckInLogin,
    children: [
      {
        path: 'landing',
        name: 'landing',
        component: LandingView
      },
      {
        path: '',
        name: 'login',
        beforeEnter: loginCheck,
        component: LoginForm
      },
    ],
  },
  {
    path: '/onBoarding',
    name: 'onBoarding',
    component: onBoarding,
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
    path:'/feed/edit/:feedId',
    name:'FeedEditView',
    beforeEnter: checkAccessToken,
    component: FeedEditView,
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
    path: '/mission',
    name: 'MissionCreateView',
    beforeEnter: checkAccessToken,
    component: MissionCreateView,
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
    redirect: '/login/landing',
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

//Google Analytics setup
Vue.use(VueGtag, {
  config: { id: "G-D2267Q4M3G" }
}, router);

export default router
