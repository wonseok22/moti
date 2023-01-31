<template>
  <div id="my-pl-layout">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'MyPLView',
}
</script>

<style lang="scss">
#my-pl-layout {
  width: $base-width;
  height: $base-height;
  padding: 0px 10px;

  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>