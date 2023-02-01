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
                <p>{{ HeaderData.nickname }}</p>
                <button>
                    팔로우
                </button>
            </div>
            <p>{{ HeaderData.playlistName }}의 {{ HeaderData.missionName }}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: "MainFeedHeader",
    props: {
        HeaderData: Object,
    }
}
</script>

<style>

</style>