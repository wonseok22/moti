<template>
  <div class="account-layout">
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
        <img class="kakao-login-img hl" src="@/assets/images/kakao_login.png" alt="kakao-login">
      </div>
    </div>
  </div>
</template>

<script>
import * as regex from '@/tools/regex.js'

export default {
	name: 'loginForm',
  data() {
    return {
      id: null,
      password: null,
    }
  },
  methods: {
    // id 입력 받기
    idInput(event) {
      this.id = regex.characterCheck(event.target.value)
      event.target.value = this.id
    },
    // 비밀번호 입력 받기
    pwInput(event) {
      this.password = regex.characterCheck(event.target.value)
      event.target.value = this.password
    },
    // 로그인
    login() {
      if (this.isvalid) {
        console.log('로그인 실행')
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
    }
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
    }
  },
}
</script>

<style lang="scss">
</style>