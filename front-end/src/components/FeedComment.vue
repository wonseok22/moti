<template>
  <div class="feed-comment">
    <div class="comment-header">
      <span
      @click="closePage"
      class="material-symbols-outlined">
      arrow_back
      </span>
      <p>
        댓글
      </p>
    </div>
    <MainFeedHeader
    v-bind:HeaderData="feedHeaderData"/>
    <SingleFeedBody
    v-bind:BodyData="feedBodyData"/>
    <div class="comments-list">
      <div 
      v-for="(item) in this.$store.state.nowFeed.comments"
      :key="item.commentId">
        <SingleComment 
        :CommentData="{
          comment: item,
          feed: feed,
        }"
        @deleteComment="showModal"/>
      </div>
    </div>
    <div ref="commentInput" class="comment-input">
      <span class="material-symbols-outlined"
      style="color:#A3A3A3; font-size:44px">
          account_circle
      </span>
      <form 
      v-on:submit.prevent="writeComment"
      id="commentForm">
        <textarea
        ref="commentTextarea"
        placeholder="댓글을 입력하세요."
        rows="1"
        maxlength="100"
        @input="isHeightChanged">
        </textarea>
      </form>
      <button
      type="submit"
      form="commentForm">
        <p>게시</p>
      </button>
      <!-- <button
      v-show="this.writeCommentData.content.length == 0"
      type="submit"
      form="commentForm"
      style="color:#A4D1C2">
        <p>게시</p>
      </button> -->
    </div>
    <!-- <p class="letter-counts">
      {{ this.commentLength }} / 100
    </p> -->
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
        missionName: this.$store.state.nowFeed.missionName,
        nickname: this.$store.state.nowFeed.nickname,
        playlistName: this.$store.state.nowFeed.playlistName,
        feedId: this.$store.state.nowFeed.feedId,
      },
      feedBodyData: {
        content: this.$store.state.nowFeed.content,
        likes: this.$store.state.nowFeed.likes,
        hit: this.$store.state.nowFeed.hit,
        feedImages: this.$store.state.nowFeed.feedImages,
        comments: this.$store.state.nowFeed.comments,
        feedId: this.$store.state.nowFeed.feedId,
      },
      writeCommentData: {
        userId: this.$store.state.id,
        feedId: this.$store.state.nowFeed.feedId,
        content: null
      },
      feed: this.$store.state.nowFeed
    }
  },
  methods: {
    // textarea에 입력할 때 마다 입력된 글자의 개수와 줄바꿈이
    // 일어나는지를 확인하는 함수
    isHeightChanged(event) {
      const textarea = this.$refs.commentTextarea
      this.writeCommentData.content = event.target.value
      textarea.style.height = 'auto'
      textarea.style.height = textarea.scrollHeight + 'px'
    },
    writeComment() {
      this.$store.dispatch("writeComment", this.writeCommentData)
      this.$refs.commentTextarea.value = ""
      const doc = document.querySelector(".feed-comment") 
      const textarea = this.$refs.commentTextarea
      textarea.style.height = 'auto'
      doc.scrollTo({
        top: (doc.scrollHeight + 50),
        behavior:"smooth"
      })
    },
    showModal(commentId) {
      const payload = {
        commentId: commentId,
        feedId: this.$store.state.nowFeed.feedId,
      }
      this.$store.dispatch('deleteComment', payload)
    },
    closePage() {
      this.$store.dispatch("showComment")
      document.body.style.overflow = "scroll"
      window.scrollTo(0, this.$store.state.scrollY)
    },
  },
  created() {
  },  
  mounted() {
    window.scrollTo(0,0);
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