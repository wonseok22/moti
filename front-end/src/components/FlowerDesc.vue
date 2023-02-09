<template>
  <div id="flower-desc-bg">
    <div id="flower-desc-box">
      <div id="flower-desc-img">
        <img :src="flowerImage" alt="flower-desc-img">
      </div>
      <p>
        {{ flowerDesc }}
      </p>
      <button @click="close" id="flower-desc-close-btn">
        X
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FlowerDesc',
  props: {
    flowerDesc: String,
    flowerImage: String,
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
}

#flower-desc-box {
  position: absolute;
  top: 13%;
  width: 85%;
  border-radius: 20px;
	padding: 20px 5px;

  background: white;

  word-break: keep-all;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  // color: white;
}

#flower-desc-img {
  width: 25%;

  display: flex;
  align-items: center;

  img {
    width: 100%;
  }
}

#flower-desc-close-btn {
  position: absolute;
  top: 10px;
  left: 85%;
  font-size: 20px;
  font-weight: bold;
  color: black;
  background-color: white;
  border-radius: 7px;
  padding: 8px 8px;
  border: 0 solid #000;
}
</style>