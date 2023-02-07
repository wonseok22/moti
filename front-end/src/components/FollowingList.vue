<template>
    <main>
        <div class="result-box" style="padding-left : 20px; gap:0px;">
            <div v-for="(follow,idx) in follows" :key="idx" >
                <div class="follow-info" @click="moveProfile(follow.userId)">
                  <div class="follow-info-img-wrap">
                    <img :src="follow.profileImageUrl ? follow.profileImageUrl : defaultImage" alt="유저 프로필사진" class="follow-info-image">
                  </div>
                  <div  class="follow-info-nickname">{{ follow.nickname }}</div>
                </div>
            </div>
        </div>
    </main>
  </template>
  
  <script>
  
    export default {
    name: 'FollowingList',
    props: {
        keyword: String,
    },
    components: {
  
  },
    data() {
        return {
          follows:null,
            defaultImage:require(`@/assets/images/default_profile.jpg`),
        }
    },
    watch: {
    },
    created() {
      this.$axios({
          method: 'get',
          url: `${this.$baseUrl}/profile/follow?userId=${this.keyword}&type=following`
          }).then((response) => {
          this.follows = response.data.followerList;
          }).catch((error) =>{
          console.log(error);
      })
    },
  
    methods : {
      moveProfile(userId) {
        this.$store.commit("UPDATE_PROFILE_TARGET_ID",userId);
        this.$router.push({
          name: 'profile',
        }).catch(() => {location.reload();});
      }
  
    }
  }
  </script>
  <style lang="scss">

  </style>