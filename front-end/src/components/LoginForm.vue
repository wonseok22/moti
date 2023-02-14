<template>
  <div class="account-layout">
    <div class="title-box">
      <span class="fade">m</span>
      <span class="fade">o</span>
      <span class="fade">t</span>
      <span class="fade">i</span>
    </div>
    <div>
      <!-- <p class="guide-comment">로그인</p> -->
      <!-- 로그인 Form -->
      <div class="account-form">
        <div class="account-input-form">
          <!-- 아이디 -->
          <div class="account-id-box">
            <input type="text" id="input-id" class="inputbox" name="input-id" placeholder="아이디" @input="idInput">
          </div>
          <!-- 비밀번호 -->
          <div>
            <input type="password" id="input-pw" class="inputbox" name="input-pw" placeholder="비밀번호" @input="pwInput" @keyup.enter="login">
          </div>
        </div>
        <button v-if="isvalid" class="btn-green" @click="login">로그인</button>
        <button v-else class="btn-green-inactive">로그인</button>
      </div>
    </div>
    <div class="account-sub">
      <div class="login-signup">
        <span>아직 계정이 없으신가요?</span>
        <span class="hl text-active" style="font-weight:bold;" @click="toSignup">회원가입</span>
      </div>
      <div class="to-kakao">
        <p>또는</p>
        <div style="display: flex; justify-content:space-around">
          <login-kakao></login-kakao>    
          <login-google></login-google>     
        </div>
      </div>
    </div>
    <!-- 쿠키 사용 안내 문구 -->
    <span style="color:#A2A2A2; font-size:12px; font-weight: bold; font-family:'LINESeedKR-Th';position: relative; bottom: -90px;">
      모티는 사용자 경험 개선을 위해 쿠키를 사용합니다</span>
    <basic-modal
      v-if="openModal"
      :content="modalContent"
      @close="modalClose"
    >
    </basic-modal>
  </div>
</template>

<script>
import * as regex from '@/tools/regex.js'
import BasicModal from '@/components/BasicModal'
import LoginKakao from '@/components/LoginKakao'
import LoginGoogle from '@/components/LoginGoogle'

export default {
	name: 'loginForm',
  components: {
    BasicModal,
    LoginKakao,
    LoginGoogle,
},
  data() {
    return {
      id: null,
      password: null,
      timer: 300,
    }
  },
  methods: {
    // id 입력 받기
    idInput(event) {
      const regexResult = regex.characterCheck(event.target.value)
      this.id = regexResult[0]
      if (regexResult[1]) {
        const payload = {
          content: regexResult[1]
        }
        this.$store.dispatch('modalOpen', payload)
      }
      event.target.value = this.id
    },
    // 비밀번호 입력 받기
    pwInput(event) {
      const regexResult = regex.characterCheck(event.target.value)
      this.password = regexResult[0]
      if (regexResult[1]) {
        if (regexResult[1]) {
        const payload = {
          content: regexResult[1]
        }
        this.$store.dispatch('modalOpen', payload)
      }
      }
      event.target.value = this.password
    },
    // 로그인
    login() {
      if (this.isvalid) {
        const payload = {
          id: this.id,
          password: this.password,
        }
        this.$store.dispatch('login', payload)
      }
    },
    // 회원가입 페이지로 이동
    toSignup() {
      this.$router.push({ name: 'signup' })
    },
    // 모달 닫기
    modalClose() {
      this.$store.dispatch('modalClose')
    },
	},
  computed: {
    // 아이디 비밀번호 입력 여부
    isvalid() {
      if (this.id) {
        if (this.password) {
          return true
        }
      }
      return false
    },
    openModal() {
      return this.$store.state.openModal
    },
    modalContent() {
      return this.$store.state.modalContent
    }
  },
  mounted() {
    const introText = document.querySelectorAll(".fade");
    introText.forEach((item) => {
      item.style.animation = `fade 500ms ${(this.timer += 90)}ms forwards`;
    });
  }
}
</script>

<style lang="scss">
</style>