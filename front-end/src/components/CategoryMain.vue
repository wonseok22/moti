<template>
  <div id="category-main-layout">
    <main id="category-main-main">
      <p id="category-main-name">
        마음속에 들어있는 <span>동기</span> 는 무엇인가요?
      </p>
      <div 
        v-for="(category, idx) in categoryList" 
        :key="idx" 
        class="category-list"
      >
        <div @click="movePlaylist(category)" class="pl">
          <div class="pl-title">
            <img :src="category.categoryImageUrl" alt="카테고리 로고" />
            <div>{{ category.categoryName }}</div>
          </div>
          <p>현재 <span>{{category.current }}명</span>이 이 꽃을 키우고 있습니다.</p>
        </div>
      </div>
      
    </main>
  </div>
</template>
  
<script>
export default {
  name: 'playlistMain',
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
        console.log("카테고리 받아오기 성공")
      }).catch((error) =>{
        console.log(error)
      })
  },
}
</script>
<style lang="scss">
$header-height: 46px;

#category-main-layout {
  height: $base-height;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#category-main-name {
  position: relative;
  width: $base-width;
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
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  .category-list{
    margin: 30px auto;
  }
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
  gap: 20px;
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