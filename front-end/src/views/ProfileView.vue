<template>
  <div class="profile-main" v-if="profile">
    <!-- 프로필 페이지 최상단 유저이름과 메뉴바 -->
    <div class="profile-header">
      <div class="profile-nickname">
        <p>{{profile.nickname}}</p>
        <button v-if="!isMyProfile && !isFollow" class="follow" @click="follow">팔로우</button>
        <button v-if="!isMyProfile && isFollow" class="unfollow" @click="unfollow">팔로우 취소</button>
      </div>
      <!-- <img :src=""></img> -->
      <p @click="logout">임시 로그아웃 버튼</p>
      <button class="material-icons-outlined">
        menu
      </button>
    </div>

    <div class="profile-info">
      <div class="profile-info-title">
        <!-- <img :src="profile.profileImageUrl ? profile.profileImageUrl : defaultImage" alt="프로필 사진"/> -->
        <img :src="profileImageUrl" alt="프로필 사진"/>
        <div>
          <div>{{profile.playlistCompleteCnt}}</div>
          <div>키운식물</div>
        </div>
        <div>
          <div>{{profile.following}}</div>
          <div>팔로우</div>
        </div>
        <div>
          <div>{{profile.follower}}</div>
          <div>팔로워</div>
        </div>
      </div>
      <div class="profile-info-desc">
        {{ profile.userDesc }}
      </div>

    </div>

    <div class="profile-menu">
      <nav id="CategoryNav">
        <div class="profile-feed"  @click="feed()">
          <span class="material-icons-outlined">
          feed
          </span>
        </div>
        <div class="profile-pl" @click="playlist()">
          <span class="material-symbols-outlined">
          nature
          </span>
        </div>
        <div class="profile-achive" @click="achive()">
          <span class="material-icons-outlined">
          emoji_events
          </span>
        </div>
      </nav>
      <div class="profile-underbar">
        <div id="bar" class="bar1"></div>
      </div>
    </div>

    <div class="profile-detail">
      <div class="profile-detail-slide">
        <SearchUserId :keyword="`${profile.userId}`"></SearchUserId>
        <SearchMyPl :keyword="`${profile.userId}`"></SearchMyPl>
        <SearchAchieve :keyword="`${profile.userId}`"></SearchAchieve>
      </div>
    </div>
    <NavigationBar></NavigationBar>
  </div>
</template>

<script>
import SearchUserId from '@/components/SearchUserId.vue'
import SearchMyPl from '@/components/SearchMyPl.vue'
import SearchAchieve from '@/components/SearchAchieve.vue'
export default {
  name: 'ProfileView',
  data() {
    return {
      profile:null,
      isMyProfile:false,
      profileImageUrl:require(`@/assets/images/default_profile.jpg`),
      isFollow:false,
    }
  },
  components: {
    SearchUserId,
    SearchMyPl,
    SearchAchieve,
  },
  created() {
    console.log(this.$store.state.profileTargetId)
    this.$axios({
      method: 'get',
      url: `${this.$baseUrl}/profile?userId=${this.$store.state.profileTargetId}`
    }).then((response) => {
      this.profile=response.data.profile
      if (this.profile.profileImageUrl){
        this.profileImageUrl = this.profile.profileImageUrl
      }
      if (this.profile.userId === this.$store.state.id){
        this.isMyProfile = true;
      } else {
        this.isFollow = this.isFollowing()
      } 
      // 내가 아닌 경우 팔로우인지 팔로우 취소인지 체크하는 요청

        console.log("프로필 받아오기 성공")
      }).catch((error) =>{
        console.log(error)
      })
  },
  methods: {
    logout() {
      this.$store.dispatch('logout')
    },
    feed () {
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".profile-detail-slide")
      slide.style.left = 0;
      bar.className = "bar1"; 
      
    },
    playlist() {
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".profile-detail-slide")
      slide.style.left = "-100vw";
      bar.className = "bar2";
      
    },
    achive() {
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".profile-detail-slide")
      slide.style.left = "-200vw";
      bar.className = "bar3";

   }, 
   isFollowing(){
    const targetId = this.profile.userId;
    const loginId = this.$store.state.id;
    this.$axios({
      method: 'get',
      url: `${this.$baseUrl}/profile/follow/check/${loginId}/${targetId}`
    }).then((response) => {
      if (response.data.message ==="success"){
        this.isFollow = response.data.check;
      } 
      }).catch((error) =>{
        console.log(error)
      })
    return false;
   },
   follow() {
    this.$axios({
      method: 'get',
      url: `${this.$baseUrl}/profile/follow/${this.$store.state.id}/${this.profile.userId}?type=follow`
    }).then((response) => {
      if (response.data.message ==="success"){
        this.isFollow = true;
        console.log("팔로우 성공")
      } else {
        console.log("팔로우 실패")
      }
      }).catch((error) =>{
        console.log(error)
      })
   },
   unfollow() {
    this.$axios({
      method: 'get',
      url: `${this.$baseUrl}/profile/follow/${this.$store.state.id}/${this.profile.userId}?type=unfollow`
    }).then((response) => {
      if (response.data.message ==="success"){
        this.isFollow = false;
        console.log("팔로우 취소 성공")
      } else {
        console.log("팔로우 취소 실패")
      }
      }).catch((error) =>{
        console.log(error)
      })
   },

  }
}
</script>

<style>

</style>