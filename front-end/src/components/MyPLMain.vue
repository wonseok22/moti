<template>
  <div id="my-pl-main-layout">
    <!-- 상단 유저명 -->
    <header>
      <p id="my-pl-main-name">
        <span>{{ nickname }}</span>
        의 성장 플랜
      </p>
    </header>
    
    <main id="my-pl-main-main">
      <!-- 플레이리스트 -->
      <my-pl-form
        v-for="(myPL, idx) in myPLArray"
        :key="idx"
        :myPL="myPL"
      >
      </my-pl-form>
      <!-- 플레이리스트가 없을 경우 -->
      <div v-if="noList" id="no-list">
        <p>현재 진행 중인 성장 플랜이 없습니다.</p>
        <p>바라는 성장 플랜을 추가해주세요!</p>
      </div>
      <!-- 빈 플레이리스트 박스 -->
      <div @click="toPLSelect" id="empty-pl">
        <i class="material-symbols-outlined">add</i>
        <p>어떤 꽃이 있는지 확인하러 가보기</p>
      </div>
    </main>
    <NavigationBar/>
  </div>
</template>

<script>
import MyPlForm from '@/components/MyPLForm'
import NavigationBar from '@/components/NavigationBar'

export default {
  name: 'MyPLMain',
  components: {
    MyPlForm,
    NavigationBar,
  },
  methods: {
    // 플레이리스트 선택 창으로 이동
    toPLSelect() {
      this.$router.push({ name: 'playlist' })
    },
  },
  computed: {
    // 진행 중인 플레이리스트 있는지 여부
    noList() {
      // 없으면 true, 있으면 false
      if (this.$store.state.myPL.length) {
        return false
      } else {
        return true
      }
    },
    // 나의 플레이리스트
    myPLArray() {
      return this.$store.state.myPL
    },
    // 유저 닉네임
    nickname() {
      return this.$store.state.nickname
    }
  },
  created() {
    // 내 플레이리스트 정보 state에 저장하기
    this.$store.dispatch('getMyPL')
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
  
  overflow: scroll;

  &::-webkit-scrollbar {
    display: none;
  }
}

// 상단 사용자 이름 노출되는 섹션
#my-pl-main-name {
  position: relative;
  width: $base-width;
  height: $header-height;
  padding-left: 10px;
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
  gap: 30px;
  margin-top: 30px;
  // margin-top: $header-height;
  // margin-bottom: $navbar-height;

  display: flex;
  flex-direction: column;
  justify-content: center;
}

// 진행 중인 플레이리스트가 없을 경우
#no-list {
  margin-top: 50%;
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

  margin-bottom: $navbar-height + 30px;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 20px;

  font-weight: bold;
}
</style>