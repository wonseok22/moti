<template>
  <div class="navbar">
    <router-link to="/feed" 
      class="material-symbols-outlined">
      home
    </router-link>

    <router-link to="/my-pl" 
      class="material-symbols-outlined">
      history_edu
    </router-link>

    <router-link to="/search" 
      class="material-symbols-outlined">
      search
    </router-link>

    <div v-if="!isNotProfile()"  @click="change()"
      class="material-symbols-outlined">
      account_circle
    </div>

    <div v-if="isNotProfile()"
    @click="change()"
      class="material-symbols-outlined"
      style="color:#04C584;">
      account_circle
    </div>

  </div>    
</template>

<script>
export default {
  name:'NavigationBar',
  methods:{
    change() {
     this.$store.commit("UPDATE_PROFILE_TARGET_ID",this.$store.state.id);
      this.$router.push({
        name: 'profile',
      }).catch(() => {location.reload();});
    },
    isNotProfile() {
      return this.$router.history.current["path"] === "/profile"
    }
  }
}
</script>

<style scoped lang="scss">
  
  a {text-decoration: none;}
</style>
