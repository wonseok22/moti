<template>
  <div class="account-form-comp">
    <div class="title-box">
      <p style="font-size:52px;">moti</p>
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
	name: 'LoginView',
  methods: {
    go() {
      this.$router.push({ path: '/authcomplete' })
    }
  },
  created() {
    // 이미 로그인되어 있을 경우 피드로 이동
    if (this.$store.getters.isLoggedIn) {
      this.$router.push({ name: 'feed' })
    }
  }
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

.condition-valid {
  @include condition-isvalid
}

.condition-invalid {
  @include condition-isvalid($light-grey)
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