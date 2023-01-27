<template>
  <div class="feed-page">
    <div class="title-box">
      <p>moti</p>
    </div>
    <div class="feed-lists">
      <SingleFeed/>
    </div>
    <NavigationBar></NavigationBar>
  </div>
</template>

<script>
import SingleFeed from '../components/SingleFeed.vue'

export default {
  components: {
    SingleFeed
  }
}
</script>

<style>

</style>