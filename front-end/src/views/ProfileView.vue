<template>
  <div class="profile-main">
    <!-- 프로필 페이지 최상단 유저이름과 메뉴바 -->
    <div class="profile-header">
      <p>이상화</p>
      <button class="material-icons-outlined">
        menu
      </button>
    </div>
    <div class="profile-info">
      <div class="main-info">
        <span class="material-icons-outlined">
        account_circle
        </span>
        <div> 
          <div>
            <div></div>
            <div></div>
            <div></div>
          </div>
        </div>
      </div>
      <p class="my-presentation"></p>
    </div>
    <div class="profile-menu">
      <div>
        <span class="material-icons-outlined">
        feed
        </span>
        <hr>
      </div>
      <div>
        <span class="material-symbols-outlined">
        nature
        </span>
        <hr>
      </div>
      <div>
        <span class="material-icons-outlined">
        emoji_events
        </span>
        <hr>
      </div>
    </div>
    <NavigationBar></NavigationBar>
  </div>
</template>

<script>

export default {

}
</script>

<style>

</style>