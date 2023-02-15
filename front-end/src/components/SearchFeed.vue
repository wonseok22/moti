<template>
    <main>
        <div class="result-box feed" > 
          <div v-for="(feed,idx) in feeds" :key="idx" >
              <MainFeedHeader
              :HeaderData="feed"
              @deleteFeed="deleteFeed"/>
              <SingleFeedBody
              @openLikeModal="openLikeModal"
              @makeLike="makeLike"
              @deleteLike="deleteLike"
              :BodyData="feed"
              :feedIdx="idx"/>
          </div>
          <infinite-loading 
          @infinite="infiniteHandler" 
          spinner="waveDots" :distance="0" 
          direction="bottom"
          :identifier="this.keyword">
            <div slot="no-more"></div>
          </infinite-loading>
        </div>
    </main>
  </template>
  
  <script>
  import MainFeedHeader from '@/components/MainFeedHeader.vue'
  import SingleFeedBody from '../components/SingleFeedBody.vue'
  import InfiniteLoading from 'vue-infinite-loading'

  export default {
    name: 'SearchFeed',
    props: {
        keyword: String,
        likeChanged2: Object,
    },
    components: {
    MainFeedHeader,
    SingleFeedBody,
    InfiniteLoading,
  },
    data() {
      return {
        feeds:[],
        minFeedId:999999,
      }
    },
    computed: {
      changedLike() {
        return this.likeChanged2.seed
      }
    },
    watch: {
        keyword : function() {
            if (this.keyword != "") {
              this.minFeedId = 99999
              this.$axios({
                  method: 'get',
                  url: `${this.$baseUrl}/feed/search/${this.$store.state.id}/${this.keyword}/content/${this.minFeedId}`
                  }).then((response) => {
                  this.feeds = response.data.feeds;
                  }).catch((error) =>{
                  console.log(error)
              })
            }
        },
        changedLike() {
          if(this.likeChanged2.value === 1){
            this.feeds[this.likeChanged2.feedIdx].hit = true
            this.feeds[this.likeChanged2.feedIdx].likes += this.likeChanged2.value
          }
          else{
            this.feeds[this.likeChanged2.feedIdx].hit = false
            this.feeds[this.likeChanged2.feedIdx].likes += this.likeChanged2.value
          }
        }

    },
    created() {

    },

    methods : {
      openLikeModal(data) {
        this.$emit("openLikeModal", data)
      },
      infiniteHandler($state) {
        setTimeout(() => {
            this.$axios({
                method:'get',
                url: `${this.$baseUrl}/feed/search/${this.$store.state.id}/all/default/${this.minFeedId}`
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