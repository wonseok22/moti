<template>
    <main>
        <div class="result-box">
            <div v-for="(feed,idx) in feeds" :key="idx" >
                <MainFeedHeader
                :HeaderData="feed"/>
                <SingleFeedBody
                :BodyData="feed"/>
            </div>

        </div>
    </main>
  </template>
  
  <script>
import MainFeedHeader from '@/components/MainFeedHeader.vue'
import SingleFeedBody from '../components/SingleFeedBody.vue'

  export default {
    name: 'SearchFeed',
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
        feedHeaderData : {
            missionName: "이게나야",
            nickname: "이상화",
            playlistName: "어쩔",
            feedId: 2,
        },
        feedBodyData: {
            content: "지각 해버렸다 ㅠ",
            likes: 0,
            commentNum: 3,
            feedId: 2,
        },
      }
    },
    watch: {
        keyword : function() {
            if (this.keyword != "") {
                this.$axios({
                    method: 'get',
                    url: `${this.$baseUrl}/feed/search/${this.$store.state.id}/${this.keyword}/content/0`
                    }).then((response) => {
                    this.feeds = response.data.feeds;
                    console.log("피드 받아오기 성공")
                    console.log(this.feeds)
                    }).catch((error) =>{
                    console.log(error)
                })
            }
        }

    },
    created() {

    },

    methods : {


    }
  }
  </script>
  <style lang="scss">

  </style>