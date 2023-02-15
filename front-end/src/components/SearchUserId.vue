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
          :BodyData="feed"/>
        </div>
        <infinite-loading @infinite="infiniteHandler" spinner="waveDots" :distance="0" direction="bottom">
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
    name: 'SearchUserId',
    props: {
        keyword: String,
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
    watch: {

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
      }
    }
  }
  </script>
  <style lang="scss">

  </style>