<template>
    <div id="pl-layout">
      <router-view/>
    </div>
</template>

<script>
export default {
  name: 'playlistView',

}
</script>

<style lang="scss">
#pl-layout {
  height: $base-height;
  margin: 0px 10px;

  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>