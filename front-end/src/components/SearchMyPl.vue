<template>
    <main>
        <div class="result-box" style="background-color: #131532;">
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
    import SingleFeedBody from './SingleFeedBody.vue'

    export default {
    name: 'SearchMyPl',
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
                    url: `${this.$baseUrl}/feed/search/${this.keyword}/tmp/userid/0`
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


    }
  }
  </script>
  <style lang="scss">

  </style>