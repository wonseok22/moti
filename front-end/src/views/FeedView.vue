<template>
  <div class="feed-page">
    <div class="title-box">
      <p>moti</p>
    </div>
    <div class="feed-lists">
      <MainFeedHeader
      v-bind:HeaderData="feedHeaderData"/>
      <SingleFeedBody
      v-bind:BodyData="feedBodyData"/>
    </div>
    <NavigationBar></NavigationBar>
  </div>
</template>

<script>
import MainFeedHeader from '@/components/MainFeedHeader.vue'
import SingleFeedBody from '../components/SingleFeedBody.vue'

export default {
  created() {

  },
  components: {
    MainFeedHeader,
    SingleFeedBody
  },
  data() {
    return {
      feedHeaderData : {
        missionName: "이게나야",
        nickname: "이상화",
        playlistName: "어쩔",
        feedId: 2,
      },
      feedBodyData: {
        content: "지각 해버렸다 ㅠ",
        likes: 0,
        commentNum: 3,
        feedId: 2,
      },
    }
  }
}
</script>

<style>

</style>