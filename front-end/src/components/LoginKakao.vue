<template>
  <img
    class="kakao_btn"
    src="@/assets/images/kakaotalk_logo.png"
    @click="loginWithKakao"
    style="width: 50px; cursor: pointer"
  />
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "LoginKakao",
  methods: {
    ...mapActions(["kakaoLogin"]),
    loginWithKakao() {
      window.Kakao.Auth.login({
        scope: "profile_nickname, account_email",
        success: this.getProfile,
      })
    },
    getProfile() {
      window.Kakao.API.request({
        url: "/v2/user/me",
        success: (res) => {
          const kakao_account = res.kakao_account;
          let emails = kakao_account.email;
          if (emails == null) {
            if (this.$route.path != "/") this.$router.push({ name: "home" })
          } else {
            const req_body = {
              userId: "kakao_" + res.id,
              userName: kakao_account.profile.nickname,
              email: emails,
              type: "kakao",
            }
            this.kakaoLogin(req_body)
          }
        },
      });
    },
  },
};
</script>
