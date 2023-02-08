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
      <infinite-loading @infinite="infiniteHandler" spinner="waveDots" :distance="0" direction="bottom">
        <div slot="no-more"></div>
      </infinite-loading>
    </div>
    <NavigationBar></NavigationBar>
    <div class="moving-notification">
      <p>모든 피드가 로드되었습니다. <br/>이젠 {{ this.$store.state.nickname }}님의 얘기를 들려주세요!</p>
    </div>
    <div v-if="isCommentClicked" class="comment-page">
      <FeedComment/>
    </div>
  </div>
</template>

<script>
import InfiniteLoading from 'vue-infinite-loading'
import MainFeedHeader from '@/components/MainFeedHeader.vue'
import SingleFeedBody from '../components/SingleFeedBody.vue'
import FeedComment from '@/components/FeedComment.vue'

export default {
  components: {
    InfiniteLoading,
    MainFeedHeader,
    SingleFeedBody,
    FeedComment,
  },
  data() {
    return {
      newKind: "following",
      feeds: [],
      minFeedId: 9999999
      
    }
  },
  methods: {
    infiniteHandler($state) {
      setTimeout(() => {
        this.$axios({
          method:'get',
          url: `${this.$baseUrl}/feed/search/${this.$store.state.id}/${this.newKind}/default/${this.minFeedId}`
        })
        .then((res) => {
          // console.log(res.data)
          this.feeds = this.feeds.concat(res.data.feeds)
          $state.loaded()
          this.minFeedId = res.data.minFeedId
          if(res.data.isLast) {
            $state.complete()
            const notification = document.querySelector(".moving-notification")
            notification.classList.add('show')
            setTimeout(() => {
              notification.classList.remove('show')
            }, 2000)
            this.isMorePage = false
          }
          else if(res.data.keyword === "all") {
            if(this.newKind === "following"){
              this.newKind = "all"
            }
          }
        })
      }, 500)
    },
  },
  computed: {
    isCommentClicked() {
      return this.$store.getters.isCommentClicked
    }
  },  
  watch: {
  }
}
</script>

<style>

</style>