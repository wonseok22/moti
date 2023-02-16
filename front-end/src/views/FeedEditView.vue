<template>
  <div id="feed-update-layout">
    <main id="feed-update-main">
      <header id="feed-update-header">
        <!-- 취소 -->
        <span
      @click="goBack"
      class="material-symbols-outlined text-active">
      arrow_back
      </span>
      <span
      @click="editFeed"
      class="material-symbols-outlined text-active">
      send
      </span>
        <!-- <span @click="goBack" class="text-active">취소</span> -->
        <!-- <span @click="editFeed" class="text-active">수정</span> -->
        <!-- 등록 -->
      </header>
      <section id="feed-update-section">
        <!-- 카테고리 이름 -->
        <!-- <p>{{ missionInfo.categoryName}}</p> -->
        <!-- 플레이리스트 이름 -->
        <p>{{ this.$store.state.nowFeed.playlistName }}</p>
        <!-- 미션 이름 -->
        <p>{{ this.$store.state.nowFeed.missionName }}</p>
      </section>
      <div>
        <hr>
      </div>
      <article id="feed-update-article">
        <!-- 피드 작성 -->
        <textarea 
          @input="inputContent" 
          name="feed-update-input" 
          id="feed-update-input"
          maxlength="500"
          placeholder="피드의 사진은 수정이 불가능합니다."
        >
          </textarea>
      </article>
    </main>

    <!-- 이미지 -->
    <aside 
      v-show="this.images"
      id="preview-img-layout">
    </aside>

    <basic-modal
      v-if="openModal"
      :content="modalContent"
      @close="modalClose"
    >
    </basic-modal>

    <feed-creating-modal
      v-if="isWriting"
    >
    </feed-creating-modal>
  </div>
</template>

<script>
import BasicModal from '@/components/BasicModal'
import { basicModalMixin } from '@/tools/basicModalMixin.js'
import FeedCreatingModal from '@/components/FeedCreatingModal'

export default {
  name: 'FeedEditView',
  components: {
    BasicModal,
    FeedCreatingModal,
  },
  mixins: [
    basicModalMixin,
  ],
  data() {
    return {
      isWriting: false,
      content: null,
      images: null,
      feedId: this.$store.state.nowFeed.feedId
    }
  },
  methods: {
    // 취소버튼: 뒤로가기
    goBack() {
      this.$router.go(-1)
    },
    // 피드 등록
    editFeed() {
      if (this.content == "" || this.content == null){
        this.openModal = true
        this.modalContent = "내용을 입력해주세요!"
        return
      }

      if (!this.isWriting) {
        this.isWriting = true
        const payload = {
          feedId: this.$store.state.nowFeed.feedId,
          content: this.content,
        }
        this.$axios({
          method: 'put',
          url: `${this.$baseUrl}/feed/${this.feedId}`,
          headers: {
            'Content-Type': 'multipart/form-data',
          },
          data: payload
        })
          .then(() => {
            this.isWriting = false
            this.$router.push({ name: 'feed' })
          })
          .catch((error) => {
            this.isWriting = false
            console.log(error)
          })
      }
    },
    // 작성 내용 저장하기
    inputContent(event) {
      this.content = event.target.value
    },
    // 이미지 받기
    inputImage() {
      if (this.images) {
        let idx = 0
        const parentTag = document.querySelector('aside')
        // 이미지 미리보기 태그 초기화
        while (parentTag.hasChildNodes()){
          parentTag.removeChild( parentTag.firstChild );       
        }
        
        for (const img of this.images) {
          const divTag = document.createElement('div')
          divTag.setAttribute('class', 'preview-img-div')
          divTag.setAttribute('id', `preview-img-div-${idx}`)
          // 태그 만들기
          const imgTag = document.createElement('img')
          imgTag.src = img
          imgTag.setAttribute('class', 'preview-img')
          
          divTag.append(imgTag)
          parentTag.append(divTag)

          idx += 1
        }
      }
    },
    // 비공개 여부
    // isprivateCheck() {
    //   this.isprivate = !this.isprivate
    // },
    // 이미지 삭제
  },
  computed: {
    missionInfo() {
      return this.$route.query
    },
    nowImages() {
      return false
    }
  },
  mounted() {
      let imageUrls = []
      if(this.$store.state.nowFeed.feedImages){
        for(const img of this.$store.state.nowFeed.feedImages){
          imageUrls.push(img.feedImageUrl)
        }
        this.images = imageUrls
        this.inputImage()
      }
      this.content = this.$store.state.nowFeed.content
      this.feedId = this.$store.state.nowFeed.feedId
      document.querySelector("#feed-update-input").value = this.$store.state.nowFeed.content
  }
}
</script>

<style lang="scss">
  $feed-update-footer-height: 15%;
  
  // 기본 레이아웃
  #feed-update-layout {
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
  
    padding: 0px 10px;
  }
  
  // header 레이아웃
  #feed-update-header {
    display: flex;
    justify-content: space-between;
    
    margin-top: 10px;
    margin-bottom: 15px;
  
    // 취소
    span:nth-child(1) {
      color: $light-grey;
    }
  
    // 등록
    span:nth-child(2) {
      color: $light-green;
    }
  }

  #feed-update-main {
  height: 60%;

  display: flex;
  flex-direction: column;
}
  
  // 미션명
  #feed-update-section {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    // 모든 자식 p 태그 마진 제거
    > p {
      margin: 0px;
    }
    // 카테고리
    p:nth-child(1) {
      font-size: $fs-7;
    }

    // 미션명
    p:nth-child(2) {
      text-align: start;
      font-weight: bold;
      color: $mission-green;
    }
  }
  
  // 피드 작성 부분
  #feed-update-article {
    height: 100%;
  }
  
  // 피드 내용 작성
  #feed-update-input {
    width: 100%;
    height: 100%;
    border: none;
    resize: none;
    padding: 5px;

    border: 1px solid;

    font-size: $fs-6;
    color: $dark-grey;
    white-space: pre-line;

    &::placeholder {
      color: $light-grey;
    }

    &:focus {
      outline: none;
    }
  }
  
  // 이미지 프리뷰 레이아웃
  #preview-img-layout {
    height: 10vh;
    display: flex;
    overflow-x: scroll;
    gap: 10px;

    margin-top: 10px;
  
    &::-webkit-scrollbar {
    display: none;
  }
  }
  
  .preview-img-div {
    position: relative;
    
  }
  
  .preview-img {
    height: 90%;
    max-width: 85px;
    min-width: 40px;
  }
  
  .preview-img-span {
    font-size: $fs-6;
    width: $fs-5;
    height: $fs-5;
    position: absolute;
    right: 3px;
    top: 3px;
    background-color: $light-grey;
    border-radius: 50%;
    text-align: center;
  }
  
  // footer
  #feed-update-footer {
    height: $feed-update-footer-height;
    display: flex;
    justify-content: space-between;
    align-items: center;
    
    margin-bottom: 10px;
  }
  
  #feed-update-footer-private {
    display: flex;
    justify-content: center;
    white-space:nowrap;
  }
  
  .material-symbols-outlined {
    font-variation-settings:
    'FILL' 0,
    'wght' 400,
    'GRAD' 0,
    'opsz' 48
  }
  </style>