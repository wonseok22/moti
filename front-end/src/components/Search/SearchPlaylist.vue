<template>
    <main>
        <div class="result-box feed">
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
        </div>
    </main>
  </template>
  
  <script>
    import MainFeedHeader from '@/components/Feed/MainFeedHeader.vue'
    import SingleFeedBody from '@/components/Feed/SingleFeedBody.vue'

    export default {
    name: 'SearchPlaylist',
    props: {
        keyword: String,
        likeChanged: Object,
    },
    components: {
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
        keyword : function() {
            if (this.keyword != "") {
                this.$axios({
                    method: 'get',
                    url: `${this.$baseUrl}/feed/search/${this.$store.state.id}/${this.keyword}/playlist/${this.minFeedId}`
                    }).then((response) => {
                    this.feeds = response.data.feeds;
                    }).catch((error) =>{
                    console.log(error);
                })
            }
        },
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
    created() {

    },

    methods : {
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