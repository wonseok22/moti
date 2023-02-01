<template>
  <div id="my-pl-main-layout">
    <!-- 상단 유저명 -->
    <header>
      <p id="my-pl-main-name">
        <span>사용자</span>
        의 꽃 목록
      </p>
    </header>
    
    <main id="my-pl-main-main">
      <!-- 플레이리스트 -->
      <MyPLForm/>
      <!-- 플레이리스트가 없을 경우 -->
      <div v-if="noList" id="no-list">
        <p>지금 성장 중인 식물이 없습니다.</p>
        <p>키우고 싶은 식물을 추가해주세요!</p>
      </div>
      <!-- 빈 플레이리스트 박스 -->
      <div @click="toPLSelect" id="empty-pl">
        <i class="material-symbols-outlined">add</i>
        <p>어떤 식물이 있는지 확인하러 가보기</p>
      </div>
    </main>
  </div>
</template>

<script>
import MyPLForm from '@/components/MyPLForm'

export default {
  name: 'MyPLMain',
  components: {
    MyPLForm,
  },
  methods: {
    // 플레이리스트 선택 창으로 이동
    toPLSelect() {
      // this.$router.push({ path: '' })
    },
    // 내 플레이리스트 정보 state에 저장하기
    getMyPL() {
      this.$store.dispatch('getMyPL')
    }
  },
  computed: {
    // 진행 중인 플레이리스트 있는지 여부
    noList() {
      // 없으면 true, 있으면 false
      return false
    },
  },
  created() {
    this.getMyPL()
  }
}

</script>
<style lang="scss">
$header-height: 46px;

// 기본 레이아웃
#my-pl-main-layout {
  width: 100%;
  height: inherit;
  display: flex;
  flex-direction: column;
  align-items: center;
}

// 상단 사용자 이름 노출되는 섹션
#my-pl-main-name {
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
  
  // 사용자 이름
  span {
    color: $main-green;
  }
}

// 본문 레이아웃
#my-pl-main-main {
  height: 100%;
  gap: 30px;

  display: flex;
  flex-direction: column;
  justify-content: center;
}

// 진행 중인 플레이리스트가 없을 경우
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

// + 아이콘
.material-symbols-outlined {
  font-variation-settings:
  'FILL' 0,
  'wght' 600,
  'GRAD' 0,
  'opsz' 48
}

// 플레이리스트 추가
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