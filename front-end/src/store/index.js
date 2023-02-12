import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'


Vue.use(Vuex)

const save = (context, payloadToken, payloadInfo) => {
  return new Promise((resolve) => {
    const promise1 = context.commit('SAVE_TOKEN', payloadToken)
    const promise2 = context.commit('GET_USER_INFO', payloadInfo)
    Promise.all([promise1, promise2])
      .then(() => {
        resolve()
      })
  })
}


export default new Vuex.Store({
  plugins: [
    createPersistedState({
      reducer: (persistedState) => {
        const stateFilter = Object.assign({}, persistedState)
        const blackList = ['isComment']
  
        blackList.forEach((item) => {
          delete stateFilter[item]
        })
  
        return stateFilter
      }
    }),
  ],
  state: {
    id: null,
    password: null,
    email: null,
    nickname: null,
    accessToken: null,
    refreshToken: null,
    myPL: null,
    myMission: null,
    nowPL: null,
    nowFeed: null,
    profileTargetId:null,
    isComment: false,
    scrollY : null,
    
    // 로그인에서 사용하는 모달 관련 데이터
    openModal: false,
    modalContent: null,
    modalReload: false,
  },
  getters: {
    // 로그인 여부
    isLoggedIn(state) {
      if (state.refreshToken) {
        return true
      } else {
        return false
      }
    },
    isCommentClicked(state) {
      return state.isComment
    }
  },
  mutations: {
    UPDATE_PROFILE_TARGET_ID(state, data) {
      state.profileTargetId = data;
    },
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
        }
      }
      return Promise.resolve()
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
        return Promise.resolve()
      } else {
        // 로그인에 실패했을 경우
        if (payload.length == 2) {
          state.modalOpen = true
          state.modalContent = '아이디 또는 비밀번호를 확인해주세요.'
        }
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
    },
    // 나의 미션 저장
    GET_MY_MISSION(state, payload) {
      state.myMission = null
      state.myMission = payload
    },
    GET_NOW_PL(state, payload) {
      state.nowPL = state.myMission[payload.userPlaylistId]
    },
    GET_FEED(state, payload) {
      state.nowFeed = payload.feedData
    },
    // 모달 열기
    MODAL_OPEN(state, payload) {
      state.modalContent = payload.content
      state.openModal = true
      if (Object.keys(payload).length == 2) {
        state.modalReload = true
      }
    },
    // 모달 닫기
    MODAL_CLOSE(state) {
      state.modalContent = null
      state.openModal = false
      state.modalReload = false
    },
    //댓글 창 띄우기
    COMMENT_CHANGE(state) {
      if(state.isComment){
        state.isComment = false
      }
      else{
        state.isComment = true
      }
    },
    SCROLL_HEIGHT(state, height) {
      state.scrollY = height
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
          if (response.status == '202') {
            const payload = {
              content: '아이디 또는 비밀번호를 확인해주세요.'
            }
            context.commit('MODAL_OPEN', payload)
            
          } else {
            const payloadToken = {
              accessToken: response.data['access-token'],
              refreshToken: response.data['refresh-token'],
            }
            const payloadInfo = {
              id: response.data.userId,
              nickname: response.data.nickname,
            }
            save(context, payloadToken, payloadInfo)
              .then(() => {
                this.$router.push({ name: 'feed' })
              })
            }
        })
        .catch(() => {
          const payload = {
              content: '알 수 없는 에러가 발생했습니다. 고객센터에 문의해주세요.'
            }
            context.commit('MODAL_OPEN', payload)
        })
    },

    // 카카오로그인
    kakaoLogin(context, payload) {
      // UserDto 객체 정의
      const UserDto = {
        userId: payload.userId,
        nickname : payload.userName,
        email : payload.email,
        type : payload.type,
      }
      this.$axios({
        method: 'post',
        url: `${this.$baseUrl}/users/kakao`,
        data: UserDto,
      })
        .then((response) => {
          if (response.status == '202') {
            const payload = {
              content: '해당 이메일로 가입된 일반 사용자가 있습니다.'
            }
            context.commit('MODAL_OPEN', payload)
          } else {
            const payloadToken = {
              accessToken: response.data['access-token'],
              refreshToken: response.data['refresh-token'],
            }
            const payloadInfo = {
              id: response.data.userId,
              nickname: response.data.nickname,
            }
            context.commit('SAVE_TOKEN', payloadToken)
            context.commit('GET_USER_INFO', payloadInfo)

            // 피드 페이지로 이동
            this.$router.push({ name: 'feed' })
            }
        })
        .catch(() => {
          const payload = {
              content: '알 수 없는 에러가 발생했습니다. 고객센터에 문의해주세요.'
            }
            context.commit('MODAL_OPEN', payload)
        })
    },

    // 로그아웃
    logout(context) {
      // 로그아웃 요청
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/users/logout/${context.state.id}`,
      })
        .then(() => {
          context.commit('LOGOUT')
          this.$router.push({ name: 'login' })
        })
        .catch((error) => {
          console.log(`로그아웃 실패: status: ${error.response.status}`)
        })
    },
    // 이메일 인증 요청
    authStart(context, payload) {
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
          if (error.response.status == 500)  {
            const payload = {
              content: '이메일 발송에 실패했어요. 고객센터에 문의해주세요 :(',
              reload: true,
            }
            context.commit('MODAL_OPEN', payload)
          } else {
            const payload = {
              content: '이메일 발송에 실패했어요. 이메일을 정확히 입력해주세요.',
              reload: true,
            }
            context.commit('MODAL_OPEN', payload)
          }
        })
    },
    // 이메일 인증 확인
    authCheck(context, payload) {
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/users/auth?email=${payload.email}`,
      })
        .then(() => {
          context.commit('GET_USER_INFO', payload)
          this.$router.push({ path: '/signup/nickname'})
        })
        .catch((error) => {
          const payload = {
            content: '이메일 인증에 실패했어요. 다시 시도해주세요.',
            reload: true,
          }
          context.commit('MODAL_OPEN', payload)
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
          if (response.status == 202) {
            alert('202 응답')
          } else {
            context.commit('ERASE_INFO')
            this.$router.push({ name: 'login' })
          }
        })
        .catch((error) => {
          console.log(error)
        })
    },
    // 토큰 재발급
    tokenRegeneration(context) {
      const UserDto = {
        userId: context.state.id
      }
      this.$axios({
        method: 'post',
        url: `${this.$baseUrl}/users/refresh`,
        headers: {
          'refresh-token' : context.state.refreshToken,
        },
        data: UserDto
      })
        .then((response) => {
          // refresh token 유효 -> access token 갱신
          const payload = {
            accessToken: response.data['access-token'],
          }
          context.commit('SAVE_TOKEN', payload)
          return Promise.resolve()
        })
        .catch((error) => {
          // refresh token 만료 -> 재로그인
          if (error.response.status == '401') {
            const params = {
              error: error.response.status
            }
            this.$router.push({ name: 'sessionExpired', params: params })
          } else {
            const params = {
              error: error.response.status
            }
            this.$router.push({ name: 'sessionExpired', params: params })
          }
        })
    },
    // 나의 플레이리스트 정보 가져오기
    getMyPL(context) {
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/playlist/current/${context.state.id}`
      })
        .then((response) => {
          const myPLList = response.data.currentPlaylists
          // 진행 중인 플레이리스트가 있을 경우 플레이리스트 저장 및 미션 가져오기
          if (myPLList) {
            const payload = {
              myPL: myPLList,
            }
            context.commit('GET_MY_PL', payload)
            const payloadMission = {}
            context.state.myPL.forEach((pl) => {
              payloadMission[pl.playlist.playlistId] = pl.userPlaylistId 
            })
            context.dispatch('getMyMission', payloadMission)
          }
        })
        .catch((error) => {
          console.log(`유저 플레이리스트 가져오기 실패: status ${error.response.status}`)
        })
    },
    // 내 플레이리스트별 미션 정보 가져오기
    getMyMission(context, payload) {
      // 내 플레이리스트별 미션 모아둘 리스트
      const payloadMission = {}

      for (let [key, value] of Object.entries(payload)) {
        this.$axios({
          method: 'get',
          url: `${this.$baseUrl}/playlist/detail/${context.state.id}/${value}`
        })
          .then((response) => {
            // const missions = {
            //   [key]: response.data.myPlaylist
            // }
            payloadMission[key] = response.data.myPlaylist
          })
          .catch((error) => {
            console.log(`플레이리스트별 미션 가져오기 실패: status ${error.response.status}`)
          })
      }
      // 시간 차이가 발생한다.
      context.commit('GET_MY_MISSION', payloadMission)
    },
    getNowPL(context, payload) {
      context.commit('GET_NOW_PL', payload)
    },
    // 피드 상세정보 호출
    async getSingleFeed(context, feedId) {
      return this.$axios({
        method:'get',
        url:`${this.$baseUrl}/feed/${feedId}/${this.state.id}`
      })
    },
    //피드 상세정보 저장
    putSingleFeed(context, payload) {
      const data = {
        feedData: payload
      }
      context.commit('GET_FEED', data)
    },
    showComment(context) {
      context.commit('COMMENT_CHANGE')
    },
    putScrollHeight(context, height) {
      context.commit('SCROLL_HEIGHT', height)
    },
    //댓글 작성
    writeComment(context, payload) {
      const writeCommentDto = {
        userId: payload.userId,
        feedId: payload.feedId,
        content: payload.content
      }
      this.$axios({
        method:'post',
        url:`${this.$baseUrl}/feed/comment`,
        data: writeCommentDto,
      })
      .then(() => {
        return this.dispatch('getSingleFeed', writeCommentDto.feedId)
      })
      .then((res) => {
        console.log(res)
        const data = {
          feedData: res.data.feed
        }
        context.commit('GET_FEED', data)
      })
      .catch((error) => {
        console.log(error)
      })
    },
    //댓글 삭제
    deleteComment(context, payload) {
      this.$axios({
        method:'delete',
        url:`${this.$baseUrl}/feed/comment/${payload.commentId}`
      })
      .then(() => {
        return this.dispatch('getSingleFeed', payload.feedId)
      })
      .then((res) => {
        const data = {
          feedData: res.data.feed
        }
        context.commit('GET_FEED', data)
      })
      .catch((error) => {
        console.log(error)
      })
    },
    //좋아요 등록
    makeLike(context, feedId) {
      this.$axios({
        method:'post',
        url:`${this.$baseUrl}/feed/like/${this.state.id}/${feedId}`
      })
      .then(() => {
      })
      .catch((error) => {
        console.log(error)
      })
    },
    //좋아요 해제
    deleteLike(context, feedId) {
      this.$axios({
        method:'delete',
        url:`${this.$baseUrl}/feed/like/${this.state.id}/${feedId}`
      })
      .then(() => {
        
      })
      .catch((error) => {
        console.log(error)
      })
    },
    //피드 검색
    async FeedSearch(context, payload) {
      return this.$axios({
        method:'get',
        url:`${this.$baseUrl}/feed/search/${this.state.id}/${payload.kind}/default/${payload.pageNum}`
      })
    },
    //팔로우 언팔로우
    async FollowUnfollow(context, payload) {
      return this.$axios.get(`${this.$baseUrl}/profile/follow/${this.state.id}/${payload.targetId}`, {
        params: {
          type: payload.type
        }})
    },
    //팔로우 했는지 체크
    async followCheck(context, targetId) {
      return this.$axios({
        method:'get',
        url: `${this.$baseUrl}/profile/follow/check/${this.state.id}/${targetId}`
      })
    },
    // 모달 닫기
    modalClose(context) {
      context.commit('MODAL_CLOSE')
    },
    modalOpen(context, payload) {
      context.commit('MODAL_OPEN', payload)
    },
    //피드삭제
    feedDelete(context, feedId) {
      this.$axios({
        method:'delete',
        url:`${this.$baseUrl}/feed/${feedId}`
      })
      .catch((err) => {
        console.log(err)
      })
    }
  },
  feedEdit(context, feedId) {
    this.$axios({
      method:'put',
      url:`${this.$baseUrl}/feed/${feedId}`
    })
    .catch((err) => {
      console.log(err)
    })
  },
  modules: {
  }
})
