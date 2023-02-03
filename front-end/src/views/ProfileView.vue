<template>
  <div class="profile-main" v-if="profile">
    <!-- 프로필 페이지 최상단 유저이름과 메뉴바 -->
    <div class="profile-header">
      <div class="profile-nickname">
        <p>{{profile.nickname}}</p>
          <button v-if="isMyProfile" class="follow" @click="follow">팔로우</button>
      </div>
      <!-- <img :src=""></img> -->
      <!-- <p @click="logout">임시 로그아웃 버튼</p> -->
      <button class="material-icons-outlined">
        menu
      </button>
    </div>

    <div class="profile-info">
      <div class="profile-info-title">
        <!-- <img :src="profile.profileImageUrl ? profile.profileImageUrl : defaultImage" alt="프로필 사진"/> -->
        <img src="@/assets/images/default_profile.jpg" alt="프로필 사진"/>
        <div>
          <div>{{profile.follower}}</div>
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
        <!-- <SearchPlaylist :keyword="keyword"></SearchPlaylist> -->
        <!-- <SearchFeed :keyword="keyword"></SearchFeed> -->
        <!-- <SearchNickname :keyword="keyword"></SearchNickname> -->
      </div>
    </div>
    <NavigationBar></NavigationBar>
  </div>
</template>

<script>

export default {
  name: 'ProfileView',
  data() {
    return {
      profile:null,
      defaultImage : '@/assets/images/default_profile.jpg',
      isMyProfile:true,
    }
  },
  created() {
    this.$axios({
      method: 'get',
      url: `${this.$baseUrl}/profile?userId=${this.$store.state.id}`
    }).then((response) => {
      this.profile=response.data.profile
      if (this.profile.userId === this.$store.state.id){
        this.isMyProfile = false;
      }
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
      // const slide = document.querySelector(".SearchResult-slide")
      // slide.style.left = 0;
      bar.className = "bar1"; 
      
    },
    playlist() {
      const bar = document.getElementById("bar");
      // const slide = document.querySelector(".SearchResult-slide")
      // slide.style.left = "-360px";
      bar.className = "bar2";
      
    },
    achive() {
      const bar = document.getElementById("bar");
      // const slide = document.querySelector(".SearchResult-slide")
      // slide.style.left = "-720px";
      bar.className = "bar3";

   }

  }
}
</script>

<style>

</style>