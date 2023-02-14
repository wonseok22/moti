<template>
  <div class="feed-comment">
    <MainFeedHeader
    v-bind:HeaderData="this.$store.state.nowFeed"
    @deleteFeed="deleteFeed"/>
    <SingleFeedBody
    :BodyData="this.feed"/>
    <div class="comments-list">
      <div 
      v-for="(item) in this.$store.state.nowFeed.comments"
      :key="item.commentId">
        <SingleComment 
        :CommentData="{
          comment: item,
          feed: feed,
        }"/>
      </div>
    </div>
    <div ref="commentInput" class="comment-input">
     
      <form 
      v-on:submit.prevent=""
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
      @click="writeComment"
      type="submit"
      form="commentForm">
        <span
        class="material-symbols-outlined">
        send
        </span>
      </button>
      <!-- <button
      @click="writeComment"
      type="submit"
      form="commentForm">
        <p>게시</p>
      </button> -->
    </div>
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
    <div class="feed-delete-modal"
    v-show="isDelete">
      <div class="feed-delete-modal-body">
        <p> 해당 피드를 정말로 삭제하시겠습니까?</p>
        <div>
          <button @click="finalNo">취소</button>
          <button @click="finalOk">삭제</button>
        </div>
      </div>
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
      writeCommentData: {
        userId: this.$store.state.id,
        feedId: this.$store.state.nowFeed.feedId,
        content: null
      },
      feed: this.$store.state.nowFeed,
      isDelete: false,
      deleteId: this.$store.state.nowFeed.feedId,
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
      if(this.writeCommentData.content != "" && this.writeCommentData.content != null){
        this.$store.dispatch("writeComment", this.writeCommentData)
        this.$refs.commentTextarea.value = ""
        this.writeCommentData.content = null
        const doc = document.querySelector(".feed-comment") 
        const textarea = this.$refs.commentTextarea
        textarea.style.height = 'auto'
        doc.scrollTo({
          top: (doc.scrollHeight + 50),
          behavior:"smooth"
        })
      } else 
      {
        alert("댓글을 입력해주세요.")
      }
    },
    showModal(commentId) {
      const payload = {
        commentId: commentId,
        feedId: this.$store.state.nowFeed.feedId,
      }
      this.$store.dispatch('deleteComment', payload)
    },
    closePage() {
      console.log(this.$store.state.scrollY)
      this.$store.dispatch("closeComment")
      document.body.style.overflow = "scroll"
      window.scrollTo(0, this.$store.state.scrollY)
    },
    thisFeedLiked() {
      this.$emit("thisFeedLiked")
    },
    deleteFeed() {
      const nowHeight = window.scrollY
      document.querySelector(".feed-delete-modal").style.top = `${nowHeight}px`
      document.body.classList.add("stop-scroll")
      this.isDelete = true
    },
    finalOk() {
      this.$store.dispatch("feedDelete", this.deleteId)
      document.body.classList.remove("stop-scroll")
      this.isDelete = false
      window.location.reload()
    },
    finalNo() {
      document.body.classList.remove("stop-scroll")
      this.isDelete = false
    },
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