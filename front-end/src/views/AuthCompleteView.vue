<template>
  <div id="authcomp-layout">
    <p id="authcomp-title">moti</p>
    <div id="authcomp-content">
      <p>이메일 인증에 성공했어요!</p>
      <p>moti 회원가입 페이지에서</p>
      <p><b>'인증완료'</b> 버튼을 눌러주세요.</p>
    </div>
    <!-- <p id="close-tap" class="text-active" @click="closeTap">현재 창 닫기</p> -->
  </div>
</template>

<script>
export default {
  name: 'AuthCompleteView',
  // methods: {
  //   // 현재 탭 닫기
  //   closeTap() {
  //     window.close()
  //   }
  // }
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

// #close-tap {
//   font-size: $fs-6;
//   color: $dark-grey;
//   text-decoration: underline;
// }
</style>