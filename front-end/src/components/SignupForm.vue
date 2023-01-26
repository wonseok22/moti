<template>
  <div id="signup-layout">
    <div>
      <p class="guide-comment">당신의 씨앗을 키워보세요</p>
      <!-- 회원가입 Form -->
      <div id="signup-form">
        <div id="signup-input-form">
          <!-- 아이디 -->
          <div id="id-box">
            <input type="text" id="input-id" class="inputbox" name="input-id" placeholder="아이디" @input="idInput">
            <button id="double-check-id" class="btn-green" @click="doubleCheck">중복체크</button>
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
    <div id="signup-sub">
      <div id="to-login">
        <span>이미 계정이 있으신가요?</span>
        <span class="hl text-active">로그인</span>
      </div>
      <div id="to-kakao">
        <p>또는</p>
        <img id="kakao-login-img" class="hl" src="@/assets/images/kakao_login.png" alt="kakao-login">
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

      idActive: false,
      pwActive: false,
      pw2Active: false,
    }
  },
  methods: {
    idInput(event) {
      this.idActive = true
      // 띄어쓰기 및 특수문자 제거
      this.id = regex.characterCheck(event.target.value)
      event.target.value = this.id
    },
    pwInput(event) {
      this.pwActive = true
      // 띄어쓰기 및 특수문자 제거
      this.password = regex.characterCheck(event.target.value)
      event.target.value = this.password
    },
    pwInput2(event) {
      this.pw2Active = true
      this.password2 = event.target.value
    },
    // 아이디 중복 체크
    doubleCheck() {
      console.log('중복체크 실행')
      // 아이디 조건 충족 여부 체크
      const idConditions = this.idConditions

      // 아이디 조건을 충족하지 못한 경우
      if ( !idConditions[0].valid ) {
        alert('아이디 형식을 지켜주세요.')
      }
      // 아이디 조건을 충족한 경우
      else {
        // 중복체크
        this.$axios({
          method: 'get',
          url: `/users/check?type=id&value=${this.id}`,
          data: {
            id: this.id,
          }
        })
          .then((response) => {
            // 응답 예시
            // 성공시(이미 존재할 때 ) : 200, already exists
            // 실패시(없을 때) : 200, success 
            // 서버 에러시 : 500, fail

            // 이미 아이디가 존재할 경우
            if ( response.data === 'already exists' ) {
              alert('이미 사용 중인 아이디에요.')
              this.id = null
            } 
            else if ( response.data === 'success' ) {
              alert('사용할 수 있는 아이디에요.')
              const idInputTag = document.querySelector('#input-id')
              // 현재 아이디로 고정
              idInputTag.setAttribute('disabled')
            }
            // context.commit('LIKE_ARTICLE', { 'articleId': payload.articleId, 'data': response.data })
          })
          .catch((error) => {
            console.log(error)
          })
        }
    },
    toEmailAuth() {
      this.$router.push({ path: '/signup/auth' })
    }
  },
  computed: {
    // input이 제대로 입력되었는지 여부 return
    isvalid() {
      return true
    },
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
      if (this.password.length >= 6 & this.password.length <= 50) {
        conditions[0].comment = 'O ' + conditions[0].comment.substring(1, )
        conditions[0].valid = true
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
// 컴포넌트 모든 콘텐츠에 대한 css
#signup-layout {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;

  height: $signup-height
}

// input 태그와 다음 버튼에 대한 css
#signup-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px;
  gap: 26px;

  // position: absolute;
  width: 332px;
  // height: 245px;
  left: 14px;
  top: 217px;
}

// input 태그에 대한 css
#signup-input-form {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  gap: 23px;

  width: 332px;
}

#signup-sub {
  display: flex;
  flex-direction: column;
  color: $dark-grey;
  gap: 15px;
}

#to-login {
  display: flex;
  gap: 10px;
}

#to-kakao {
  font-size: $fs-6;
}

#kakao-login-img {
  width: 60px;
}
</style>