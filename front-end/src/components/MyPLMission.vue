<template>
  <div id="my-pl-mission-layout">
    <div id="my-pl-info-layout">
      <!-- PL 설명 -->
      <div id="my-pl-info">
        <!-- 꽃 사진 -->
        <div id="my-pl-info-img">
          <img src="https://cdn-icons-png.flaticon.com/512/4139/4139434.png" alt="my-pl-info-img">
        </div>
        <!-- PL 상세 -->
        <div id="my-pl-info-text">
          <p>{{ missions.playlist.playlistName }}</p>
          <span>{{ missions.playlist.playlistDesc }}</span>
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
          <img :src="img" alt="my-pl-info-img">
        </div>
      </div>
    </div>

    <div id="mission-info-layout">
      <!-- 미션 리스트 -->
      <article id="mission-list-layout">
        <div v-for="(mission, idx) in missions.playlist.missions"
          :key="idx"
          class="mission-list-detail"
          :id="`mission-${idx+1}`"
          @click="select(idx)"
        >
        {{ mission.missionName }}
        </div>
      </article>
    </div>
    <!-- 미션 후기 작성 -->
    <button v-if="isvalid" class="btn-green" @click="toCreateFeed">미션 후기 작성</button>
    <button v-else class="btn-green-inactive">미션 후기 작성</button>

    <!-- nav 바 -->
    <NavigationBar/>
  </div>
</template>

<script>
import NavigationBar from '@/components/NavigationBar'

// 진행상황 표시하는 이미지
const progressImg = {
    lv1: ['https://cdn-icons-png.flaticon.com/512/2771/2771094.png', 'https://cdn-icons-png.flaticon.com/512/2771/2771111.png'],
    lv2: ['https://cdn-icons-png.flaticon.com/512/1269/1269251.png', 'https://cdn-icons-png.flaticon.com/512/1269/1269325.png'],
    lv3: ['https://cdn-icons-png.flaticon.com/512/628/628379.png', 'https://cdn-icons-png.flaticon.com/512/628/628297.png'],
    lv4: ['https://cdn-icons-png.flaticon.com/512/119/119311.png', 'https://cdn-icons-png.flaticon.com/512/186/186135.png'],
    lv5: ['https://cdn-icons-png.flaticon.com/512/4139/4139436.png', 'https://cdn-icons-png.flaticon.com/512/4139/4139434.png']
  }

export default {
  name: 'MyPLMission',
  components: {
    NavigationBar,
  },
  data() {
    return {
      progress: {
        lv1: false,
        lv2: false,
        lv3: false,
        lv4: false,
        lv5: false,
      },

      // 선택된 미션
      selected: null,
    }
  },
  methods: {
    // 미션 진행 상황 업데이트
    progressCheck() {
      // data의 progress 업데이트
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
      const missionIdx = this.selected - 1
      const params = {
        playlistName: this.missions.playlist.playlistName,
        userPlaylistId: this.missions.userPlaylistId,
        missionName: this.missions.playlist.missions[missionIdx].missionName,
        missionId: this.missions.playlist.missions[missionIdx].missionId
      }
      this.$router.push({ name: 'feedcreate', params: params })
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
    // 플레이리스트 디테일 설명
    plInfoDetail() {
      const detail = '분홍색 튤립의 꽃말은 애정이에요. 하루하루 자신에 대한 칭찬 일기를 작성하여 스스로에 대한 애정을 키워봅시다!'
      return detail
    },
    // 미션 진행도에 따른 아이콘 매칭 결과 return
    progressFinal() {
      const finalImg = []
      for (let [key, value] of Object.entries(this.progress)) {
        if (!value) {
          console.log(progressImg[key][0])
          finalImg.push(progressImg[key][0])
        } else {
          console.log(progressImg[key][0])
          finalImg.push(progressImg[key][1])
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
    // 미션 진행도 업데이트
    this.progressCheck()
  },
}
</script>

<style lang="scss">
// 기본 레이아웃
#my-pl-mission-layout {
  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: column;
  align-items: center;
}

// 플레이리스트 정보 레이아웃
#my-pl-info-layout {
  width: 100%;
  height: 25%;
  padding-top: 30px;
  margin-bottom: 30px;
  
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

// 플레이리스트 정보
#my-pl-info {
  display: flex;
  width: 100%;
  // height: 96px;
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

  border: none;

  img {
    height: 100%;
  }
}

// 미션 리스트 레이아웃
#mission-list-layout {
  height: 80%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}

// 미션 디테일
.mission-list-detail {
  box-shadow: 1px 3px 2px rgba(0, 0, 0, 0.08);
  border-radius: 8.6px;
  background-color: $light-yellow;
  width: 100%;
  height: auto;
}

// 선택된 미션
.mission-selected {
  background-color: $dark-green;
  color: white;
}
</style>