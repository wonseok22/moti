<template>
    <main>
      <div class="result-box feed" >
        <div v-for="(feed,idx) in feeds" :key="idx" style="margin-bottom:10px">
          <MainFeedHeader
          :HeaderData="feed"
          @deleteFeed="deleteFeed"
         
          />
          <SingleFeedBody
          @openLikeModal="openLikeModal"
          @makeLike="makeLike"
          @deleteLike="deleteLike"
          :BodyData="feed"
          :feedIdx="idx"/>
        </div>
        <infinite-loading @infinite="infiniteHandler" spinner="waveDots" :distance="50" direction="bottom">
            <div slot="no-more"></div>
        </infinite-loading>
      </div>
    </main>
  </template>
  
  <script>
    import MainFeedHeader from '@/components/Feed/MainFeedHeader.vue'
    import SingleFeedBody from '@/components/Feed/SingleFeedBody.vue'
    import InfiniteLoading from 'vue-infinite-loading'

    export default {
    name: 'SearchUserId',
    props: {
        keyword: String,
        likeChanged: Object,
    },
    components: {
    InfiniteLoading,
    MainFeedHeader,
    SingleFeedBody
  },
    data() {
      return {
        feeds:[],
        minFeedId: 99999,
      }
    },
    computed: {
      changedLike() {
        return this.likeChanged.seed
      }
    },
    watch: {
      changedLike() {
        if(this.likeChanged.value === 1){
          this.feeds[this.likeChanged.feedIdx].hit = true
          this.feeds[this.likeChanged.feedIdx].likes += this.likeChanged.value
        }
        else{
          this.feeds[this.likeChanged.feedIdx].hit = false
          this.feeds[this.likeChanged.feedIdx].likes += this.likeChanged.value
        }
      }
    },
    methods : {
      infiniteHandler($state) {
        setTimeout(() => {
            this.$axios({
                method:'get',
                url: `${this.$baseUrl}/feed/search/${this.$store.state.id}/${this.keyword}/userId/${this.minFeedId}`
            })
            .then((res) => {
                this.feeds = this.feeds.concat(res.data.feeds)
                $state.loaded()
                this.minFeedId = res.data.minFeedId
                if(res.data.isLast) {
                    $state.complete()
                }
            })
        }, 500)
      },
      deleteFeed(feedId) {
        this.$emit("deleteFeed", feedId)
      },
      openLikeModal(data) {
        this.$emit("openLikeModal", data)
      },
      makeLike(payload) {
        this.$emit("makeLike", payload)
      },
      deleteLike(payload) {
        this.$emit("deleteLike", payload)
      }
    }
  }
  </script>
  <style lang="scss">

  </style>