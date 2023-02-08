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
  </div>
</template>

<script>
import InfiniteLoading from 'vue-infinite-loading'
import MainFeedHeader from '@/components/MainFeedHeader.vue'
import SingleFeedBody from '../components/SingleFeedBody.vue'

export default {
  components: {
    InfiniteLoading,
    MainFeedHeader,
    SingleFeedBody
  },
  data() {
    return {
      payload: {
        pageNum: 0,
        kind: "following",
      },
      limit:0,
      newPageNum: 0,
      newKind: "following",
      feeds: [],
      isMorePage: true,

    }
  },
  methods: {
    infiniteHandler($state) {
      setTimeout(() => {
        if(this.isMorePage){
          this.$axios({
            method:'get',
            url: `${this.$baseUrl}/feed/search/${this.$store.state.id}/${this.newKind}/default/${this.limit}`
          })
          .then((res) => {
            console.log(res.data)
            this.feeds = this.feeds.concat(res.data.feeds)
            $state.loaded()
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
                this.newPageNum = 1
                this.limit = 1
              }
              else{
                this.newPageNum += 1
                this.limit += 1
              }
            }
            else{
              this.newPageNum += 1
              this.limit += 1
            }
          })
        }
      }, 1000)
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
<<<<<<< HEAD
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
=======
>>>>>>> 243e4bae738b4e69b119b677e8beb66100d296e3
  },
}
</script>

<style>

</style>