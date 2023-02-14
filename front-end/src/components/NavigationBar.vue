<template>
  <div class="navbar">
    <router-link to="/feed" 
      class="material-symbols-outlined">
      home
    </router-link>

    <div v-if="plActive"
      @click="goPl"
      class="material-symbols-outlined nav-active">
      history_edu
    </div>

    <div v-else
      @click="goPl"
      class="material-symbols-outlined">
      history_edu
    </div>

    <router-link to="/search" 
      class="material-symbols-outlined">
      search
    </router-link>

    <div v-if="!isNotProfile()"  @click="change()"
      class="material-symbols-outlined">
      account_circle
    </div>

    <div 
      v-else
      @click="change()"
      class="material-symbols-outlined nav-active"
    >
      account_circle
    </div>

  </div>    
</template>

<script>
export default {
  name:'NavigationBar',
  props: {
    plActive: Boolean,
  },
  methods:{
    change() {
     this.$store.commit("UPDATE_PROFILE_TARGET_ID",this.$store.state.id);
      this.$router.push({
        name: 'profile',
      }).catch(() => {location.reload();});
    },
    isNotProfile() {
      return this.$router.history.current["path"] === "/profile"
    },
    goPl() {
      this.$router.push({ name: 'my-pl' })
        .catch(() => { 
          location.reload()
        })
    },
  },
  created() {
    console.log(this.plActive)
  }
}
</script>

<style scoped lang="scss">
</style>
