<template>
  <div>
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons"
      rel="stylesheet">
    <div class="navbar">
      <router-link to="feed" 
        class="material-icons md-40"
        style="color:#A3A3A3;">
        home
      </router-link>

      <router-link to="userflowerlist" 
        class="material-icons md-40"
        style="color:#A3A3A3;">
        history_edu
      </router-link>

      <router-link to="search" 
        class="material-icons md-40"
        style="color:#A3A3A3;">
        search
      </router-link>

      <router-link to="profile" 
        class="material-icons md-40"
        style="color:#A3A3A3;">
        account_circle
      </router-link>

    </div>
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