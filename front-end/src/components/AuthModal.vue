<template>
  <div>
    <div class="modal-bg">
      <div class="modal-box">
        <span>이메일 주소 {{ emailMasked }}으로 전송된 링크를 눌러 인증하세요.</span>
        <p v-if="!timeCounter" class="text-active" @click="AuthRestart">메일 재전송</p>
        <p v-else class="text-inactive">메일 재전송</p>
        <p>{{ timerStr }}</p>
        <button v-if="authPossible" class="btn-green-sm" @click="authCheck">인증완료</button>
        <button v-else class="btn-green-inactive-sm">인증완료</button>
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
      timeCounter: 5,
      timerStr: "05:00",
      authPossible: true,
    }
  },
  mounted() {
    if (this.timer) {
      this.timerStop(this.timer)
      this.timer = null
    }
    this.timer = this.timerStart()
  },
  methods: {
    // 인증 완료 여부 확인
    authCheck() {
    },
    // 인증 메일 재발송
    AuthRestart() {
      this.timeCounter = 5
      this.timer = this.timerStart()
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
      return 'ab**@*****.com'
    }
  },
  watch: {
    timeCounter(val) {
      if (!val) {
        this.authPossible = false
      }
    }
  }
}
</script>

<style lang="scss">
.modal-bg {
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

.modal-box {
	width: 90%;
	margin: auto;
	background: white;
	border-radius: 5px;
	padding: 20px 5px;
}
</style>