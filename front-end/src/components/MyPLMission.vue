<template>
  <div id="my-pl-mission-layout">
    <div id="my-pl-info-layout">
      <!-- PL 설명 -->
      <div id="my-pl-info">
        <!-- 꽃 사진 -->
        <div id="my-pl-info-img">
          <img :src="missions.playlist.flower.flowerImageUrl" alt="my-pl-info-img">
        </div>
        <!-- PL 상세 -->
        <div id="my-pl-info-text">
          <p id="my-pl-info-title">{{ missions.playlist.playlistName }}</p>
          <span id="my-pl-info-content">{{ missions.playlist.playlistDesc }}</span>
        </div>
      </div>

      <!-- 진행도 -->
      <!-- 완료 표시 -->
      <div id="mission-progress-layout">
        <!-- 완료 -->
        <div v-for="(img, idx) in progressFinal"
          :key="idx"
          class="mission-progress-box"
          >
          <img 
            :src="img[0]"
            :class="{ 'uncompleted': !img[1] }"
            alt="my-pl-info-img">
        </div>
      </div>
    </div>

    <div id="mission-info-layout">
      <!-- 미션 리스트 -->
      <article id="mission-list-layout">
        <p v-for="(mission, idx) in missions.playlist.missions"
          :key="idx"
          class="mission-list-detail"
          :id="`mission-${idx+1}`"
          @click="select(idx)"
        >
        {{ mission.missionName }}
        </p>
      </article>
    </div>
    <!-- 미션 후기 작성 -->
    <button v-if="missions.today" class="btn-green-inactive">미션 인증 완료!</button>
    <button v-else :class="[ isvalid ? 'btn-green': 'btn-green-inactive' ]" @click="toCreateFeed">미션 후기 작성</button>

    <!-- nav 바 -->
    <NavigationBar :plActive="true"/>
  </div>
</template>

<script>
import NavigationBar from '@/components/NavigationBar'

// 진행상황 표시하는 이미지
export default {
  name: 'MyPLMission',
  components: {
    NavigationBar,
  },
  data() {
    return {
      progressImg: {
        1: require('./../assets/images/1_soil.png'),
        2: require('./../assets/images/2_watering_can.png'),
        3: require('./../assets/images/3_sprout.png'),
        4: require('./../assets/images/4_sun.png'),
        5: null,
      },
      progress: {
        1: false,
        2: false,
        3: false,
        4: false,
        5: false,
      },

      // 선택된 미션
      selected: null,

      imgSrc: null,
    }
  },
  methods: {
    // 미션 진행 상황 업데이트
    progressCheck() {
      // data의 progress 업데이트
      let i = 1
      for (i; i <= this.missions.done; i += 1) {
        this.progress[i] = true
      }
    },
    // 인증할 미션 선택
    select(idx) {
      // 선택한 태그
      const missionTagBefore = document.querySelector(`#mission-${this.selected}`)
      const targetIdx = idx + 1
      const missionTag = document.querySelector(`#mission-${targetIdx}`)

      if (this.selected) {
        missionTagBefore.classList.remove('mission-selected')

        if (this.selected !== targetIdx) {
          this.selected = targetIdx
          missionTag.classList.add('mission-selected')
        } else {
          this.selected = null
        }
      } else {
        this.selected = targetIdx
        missionTag.classList.add('mission-selected')
      }
    },
    // 피드 작성 페이지로 이동
    toCreateFeed() {
      if (this.selected) {
        const missionIdx = this.selected - 1
        const query = {
          playlistName: this.missions.playlist.playlistName,
          userPlaylistId: this.missions.userPlaylistId,
          missionName: this.missions.playlist.missions[missionIdx].missionName,
          missionId: this.missions.playlist.missions[missionIdx].missionId,
          categoryName: this.missions.playlist.categoryName,
        }
        this.$router.push({ name: 'feedcreate', query: query })
      }
    },
    getNowPL() {
      const params = {
        userPlaylistId: this.$route.query.plId
      }
      this.$store.dispatch('getNowPL', params)
    }
  },
  computed: {
    // 미션이 선택되었는지 여부
    isvalid() {
      if (this.selected) {
        return true
      } else {
        return false
      }
    },
    // 미션 진행도에 따른 아이콘 매칭 결과 return
    progressFinal() {
      const finalImg = []
      for (let [key, value] of Object.entries(this.progress)) {
        // 배열의 bool 원소를 통해 유/무채색 조정
        if (!value) {
          finalImg.push([this.progressImg[key], false])
        } else {
          finalImg.push([this.progressImg[key], true])
        }
      }
      return finalImg
    },
    // 미션 리스트
    missions() {
      return this.$store.state.nowPL
    },
    myMissionUpdated() {
      return this.$store.state.myMission
    },
  },
  created() {
    // 현재 선택 플레이리스트 state에 저장하기
    this.getNowPL()
    
  },
  mounted() {
    // 미션 진행도 업데이트
    this.progressImg[5] = this.missions.playlist.flower.flowerImageUrl
  },
  watch: {
    missions() {
      this.progressCheck()
    }
  }
}
</script>

<style lang="scss">
// 기본 레이아웃
#my-pl-mission-layout {
  width: 100%;
  height: $base-height;

  display: flex;
  flex-direction: column;
  align-items: center;
}

// 플레이리스트 정보 레이아웃
#my-pl-info-layout {
  width: 100%;
  height: 25%;
  padding-top: 30px;
  
  display: flex;
  flex-direction: column;
  // justify-content: space-between;
  align-items: center;
}

// 플레이리스트 정보
#my-pl-info {
  display: flex;
  width: 90%;
  gap: 20px;
  justify-content: center;
}

// 플레이리스트 이미지
#my-pl-info-img {
  width: 25%;

  display: flex;
  align-items: center;

  img {
    width: 100%;
  }
}

// 플레이리스트 상세 정보
#my-pl-info-text {
  height: 100%;
  text-align: start;
  max-width: 66%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: start;

  gap: 5px;

  #my-pl-info-title {
    font-size: $fs-4;
    font-weight: bold;
    margin: 0px;
  }
  #my-pl-info-content {
    font-size: $fs-6;
    color: $dark-grey;
    margin-bottom: 0px;
    word-break: keep-all;
    span {
      color: $text-base-color;
      font-weight: bold;
    }
  }
}

// 미션 수행 가이드
#guide {
  font-size: $fs-6;
  color: $light-grey;
}

// 미션 정보 레이아웃
#mission-info-layout {
  width: 90%;
  margin-top: 30px;
  height: 50%;
}

// 미션 진행상황 레이아웃
#mission-progress-layout {
  display: flex;
  justify-content: space-evenly;

  width: 100%;
  margin-top: 20px;
}

// 미션 진행상황 박스
.mission-progress-box {
  width: 51px;
  height: 51px;
  
  display: flex;
  align-items: center;
  justify-content: center;

  border: none;

  img {
    height: 100%;
  }
}

// 미션 리스트 레이아웃
#mission-list-layout {
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
.mission-list-detail {
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;

  box-shadow: 1px 3px 2px rgba(0, 0, 0, 0.08);
  border-radius: 8.6px;
  background-color: $light-yellow;
  width: 100%;

  min-height: 50px;

  word-break: keep-all;
}

// 선택된 미션
.mission-selected {
  background-color: $dark-green;
  color: white;
}

.uncompleted {
  filter: grayscale(100%);
}
</style>