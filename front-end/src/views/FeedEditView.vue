<template>
    <div id="feed-create-layout">
      <header id="feed-create-header">
        <!-- 취소 -->
        <span @click="goBack" class="text-active">취소</span>
        <span @click="editFeed" class="text-active">수정</span>
        <!-- 등록 -->
      </header>
      <section id="feed-create-section">
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
  
      <article id="feed-create-article">
        <!-- 피드 작성 -->
        <textarea 
          @input="inputContent" 
          name="feed-create-input" 
          id="feed-create-input"
          maxlength="500"
          placeholder="미션에 대한 후기나 감상을 공유해보세요! 
사진(최대 10장)을 이용하면 더 좋아요!"
        >
          </textarea>
      </article>
  
      <div>
        <hr>
      </div>
      
      <!-- 이미지 -->
      <aside 
        v-show="this.images"
        id="preview-img-layout">
      </aside>
  
      <footer id="feed-create-footer">
        <!-- 사진 등록 -->
        <label for="image-input"><i class="material-symbols-outlined text-active" id="photo-camera">photo_camera</i></label>
        <input 
          @change="inputImage"  
          type="file" multiple id="image-input" style="visibility:hidden;"
        >
        <!-- 피드 비공개 -->
        <!-- <div id="feed-create-footer-private">
          <input 
            @click="isprivateCheck"
            type="checkbox" id="feed-create-footer-checkbox" name="feed-create-footer-checkbox"
          >
          <span><label for="feed-create-footer-checkbox" class="text-active-normal">피드 비공개</label></span>
        </div> -->
      </footer>
      <basic-modal
        v-if="openModal"
        :content="modalContent"
        @close="modalClose"
      >
      </basic-modal>
    </div>
  </template>
  
  <script>
  import BasicModal from '@/components/BasicModal'
  import { basicModalMixin } from '@/tools/basicModalMixin.js'
  
  
  export default {
    name: 'FeedEditView',
    components: {
      BasicModal,
    },
    mixins: [
      basicModalMixin,
    ],
    data() {
      return {
        content: null,
        images: null,
        isprivate: false,
      }
    },
    methods: {
      // 취소버튼: 뒤로가기
      goBack() {
        this.$router.go(-1)
      },
      // 피드 등록
      editFeed() {
        const formData = new FormData()
        const writeFeedDto = {
          userId: this.$store.state.id,
          userPlaylistId: this.$store.state.nowFeed.userPlaylistId,
          missionId: this.$store.state.nowFeed.missionId,
          content: this.content,
        }
  
        const writeFeedDtoJson = new Blob([JSON.stringify(writeFeedDto)], { type: "application/json" })
        
        formData.append('writeFeedDto', writeFeedDtoJson)
        
        // 이미지
        if (this.images) {
          for (const img of this.images) {
            formData.append('images', new Blob([JSON.stringify(img)], { type: "application/json" }))
          }
        } else {
          const dump = {}
          formData.append('images', new Blob([JSON.stringify(dump)], { type: "application/json" }))
        }
        
        this.$axios({
          method: 'put',
          url: `${this.$baseUrl}/feed/${this.$store.state.nowFeed.feedId}`,
          headers: {
            'Content-Type': 'multipart/form-data',
          },
          data: formData,
        })
          .then(() => {
            this.$router.push({ name: 'feed' })
          })
          .catch((error) => {
            console.log(error)
          })
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
            // 화살표 함수로 안 쓰면 에러남
            divTag.addEventListener('click', (event) => {
              this.deleteImage(event.target)
            })
            const spanTag = document.createElement('span')
            spanTag.innerText = 'x'
            spanTag.setAttribute('class', 'preview-img-span')
            spanTag.setAttribute('id', `preview-img-span-${idx}`)
            // 태그 만들기
            const imgTag = document.createElement('img')
            imgTag.src = img
            imgTag.setAttribute('class', 'preview-img')
            
            divTag.append(imgTag)
            divTag.append(spanTag)
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
      deleteImage(target) {
        // data에서 제거
        const targetIdx = target.id.substring(17, )
        const dataTransfer = new DataTransfer()
        let fileArray = Array.from(this.images)	//변수에 할당된 파일을 배열로 변환(FileList -> Array)
        fileArray.splice(targetIdx, 1)	//해당하는 index의 파일을 배열에서 제거
        fileArray.forEach(file => { dataTransfer.items.add(file) })
        this.images = dataTransfer.files	//제거 처리된 FileList를 돌려줌
        this.inputImage()
      }
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
        for(const img of this.$store.state.nowFeed.feedImages){
            imageUrls.push(img.feedImageUrl)
        }
        this.images = imageUrls
        this.inputImage()
        this.content = this.$store.state.nowFeed.content
        document.querySelector("#feed-create-input").value = this.$store.state.nowFeed.content
    }
  }
  </script>
  
  <style lang="scss">
  $feed-create-footer-height: 5%;
  
  // 기본 레이아웃
  #feed-create-layout {
    height: 100vh;
    display: flex;
    flex-direction: column;
  
    padding: 0px 10px;
  }
  
  // header 레이아웃
  #feed-create-header {
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
  
  // 미션명
  #feed-create-section {
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
  
    // 플레이리스트
    p:nth-child(2) {
      font-size: $fs-6;
    }
  
    // 미션명
    p:nth-child(3) {
      text-align: start;
      font-weight: bold;
    }
  }
  
  // 피드 작성 부분
  #feed-create-article {
    height: (100% - $feed-create-footer-height);
  }
  
  // 피드 내용 작성
  #feed-create-input {
    width: 100%;
    height: 100%;
    border: none;
    resize: none;
  
    font-size: $fs-6;
    color: $dark-grey;
  
    &::placeholder {
      color: $light-grey;
    }
  
    &:focus {
      outline: none;
    }
  }
  
  // 이미지 프리뷰 레이아웃
  #preview-img-layout {
    height: 15vh;
    display: flex;
    overflow-x: scroll;
    gap: 10px;
  
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
  #feed-create-footer {
    height: $feed-create-footer-height;
    display: flex;
    justify-content: space-between;
    align-items: center;
    
    margin-bottom: 10px;
  }
  
  #feed-create-footer-private {
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