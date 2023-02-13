<template>
  <div id="category-main-layout">
    <main id="category-main-main">
      <p id="category-main-name">
        어떤 분야에서 성장하고 싶나요?
      </p>
      <div id="category-list">
        <div 
          v-for="(category, idx) in categoryList" 
          :key="idx" 
          @click="movePlaylist(category)" class="pl">
          <div class="pl-title">
            <img :src="category.categoryImageUrl" alt="카테고리 로고" />
            <div>{{ category.categoryName }}</div>
          </div>
          <p class="category-current">현재 <span>{{category.current }}명</span>이 이 꽃을 키우고 있습니다.</p>
        </div>
      </div>
    </main>
    <NavigationBar/>
  </div>
</template>
  
<script>
import NavigationBar from '@/components/NavigationBar'

export default {
  name: 'playlistMain',
  components: {
    NavigationBar,
  },
  data() {
    return {
      categoryList: null,
      categoryTitleList: null,
    }
  },
  computed: {
  },
  methods : {
    movePlaylist(selected){
      this.$router.push({
        name: 'playlistSelect',
        query: { categoryId: selected.categoryId,
                  categoryName: selected.categoryName
        },
      });
    }
  },
    created() {
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/category/`
      }).then((response) => {
        this.categoryList = response.data.categories;
        this.categoryTitleList = this.categoryList.map(e => e.categoryName)
      }).catch((error) =>{
        console.log(error)
      })
  },
}
</script>
<style lang="scss">
$header-height: 46px;

#category-main-layout {
  width: 90%;
  height: $base-height;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#category-main-name {
  position: relative;
  height: 70px;
  // left: 5px;
  font-size: 19px;
  font-weight: bold;
  margin: 0 auto;
  
  // display: flex;
  justify-content: flex-start;
  align-items: center;

  
  span {
    color: $main-green;
  }
}

#category-main-main {
  width: 90%;
  height: 70%;

  display: flex;
  flex-direction: column;
  justify-content: center;
}

#category-list {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  height: 70%;
  gap: 30px;
}


.pl {
  width: 304px;
  height: 156px;
  
  background: $light-yellow;
  box-shadow: 1px 3px 3px rgba(0, 0, 0, 0.17);
  border-radius: 43px;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-weight: 300;

  cursor: pointer;

  span {
    font-weight: bold ; 
  }
  .pl-title {
    display: flex;
    align-items: center;
    gap: 10px;
    height: $fs-0;

    margin-bottom: 10px;

    img {
      height: 100%;
    }
    div {
      font-weight: bold;
      font-size: $fs-1;
    }
    display: flex;
  }
}
</style>