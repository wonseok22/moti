<template>
  <div @click="toMission" id="my-pl-box">
    <div id="my-pl-title">
      <img :src="pl.playlist.flower.flowerImageUrl" alt="pl-flower-img" />
      <div>{{ pl.playlist.playlistName }}</div>
    </div>
    <p>현재 <span>{{ pl.playlist.current }}명</span>이 함께 이 꽃을 키우는 중입니다.</p>
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
    <p>남은 시간 <span>7일</span></p>
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
      console.log(this.myPL.playlist.playlistId)
      this.$router.push({ path: '/my-pl/mission', query: { plId: `${this.myPL.playlist.playlistId}` } })
    }
  },
  computed: {
    complete() {
      return 0
    },
    incomplete() {
      return 5 - this.complete
    },
    // 플레이리스트
    pl() {
      console.log()
      return this.myPL
    },
    // 남은 일수 계산
    // remainDays() {
    //   const now = new Date()
    //   const end = new Date(this.myPL.endDate)

    //   const remain_ms = end.getTime() - now.getTime()
    //   const remain_day = remain_ms / (1000*60*60*24)

    //   console.log(remain_day)
    //   return null
    // }
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

  // PL 이름
  p:nth-child(1) {
    font-weight: bold;
    font-size: $fs-4;
  }

  // 현재 참여 중인 인원
  p:nth-child(2) {
    span {
      font-weight: bold;
    }
  }

  // 남은 시간
  p:nth-child(3) {
    font-size: $fs-6;
    span {
      font-weight: bold;
    }
  }

#pl-progress-layout {
  display: flex;
}

.pl-complete-box {
  width: 52px;
  height: 48px;

  background: $light-green;
  border-radius: 5px 0px 0px 5px;
  border: 1px solid $light-grey;
}

.pl-incomplete-box {
  width: 52px;
  height: 48px;

  border-radius: 5px 0px 0px 5px;
  border: 1px solid $light-grey;
}
}

#my-pl-title {
  display: flex;
  align-items: center;
  gap: 10px;
  height: $fs-1;

  img {
    height: 100%;
  }
  div {
    font-weight: bold;
    font-size: $fs-2;
  }
  display: flex;
}
</style>