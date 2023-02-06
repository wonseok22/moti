<template>
  <div>
    <div class="single-comment">
      <span class="material-symbols-outlined"
      style="color:#A3A3A3; font-size:44px">
          account_circle
      </span>
      <div>
        <div>
          <p class="comment-nickname">{{ CommentData.nickname }}</p>
          <!-- <button 
          v-show="this.$store.state.nickname == CommentData.nickname"
          class="material-icons-outlined">
            delete
          </button> -->
          <button 
          class="material-icons-outlined"
          v-show="(CommentData.nickname === this.$store.state.nickname) || (this.$store.state.nickname === CommentData.nickname )"
          v-on:click="tabOpened = true">
            more_horiz
          </button>
        </div>
          <p class="comment-content">{{ CommentData.content }}</p>
      </div>
      <div class="more-modal" 
      v-show="tabOpened" 
      v-click-outside="onClickOutside"
      @click="showFinalModal">
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
      // console.log(this.CommentData)
    },
    props: {
        CommentData: Object,
    },  
    data() {
      return {
        tabOpened: false,
      }
    },
    methods: {
      onClickOutside () {
        this.tabOpened = false
      },
      showFinalModal() {
        this.$emit('deleteComment', this.CommentData.commentId)
        this.tabOpened = false
      //   this.$modal.show('dialog',{
      //   text:'삭제 콜?',
      //   buttons: [
      //     {
      //       title: '아뇨, 그대로 두겠습니다.',
      //       handler: () => {
      //         this.$modal.hide('dialog')
      //       }
      //     },
      //     {
      //       title:'네,, 삭제하겠습니다.',
      //       handler:() => {
      //         alert('진짜 지웠네')
      //       }
      //     }
      //   ]
      // })
      },
    }
}
</script>

<style>

</style>