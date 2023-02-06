<template>
  <div @keyup.enter="comfirm">
    <div class="modal-bg">
      <div class="modal-box">
        <p>{{ content }}</p>
        <button id="confirm-btn" class="btn-green-sm" @click="comfirm">확인</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BasicModal',
  props: {
    content: String,
    reload: Boolean,
  },
  methods: {
    comfirm() {
      console.log(this.reload)
      if (this.reload) {
        console.log('새로고침')
        location.reload()
      }
      this.$emit('close')
      // 새로고침이 필요할 경우
      
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