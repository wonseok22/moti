<template>
  <div id="width-warning-layout">
    <p id="width-warning-title">화면이 너무 커요! :(</p>
    <p id="width-warning-subtitle">moti는 가로 <span>450px</span> 이하에서 작동해요!</p>
    <p id="width-warning-aside">(권장 가로 사이즈 360px)</p>
  </div>
</template>

<script>
export default {
  name: 'WarningWidthView',
}
</script>

<style lang="scss">
#width-warning-layout {
  position: absolute;
  top: 0px;

  width: 100vw;
  height: 100vh;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: $main-green;
  opacity: 0.9;
}

#width-warning-title {
  font-size: $fs-1;
  font-weight: bold;

  margin-top: 0px;
  color: white;
}

#width-warning-subtitle {
  font-size: $fs-4;
  font-weight: bold;
  margin: 0px;
  color: white;

  span {
    text-decoration: underline;
  }
}

#width-warning-aside {
  font-size: $fs-4;
  margin-top: 0px;
  color: white;
}
</style>