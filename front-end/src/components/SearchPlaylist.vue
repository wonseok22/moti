<template>
    <main>
        <div class="result-box feed">
            <div v-for="(feed,idx) in feeds" :key="idx" >
                <MainFeedHeader
                :HeaderData="feed"
                @deleteFeed="deleteFeed"/>
                <SingleFeedBody
                @openLikeModal="openLikeModal"
                :BodyData="feed"/>
            </div>
        </div>
    </main>
  </template>
  
  <script>
    import MainFeedHeader from '@/components/MainFeedHeader.vue'
    import SingleFeedBody from '../components/SingleFeedBody.vue'

    export default {
    name: 'SearchPlaylist',
    props: {
        keyword: String,
    },
    components: {
    MainFeedHeader,
    SingleFeedBody
  },
    data() {
        return {
            feeds:null,
            minFeedId: 99999,
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
    }
  }
  </script>
  <style lang="scss">

  </style>