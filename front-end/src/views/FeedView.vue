<template>
  <div class="feed-page">
    <div class="title-box">
      <p id="main-logo">moti</p>
    </div>
    <div class="feed-lists">
      <div v-for="feed in this.feeds" :key="feed.feedId">
        <MainFeedHeader
        v-bind:HeaderData="feed"
        @deleteFeed="deleteFeed"/>
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
    <div class="feed-delete-modal"
    v-show="isDelete">
      <div class="feed-delete-modal-body">
        <p> 해당 피드를 정말로 삭제하시겠습니까?</p>
        <div>
          <button @click="finalNo">취소</button>
          <button @click="finalOk">삭제</button>
        </div>
      </div>
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
  mixins: {

  },
  data() {
    return {
      newKind: "following",
      feeds: [],
      minFeedId: 9999999,
      isDelete: false,
      deleteId: null,
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
    deleteFeed(feedId) {
      const nowHeight = window.scrollY
      document.querySelector(".feed-delete-modal").style.top = `${nowHeight}px`
      document.body.classList.add("stop-scroll")
      this.isDelete = true
      this.deleteId = feedId
    },
    finalOk() {
      this.$store.dispatch("feedDelete", this.deleteId)
      document.body.classList.remove("stop-scroll")
      // for(let i=0; i<this.feeds.length;i++){
      //   if(this.feeds[i].feedId === this.deleteId){
      //     this.feeds.splice(i,1)
      //   }
      // }
      this.isDelete = false
      window.location.reload()
    },
    finalNo() {
      document.body.classList.remove("stop-scroll")
      this.isDelete = false
    },
    closePage() {
      this.$store.dispatch("showComment")
      document.body.style.overflow = "scroll"
      window.scrollTo(0, this.$store.state.scrollY)
    }

  },
  computed: {
    isCommentClicked() {
      return this.$store.getters.isCommentClicked
    }
  },  
  watch: {
  },
  mounted () {
    window.onpageshow = () => {
      this.closePage()
    }
  },
}
</script>

<style lang="scss">
#main-logo {
  font-size: $fs-0;
}

  //   .slide-leave-active {
  //       animation: slide-out 1s ease-out forwards;
  //       transition: opacity .5s;
  //       opacity: 1;
  //   }
</style>