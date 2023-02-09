<template>
  <!-- 하나의 피드에서 제목과 유저 프로필에 해당되는 부분 -->
  <div class="feed-header">
    <div class="feed-header-img-wrap">
        <img :src="HeaderData.profileImageUrl ? HeaderData.profileImageUrl : profileImageUrl" alt="프로필사진" @click="moveProfile(HeaderData.userId)">
    </div>
    <div class="feed-header-content">   
        <div class="feed-header-nickname">
            <img :src="HeaderData.achievementImageUrl" alt="댜표뱃지" class="feed-header-achieve" v-if="HeaderData.achievementImageUrl">
            <!-- <img 
            v-show="this.profileData.achievementImageUrl !== null" 
            :src="this.profileData.achievementImageUrl" 
            alt="achievementImage"> -->
            <p class="feed-header-nickname" @click="moveProfile(HeaderData.userId)">{{ HeaderData.nickname }}</p>
            <!-- <div v-show="this.$store.state.nickname !== HeaderData.nickname">
                <button 
                v-show="!this.Following"
                @click="FollowOrUnfollow">
                    팔로우
                </button>
                <button
                v-show="this.Following" 
                style="color:#ababab;"
                @click="FollowOrUnfollow">
                    팔로잉
                </button>
            </div> -->
        </div>
        <p class="feed-header-mission">
            <span class="playlist-name">{{ HeaderData.playlistName }}</span><span style="font-size:12px;">의 </span>
            <span class="mission-name">{{ HeaderData.missionName }}</span>
        </p>
    </div>
</div>
</template>

<script>
export default {
    name: "MainFeedHeader",
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

    }
}
</script>

<style lang="scss">
.feed-header-img-wrap{
    background-color: #ccc;
    width: 50px !important;
    height: 50px !important;
    border-radius: 100%;
    overflow: hidden;
    img {
        object-fit: cover;
        width: 50px;
        height: 50px;
    }

}
.feed-header-achieve{
    padding-bottom: 5px;
    width: 18px;
    height: 18px;
}
.feed-header-nickname{
    font-size: 16px;
}
.mission-name{
    font-size: 12px;
    font-weight: bold;
    line-height: 1.4;
}  

.playlist-name{
    line-height: 1.4;
    font-size: 12px;
    font-weight: bold;
}
</style>