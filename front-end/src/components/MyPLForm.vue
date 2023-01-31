<template>
  <div @click="toPL" id="my-pl-box">
    <p>칭찬일기 작성</p>
    <p>현재 <span>5명</span>이 함께 이 꽃을 키우는 중입니다.</p>
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
    <p>남은 시간 <span>5일</span></p>
  </div>
</template>

<script>
export default {
  name: 'MyPLForm',
  props: [

  ],
  methods: {
    // 선택한 플레이리스트 상세 페이지로 이동
    toPL() {

    }
  },
  computed: {
    complete() {
      return 4
    },
    incomplete() {
      return 5 - this.complete
    }
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
</style>