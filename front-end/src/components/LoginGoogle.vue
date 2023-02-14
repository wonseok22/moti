<template>
  <div>
    <div id="button-div"></div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "LoginGoogle",
  mounted() {
    const client_id = "293618779849-da4im7oh35dq851ro2l9htrvi4ivbtc0.apps.googleusercontent.com"
    window.google.accounts.id.initialize({
      client_id: client_id,
      callback: this.handleCredentialResponse,
      auto_select:true
    });
    window.google.accounts.id.renderButton(
      document.getElementById("button-div"),
      { type: "icon", theme: "outline", size: "large", shape: "circle", width:'40'} // customization attributes
    );
    //window.google.accounts.id.prompt(); // also display the One Tap dialog
  },
  methods: {
    ...mapActions(["socialLogin"]),
    handleCredentialResponse(response) {
      const responsePayload = this.decodeJwtResponse(response.credential);

      const req_body = {
        userId: "google" + responsePayload.sub,
        userName: "google" + responsePayload.sub,
        email: responsePayload.email,
        type: "google",
      };
    //   console.log(responsePayload)
    //   console.log(req_body)
      this.socialLogin(req_body);
    },
    decodeJwtResponse(token) {
      var base64Url = token.split(".")[1];
      var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
      var jsonPayload = decodeURIComponent(
        atob(base64)
          .split("")
          .map(function (c) {
            return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
          })
          .join("")
      );

      return JSON.parse(jsonPayload);
    },
  },
};
</script>
<style lang="scss">
</style>