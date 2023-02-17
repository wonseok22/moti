<template>
  <div class="account-form-comp">
    <div class="title-box">
      <span class="fade">m</span>
      <span class="fade">o</span>
      <span class="fade">t</span>
      <span class="fade">i</span>
    </div>
  </div>
</template>

<script>
export default {
	name: 'LandingView',
  data() {
    return {
      timer: 300,
    }
  },
  mounted() {
    const introText = document.querySelectorAll(".fade");
    introText.forEach((item) => {
      item.style.animation = `fade 500ms ${(this.timer += 90)}ms forwards`;
    });

    setTimeout(() => {
      this.$router.push({ name: 'login' })
      .catch(() => {});
    }, '2000')
  },
}
</script>

<style lang="scss">
</style>