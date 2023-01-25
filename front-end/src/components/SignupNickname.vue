<template>
  <div>
    <div id="auth-layout">
      <p class="guide-comment">사용자의 닉네임을 입력해주세요.</p>
      <!-- 닉네임 설정 -->
      <div id="auth-form">
        <div id="auth-input-form">
          <div id="id-box">
            <input type="text" id="input-nickname" class="inputbox" name="input-nickname" placeholder="닉네임" v-model="nickname">
            <button id="double-check-id" class="btn-green" @click="doubleCheck">중복체크</button>
          </div>
        </div>
        <button v-if="isvalid" class="btn-green" @click="toAuthModal">다음</button>
        <button v-else class="btn-green-inactive">다음</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
	name: 'SignupNickname',
  data() {
    return {
      nickname: null,
    }
  },
  methods: {
    // 닉네임 중복 체크
    doubleCheck() {
    }
  },
  computed: {
    // 닉네임 입력되었는지 여부 return
    isvalid() {
      return true
    }
  }
}
</script>

<style lang="scss">
// SignupAuth와 동일. SignupView에 지정되어 있음
</style>