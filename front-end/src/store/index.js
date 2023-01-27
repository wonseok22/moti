import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    id: null,
    password: null,
    email: null,
    nickname: null,
  },
  getters: {
  },
  mutations: {
    // 회원가입
    SIGNUP(state, payload) {
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
  },
  actions: {
    // 회원가입
    signup(context, payload) {
      // mutations로 전달
      context.commit('SIGNUP', payload)
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
        url: `${this.$baseUrl}/users/login/`,
        data: UserDto,
      })
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {
          console.log(error)
        })
    },
    // 이메일 인증 요청
    authStart(context, payload) {
      console.log('인증 메일을 보냈습니다.')
      this.$axios({
        method: 'post',
        url: `${this.$baseUrl}//users/email/`,
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
      const authResult = this.$axios({
        method: 'post',
        url: `${this.$baseUrl}//users/email/`,
        data: {
          email: payload.email
        }
      })
        .then((response) => {
          context.commit('SIGNUP', payload)
          return response
        })
        .catch((error) => {
          return error
        })
      return authResult
    },
    // 회원정보 서버 전달(회원가입 완료)
    register(context, payload) {
      // state에 닉네임 저장
      context.commit('SIGNUP', payload)
      // UserDto 객체 정의
      const UserDto = {
        userId: context.state.id,
        password: context.state.password,
        email: context.state.email,
        nickname: context.state.nickname,
      }
      this.$axios({
        method: 'post',
        url: `${this.$baseUrl}/users/`,
        data: UserDto,
      })
        .then((response) => {
          console.log('회원가입 완료')
          console.log(response.data.message)
        })
        .catch((error) => {
          console.log(error)
        })
    }
  },
  modules: {
  }
})
