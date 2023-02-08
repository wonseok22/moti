<template>
  <div @keyup.enter="comfirm">
    <div class="session-expired-bg">
      <div class="session-expired-box">
        <p>{{ errorMessage1 }}</p>
        <p>{{ errorMessage2 }}</p>
        <button id="confirm-btn" class="btn-green-sm" @click="comfirm">확인</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SessionExpiredView',
  data() {
    return {
      errorMessage1: null,
      errorMessage2: null,
    }
  },
  methods: {
    comfirm() {
      
      if (this.$route.params.error == 401) {
        this.$store.dispatch('logout')
      } else {
        this.$router.push('/feed')
      }
    }
  },
  created() {
    if (this.$route.params.error == 401) {
      this.errorMessage1 = '세션이 만료되었어요.'
      this.errorMessage2 = '다시 로그인 해주세요!'
    } else {
      this.errorMessage1 = '에러가 발생했어요.'
      this.errorMessage2 = `status: ${this.$route.params.error}`
    }
  },
  mounted() {
    // focus를 확인 버튼으로 이동
    const confirmBtn = document.querySelector('#confirm-btn')
    confirmBtn.focus()
  }
}
</script>

<style lang="scss">
.session-expired-bg {
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.6);
  position: absolute;
  top: 0px;
  left: 0px;

  display: flex;
  flex-direction: column;
  justify-content: center;
}

.session-expired-box {
	width: 90%;
  min-height: 100px;
	margin: auto;
	background: white;
	border-radius: 5px;
	padding: 20px 5px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  p {
    margin-top: 0px;
  }
}
</style>