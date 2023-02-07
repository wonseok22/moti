<template>
  <div id="feed-create-layout">
    <header id="feed-create-header">
      <!-- 취소 -->
      <span @click="goBack" class="text-active">취소</span>
      <span @click="createFeed" class="text-active">등록</span>
      <!-- 등록 -->
    </header>
    <section id="feed-create-section">
      <!-- 카테고리 이름 -->
      <p>행복</p>
      <!-- 플레이리스트 이름 -->
      <p>{{ missionInfo.playlistName }}</p>
      <!-- 미션 이름 -->
      <p>{{ missionInfo.missionName }}</p>
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
  </div>
</template>

<script>



export default {
  name: 'FeedCreateView',
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
    createFeed() {
      const formData = new FormData()
      const writeFeedDto = {
        userId: this.$store.state.id,
        userPlaylistId: this.missionInfo.userPlaylistId,
        missionId: this.missionInfo.missionId,
        content: this.content,
      }

      const writeFeedDtoJson = new Blob([JSON.stringify(writeFeedDto)], { type: "application/json" })
      
      formData.append('writeFeedDto', writeFeedDtoJson)
      
      // 이미지
      if (this.images) {
        for (const img of this.images) {
          formData.append('images', img)
        }
      } else {
        const dump = {}
        formData.append('images', new Blob([JSON.stringify(dump)], { type: "application/json" }))
      }
      
      this.$axios({
        method: 'post',
        url: `${this.$baseUrl}/feed`,
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: formData,
      })
        .then((response) => {
          console.log(response)
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
    inputImage(event) {
      if (event) {
        this.images = event.target.files
      }
      
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
          const imgReader = new FileReader()
          imgReader.onload = () => {
            imgTag.src = imgReader.result
            imgTag.setAttribute('class', 'preview-img')
          }
          imgReader.readAsDataURL(img)
          
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
    //   console.log(this.isprivate)
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
  created() {
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

  &:focus {
    outline: none;
  }
}

// 이미지 프리뷰 레이아웃
#preview-img-layout {
  height: 15vh;
  display: flex;
  overflow-x: scroll;

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