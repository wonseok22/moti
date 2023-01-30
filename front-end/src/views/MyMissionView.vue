<template>
  <div id="my-mission-layout">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'MyMissionView',

}
</script>

<style lang="scss">
#my-mission-layout {
  height: $base-height;
  margin: 0px 10px;

  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>