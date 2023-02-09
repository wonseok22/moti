<template>
  <!-- 하나의 피드에서 제목과 유저 프로필에 해당되는 부분 -->
  <div class="feed-header">
    <div class="feed-header-img-wrap">
        <img :src="HeaderData.profileImageUrl ? HeaderData.profileImageUrl : profileImageUrl" alt="프로필사진" @click="moveProfile(HeaderData.userId)">
    </div>
    <div class="feed-header-content">   
        <div class="feed-header-nickname">
            <img :src="HeaderData.achievementImageUrl" alt="댜표뱃지" class="feed-header-achieve" v-if="HeaderData.achievementImageUrl">

            <p class="feed-header-nickname" @click="moveProfile(HeaderData.userId)">{{ HeaderData.nickname }}</p>
        </div>
        <p class="feed-header-mission">
            <span class="playlist-name">{{ HeaderData.playlistName }}</span><span style="font-size:12px;">의 </span>
            <span class="mission-name">{{ HeaderData.missionName }}</span>
        </p>
    </div>
    <transition name="fade">
        <span 
            class="material-icons-outlined"
            v-show="(HeaderData.userId === this.$store.state.id) && this.horiOpened"
            @click="horiClicked">
            more_vert
        </span>
    </transition>
    <transition name="slide-fade" mode="out-in">
        <div 
        v-if="tabOpened" class="more-option"
        v-click-outside="onClickOutside">
            <span class="material-symbols-outlined"
            style="color:aqua;">
                edit
            </span>
            <span class="material-symbols-outlined"
            style="color:red"
            @click="deleteFeed">
                delete
            </span>
        </div>
    </transition>
</div>
</template>

<script>
import vClickOutside from 'v-click-outside'

export default {
    name: "MainFeedHeader",
    directives: {
      clickOutside: vClickOutside.directive
    },
    async created() {
        const check_res = this.$store.dispatch("followCheck", this.HeaderData.userId)
        const check_result = await check_res
        this.Following = check_result.data.check
        // this.$store.dispatch("profileCheck", this.HeaderData.userId)
        // const profile_res = this.$store.dispatch("profileCheck", this.HeaderData.userId)
        // const profile_result = await profile_res
        // console.log(profile_result)
        // this.profileData = profile_result.data.profile
    },
    props: {
        HeaderData: Object,
    },
    data() {
        return {
            profileImageUrl:require(`@/assets/images/default_profile.jpg`),
            Following: null,
            profileData: null,
            payload: {
                targetId: null,
                type: null,
            },
            horiOpened: true,
            tabOpened: false,
        }
    },
    methods: {
        async FollowOrUnfollow() {
            this.payload.targetId = this.HeaderData.userId
            if(this.Following){
                this.payload.type = "unfollow"    
                this.Following = false
            }else{
                this.payload.type = "follow"
                this.Following = true
            }
            //console.log(this.payload)
            this.$store.dispatch("FollowUnfollow", this.payload)
        },
        moveProfile(userId){
            this.$store.commit("UPDATE_PROFILE_TARGET_ID",userId);
            this.$router.push({
                name: 'profile',
            }).catch(() => {});
        },
        horiClicked() {
            this.tabOpened = true
            this.horiOpened = false
        }, 
        onClickOutside () {
            this.tabOpened = false
            this.horiOpened = true
         },
         deleteFeed() {
            this.$emit("deleteFeed", this.HeaderData.feedId)
         }
    }
}
</script>

<style scoped lang="scss">

.slide-fade-enter {
    transform: translateX(10px);
    opacity: 0;
  }
  
  .slide-fade-enter-active,
  .slide-fade-leave-active {
    transition: all 0.2s ease;
  }
  
  .slide-fade-leave-to {
    transform: translateX(10px);
    opacity: 0;
  }

.fade-enter{
    opacity: 0;
}

.fade-enter-active,
.fade-leave-active{
    transition: opacity 0.2s ease-out;
}

.fade-leave-to{
    opacity: 0;
}


</style>