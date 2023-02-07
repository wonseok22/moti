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

      <button class="material-icons-outlined" @click="menu">
        menu
      </button>
    </div>

    <div class="profile-info">
      <div class="profile-info-title">
        <div class="profile-img-wrap">
          <img :src="profileImageUrl" alt="프로필 사진"/>
        </div>
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
        <SearchAchieve :keyword="`${profile.userId}`" @openModal="openModal"></SearchAchieve>
      </div>
    </div>
    <NavigationBar></NavigationBar>


    <div class="modal" v-if="modal">
      <div class="white-bg">
        <h3>{{ achievement.achievementName }}</h3>
        <img :src="achievement.achievementImageUrl" alt="업적 이미지">
        <p>{{ achievement.achievementDesc }}</p>
        <button @click="modal = false" class="modal-close">
          닫기
        </button>
      </div>
    </div>

    <div class="menu-modal" v-if="menuModal" @click="menuModal=false">
      <div class="menu-white-bg">
        <ul>
          <li @click="moveProfileModify">
            <div class="menu-items" >
              <span class="material-symbols-outlined">
                person
              </span>
              <div>프로필 설정</div>
            </div>
          </li>
          <li @click="moveUserModify">
            <div class="menu-items">
              <span class="material-symbols-outlined">
              settings_suggest
              </span>
              <div>비밀번호 변경</div>
            </div>
          </li>
          <li @click="logout">
            <div class="menu-items">
              <span class="material-symbols-outlined">
              settings_power
              </span>
              <div>로그아웃</div>
            </div>
          </li>
        </ul>
      </div>
    </div>


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
      modal: false,
      menuModal:false,

    }
  },
  components: {
    SearchUserId,
    SearchMyPl,
    SearchAchieve,
  },
  created() {
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
      } 
      }).catch((error) =>{
        console.log(error)
      })
   },
   openModal(achievement) {
      this.achievement = achievement;
      this.modal=true;
   },
   menu(){
    this.menuModal = true;
  },
  moveProfileModify(){
      this.$router.push({
        name: 'profileModifyView',
      });
  },
  moveUserModify(){
      this.$router.push({
        name: 'userModifyView',
      });
  }
  }
}
</script>

<style lang="scss">
.modal {
  position: fixed;
  background: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  .white-bg{
    padding-top: 3px;
    padding-bottom: 10px;
    border-radius: 20px;
    background-color: #fff;
    top: 50%;
    width: 300px;
    height: 250px;
    margin: 300px auto;
    img {
      width: 70px;
      height: 70px;
    }
    .modal-close{
      background-color: #325C3E;
      font-weight: bold;
      color:white;
      border-radius: 7px;
      padding: 8px 15px;
      border: 0 solid #000;
    }
  }
}

.menu-modal {
  position: fixed;
  background: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  .menu-white-bg{
    animation-name: slidein;
    animation-fill-mode: forwards;
    animation-duration: 0.7s;
    position: fixed;
    border-radius: 15px 15px 0 0;
    background-color: #fff;
    width: 100%;
    height: 60%;
    margin: 0 auto;
    ul {
      text-align: left;
      text-decoration: none;
      list-style: none;
      padding: 5px 0 0 5px;
      li {
        .menu-items{

          font-size: 18px;
          font-weight: bold;
          margin: 20px 0;
          padding: 0 10px;
          display: flex;
          span{
            margin: 0 15px;
          }
        }
      }
    }
  }
  @keyframes slidein {
  from {
    top: 100%;
  }

  to {
    top: 40%;
  }
}
}
</style>