<template>
  <div class="account-form-comp">
    <!-- <p class="guide-comment">
        <span class="fade">당</span>
        <span class="fade">신</span>
        <span class="fade">의 </span>
        <span class="fade">씨</span>
        <span class="fade">앗</span>
        <span class="fade">을 </span>
        <span class="fade">심</span>
        <span class="fade">어</span>
        <span class="fade">보</span>
        <span class="fade">세</span>
        <span class="fade">요</span>
      </p> -->
    <div class="title-box">
      <span class="fade">m</span>
      <span class="fade">o</span>
      <span class="fade">t</span>
      <span class="fade">i</span>
      <!-- <p style="font-size:52px;">moti</p> -->
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
	name: 'LoginView',
  data() {
    return {
      timer: 300,
    }
  },
  mounted() {
    const introText = document.querySelectorAll(".fade");
    introText.forEach((item) => {
      item.style.animation = `fade 500ms ${(this.timer += 90)}ms forwards`;
    });

    setTimeout(() => {
      this.$router.push({ name: 'login' })
      .catch(() => {});
    }, '2000')
  },
}
</script>

<style lang="scss">
#auth-layout {
  display: flex;
  flex-direction: column;
  align-items: center;

  // height: $login-height
}

// input 태그와 다음 버튼에 대한 css
#auth-form {
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
.auth-input-form {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;

  width: 332px;
  height: 172px;
}

#id-box {
  position: relative;
}

#double-check-id {
  $height: 26px;

  position: absolute;
  right: 10px;
  // inputbox height 42px
  top: (42px - $height)/2;
  width: 63px;
  height: $height;

  font-size: $fs-7;
}
</style>