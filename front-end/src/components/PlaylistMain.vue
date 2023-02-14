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
    
    <p id="pl-main-title">
      당신의 <span>성장 플랜</span>을 선택해주세요
    </p>

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
    <NavigationBar :plActive="true"/>
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
    this.change()
    this.$axios({
      method: 'get',
      url: `${this.$baseUrl}/category/`
    }).then((response) => {
      this.categoryList = response.data.categories;
    }).catch((error) =>{
      console.log(error)
    })
  },

  methods : {
    change() {
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/category/${this.selectedId}`
      }).then((response) => {
          this.plList = response.data.playlist
          
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
  height: 100vh;
  width: 90%;

}

#pl-main-title {
  // position: relative;
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

.pl-main-select{
  margin: 50px 0 30px 0;

  display: flex;
  justify-content: flex-end;
}

.pl-main-option {
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


#category-main-main {
  height: 100%;

  justify-content: center;
  .category-list{
    margin: 30px auto;
  }
}

#pl-list {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.pl {
  width: 100%;
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