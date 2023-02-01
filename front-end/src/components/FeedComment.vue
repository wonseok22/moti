<template>
  <div class="feed-comment">
    <MainFeedHeader
    v-bind:HeaderData="feedHeaderData"/>
    <SingleFeedBody
    v-bind:BodyData="feedBodyData"/>
    <div>
      <SingleComment 
      v-for="(item,index) in comments" 
      v-bind:CommentData="item" 
      :key="index"/>
    </div>
    <div ref="commentInput" class="comment-input">
      <span class="material-symbols-outlined"
      style="color:#A3A3A3; font-size:44px">
          account_circle
      </span>
      <form action="">
        <textarea ref="commentTextarea" name="" cols="35" rows="1"
        @input="isHeightChanged">
        </textarea>
      </form>
      <button>
        <p>게시</p>
      </button>
    </div>
  </div>
</template>

<script>
import MainFeedHeader from '@/components/MainFeedHeader.vue'
import SingleFeedBody from '@/components/SingleFeedBody.vue'
import SingleComment from '@/components/SingleComment.vue'

export default {
  name: 'FeedComment',
  data() {
    return {
      feedHeaderData : {
        missionName: this.$route.query.FeedData.missionName,
        nickname: this.$route.query.FeedData.nickname,
        playlistName: this.$route.query.FeedData.playlistName,
        feedId: this.$route.query.FeedData.feedId,
      },
      feedBodyData: {
        content: this.$route.query.FeedData.content,
        likes: this.$route.query.FeedData.likes,
        commentNum: 3,
        feedId: this.$route.query.FeedData.feedId,
      },
      comments: this.$route.query.FeedData.comments,
    }
  },
  created() {
  },
  methods: {
    isHeightChanged() {
      const textarea = this.$refs.commentTextarea
      textarea.style.height = 'auto'
      textarea.style.height = textarea.scrollHeight + 'px'
    }
  },
  components: {
    MainFeedHeader,
    SingleFeedBody,
    SingleComment,
  }
}
</script>

<style>

</style>