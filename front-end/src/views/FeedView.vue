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
    <div class="moving-notification">
      <p>피드를 전부 감상하셨습니다. <br/>이젠 {{ this.$store.state.nickname }}님의 얘기를 들려주세요!</p>
    </div>
  </div>
</template>

<script>
import MainFeedHeader from '@/components/MainFeedHeader.vue'
import SingleFeedBody from '../components/SingleFeedBody.vue'

export default {
  async created() {
    this.getFeeds()
    // 전체 피드 조회
  },
  components: {
    MainFeedHeader,
    SingleFeedBody
  },
  data() {
    return {
      payload: {
        pageNum: 0,
        kind: "following",
      },
      feeds: [],
      isMorePage: true,

    }
  },
  methods: {
    handleNotificationListScroll() {
      const html = document.querySelector("html")
      const isAtTheBottom = (html.scrollHeight === html.scrollTop + html.clientHeight);
      // 일정 이상 밑으로 내려오면 함수 실행 / 반복된 호출을 막기위해 1초마다 스크롤 감지 후 실행
      if(isAtTheBottom) {
        setTimeout(() => this.handleLoadMore(), 500)
      }
    },
    handleLoadMore() {
      // console.log('리스트 추가')
      if(this.isMorePage) {
        this.getFeeds()
      }else{
        const notification = document.querySelector(".moving-notification")
        notification.classList.add('show')
        setTimeout(() => {
          notification.classList.remove('show')
        }, 2000)
      }
    },
    async getFeeds() {
      console.log(this.payload.pageNum)
      const res = this.$store.dispatch("FeedSearch", this.payload)
      const result = await res
      this.feeds = this.feeds.concat(result.data.feeds) 
      this.checkNextPage(result)
    },
    checkNextPage(res) {
      if(res.data.isLast) {
        this.isMorePage = false
      }
      else if(res.data.keyword === "all") {
        this.payload.kind = "all"
        this.payload.pageNum += 1
      }
      else{
        this.payload.pageNum += 1
      }
    },
  },
  mounted() {
    window.addEventListener('scroll', this.handleNotificationListScroll)
  }
}
</script>

<style>

</style>