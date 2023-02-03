<template>
  <div id="pl-main-layout">
    <div class="pl-main-select">
      <select class="pl-main-option" v-model="selectedId"  @change="change">
        <option
  
        v-for   ="category in categoryList"
        :key    ="category.categoryName"
        v-text  ="category.categoryName"
        :value  ="category.categoryId"
        
        ></option>           
      </select>
    </div>
      
    <div v-for="(pl, idx) in plList" v-bind:key="idx" class="pl-list">
      <div id="pl" @click="movePlaylist(pl)">
        <!-- <img :src="pl.flower.flowerImageUrl" alt="플레이리스트 로고" class="pl-logo"/> -->
        <div class="pl-name">{{ pl.playlistName }}</div>
        <div class="pl-curr">현재 {{ pl.current }}명이 키우는 중입니다.</div>
        <p class="pl-total">누적 참여자 수 : {{ pl.total }}명</p>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'playlistSelect',
  data() {
      return{
          plList:null,
          categoryList: null,
          selectedName:'',
          selectedId: '',

      }
  },
  computed: {
  },
  created() {
    this.selectedName = this.$route.query.categoryName;
    this.selectedId = this.$route.query.categoryId;
    console.log(this.selectedName)
    console.log(this.selectedId)
    this.change()
    this.$axios({
      method: 'get',
      url: `${this.$baseUrl}/category/`
    }).then((response) => {
      this.categoryList = response.data.categories;
      console.log("카테고리 성공")
    }).catch((error) =>{
      console.log(error)
    })
    console.log(this.plList) 
  },

  methods : {
    change() {
      console.log(this.selectedId + "선택된 아이디")
      console.log(this.selectedName + "선택된 카테고리")
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/category/${this.selectedId}`
      }).then((response) => {
          this.plList = response.data.playlist
          console.log("플레이리스트 받아오기 성공")
          console.log("바뀐 pl" + this.plList)
          
      }).catch((error) =>{
        console.log(error)
      })
    },
    movePlaylist(selected){
      this.$router.push({
        name: 'playlistDetail',
        query: { pl: selected.playlistId
        },
      });
    }
  }
}
</script>
<style lang="scss">
$header-height: 46px;

#pl-main-layout {
  height: $base-height;
  width: $base-width;

}
.pl-main-option{
  width: 100px;
  height: 35px;
  position: relative;
  font-weight: bold;
  color: white;
  border-radius: 15px;
  padding: 0 10px;
  background-color: $main-green;
  border: 0px solid #000;
  
}
.pl-main-select{
  margin: 200px 0 70px 0;
  width: $base_width;
}

#category-main-main {
  height: 100%;

  justify-content: center;
  .category-list{
    margin: 30px auto;
  }
}


#pl {
  width: 304px;
  height: 210px;
  
  background: $light-yellow;
  box-shadow: 1px 3px 3px rgba(0, 0, 0, 0.17);
  border-radius: 43px;
  margin: 0 auto;

  font-weight: 300;
  .pl-logo {
    width: 40px;
    height: 40px;
    margin-top: 25px;
    margin-bottom: 10px;
  }
  .pl-name {

    font-weight: bold;
    font-size: 24px;
  }
  .pl-curr {

    font-size: 14px;
  }
  .pl-total {
    color: #aaa;
    font-size: 10px;
  }
}
</style>