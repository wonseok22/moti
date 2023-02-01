<template>
    <div id="my-pl-mission-layout">
      <div id="pl-info-layout">
        <!-- PL 설명 -->
        <div id="pl-info">
          <!-- 꽃 사진 -->
          <div id="pl-info-img">
            <img src="https://cdn-icons-png.flaticon.com/512/4139/4139434.png" alt="pl-info-img" >
          </div>
          <!-- PL 상세 -->
          <div id="pl-info-text">
            <p class="pl-info-title">{{plDetail.playlistName}}</p>
            <span>{{ plDetail.playlistDesc }}</span>
            </div>
        </div>
        <p>현재 <span class="strong">{{ plDetail.current }}명</span>이 키우는 중입니다.</p>
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
          <button v-if="isValid" class="btn-green" @click="addPlaylist">꽃 키우러 가기</button>
          <button v-else class="btn-green-inactive">이미 키우고 있는 꽃입니다.</button>
        </article>
      </div>
  
      <!-- nav 바 -->
      <NavigationBar/>
    </div>
  </template>
  
  
  
  <script>

  export default {
    name: 'playlistDetail',
    data() {
        return{
            // 선택된 플레이리스트
            pl:null,
            plDetail:null,
            inValid:false,
        }
    },
    computed: {
    },
    created() {
      this.pl = this.$route.query.pl;
      this.getPlaylist()
      this.isValid()
    },

    methods : {
    getPlaylist() {
        this.$axios({
          method: 'get',
          url: `${this.$baseUrl}/playlist/detail/${this.pl}`
        }).then((response) => {
            this.plDetail = response.data.playlist;
            console.log(this.plDetail)
        }).catch((error) =>{
          console.log(error)
        })
      },



      moveMyPlaylist(){
        this.$router.push({
          name: 'my-pl'
        });
      },

      addPlaylist(){
        this.$axios({
          method: 'post',
        //   url: `${this.$baseUrl}/playlist/${this.$store.state.id}/${this.pl}`
          url: `${this.$baseUrl}/playlist/red/${this.pl}`
        }).then((response) => {
            if ( response.data.message === 'already exists' ) {
              alert("앗! 이미 심어진 꽃이에요.")
              window.location.reload()
            } else if ( response.data.message === 'success' ) {
                this.moveMyPlaylist();
            } else {
              console.log(response.data.message)
              alert('알 수 없는 에러가 발생했습니다. 고객센터에 문의해주세요.')
            }
        }).catch((error) =>{
          console.log(error)
        })
      },



      isValid(){
        this.$axios({
          method: 'get',
        //   url: `${this.$baseUrl}/playlist/${this.$store.state.id}/${this.pl}`
          url: `${this.$baseUrl}/playlist/check/red/${this.pl}`
        }).then((response) => {
            if ( response.data.message === 'already exists' ) {
              return true
              // 입력된 이메일 삭제
            } else if ( response.data.message === 'success' ) {
                return false
            } else {
              console.log(response.data.message)
              alert('알 수 없는 에러가 발생했습니다. 고객센터에 문의해주세요.')
            }
        }).catch((error) =>{
          console.log(error)
        })
      }

    }
  }
  </script>
  <style lang="scss">
    // 기본 레이아웃
    #my-pl-mission-layout {
        width: $base-width;
        height: $base-height;

        display: flex;
    }

    // 플레이리스트 정보 레이아웃
    #pl-info-layout {
        width: 100vw;
        height: 30%;
        padding-top: 30px;
    }

    // 플레이리스트 정보
    #pl-info {
    display: flex;
    width: 100%;
    height: 100px;
    // height: 96px;
    }

    // 플레이리스트 이미지
    #pl-info-img {
    width: 50% !important;
    height: 100%;
    align-items: center;

    img {
        width: 70% !important;
        height: 100% !important;
        object-fit: cover;
    }
    }

    // 플레이리스트 상세 정보
    #pl-info-text {
        width: 50%;
        height: 100%;
        text-align: start;

        .pl-info-title {
            font-size: $fs-4;
            font-weight: bold;
            margin: 0px;
            
        }


        span {
            // font-size: $fs-6;
            color: $dark-grey;
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
        font-size: 18px;
        line-height: 2;
    }

    // 선택된 미션
    .mission-selected {
    background-color: $dark-green;
    color: white;
    }


  </style>