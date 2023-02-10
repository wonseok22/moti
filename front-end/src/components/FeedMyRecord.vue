<template>
    <div class="feed-page scroll">
      <div class="record-title-box">
        <span
        @click="closeModal"
      class="material-symbols-outlined back-btn">
      arrow_back
      </span>
        <img :src="flowerImageUrl" alt="꽃 이미지">
        <p>나의 성장기록</p>
      </div>
      <div class="feed-lists">
        <div v-for="(feed,idx) in this.feeds" :key="feed.feedId" class="feed-item">
            <div class="feed-item-step-img-wrap">
                <img :src="RecoredImageUrls[idx]" alt="단계 이미지" class="feed-item-step-img"/>
            </div>
          <MainFeedHeader
          v-bind:HeaderData="feed"
          @deleteFeed="deleteFeed"/>
          <SingleFeedBody
          v-bind:BodyData="feed"/>
        </div>

      </div>
      <NavigationBar></NavigationBar>

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
  import MainFeedHeader from '@/components/MainFeedHeader.vue'
  import SingleFeedBody from '../components/SingleFeedBody.vue'
  import FeedComment from '@/components/FeedComment.vue'
  
  export default {
    name:'FeedMyRecord',
    components: {
      MainFeedHeader,
      SingleFeedBody,
      FeedComment,
    },
    props: {
        feeds: Array,
        flowerImageUrl:String,
    },
    mixins: {
  
    },
    data() {
      return {
        newKind: "following",
        minFeedId: 9999999,
        RecoredImageUrls : [
            require("@/assets/images/1_soil.png"),
            require("@/assets/images/2_watering_can.png"),
            require("@/assets/images/3_sprout.png"),
            require("@/assets/images/4_sun.png"),
            this.flowerImageUrl
        ],
        
      }
    },
    methods: {
      
      deleteFeed(feedId) {
        console.log(feedId)
      },
      closePage() {
        this.$store.dispatch("showComment")
        document.body.style.overflow = "scroll"
        window.scrollTo(0, this.$store.state.scrollY)
        },
        closeModal() {
          this.$emit("closeRecordModal");
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
  
<style lang="scss">
.record-title-box{
    height: 150px ;
    position: relative;
    width: 100%;
    display: block !important;
    background-color: #fff;
    .back-btn{
        position: absolute;
        left: 10px;
        top: 10px;
        font-size: 26px;
    }
    img {
        margin-top: 15px;
        width: 80px;
        height: 80px;
    }
    p{
        font-size: 24px;
        margin: 5px 0;
        padding: 0;
        font-family: "LINESeedKR-Bd";
        color: #000;
    }
}
.scroll {
    overflow: scroll;
}
.feed-item{
    .feed-item-step-img-wrap{
        width: 100%;
        height: 75px;
        background-color: #fff;
        .feed-item-step-img {
            margin-top: 5px;
            height: 70px;
            width: 70px;
        }
    }
}
</style>