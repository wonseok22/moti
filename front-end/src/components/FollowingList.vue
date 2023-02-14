<template>
    <main>
        <div class="result-box" style="padding-left : 20px; gap:0px;">
            <div v-for="(follow,idx) in follows" :key="idx" >
                <div class="follow-info" >
                  <div class="follow-info-img-wrap" @click="moveProfile(follow.userId)">
                    <img :src="follow.profileImageUrl ? follow.profileImageUrl : defaultImage" alt="유저 프로필사진" class="follow-info-image">
                  </div>
                  <div  class="follow-info-nickname" @click="moveProfile(follow.userId)">{{ follow.nickname }}</div>
                  <button v-if="!follow.following && (follow.userId != userId)" class="follow" @click="Follow(follow.userId,idx)">팔로우</button>
                  <button v-if="follow.following && (follow.userId != userId)" class="unfollow" @click="unFollow(follow.userId,idx)">팔로우 취소</button>
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
          userId:this.$store.state.id,
        }
    },
    watch: {
    },
    created() {
      this.$axios({
          method: 'get',
          url: `${this.$baseUrl}/profile/follow?targetId=${this.keyword}&type=following&userId=${this.$store.state.id}`
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
      },
      unFollow(targetId,i) {
        this.$axios({
          method: 'get',
          url: `${this.$baseUrl}/profile/follow/${this.$store.state.id}/${targetId}?type=unfollow`
        }).then((response) => {
          if (response.data.message ==="success"){
            this.follows[i].following = false;
            this.$emit("click-follow",-1);
          } 
          }).catch((error) =>{
            console.log(error)
          })
      },
      Follow(targetId,i) {
        this.$axios({
          method: 'get',
          url: `${this.$baseUrl}/profile/follow/${this.$store.state.id}/${targetId}?type=follow`
        }).then((response) => {
          if (response.data.message ==="success"){
            this.follows[i].following = true;
            this.$emit("click-follow",1);
          } 
        }).catch((error) =>{
          console.log(error)
        })
      },
  
    }
  }
  </script>
  <style lang="scss">

  </style>