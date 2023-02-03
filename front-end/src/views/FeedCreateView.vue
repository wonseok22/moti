<template>
  <div id="feed-create-layout">
    <header id="feed-create-header">
      <!-- 취소 -->
      <span @click="goBack" class="text-active">취소</span>
      <span @click="create" class="text-active">등록</span>
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
      <textarea @input="inputContent" name="feed-create-input" id="feed-create-input"></textarea>
    </article>

    <div>
      <hr>
    </div>
    
    <footer id="feed-create-footer">
      <!-- 사진 등록 -->
      <label for="image-input"><i class="material-symbols-outlined text-active" id="photo-camera">photo_camera</i></label>
      <input 
        @change="inputImage"  
        type="file" id="image-input" style="visibility:hidden;"
      >
      <!-- 피드 비공개 -->
      <div id="feed-create-footer-private">
        <input 
          @click="isprivateCheck"
          type="checkbox" id="feed-create-footer-checkbox" name="feed-create-footer-checkbox"
        >
        <span><label for="feed-create-footer-checkbox" class="text-active-normal">피드 비공개</label></span>
      </div>
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
    create() {
      const writeFeedDto = {
        userId: this.$store.state.id,
        userPlaylistId: this.missionInfo.userPlaylistId,
        missionId: this.missionInfo.missionId,
        content: this.content,
      }
      console.log(writeFeedDto)
      const writeFileDto = new FormData()
      writeFileDto.append('images', this.images)
      
      this.$axios({
        method: 'post',
        url: `${this.$baseUrl}/feed`,
        data: {
          writeFeedDto, writeFileDto
        }
      })
        .then((response) => {
          console.log(response)
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
      this.images = event.target.files
    },
    // 비공개 여부
    isprivateCheck() {
      this.isprivate = !this.isprivate
      console.log(this.isprivate)
    }
  },
  computed: {
    missionInfo() {
      return this.$route.params
    }
  }
}
</script>

<style lang="scss">
$feed-create-footer-height: 5%;

// 기본 레이아웃
#feed-create-layout {
  height: inherit;
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
    font-size: $fs-7
  }

  // 플레이리스트
  p:nth-child(2) {
    font-size: $fs-6
  }

  // 미션명
  p:nth-child(3) {
    font-size: $fs-4
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