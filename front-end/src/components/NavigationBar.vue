<template>
  <div class="navbar">
    <router-link to="/feed" 
      class="material-icons-outlined">
      home
    </router-link>

    <router-link to="/my-pl" 
      class="material-icons">
      history_edu
    </router-link>

    <router-link to="/search" 
      class="material-icons">
      search
    </router-link>

    <div  @click="change()"
      class="material-symbols-outlined">
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
      console.log( this.$store.state.profileTargetId + "프로필 조회할 id")
      this.$router.push({
        name: 'profile',
      });
    }
  }
}
</script>

<style scoped>
  a {text-decoration: none;}
</style>