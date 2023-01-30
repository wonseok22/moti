<template>
  <div class="feed-page">
    <div class="title-box">
      <p>moti</p>
    </div>
    <div class="feed-lists">
      <MainFeedHeader/>
      <SingleFeedBody/>
    </div>
    <NavigationBar></NavigationBar>
  </div>
</template>

<script>
import MainFeedHeader from '@/components/MainFeedHeader.vue'
import SingleFeedBody from '../components/SingleFeedBody.vue'

export default {
  components: {
    MainFeedHeader,
    SingleFeedBody
  }
}
</script>

<style>

</style>