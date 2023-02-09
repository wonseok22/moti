<template>
  <div>
    <div class="modal-bg">
      <div class="modal-box">
        <span>이메일 주소 {{ emailMasked }}으로 전송된 링크를 눌러 인증하세요.</span>
        <p v-if="timeCounter == 0" class="text-active" @click="AuthRestart">메일 재전송</p>
        <p v-else class="text-inactive">메일 재전송</p>
        <p>{{ timerStr }}</p>
        <button id="auth-comp-btn" class="btn-green-sm" @click="authCheck" >인증완료</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AuthModal',
  props: {
    email: String,
  },
  data() {
    return {
      timer: null,
      timeCounter: 300,
      timerStr: "05:00",
    }
  },
  methods: {
    // 인증 완료 여부 확인
    authCheck() {
      const payload = {
        email: this.email
      }
      // 인증에 성공했을 경우
      // 지금 실패해도 catch로 안 넘어감
      this.$store.dispatch('authCheck', payload)
    },
    // 인증 메일 재발송
    AuthRestart() {
      this.timeCounter = 300
      location.reload()
    },
    timerStart() {
      // 1초에 한번씩 start 호출
      const interval = setInterval(() => {
        this.timeCounter-- //1초씩 감소
        this.timerStr = this.timeFormatChange()
        if (this.timeCounter <= 0) this.timerStop(interval)
      }, 1000)
      return interval
    },
    timerStop(timer) {
      clearInterval(timer)
      this.timeCounter = 0
    },
    timeFormatChange() {
      // 시간 형식으로 변환 리턴
      let time = this.timeCounter / 60
      let minutes = parseInt(time)
      let secondes = Math.round((time - minutes) * 60)
      return (
        minutes.toString().padStart(2, "0") +
        ":" +
        secondes.toString().padStart(2, "0")
      )
    },
  },
  computed: {
    // 이메일 마스킹 처리 후 return
    emailMasked() {
      return this.email
    }
  },
  watch: {
    timeCounter(val) {
      if (!val) {
        this.authPossible = false
      }
    }
  },
  mounted() {
    if (this.timer) {
      this.timerStop(this.timer)
      this.timer = null
    }
    this.timer = this.timerStart()

    // focus를 인증완료 버튼으로 이동
    const authCompBtn = document.querySelector('#auth-comp-btn')
    authCompBtn.focus()
  },
}
</script>

<style lang="scss">
.modal-bg {
	width: 100%;
	height: 100%;
  min-height: 100px;
	background: rgba(0, 0, 0, 0.6);
  position: absolute;
  top: 0px;
  left: 0px;

  display: flex;
  flex-direction: column;
  justify-content: center;
}

.modal-box {
	width: 90%;
	margin: auto;
	background: white;
	border-radius: 5px;
	padding: 20px 5px;
}
</style>