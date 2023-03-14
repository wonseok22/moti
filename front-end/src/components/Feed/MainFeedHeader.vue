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
            <p class="feed-date">{{ this.date }}</p>
        </div>
        <p class="feed-header-mission">
            <span class="playlist-name">{{ HeaderData.playlistName }}</span><span style="font-size:12px;">의 </span>
            <span class="mission-name">{{ HeaderData.missionName }}</span>
        </p>
    </div>
    <transition name="fade">
        <span 
            class="material-icons-outlined"
            v-show="(HeaderData.userId === this.$store.state.id) && this.horiOpened && settingPossible"
            @click="horiClicked">
            more_vert
        </span>
    </transition>
    <transition name="slide-fade" mode="out-in">
        <div 
        v-if="tabOpened" class="more-option"
        v-click-outside="onClickOutside">
            <span class="material-symbols-outlined"
            @click="editFeed">
                edit
            </span>
            <span class="material-symbols-outlined"
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
        if (this.settingImpossible) {
            this.settingPossible = false
        }
        const check_res = this.$store.dispatch("followCheck", this.HeaderData.userId)
        const check_result = await check_res
        this.Following = check_result.data.check
        var date = new Date(this.HeaderData.createdDate); // 작성시간
        var now = new Date(); // 현재시간(-9시간 되어있음)
        this.date = new Date(date.getTime() - date.getTimezoneOffset()*60000) // 현재시간에 9시간 더함
        let diffTime = (now.getTime() - this.date.getTime())/60000 // 작성시간 - 현재시간 분단위로
        if(diffTime < 1){ // 1분 이하일경우
            this.date = "방금 전"
        }else if(diffTime < 60) { // 1시간 이하일 경우
            this.date = parseInt(diffTime) + "분 전"
        } else if(diffTime < 1440) { // 24시간 이하일 경우
            this.date = parseInt(diffTime/60) + "시간 전"
        } else if(diffTime < 1440 * 30) { // 한달 이내일 경우
            if (parseInt(diffTime/ 1440) == 1){ // 하루 전일경우
            this.date = "어제"
            } else { // 2일보다 오래된 경우
            this.date = parseInt(diffTime/ 1440) + "일 전"
            }
        }
        // this.$store.dispatch("profileCheck", this.HeaderData.userId)
        // const profile_res = this.$store.dispatch("profileCheck", this.HeaderData.userId)
        // const profile_result = await profile_res
        // this.profileData = profile_result.data.profile
    },
    props: {
        HeaderData: Object,
        settingImpossible: Boolean,
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
            date:null,
            settingPossible: true,
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
            this.$store.dispatch("FollowUnfollow", this.payload)
        },
        moveProfile(userId){
            this.$store.dispatch("closeComment")
            document.body.style.overflow = "scroll"
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
        },
        async editFeed() {
            this.$store.dispatch("closeComment")
            document.body.style.overflow = "scroll"
            const resp = this.$store.dispatch("getSingleFeed", this.HeaderData.feedId)
            const result = await resp 
            await this.$store.dispatch("putSingleFeed", result.data.feed)
            await this.$router.push({
                name:'FeedEditView',
                params: {
                    feedId: this.HeaderData.feedId
                }
            })
        }
    },
}
</script>

<style lang="scss">

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