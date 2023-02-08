<template>
  <div @click="close" id="flower-desc-bg">
    <p id="flower-desc-box">
      {{ flowerDesc }}
    </p>
  </div>
</template>

<script>
export default {
  name: 'FlowerDesc',
  props: {
    flowerDesc: String,
  },
  methods: {
    close() {
      this.$emit('close')
      // 새로고침이 필요할 경우
    }
  },
}
</script>

<style lang="scss">
#flower-desc-bg {
  position: absolute;
  left: 0;
  
  background: rgba(0, 0, 0, 0.6);

  width: 100vw;
  height: 100vh;
  opacity: 100%;

  display: flex;
  justify-content: center;
  align-items: center;
}

#flower-desc-box {
  position: absolute;
  top: 13%;
  width: 340px;
  border-radius: 5px;
	padding: 20px 5px;

  background: white;
  // color: white;
}
</style>