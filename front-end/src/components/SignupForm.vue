<template>
  <div id="signup-layout">
    <div>
      <p class="guide-comment">당신의 씨앗을 키워보세요</p>
      <!-- 회원가입 Form -->
      <div id="signup-form">
        <div id="signup-input-form">
          <!-- 아이디 -->
          <div id="id-box">
            <input type="text" id="input-id" class="inputbox" name="input-id" placeholder="아이디" v-model="id">
            <button id="double-check-id" class="btn-green" @click="doubleCheck">중복체크</button>
          </div>
          <!-- 비밀번호 -->
          <input type="password" id="input-pw" class="inputbox" name="input-pw" placeholder="비밀번호" v-model="password">
          <!-- 비밀번호 재입력 -->
          <input type="password" id="input-pw2" class="inputbox" name="input-pw2" placeholder="비밀번호 재입력" v-model="password2">
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
export default {
	name: 'SignupForm',
  data() {
    return {
      id: null,
      password: null,
      password2: null,
    }
  },
  methods: {
    // 아이디 중복 체크
    doubleCheck() {
      console.log('중복체크 실행')
    },
    toEmailAuth() {
      this.$router.push({ path: '/signup/auth' })
    }
  },
  computed: {
    // input이 제대로 입력되었는지 여부 return
    isvalid() {
      return true
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
  height: 245px;
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
  height: 172px;
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