<template>
  <div class="feed-page">
    <div class="title-box">
      <p id="main-logo">moti</p>
    </div>
    <div class="feed-lists">
      <div v-for="(feed, idx) in this.feeds" :key="feed.feedId">
        <MainFeedHeader
        v-bind:HeaderData="feed"
        @deleteFeed="deleteFeed"/>
        <SingleFeedBody
        @openLikeModal="openLikeModal"
        @makeLike="makeLike"
        @deleteLike="deleteLike"
        :BodyData="feed"
        :feedIdx="idx"/>
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
      <FeedComment
      @makeLike="makeLike"
      @deleteLike="deleteLike"/>
      <FeedComment  @openLikeModal="openLikeModal"/>
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


    <div class="like-modal" v-show="likeModal">
      <div class="like-modal-close" @click="likeModal = false"></div>
      <div class="like-white-bg">
        <h3>좋아요 목록</h3>
        <div class="like-list">
          <div  v-for="(like,idx) in likes" :key="idx" class="like-item"  @click="moveProfile(like.userId)">
            <div class="like-img-wrap">
              <img :src="like.profileImageUrl ? like.profileImageUrl : defaultImage" alt="프로필사진" class="like-img">
            </div>
            <div class="like-nickname-wrap">
              <img :src="like.achievementImageUrl" alt="대표뱃지" class="like-achieve" v-if="like.achievementImageUrl">
              <div class="like-nickname">{{ like.nickname  }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>



  </div>
</template>

<script>
import InfiniteLoading from 'vue-infinite-loading'
import MainFeedHeader from '@/components/Feed/MainFeedHeader.vue'
import SingleFeedBody from '../components/Feed/SingleFeedBody.vue'
import FeedComment from '@/components/Comment/FeedComment.vue'

export default {
  beforeRouteLeave(to,from,next){
    if(this.$store.state.isComment){
      document.body.style.overflow = "scroll"
      this.$store.dispatch("closeComment")
      next(false)
      //nextTick을 통해 렌더링이 완전히 이루어졌음을 확인
      this.$nextTick(() => {
        //setTimeout을 통해 크롬의 기본 scroll anchor 현상보다 먼저 일어나지 않도록 한다
        setTimeout(() => {
          window.scrollTo(0, this.$store.state.scrollY)
        }, 1)
      })
    }
    else{
      next()
    }
  },
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
      likeModal:false,
      likes:null,
      defaultImage:require(`@/assets/images/default_profile.jpg`),


    }
  },
  methods: {
    moveProfile(targetId) {
      this.$store.commit("UPDATE_PROFILE_TARGET_ID", targetId);
      this.$router
        .push({
          name: "profile",
        })
        .catch(() => {
          location.reload();
        });
      },
    openLikeModal(data) {
      this.$axios({
          method: "get",
          url: `${this.$baseUrl}/feed/like/${data.feedId}`,
        })
          .then((response) => {
           this.likes = response.data.list
          })
          .catch((error) => {
            console.log(error)
          });
      this.likeModal = true
      this.likeData = data
    },
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
    makeLike(payload) {
      if(this.$store.state.isComment){
        this.feeds[this.$store.state.feedIdx].hit = true
        this.feeds[this.$store.state.feedIdx].likes += payload.value
      }
      else{
        this.feeds[payload.feedIdx].hit = true
        this.feeds[payload.feedIdx].likes += payload.value
      }
    },
    deleteLike(payload){
      if(this.$store.state.isComment){
        this.feeds[this.$store.state.feedIdx].hit = false
        this.feeds[this.$store.state.feedIdx].likes += payload.value
      }
      else{
        this.feeds[payload.feedIdx].hit = false
        this.feeds[payload.feedIdx].likes += payload.value
      }
    }
  },
  computed: {
    isCommentClicked() {
      return this.$store.getters.isCommentClicked
    }
  },  
  watch: {
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