<template>
  <div>
    <Header id="SearchHeader">
      
      <input type="text" @input="change" placeholder="검색"/>
    </Header>
    <nav id="CategoryNav">
      <div class="playlist" @click="playlist()">플레이리스트</div>
      <div class="feed" @click="feed()">게시글</div>
      <div class="nickname" @click="nickname()">계정</div>
    </nav>
    <div class="underbar">
      <div id="bar" class="bar1"></div>
    </div>
    <div class="SearchResult">
      <div class="SearchResult-slide">
        <SearchPlaylist :keyword="keyword"></SearchPlaylist>
        <SearchFeed :keyword="keyword"></SearchFeed>
        <SearchNickname :keyword="keyword"></SearchNickname>
      </div>
    </div>
    <NavigationBar></NavigationBar>
  </div>
</template>

<script>
import SearchPlaylist from '@/components/SearchPlaylist.vue'
import SearchFeed from '@/components/SearchFeed.vue'
import SearchNickname from '@/components/SearchNickname.vue'
// import SingleFeedBody from '../components/SingleFeedBody.vue'

export default {
  data() {
    return {
      keyword : "",
    }
  },
  created() {
  },
  methods: {
    change(event) {
      this.keyword=event.target.value;
    },
   playlist () {
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".SearchResult-slide")
      slide.style.left = 0;
      bar.className = "bar1"; 
      
    },
    feed() {
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".SearchResult-slide")
      slide.style.left = "-360px";
      bar.className = "bar2";
      
    },
    nickname() {
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".SearchResult-slide")
      slide.style.left = "-720px";
      bar.className = "bar3";

   }
  },
  components: {
    SearchPlaylist,
    SearchFeed,
    SearchNickname,
  }
}
</script>

<style  lang="scss">
#SearchHeader{

  width: $base_width;
  height: 90px;
  align-items: center;
  vertical-align: middle;

  
  input {
    margin-top: 30px;
    border-radius: 8px;
    height: 40px;
    width: 80%;
  }
  button {
    width: 20%;
    height: 100%;
  }
}
#CategoryNav {
  margin: 5px auto;
  width: 80%;
  display: flex;
  justify-content:  space-between;
  font-weight: bold;

  .playlist {
    width: 40%;
  }
  .feed {
    width: 30%;
  }
  .nickname {
    width: 30%;
  }
}
.underbar {
  margin: 0 auto;
  width: 80%;
  border-bottom: 1px solid #ddd;
  div {
    position: relative;
    border-bottom: 2px solid #000;
    transition-property:left;
  }
  
  .bar1 {
    width: 40%;
    left:0;
    transition:0.3s;
  }
  .bar2 {
    width: 30%;
    left: 40%;
    transition: 0.3s;
  }
  .bar3 {
    width: 30%;
    left: 70%;
    transition: 0.3s;
  }

  }
.SearchResult {
  margin-top: 20px;
  width: $base_width;
  height: 600px;
  position: relative;
  overflow: hidden;

  .SearchResult-slide{
    transition : 0.5s;
    left:0;
    position: absolute;
    width: 300vw;
    height: 100%;
    display: flex;
    main{
      width: $base_width;
      height: 100%;
      .result-box {
        width: 100%;
        height: 100%;
        margin: 0 auto;
        // background-color: #bbb;
      }
    }
  }
}
</style>