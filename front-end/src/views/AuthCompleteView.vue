<template>
  <div id="authcomp-layout">
    <p id="authcomp-title">moti</p>
    <div id="authcomp-content">
      <p>이메일 인증에 성공했어요!</p>
      <p>moti 회원가입 페이지에서</p>
      <p><b>'인증완료'</b> 버튼을 눌러주세요.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AuthCompleteView',
  methods: {
    // 이메일 인증 코드 발송
    emailAuthCode() {
      console.log(this.$route.query.code)
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/users/email?code=${this.$route.query.code}`
      })
        .then((response) => {
          console.log(`이메일 인증 코드 발송 완료: status ${response.status}`)
        })
        .catch((error) => {
          console.log(`이메일 인증 코드 발송 실패: status ${error.response.status}`)
        })
    }
  },
  created() {
    this.emailAuthCode()
  }
}
</script>

<style lang="scss">
#authcomp-layout {
  height: inherit;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#authcomp-title {
  font-size: $fs-2;
  color: $main-green;
  font-weight: bold;

  margin-top: 0px;
}

#authcomp-content {
  p:nth-child(2) {
    margin: 0px;
  }
  p:nth-child(3) {
    margin: 0px;
  }
}
</style>