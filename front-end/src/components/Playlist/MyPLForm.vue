<template>
  <div @click="toMission" id="my-pl-box">
    <div id="my-pl-title">
      <img :src="myPL.playlist.flower.flowerImageUrl" alt="pl-flower-img" />
      <div>{{ myPL.playlist.playlistName }}</div>
    </div>
    <p class="pl-current">현재 <span>{{ myPL.playlist.current }}명</span>이 함께 이 꽃을 키우는 중입니다.</p>
    <div id="pl-progress-layout">
      <!-- 완료 -->
      <div v-for="n in complete"
        :key="`complete-${n}`"
        class="pl-complete-box"
        >
      </div>
      <!-- 미완료 -->
      <div v-for="n in incomplete"
        :key="`incomplete-${n}`"
        class="pl-incomplete-box"
        >
      </div>
    </div>
    <p class="pl-remain">남은 시간 <span>{{ remainDays }}</span></p>
  </div>
</template>

<script>
export default {
  name: 'MyPLForm',
  props: {
    myPL: Object,
  },
  methods: {
    // 선택한 플레이리스트 상세 페이지로 이동
    toMission() {
      this.$router.push({ path: '/my-pl/mission', query: { plId: `${this.myPL.playlist.playlistId}` } })
    }
  },
  computed: {
    complete() {
      return this.myPL.done
    },
    incomplete() {

      return 5 - this.complete
    },
    // 플레이리스트
    // pl() {
    //   return this.myPL
    // },
    // 남은 일수 계산
    remainDays() {
      // 오늘 날짜
      const now = new Date()
      // 끝나는 날짜
      let end = new Date(this.myPL.endDate)

      end.setHours(end.getHours() + 9)
      

      // ms 단위 남은 기간
      const remain_ms = end.getTime() - now.getTime()
      // day 단위 남은 기간
      const remain_day = remain_ms / (1000 * 60 * 60 * 24)

      // 1일 이상 남은 경우
      if (remain_day > 1) {
        return String(remain_day + 1).substring(0, 1) + '일'
      } else {
        // 1일 미만으로 남은 경우 시간 단위
        const remain_hour = String(remain_day * 24)
        if (remain_hour.indexOf('.') == 2) {
          return String(remain_hour).substring(0, 2) + '시간'
        } else {
          return String(remain_hour).substring(0, 1) + '시간'
        }
      }
    }
  },
  created() {
  }
}
</script>

<style lang="scss">
#my-pl-box {

  width: 304px;
  height: 213px;

  background: $light-yellow;
  box-shadow: 1px 3px 3px rgba(0, 0, 0, 0.17);
  border-radius: 43px;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  // // PL 이름
  // p:nth-child(1) {
  //   font-weight: bold;
  //   font-size: $fs-4;
  // }

  // // 현재 참여 중인 인원
  // p:nth-child(2) {
  //   span {
  //     font-weight: bold;
  //   }
  // }

  // // 남은 시간
  // p:nth-child(3) {
  //   font-size: $fs-6;
  //   span {
  //     font-weight: bold;
  //   }
  // }
}

#pl-progress-layout {
  display: flex;
}

.pl-complete-box {
  width: 52px;
  height: 48px;

  background: $main-green;
  border-radius: 5px 0px 0px 5px;
  border: 1px solid $light-grey;
}

.pl-incomplete-box {
  width: 52px;
  height: 48px;

  border-radius: 5px 0px 0px 5px;
  border: 1px solid $light-grey;
}


#my-pl-title {
  display: flex;
  align-items: center;
  gap: 10px;
  height: 30%;
  margin-top: 10px;

  img {
    height: 80%;
  }
  div {
    font-weight: bold;
    font-size: $fs-4;
  }
  display: flex;
}

.pl-current {
  font-size: $fs-6;
  span {
    font-weight: bold;
  }
}

.pl-remain {
  font-size: $fs-6;
}
</style>