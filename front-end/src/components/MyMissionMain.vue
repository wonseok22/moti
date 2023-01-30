<template>
  <div id="my-mission-main-layout">
    <!-- 상단 유저명 -->
    <header>
      <p id="my-mission-main-name">
        <span>사용자</span>
        의 꽃 목록
      </p>
    </header>
    <main id="my-mission-main-main">
      <!-- 플레이리스트가 없을 경우 -->
      <div v-if="noList" id="no-list">
        <p>지금 성장 중인 식물이 없습니다.</p>
        <p>키우고 싶은 식물을 추가해주세요!</p>
      </div>
      <!-- 빈 플레이리스트 박스 -->
      <div id="empty-pl">
        <i class="material-symbols-outlined">add</i>
        <p>어떤 식물이 있는지 확인하러 가보기</p>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  name: 'MyMissionMain',
  computed: {
    // 진행 중인 플레이리스트 있는지 여부
    noList() {
      // 없으면 true, 있으면 false
      return true
    }
  }
}
</script>
<style lang="scss">
$header-height: 46px;

#my-mission-main-layout {
  height: $base-height;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#my-mission-main-name {
  position: relative;
  width: $base-width;
  height: $header-height;
  left: 5px;
  // top: 25px;
  
  margin: 0px;
  
  display: flex;
  justify-content: flex-start;
  align-items: center;

  border-bottom: 1px solid $light-grey;

  span {
    color: $main-green;
  }
}

#my-mission-main-main {
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
}

#no-list {
  p:nth-child(1) {
    color: $light-grey;
    font-size: $fs-6;
  }
  p:nth-child(2) {
    font-size: $fs-4;
    font-weight: bold;
  }
}

.material-symbols-outlined {
  font-variation-settings:
  'FILL' 0,
  'wght' 600,
  'GRAD' 0,
  'opsz' 48
}

#empty-pl {
  width: 304px;
  height: 156px;
  
  background: $light-yellow;
  box-shadow: 1px 3px 3px rgba(0, 0, 0, 0.17);
  border-radius: 43px;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 20px;

  font-weight: bold;
}
</style>