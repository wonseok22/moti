<template>
  <div>
    <Header id="SearchHeader">
      
      <input type="text" @input="change" placeholder=" 검색"/>
    </Header>
    <nav id="CategoryNav">
      <div class="playlist" @click="playlist()">성장 플랜</div>
      <div class="feed" @click="feed()">게시글</div>
      <div class="nickname" @click="nickname()">계정</div>
    </nav>
    <div class="underbar">
      <div id="bar" class="bar1"></div>
    </div>
    <div class="SearchResult">
      <div class="SearchResult-slide">
        <SearchPlaylist :keyword="keyword"
        @deleteFeed="deleteFeed"
        @openLikeModal="openLikeModal"
        @makeLike="makeLike"
        @deleteLike="deleteLike"
        :likeChanged = "likeChanged" ></SearchPlaylist>
        <SearchFeed :keyword="keyword"
        @deleteFeed="deleteFeed"
        @openLikeModal="openLikeModal"
        @makeLike="makeLike2"
        @deleteLike="deleteLike2"
        :likeChanged2 = "likeChanged2" ></SearchFeed>
        <SearchNickname :keyword="keyword"></SearchNickname>
      </div>
    </div>
    <div v-if="isCommentClicked" class="comment-page">
      <FeedComment @openLikeModal="openLikeModal"/>
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
    <NavigationBar></NavigationBar>

    <div v-if="isCommentClicked && (this.pageType == 1)" class="comment-page">
      <FeedComment
      @makeLike="makeLike"
      @deleteLike="deleteLike"/>
    </div>

    <div v-if="isCommentClicked && (this.pageType == 2)" class="comment-page">
      <FeedComment
      @makeLike="makeLike2"
      @deleteLike="deleteLike2"/>
    </div>

    <div class="like-modal" v-show="likeModal">
      <div class="like-modal-close" @click="likeModal = false"></div>
      <div class="like-white-bg">
        <h3>좋아요 목록</h3>
        <div class="like-list">
          <div  v-for="(like,idx) in likes" :key="idx" class="like-item"  @click="moveProfile(like.userId)">
            <div class="like-img-wrap">
              <img :src="like.profileImageUrl ? like.profileImageUrl : defaultImage" alt="프로필사진" class="like-img">
            </div>
            <div class="like-nickname-wrap">
              <img :src="like.achievementImageUrl" alt="대표뱃지" class="like-achieve" v-if="like.achievementImageUrl">
              <div class="like-nickname">{{ like.nickname  }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import SearchPlaylist from '@/components/Search/SearchPlaylist.vue'
import SearchFeed from '@/components/Search/SearchFeed.vue'
import SearchNickname from '@/components/Search/SearchNickname.vue'
import FeedComment from '@/components/Comment/FeedComment.vue'

export default {
  beforeRouteLeave(to,from,next){
    if(this.$store.state.isComment){
      document.body.style.overflow = "scroll"
      this.$store.dispatch("closeComment")
      next(false)
      //nextTick을 통해 렌더링이 완전히 이루어졌음을 확인
      this.$nextTick(() => {
        //setTimeout을 통해 크롬의 기본 scroll anchor 현상보다 먼저 일어나지 않도록 한다
        setTimeout(() => {
          window.scrollTo(0, this.$store.state.scrollY)
        }, 1)
      })
    }
    else{
      next()
    }
  },
  data() {
    return {
      keyword : "",
      isDelete: false,
      deleteId: null,
      likeModal:false,
      likes:null,
      defaultImage:require(`@/assets/images/default_profile.jpg`),
      likeChanged: {
        feedIdx: null,
        value: null,
        seed: 1,
      },
      likeChanged2: {
        feedIdx: null,
        value: null,
        seed: 1,
      },
      pageType: 1,
    }
  },
  created() {
  },
  methods: {
    openLikeModal(data) {
      this.$axios({
          method: "get",
          url: `${this.$baseUrl}/feed/like/${data.feedId}`,
        })
          .then((response) => {
           this.likes = response.data.list
          })
          .catch((error) => {
            console.log(error)
          });
      this.likeModal = true
      this.likeData = data
    },
    change(event) {
      this.keyword=event.target.value;
    },
    playlist () {
      this.pageType = 1
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".SearchResult-slide")
      slide.style.left = 0;
      bar.className = "bar1"; 
      
    },
    feed() {
      this.pageType = 2
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".SearchResult-slide")
      slide.style.left = "-100vw";
      bar.className = "bar2";
      
    },
    nickname() {
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".SearchResult-slide")
      slide.style.left = "-200vw";
      bar.className = "bar3";

    },
    deleteFeed(feedId) {
      const nowHeight = window.scrollY
      document.querySelector(".feed-delete-modal").style.top = `${nowHeight}px`
      document.body.classList.add("stop-scroll")
      this.isDelete = true
      this.deleteId = feedId
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
    makeLike(payload) {
      if(this.$store.state.isComment){
        this.likeChanged.feedIdx = this.$store.state.feedIdx
        this.likeChanged.value = 1
      }
      else{
        this.likeChanged.feedIdx = payload.feedIdx
        this.likeChanged.value = 1
      }
      this.likeChanged.seed *= -1
    },
    deleteLike(payload){
      if(this.$store.state.isComment){
        this.likeChanged.feedIdx = this.$store.state.feedIdx
        this.likeChanged.value = -1
      }
      else{
        this.likeChanged.feedIdx = payload.feedIdx
        this.likeChanged.value = -1
      }
      this.likeChanged.seed *= -1
    },
    makeLike2(payload) {
      if(this.$store.state.isComment){
        this.likeChanged2.feedIdx = this.$store.state.feedIdx
        this.likeChanged2.value = 1
      }
      else{
        this.likeChanged2.feedIdx = payload.feedIdx
        this.likeChanged2.value = 1
      }
      this.likeChanged2.seed *= -1
    },
    deleteLike2(payload){
      if(this.$store.state.isComment){
        this.likeChanged2.feedIdx = this.$store.state.feedIdx
        this.likeChanged2.value = -1
      }
      else{
        this.likeChanged2.feedIdx = payload.feedIdx
        this.likeChanged2.value = -1
      }
      this.likeChanged2.seed *= -1
    }
    
  },
  components: {
    FeedComment,
    SearchPlaylist,
    SearchFeed,
    SearchNickname,
  },
  computed: {
    isCommentClicked() {
      return this.$store.getters.isCommentClicked
    }
  }, 
}
</script>

<style  lang="scss">
#SearchHeader{

  width: $base_width;
  height: 90px;
  align-items: center;
  vertical-align: middle;

  
  input {
    margin-top: 30px;
    border-radius: 8px;
    height: 40px;
    width: 80%;
  }
  button {
    width: 20%;
    height: 100%;
  }
}
#CategoryNav {
  margin: 5px auto;
  width: 80%;
  display: flex;
  justify-content:  space-between;
  font-weight: bold;

  .playlist {
    width: 40%;
  }
  .feed {
    width: 30%;
  }
  .nickname {
    width: 30%;
  }
}
.underbar {
  margin: 0 auto;
  width: 80%;
  border-bottom: 1px solid #ddd;
  div {
    position: relative;
    border-bottom: 2px solid #000;
    transition-property:left;
  }
  
  .bar1 {
    width: 40%;
    left:0;
    transition:0.3s;
  }
  .bar2 {
    width: 30%;
    left: 40%;
    transition: 0.3s;
  }
  .bar3 {
    width: 30%;
    left: 70%;
    transition: 0.3s;
  }

  }
  .SearchResult {
    margin-top: 20px;
    width: $base-width;
    height: 600px;
    position: relative;
    overflow: hidden;

    .SearchResult-slide{
      transition : 0.5s;
      left:0;
      position: absolute;
      width: 300vw;
      height: 100%;
      display: flex;
      main{
        width: $base-width;
        height: 100%;
        .result-box {
          display: flex;
          flex-direction: column;
          gap: 20px;
          
          width: 100%;
          height: 100%;
          margin: 0 auto;
          padding-left: 0;
          overflow-y:scroll;
          padding-bottom: 30px;
          // background-color: #bbb;
        }
      }
      .feed {
        background-color: #eee;
      }
    }
  }
</style>