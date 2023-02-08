<template>
  <div id="notfound-layout">
    <p id="notfound-title">404 Not Found</p>
    <p id="notfound-subtitle">페이지를 찾을 수 없어요 :(</p>
    <div id="notfound-content" class="text-active">
      <p @click="toFeed">moti 메인 페이지로 가기</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'notfoundleteView',
  methods: {
    // 피드 페이지로 이동
    toFeed() {
      this.$router.push({ name: 'feed' })
    }
  },
}
</script>

<style lang="scss">
#notfound-layout {
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#notfound-title {
  font-size: $fs-1;
  color: $main-green;
  font-weight: bold;

  margin-top: 0px;
  margin-bottom: 5px;
}

#notfound-subtitle {
  font-size: $fs-4;
  font-weight: bold;
  margin-top: 0px;

  margin-top: 0px;
}

#notfound-content {
  text-decoration: underline;
}
</style>