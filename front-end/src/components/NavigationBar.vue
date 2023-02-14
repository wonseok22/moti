<template>
  <div class="navbar">
    <div v-if="!isNotHome()" 
      class="material-symbols-outlined"
      @click="goUp">
      home
    </div>

    <div v-else
      class="material-symbols-outlined nav-active"
      @click="goUp">
      home
    </div>

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
    isNotHome() {
      return this.$router.history.current["path"] === "/feed"
    },
    goPl() {
      this.$router.push({ name: 'my-pl' })
        .catch(() => { 
          location.reload()
        })
    },
    goUp() {
      console.log(this.$router.history.current["path"])
      if(this.$router.history.current["path"] === "/feed"){
        window.scrollTo({
          top:0,
          left:0,
          behavior:'smooth'
        })
      }
      else{
        this.$router.push({name: 'feed'})
      }
    },
  },
  created() {

    //console.log(this.plActive)
  }
}
</script>

<style scoped lang="scss">
</style>
