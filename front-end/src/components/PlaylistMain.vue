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
      
    <div id="pl-list">
      <div 
        v-for="(pl, idx) in plList"
        v-bind:key="idx"
        class="pl"
        @click="movePlaylist(pl)">
        <img :src="pl.flower.flowerImageUrl" alt="playlist-logo" class="pl-logo">
        <div class="pl-name">{{ pl.playlistName }}</div>
        <div class="pl-curr">현재 <span>{{ pl.current }}명</span>이 키우는 중입니다.</div>
        <div class="pl-total">누적 참여자 수 : {{ pl.total }}명</div>
      </div>
    </div>
    <NavigationBar/>
  </div>
</template>

<script>
import NavigationBar from '@/components/NavigationBar'

export default {
  name: 'playlistSelect',
  components: {
    NavigationBar,
  },
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
  margin: 100px 0 70px 0;
  width: $base_width;
}

#category-main-main {
  height: 100%;

  justify-content: center;
  .category-list{
    margin: 30px auto;
  }
}

#pl-list {
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.pl {
  width: 304px;
  height: 210px;
  
  background: $light-yellow;
  box-shadow: 1px 3px 3px rgba(0, 0, 0, 0.17);
  border-radius: 43px;
  margin: 0 auto;

  display: flex;
  flex-direction: column;
  gap: 0;

  font-weight: 300;

  .pl-logo {
    height: 45%;
    padding-bottom: 10px;
  }
  .pl-name {
    font-weight: bold;
    font-size: $fs-3;
    margin-bottom: 15px;
  }
  .pl-curr {
    // font-weight: $bold-7;
    // font-size: $fs-6;
    span {
      font-weight: $bold-7;
    }
  }
  .pl-total {
    color: $dark-grey;
    font-size: $fs-6;
  }
}
</style>