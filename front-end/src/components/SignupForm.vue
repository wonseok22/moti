<template>
  <div class="account-layout">
    <div>
      <p class="guide-comment">당신의 씨앗을 키워보세요</p>
      <!-- 회원가입 Form -->
      <div class="account-form">
        <div class="account-input-form">
          <!-- 아이디 -->
          <div class="account-id-box">
            <input type="text" id="input-id" class="inputbox" name="input-id" placeholder="아이디" @input="idInput">
            <button class="double-check btn-green" @click="doubleCheck">중복체크</button>
            <div v-if="idActive">
              <p
                v-for="(condition, idx) in idConditions"
                :key="idx"
                :class="[condition.valid? 'condition-valid': 'condition-invalid']"
              >
                {{ condition.comment }}
              </p>
            </div>
            
          </div>
          <!-- 비밀번호 -->
          <div>
            <input type="password" id="input-pw" class="inputbox" name="input-pw" placeholder="비밀번호" @input="pwInput">
            <div v-if="pwActive">
              <p
                v-for="(condition, idx) in pwConditions"
                :key="idx"
                :class="[condition.valid? 'condition-valid': 'condition-invalid']"
              >
                {{ condition.comment }}
              </p>
            </div>
          </div>
          <!-- 비밀번호 재입력 -->
          <div>
            <input type="password" id="input-pw2" class="inputbox" name="input-pw2" placeholder="비밀번호 재입력" @input="pwInput2">
            <div v-if="pw2Active">
              <p
                v-for="(condition, idx) in pw2Conditions"
                :key="idx"
                :class="[condition.valid? 'condition-valid': 'condition-invalid']"
              >
                {{ condition.comment }}
              </p>
            </div>
          </div>
        </div>
        <button v-if="isvalid" class="btn-green" @click="toEmailAuth">다음</button>
        <button v-else class="btn-green-inactive">다음</button>
      </div>
    </div>
    <div class="account-sub">
      <div class="login-signup">
        <span>이미 계정이 있으신가요?</span>
        <span class="hl text-active" @click="toLogin">로그인</span>
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
	name: 'SignupForm',
  data() {
    return {
      id: null,
      password: null,
      password2: null,

      idDoubleChecked: false,
      idActive: false,
      pwActive: false,
      pw2Active: false,
    }
  },
  methods: {
    // id 입력 받기
    idInput(event) {
      this.idActive = true
      // 띄어쓰기 및 특수문자 제거
      this.id = regex.characterCheck(event.target.value)
      event.target.value = this.id
    },
    // 비밀번호 입력 받기
    pwInput(event) {
      this.pwActive = true
      // 띄어쓰기 및 특수문자 제거
      this.password = regex.characterCheck(event.target.value)
      event.target.value = this.password
    },
    // 비밀번호 재입력 받기
    pwInput2(event) {
      this.pw2Active = true
      this.password2 = event.target.value
    },
    // 아이디 중복 체크
    doubleCheck() {
      console.log('아이디 중복체크 실행')
      // 임시로 중복체크 true로 바꿔줌
      // this.idDoubleChecked = true
      
      // 아이디 조건을 충족하지 못한 경우
      if ( !this.idConditions[0].valid ) {
        alert('아이디 형식을 지켜주세요.')
      }
      // 아이디 조건을 충족한 경우
      else {
        // 중복체크
        this.$axios({
          method: 'get',
          url: `${this.$baseUrl}/users/check?type=id&value=${this.id}/`
        })
          .then((response) => {
            // 응답 예시
            // 성공시(이미 존재할 때 ) : 200, already exists
            // 실패시(없을 때) : 200, success 
            // 서버 에러시 : 500, fail

            // 이미 아이디가 존재할 경우
            if ( response.data.message === 'already exists' ) {
              alert('이미 사용 중인 아이디에요.')
              console.log(`중복체크 결과/message: ${response.data.message}`)
              this.id = null
            } else if ( response.data.message === 'success' ) {
              alert('사용할 수 있는 아이디에요.')
              console.log(`중복체크 결과/message: ${response.data.message}`)
              const idInputTag = document.querySelector('#input-id')
              // 현재 아이디로 고정
              idInputTag.setAttribute('disabled', true)
              this.idDoubleChecked = true
            } else {
              console.log(response.data.message)
              alert('알 수 없는 에러가 발생했습니다. 고객센터에 문의해주세요.')
            }
          })
          .catch((error) => {
            console.log(error)
          })
        }
    },
    // 이메일 인증으로 넘어가기
    toEmailAuth() {
      const payload = {
        id: this.id,
        password: this.password,
      }
      // 아이디, 비밀번호 store로 전달
      this.$store.dispatch('signup', payload)
      this.$router.push({ path: '/signup/auth' })
    },
    // 로그인 페이지로 이동
    toLogin() {
      this.$router.push({ name: 'login' })
    }
  },
  computed: {
    // input이 제대로 입력되었는지 여부 return
    isvalid() {
      // 아이디 중복 체크 / 비밀번호 조건 충족 여부 / 비밀번호 일치 여부
      if ( this.idDoubleChecked & this.pwConditions[0].valid === true & this.pwConditions[1].valid === true & this.pwConditions[2].valid === true & this.pw2Conditions[0].valid === true ) {
        return true
      } else {
        return false
      }
    },
    // 아이디 조건 충족 여부
    idConditions() {
      let conditions = [{
        comment: 'X 4~16자. 띄어쓰기와 특수문자는 사용이 안 돼요',
        valid: false,
      }]
      // 아이디 길이 체크
      if ( this.id ) {
        if ( this.id.length >= 4 & this.id.length <= 16) {
        conditions[0].comment = 'O ' + conditions[0].comment.substring(1, )
        conditions[0].valid = true
        }
      }
      return conditions
    },
    // 비밀번호 조건 충족 여부
    pwConditions() {
      let conditions = [
        {
          comment: 'X 6~50자. 띄어쓰기와 특수문자는 사용이 안 돼요',
          valid: false,
        },
        {
          comment: 'X 영어 대문자 또는 소문자가 포함되어야 해요.',
          valid: false,
        },
        {
          comment: 'X 숫자가 포함되어야 해요.',
          valid: false,
        }
      ]

      // 아이디 길이 체크
      if ( this.password ) {
        if (this.password.length >= 6 & this.password.length <= 50) {
        conditions[0].comment = 'O ' + conditions[0].comment.substring(1, )
        conditions[0].valid = true
        }
      }
      // 영어 포함 여부 체크
      if ( regex.englishCheck(this.password) ) {
        conditions[1].comment = 'O ' + conditions[1].comment.substring(1, )
        conditions[1].valid = true
      }
      // 숫자 포함 여부 체크
      if ( regex.numberCheck(this.password) ) {
        conditions[2].comment = 'O ' + conditions[2].comment.substring(1, )
        conditions[2].valid = true
      }
      return conditions
    },
    // 비밀번호 재입력 조건 충족 여부
    pw2Conditions() {
      let conditions = [
        {
          comment: 'X 비밀번호가 일치해야 해요.',
          valid: false,
        },
      ]
      // 비밀번호 일치 여부
      if ( this.password === this.password2 ) {
        conditions[0].comment = 'O ' + conditions[0].comment.substring(1, )
        conditions[0].valid = true
      }
      return conditions
    }
  }
}
</script>

<style lang="scss">
</style>