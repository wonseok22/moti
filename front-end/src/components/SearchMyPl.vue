<template>
    <main>
      <h2>나의 성장기록</h2>
        <div class="playlist-result-box" >
          <div v-for="(playlist,idx) in playlists" :key="idx" class="playlist-box">
            <div class="playlist-items">
              <div v-for="(pl,i) in playlist" :key="i" class="platlist-item" @click="openModalPl(pl)">
                <!-- <img :src="pl.playlist.flower.flowerImageUrl" alt="PL 이미지" class="playlist-img-wrap"> -->
                <img :src="pl.playlist.flower.flowerImageUrl" alt="PL 이미지" :class="'grayscale-' + pl.done" class="playlist-img">
              </div>
            </div>
            <img src="@/assets/images/grass.png" alt="잔디 이미지" class="playlist-grass">
          </div>
        </div>
    </main>
  </template>
  
  <script>

    export default {
    name: 'SearchMyPl',
    props: {
      keyword: String,
    },
    components: {
    },
    data() {
        return {
            playlists:[[]],
        }
    },
    
    created() {
      this.$axios({
            method: 'get',
            url: `${this.$baseUrl}/playlist/${this.keyword}`
            }).then((response) => {
              const tmp = response.data.myPlaylists;

              let now = new Date();
              now = String(now.getFullYear()).padStart(2,"0") + "-"
              + String(now.getMonth() + 1).padStart(2,"0") + "-"
              + String(now.getDate()).padStart(2,"0") + "T" 
              + String(now.getHours()).padStart(2,"0") + ":"
              + String(now.getMinutes()).padStart(2,"0") + ":"
              + String(now.getSeconds()).padStart(2,"0")
              
              tmp.map((e) => {
                
                var startDate = String(e.startDate).slice(0,4) + "년 " +
                String(e.startDate).slice(5,7) + "월 " + 
                String(e.startDate).slice(8,10) + "일"
                e.startDate = startDate
                if (now >= e.endDate) {
                  e["isDone"] = true
                } else {
                  e["isDone"] = false
                }
                if (this.playlists[this.playlists.length - 1].length == 4) {
                  this.playlists.push(new Array())
                } 
                this.playlists[this.playlists.length - 1].push(e)
              })
            }).catch((error) =>{
            console.log(error);
        })
    },

    methods : {
      openModalPl(pl) {
          this.$emit("openModalPl", pl);
      }
    }
  }
  </script>
  <style lang="scss">
  .playlist-result-box{
    width: 340px;
    margin: 0 auto;
    height: 100%;
    .playlist-box{
      width: 100%;
      height: 82px;
      position: relative;
      margin: 0 auto;
      margin-top: 25px;
      .playlist-grass{
        position: absolute;
        left: 0;
        top: 70px;
      }
      .playlist-items{
        display: flex;
        width: 100%;
        height: 75px;
        .platlist-item{
          width: 25%;
          height: 75px;
          position: relative;
          // margin-right: calc((100% - 340px)/3);
          // margin-right: 3%;
          // .playlist-img-wrap{
          //   width: 90%;
          //   height: 100%;
          //   position: absolute;
          //   filter: grayscale(100%);
          //   top: 0px;
          //   object-fit: cover;
          // }
          .playlist-img{
            width: 90%;
            height: 100%;
          }
        }
      }
    }
  }

  .grayscale-0{
        filter: grayscale(100%);
  }
  .grayscale-1{
        filter: grayscale(100%);
  }
  .grayscale-2{
        filter: grayscale(100%);
  }
  .grayscale-3{
        filter: grayscale(100%);
  }
  .grayscale-4{
        filter: grayscale(100%);
  }
  .grayscale-5{
        filter: grayscale(0%);
  }
  </style>