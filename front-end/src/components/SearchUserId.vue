<template>
    <main>
        <div class="result-box feed" >
            <div v-for="(feed,idx) in feeds" :key="idx" style="margin-bottom:10px">
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
    name: 'SearchUserId',
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

    },
    created() {
        this.$axios({
            method: 'get',
            url: `${this.$baseUrl}/feed/search/${this.$store.state.id}/${this.keyword}/userId/0`
            }).then((response) => {
            this.feeds = response.data.feeds;
            }).catch((error) =>{
            console.log(error);
        })
    },

    methods : {


    }
  }
  </script>
  <style lang="scss">

  </style>