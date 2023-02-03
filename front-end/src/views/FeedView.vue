<template>
  <div class="feed-page">
    <div class="title-box">
      <p>moti</p>
    </div>
    <div class="feed-lists">
      <div v-for="feed in this.feeds" :key="feed.feedId">
        <MainFeedHeader
        v-bind:HeaderData="feed"/>
        <SingleFeedBody
        v-bind:BodyData="feed"/>
      </div>
    </div>
    <NavigationBar></NavigationBar>
  </div>
</template>

<script>
import MainFeedHeader from '@/components/MainFeedHeader.vue'
import SingleFeedBody from '../components/SingleFeedBody.vue'

export default {
  created() {
    // 전체 피드 조회
    this.$axios({
      method:'get',
      url:`${this.$baseUrl}/feed/search/red/a/default/${this.pageNum}`
    })
    .then((res) => {
      this.feeds = res.data.feeds
    })
    .catch((error) => {
      console.log(error)
    })
  },
  components: {
    MainFeedHeader,
    SingleFeedBody
  },
  data() {
    return {
      pageNum: 0,
      feeds: null,
    }
  }
}
</script>

<style>

</style>