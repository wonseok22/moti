import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'


Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [
    createPersistedState(),
  ],
  state: {
    id: null,
    password: null,
    email: null,
    nickname: null,
    accessToken: null,
    refreshToken: null,
    myPL: null,
  },
  getters: {
    // 로그인 여부
    isLoggedIn(state) {
      if (state.refreshToken) {
        return true
      } else {
        return false
      }
    }
  },
  mutations: {
    // 유저 정보 저장하기
    GET_USER_INFO(state, payload) {
      for (let [key, value] of Object.entries(payload)) {
        if (key === 'id') {
          state.id = value
        } else if (key === 'password') {
          state.password = value
        } else if (key === 'email') {
          state.email = value
        } else if (key === 'nickname') {
          state.nickname = value
        } else {
          console.log(`알 수 없는 회원 정보가 입력되었습니다: ${key}`)
        }
      }
    },
    // 회원가입 완료 후 저장된 일부 정보 삭제하기(보안상)
    ERASE_INFO(state) {
      state.password = null
      state.email = null
    },
    // 토큰 저장하기
    SAVE_TOKEN(state, payload) {
      for (let [key, value] of Object.entries(payload)) {
        if (key === 'accessToken') {
          state.accessToken = value
        } else if (key === 'refreshToken') {
          state.refreshToken = value
        }
      }
      // 토근 정상 저장 여부 확인
      if ( state.accessToken && state.refreshToken ) {
        console.log('토큰이 정상적으로 저장되었습니다.')
      } else {
        // 로그인에 실패했을 경우
        if (payload.length == 2) {
          alert('아이디 또는 비밀번호를 확인해주세요.')
        }
        console.log('토큰 저장에 실패했습니다.')
      }
    },
    // 로그아웃
    LOGOUT(state) {
      state.id = null
      state.email = null
      state.nickname = null
      state.accessToken = null
      state.refreshToken = null
    },
    // 나의 플레이리스트 저장
    GET_MY_PL(state, payload) {
      state.myPL = payload.myPL
      console.log(state.myPL)
      console.log(`플레이리스트 출력: ${state.myPL}`)
    }
  },
  actions: {
    // 유저정보 받기(회원가입 시)
    getUserInfo(context, payload) {
      // mutations로 전달
      context.commit('GET_USER_INFO', payload)
    },
    // 로그인
    login(context, payload) {
      // UserDto 객체 정의
      const UserDto = {
        userId: payload.id,
        password: payload.password,
      }
      this.$axios({
        method: 'post',
        url: `${this.$baseUrl}/users/login`,
        data: UserDto,
      })
        .then((response) => {
          console.log(`로그인 응답 status: ${response.status}`)
          const payload = {
            accessToken: response.data['access-token'],
            refreshToken: response.data['refresh-token'],
          }
          context.commit('SAVE_TOKEN', payload)
        })
        .catch((error) => {
          console.log(error)
        })
    },
    // 로그아웃
    logout(context) {
      context.commit('LOGOUT')
    },
    // 이메일 인증 요청
    authStart(context, payload) {
      console.log('인증 메일을 보냈습니다.')
      this.$axios({
        method: 'post',
        url: `${this.$baseUrl}/users/email`,
        data: {
          email: payload.email
        }
      })
      .then((response) => {
          console.log(`인증 메일 전송 성공/status:${response.status}`)
        })
        .catch((error) => {
          console.log(`인증 메일 전송 실패: ${error}`)
        })
    },
    // 이메일 인증 확인
    authCheck(context, payload) {
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/users/auth?email=${payload.email}`,
      })
        .then((response) => {
          console.log('이메일 인증 성공')
          console.log(response)
          context.commit('GET_USER_INFO', payload)
          this.$router.push({ path: '/signup/nickname'})
        })
        .catch((error) => {
          console.log('이메일 인증 실패')
          alert('이메일 인증에 실패했어요. 다시 시도해주세요.')
          this.$router.go()
          console.log(error)
        })
    },
    // 회원정보 서버 전달(회원가입 완료)
    register(context, payload) {
      // state에 닉네임 저장
      context.commit('GET_USER_INFO', payload)
      // UserDto 객체 정의
      const UserDto = {
        userId: context.state.id,
        password: context.state.password,
        email: context.state.email,
        nickname: context.state.nickname,
      }
      this.$axios({
        method: 'post',
        url: `${this.$baseUrl}/users`,
        data: UserDto,
      })
        .then((response) => {
          console.log('회원가입 완료')
          context.commit('ERASE_INFO')
          console.log(response.data.message)
        })
        .catch((error) => {
          console.log(error)
        })
    },
    // 토큰 재발급
    tokenRegeneration(context) {
      console.log('access token 재발급 요청')
      const UserDto = {
        'refresh-token' : context.state.refreshToken
      }
      this.$axios({
        method: 'post',
        url: `${this.$baseUrl}/users/refresh`,
        data: UserDto
      })
        .then((response) => {
          // refresh token 유효 -> access token 갱신
          const payload = {
            accessToken: response.data['access-token'],
          }
          context.commit('SAVE_TOKEN', payload)
        })
        .catch((error) => {
          // refresh token 만료 -> 재로그인
          if (error.response.status == '401') {
            alert('다시 로그인 해주세요.')
          } else {
            alert(`에러가 발생했습니다. 고객센터에 문의해주세요. 에러: ${error.response.status}`)  
          }
          this.$router.push({ path: '/login/main'})
        })
    },
    // 나의 플레이리스트 정보 가져오기
    getMyPL(context) {
      console.log('유저 플레이리스트를 가져옵니다.')
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/playlist/${context.state.id}}`
      })
        .then((response) => {
          console.log(`유저 플레이리스트 가져오기 성공: status ${response.status}`)
          const payload = {
            myPL: response.data.myPlaylists,
          }
          console.log(response)
          context.commit('GET_MY_PL', payload)
        })
        .catch((error) => {
          console.log(`유저 플레이리스트 가져오기 실패: status ${error.response.status}`)
        })
    },
  },
  modules: {
  }
})
