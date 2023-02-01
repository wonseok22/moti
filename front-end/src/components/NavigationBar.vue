<template>
  <div class="navbar">
    <router-link to="feed" 
      class="material-icons-outlined">
      home
    </router-link>

    <router-link to="userflowerlist" 
      class="material-icons">
      history_edu
    </router-link>

    <router-link to="search" 
      class="material-icons">
      search
    </router-link>

    <router-link to="profile" 
      class="material-symbols-outlined">
      account_circle
    </router-link>
  </div>    
</template>

<script>
export default {
    name:'NavigationBar',
}
</script>

<style scoped>
  a {text-decoration: none;}
</style>