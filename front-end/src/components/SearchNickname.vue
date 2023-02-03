<template>
  <main>
      <div class="result-box">
          <div v-for="(user,idx) in users" :key="idx" >
              <div class="user-info" @click="moveProfile(user.userId)">
                <img :src="user.profileImageUrl ? user.profileImageUrl : defaultImage" alt="유저 프로필사진" class="user-info-image">
                <div class="user-info-nickname">{{ user.nickname }}</div>
              </div>
          </div>
      </div>
  </main>
</template>

<script>

  export default {
  name: 'SearchPlaylist',
  props: {
      keyword: String,
  },
  components: {

},
  data() {
      return {
          users:null,
          defaultImage:require(`@/assets/images/default_profile.jpg`),
      }
  },
  watch: {
      keyword : function() {
          if (this.keyword != "") {
              this.$axios({
                  method: 'get',
                  url: `${this.$baseUrl}/users/search/${this.keyword}/0`
                  }).then((response) => {
                  this.users = response.data. users;
                  console.log("유저 리스트 받아오기 성공");
                  }).catch((error) =>{
                  console.log(error);
              })
          }
      },
  },
  created() {

  },

  methods : {
    moveProfile(userId) {
      this.$store.commit("UPDATE_PROFILE_TARGET_ID",userId);
      this.$router.push({
        name: 'profile',
      });
    }

  }
}
</script>
<style lang="scss">
.result-box{
  padding-left: 30px;
  .user-info {
    height: 50px;
    margin-bottom: 10px;
   display: flex;
   .user-info-image {
     width: 50px;
     height: 50px;
   }
   .user-info-nickname {
    padding-left: 10px;
    font-size: 16px;
    font-weight: bold;
    line-height: 2.8;
   }
  }
}
</style>