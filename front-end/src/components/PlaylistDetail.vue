<template>
    <div id="my-pl-mission-layout">
      <div id="pl-info-layout">
        <!-- PL 설명 -->
        <div id="pl-info">
          <!-- 꽃 사진 -->
          <div id="pl-info-img">
            <img src="https://cdn-icons-png.flaticon.com/512/4139/4139434.png" alt="pl-info-img">
          </div>
          <!-- PL 상세 -->
          <div id="pl-info-text">
            <p>칭찬일기 작성</p>
            <span>{{ plInfoDetail }}</span>
          </div>
        </div>

  
      <div id="mission-info-layout">
        <!-- 미션 리스트 -->
        <article id="mission-list-layout">
          <div v-for="(mission, idx) in missions"
            :key="idx"
            class="mission-list-detail"
            :id="`mission-${idx+1}`"
            @click="select(idx)"
          >
          {{ mission }}
          </div>
          <!-- 미션 후기 작성 -->
          <button v-if="isvalid" class="btn-green" @click="toCreateFeed">미션 후기 작성</button>
          <button v-else class="btn-green-inactive">미션 후기 작성</button>
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
            plList:null,
            categoryList: null,
            selectedName:'',
            selectedId: '',

        }
    },
    computed: {
    },
    created() {
      this.selectedName = this.$route.query.categoryName;
      this.selectedId = this.$route.query.categoryId;
      console.log(this.selectedName)
      console.log(this.selectedId)
      this.change()
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/category/`
      }).then((response) => {
        this.categoryList = response.data.categories;
        console.log("카테고리 성공")
      }).catch((error) =>{
        console.log(error)
      })
      console.log(this.plList) 
    },

    methods : {
      change() {
        console.log(this.selectedId + "선택된 아이디")
        console.log(this.selectedName + "선택된 카테고리")
        this.$axios({
          method: 'get',
          url: `${this.$baseUrl}/category/${this.selectedId}`
        }).then((response) => {
            this.plList = response.data.playlist
            console.log("플레이리스트 받아오기 성공")
            console.log("바뀐 pl" + this.plList)
            
        }).catch((error) =>{
          console.log(error)
        })
      },
      movePlaylist(selected){
        this.$router.push({
          name: 'playlistDetail',
          query: { pl: selected.categoryId
          },
        });
      }
    }
  }
  </script>
  <style lang="scss">
    // 기본 레이아웃
    #my-pl-mission-layout {
    width: 100%;
    height: 100%;

    display: flex;
    flex-direction: column;
    }

    // 플레이리스트 정보 레이아웃
    #pl-info-layout {
    height: 40%;
    padding-top: 30px;
    }

    // 플레이리스트 정보
    #pl-info {
    display: flex;
    width: 100%;
    // height: 96px;
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
    width: 75%;
    text-align: start;

    p {
        font-size: $fs-4;
        font-weight: bold;
        margin: 0px;
    }

    span {
        // font-size: $fs-6;
        color: $dark-grey;
    }
    }

    // 미션 수행 가이드
    #guide {
    font-size: $fs-6;
    color: $light-grey;
    }

    // 미션 정보 레이아웃
    #mission-info-layout {
    height: 50%;
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
    }

    // 선택된 미션
    .mission-selected {
    background-color: $dark-green;
    color: white;
    }


  </style>