<template>
  <!-- 하나의 피드에서 제목과 유저 프로필에 해당되는 부분 -->
  <div class="feed-header">
        <span class="material-symbols-outlined"
        style="color:#A3A3A3; font-size:44px">
            account_circle
        </span>
        <div>   
            <div>
                <span class="material-symbols-outlined"
                style="color:#A3A3A3">
                    workspace_premium 
                </span>
                <!-- <img 
                v-show="this.profileData.achievementImageUrl !== null" 
                :src="this.profileData.achievementImageUrl" 
                alt="achievementImage"> -->
                <p>{{ HeaderData.nickname }}</p>
                <div v-show="this.$store.state.nickname !== HeaderData.nickname">
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
                </div>
            </div>
            <p>
                <span class="playlist-name">"{{ HeaderData.playlistName }}"</span>의 
                <span class="mission-name">'{{ HeaderData.missionName }}'</span>
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
        }
    }
}
</script>

<style>

</style>