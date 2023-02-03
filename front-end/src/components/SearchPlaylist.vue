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
        }
    },
    watch: {
        keyword : function() {
            if (this.keyword != "") {
                this.$axios({
                    method: 'get',
                    url: `${this.$baseUrl}/feed/search/${this.$store.state.id}/${this.keyword}/playlist/0`
                    }).then((response) => {
                    this.feeds = response.data.feeds;
                    console.log("피드 받아오기 성공");
                    console.log(this.feeds);
                    }).catch((error) =>{
                    console.log(error);
                })
            }
        },
        feeds: function() {
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