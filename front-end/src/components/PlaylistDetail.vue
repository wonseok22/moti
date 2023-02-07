<template>
  <div v-if="plDetail"
    id="my-pl-mission-layout"
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
          <p class="pl-info-title">{{plDetail.playlistName}}</p>
          <!-- <span>{{ plDetail.playlistDesc }}</span> -->
          <p class="pl-info-content"> 현재 <span class="strong">{{ plDetail.current }}명</span>이 키우는 중입니다.</p>
          </div>
      </div>
      <p>일주일 중 5일 미션을 완수하면 [플리] 완수에요. 당일 미션은 자정까지만 수행 가능해요 </p>
    </div>

    
    <div id="mission-info-layout">
      <!-- 미션 리스트 -->
      <article id="mission-list-layout">
        <div v-for="(mission, idx) in plDetail.missions"
          :key="idx"
          class="mission-list-detail"
        >
        {{ mission.missionName }}
        </div>
        <!-- 미션 후기 작성 -->
      </article>
    </div>

    <button v-if="isValid" class="btn-green" @click="addPlaylist">꽃 키우러 가기</button>
    <button v-else class="btn-green-inactive">이미 키우고 있는 꽃이에요.</button>

    <!-- nav 바 -->
    <NavigationBar/>
  </div>
</template>
<script>
import NavigationBar from '@/components/NavigationBar'

export default {
  name: 'playlistDetail',
  components: {
    NavigationBar,
  },
  data() {
    return{
      // 선택된 플레이리스트
      pl: null,
      plDetail: null,
      isValid: false,
    }
  },
  computed: {
  },
  methods: {
    getPlaylist() {
      console.log('플레이리스트를 가져옵니다.')
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/playlist/detail/${this.pl}`
      }).then((response) => {
          console.log(`플레이리스트 가져오기 성공: status ${response.status}`)
          this.plDetail = response.data.playlist
          console.log(this.plDetail)
      }).catch((error) =>{
        console.log(`플레이리스트 가져오기 실패: status ${error.response.status}`)
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
              console.log(`플레이리스트 추가 완료: status ${response.data.status}`)
              this.moveMyPlaylist();
          } else {
            console.log(response.data.message)
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
  #my-pl-mission-layout {
    width: $base-width;
    height: $base-height;

    display: flex;
    flex-direction: column;
  }

  // 플레이리스트 정보 레이아웃
  #pl-info-layout {
      // width: 100vw;
      // height: 30%;
      // padding-top: 30px;
    width: 100%;
    height: 25%;
    padding-top: 50px;
    margin-bottom: 30px;
    
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }

  // 플레이리스트 정보
  #pl-info {
  display: flex;
  width: 100%;
  gap: 15px;
  // height: 100px;
  // height: 96px;
  }

  // 플레이리스트 이미지
  #pl-info-img {
  // width: 50% !important;
  // height: 100%;
  // align-items: center;

  // img {
  //     width: 70% !important;
  //     height: 100% !important;
  //     object-fit: cover;
  // }

   width: 25%;

    display: flex;
    align-items: center;

    img {
      width: 100%;
    }
  }

  // 플레이리스트 상세 정보
  #pl-info-text {
    width: 75%;
    height: 100%;
    text-align: start;

    display: flex;
    flex-direction: column;
    justify-content: center;

    .pl-info-title {
        font-size: $fs-4;
        font-weight: bold;
        margin: 0px;
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
  // 미션 수행 가이드
  #guide {
  font-size: $fs-6;
  color: $light-grey;
  }

  // 미션 정보 레이아웃
  #mission-info-layout {
  height: 60%;
  }

  // 미션 진행상황 레이아웃
  #mission-progress-layout {
  display: flex;
  justify-content: space-evenly;
  }

  // 미션 진행상황 박스
  .mission-progress-box {
  width: 51px;
  height: 51px;
  
  display: flex;
  align-items: center;
  justify-content: center;

  border: 1px solid;

  img {
      height: 100%;
  }
  }

  // 미션 리스트 레이아웃
  #mission-list-layout {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  gap: 20px;
  }

  // 미션 디테일
  .mission-list-detail {
      box-shadow: 1px 3px 2px rgba(0, 0, 0, 0.08);
      border-radius: 8.6px;
      background-color: $light-yellow;
      width: 100%;
      height: 36px;
      // font-size: 18px;
      line-height: 2;
  }

  // 선택된 미션
  .mission-selected {
  background-color: $dark-green;
  color: white;
  }


</style>