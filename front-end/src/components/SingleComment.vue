<template>
  <div>
    <div class="single-comment">
      <div class="comment-img-wrap">
        <img :src="CommentData.comment.profileImageUrl ? CommentData.comment.profileImageUrl : profileImageUrl" alt="프로필사진" 
        @click="moveProfile(CommentData.comment.userId)">
      </div>
      <div class="comment-text">
        <div>
          <p class="comment-nickname"
          @click="moveProfile(CommentData.comment.userId)">
            {{ CommentData.comment.nickname }}
          </p>
          <button 
          class="material-icons-outlined"
          v-show="((CommentData.comment.nickname === this.$store.state.nickname) || (CommentData.feed.nickname === this.$store.state.nickname)) && this.horiOpened"
          @click="horiClicked">
            more_horiz
          </button>
        </div>
        <p class="comment-content">{{ CommentData.comment.content }}</p>
      </div>
      <div class="more-modal" 
      v-show="tabOpened" 
      v-click-outside="onClickOutside"
      @click="deleteComment">
        <p>삭제</p>
      </div>
    </div>
  </div>
</template>

<script>
import vClickOutside from 'v-click-outside'

export default {
    name:'SingleComment',
    directives: {
      clickOutside: vClickOutside.directive
    },
    mounted() {
    },
    props: {
        CommentData: Object,
    },  
    data() {
      return {
        tabOpened: false,
        horiOpened: true,
        profileImageUrl:require(`@/assets/images/default_profile.jpg`),
      }
    },
    methods: {
      horiClicked() {
        this.tabOpened = true
        this.horiOpened = false
      }, 
      onClickOutside () {
        this.tabOpened = false
        this.horiOpened = true
      },
      deleteComment() {
        const payload = {
          commentId: this.CommentData.comment.commentId,
          feedId: this.$store.state.nowFeed.feedId,
        }
        this.$store.dispatch('deleteComment', payload)
        this.tabOpened = false
      },
      moveProfile(userId){
          this.$store.commit("UPDATE_PROFILE_TARGET_ID",userId);
          this.$store.dispatch("showComment")
          document.body.style.overflow = "scroll"
          this.$router.push({
              name: 'profile',
          }).catch(() => {});
      },
    }
}
</script>

<style>

</style>