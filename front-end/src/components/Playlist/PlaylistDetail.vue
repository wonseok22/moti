<template>
  <div v-if="plDetail"
    id="pl-mission-layout"
  >
    <div id="pl-info-layout">
      <!-- PL 설명 -->
      <div id="pl-info">
        <!-- 꽃 사진 -->
        <div id="pl-info-img">
          <img :src="plDetail.flower.flowerImageUrl" alt="pl-info-img">
        </div>
        <!-- PL 상세 -->
        <div id="pl-info-text">
          <p class="pl-info-title">{{ plDetail.playlistName }}
            <span @click="openModal" class="material-symbols-outlined">info</span>
          </p>
          <!-- <span>{{ plDetail.playlistDesc }}</span> -->
          <p class="pl-info-content"> 현재 <span class="strong">{{ plDetail.current }}명</span>이 키우는 중입니다.</p>
          </div>
      </div>
      <div id="pl-mission-guide">
        <p>7일 중 5일 미션을 수행하면 성장 플랜 성공!</p>
        <p>하루에 1번 미션 수행 가능해요.</p>
      </div>
    </div>

    <div id="pl-mission-info-layout">
      <!-- 미션 리스트 -->
      <article id="pl-mission-list-layout">
        <p v-for="(mission, idx) in plDetail.missions"
          :key="idx"
          class="pl-mission-list-detail"
        >
        {{ mission.missionName }}
        </p>
        <!-- 미션 후기 작성 -->
      </article>
    </div>

    <button v-if="isValid" class="btn-green" @click="addPlaylist">꽃 키우러 가기</button>
    <button v-else class="btn-green-inactive">이미 키우고 있는 꽃이에요.</button>

    <!-- 꽃말 모달 -->
    <flower-desc
      v-if="isModalOpened"
      @close="closeModal"
      :flowerDesc="plDetail.playlistDesc"
      :flowerImage="plDetail.flower.flowerImageUrl"
    >
    </flower-desc>
    
    <!-- nav 바 -->
    <NavigationBar :plActive="true"/>
  </div>
</template>
<script>
import NavigationBar from '@/components/Common/NavigationBar'
import FlowerDesc from '@/components/Playlist/FlowerDesc'

export default {
  name: 'playlistDetail',
  components: {
    NavigationBar,
    FlowerDesc,
  },
  data() {
    return{
      // 선택된 플레이리스트
      pl: null,
      plDetail: null,
      isValid: false,
      isModalOpened: false,
    }
  },
  computed: {
  },
  methods: {
    getPlaylist() {

      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/playlist/detail/${this.pl}`
      }).then((response) => {
          this.plDetail = response.data.playlist
      }).catch((error) =>{
        console.log(`status ${error.response.status}`)
      })
    },
    moveMyPlaylist(){
      this.$router.push({
        name: 'my-pl'
      });
    },
    // 플레이리스트 추가
    addPlaylist(){
      this.$axios({
        method: 'post',
        url: `${this.$baseUrl}/playlist/${this.$store.state.id}/${this.pl}`
        // url: `${this.$baseUrl}/playlist/red/${this.pl}`
      }).then((response) => {
          // if ( response.data.message === 'already exists' ) {
          //   alert("앗! 이미 심어진 꽃이에요.")
          //   window.location.reload()
          // } 
          if (response.data.message === 'success') {
              this.moveMyPlaylist();
          } else {
            alert('알 수 없는 에러가 발생했습니다. 고객센터에 문의해주세요.')
          }
      }).catch((error) =>{
        console.log(error)
      })
    },
    validCheck(){
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/playlist/check/${this.$store.state.id}/${this.pl}`
      })
        .then((response) => {
          if (response.data.message == 'success') {
            this.isValid = true
          }
      })
        .catch((error) =>{
          console.log(error)
      })
    },
    // 모달 열기
    openModal() {
      this.isModalOpened = true
    },
    // 모달 닫기
    closeModal() {
      this.isModalOpened = false
    }
  },
  created() {
    this.pl = this.$route.query.pl;
    this.getPlaylist()
    this.validCheck()
  },
}
</script>
<style lang="scss">
// 기본 레이아웃
#pl-mission-layout {
  width: 100%;
  height: $base-height;

  display: flex;
  flex-direction: column;
  align-items: center;
}

// 플레이리스트 정보 레이아웃
#pl-info-layout {
  width: 100%;
  height: 25%;
  padding-top: 30px;
  
  display: flex;
  flex-direction: column;
}

// 플레이리스트 정보
#pl-info {
display: flex;
width: 100%;
gap: 20px;
justify-content: center;
}

// 플레이리스트 이미지
#pl-info-img {
  width: 25%;

  display: flex;
  align-items: center;

  img {
    width: 100%;
  }
}

// 플레이리스트 상세 정보
#pl-info-text {
  height: 100%;
  text-align: start;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .pl-info-title {
    font-size: $fs-4;
    font-weight: bold;
    margin: 0px;
    
    display: flex;
    align-items: middle;
    gap: 5px;
  }
  .pl-info-content {
    font-size: $fs-6;
    color: $dark-grey;
    margin-bottom: 0px;
    span {
      color: $text-base-color;
      font-weight: bold;
    }
  }
}
.strong {
    font-weight: bold;
}

// 미션 정보 레이아웃
#pl-mission-info-layout {
  margin-top: 30px;
  width: 90%;
  height: 50%;
  
  // margin-bottom: 30px;
}


// 미션 리스트 레이아웃
#pl-mission-list-layout {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;

  p {
    padding: 0 10px;
  }
}

// 미션 디테일
.pl-mission-list-detail {
  margin: 0;
  display: flex;
  // flex-wrap: wrap;
  justify-content: center;
  align-items: center;

  box-shadow: 1px 3px 2px rgba(0, 0, 0, 0.08);
  border-radius: 8.6px;
  background-color: $light-yellow;
  width: 100%;

  min-height: 50px;

  word-break: keep-all;

}

#pl-mission-guide {
  margin-top: 20px;
  p {
    margin: 0;
  }
}


</style>