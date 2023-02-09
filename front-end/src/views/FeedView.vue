<template>
  <div class="feed-page">
    <div class="title-box">
      <p>moti</p>
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
    <transition>
      <div v-if="isCommentClicked" class="comment-page">
        <FeedComment/>
      </div>
    </transition>
    <!-- <div class="feed-delete-modal">
      <div class="feed-delete-modal-body">

      </div>
    </div> -->
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
    deleteFeed(feedId) {
      console.log(feedId)
    }
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

<style scoped lang="scss">
  // .slide-enter{
  //     transform: translateX(-50px);
  //     opacity: 1;
  //   }

  //   .slide-enter-active{
  //       animation: slide-in 1s ease-out forwards;
  //       transition: opacity .5s;
  //   }

  //   .slide-leave-to {
  //     transform: translateX(50px);
  //   }

  //   .slide-leave-active {
  //       animation: slide-out 1s ease-out forwards;
  //       transition: opacity .5s;
  //       opacity: 1;
  //   }
</style>