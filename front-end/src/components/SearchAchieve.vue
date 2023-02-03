<template>
    <main>
        <div class="result-box flex">
            <div v-for="(achievement,idx) in achievements" :key="idx" class="achieve-lists">
                <img src="@/assets/images/default_profile.jpg" alt="업적 이미지">
            </div>
        </div>
    </main>
  </template>
  
  <script>

    export default {
    name: 'SearchAchieve',
    props: {
        keyword: String,
    },
    components: {

    },
    data() {
        return {
            achievements:null,
        }
    },
    watch: {

    },
    created() {
        console.log(this.keyword)
        this.$axios({
            method: 'get',
            url: `${this.$baseUrl}/achievement/${this.keyword}`
            }).then((response) => {
            this.achievements = response.data.achievements;
            console.log(this.achivements)
            }).catch((error) =>{
            console.log(error);
        })
    },

    methods : {


    }
  }
  </script>
  <style lang="scss">
    .flex {
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap; // 복수의 행
    }
    .achieve-lists{
        width: 90px;
        height: 90px;
        margin-top: 40px;
        img {
            width: 70px;
            height: 70px;
        }
    }
  </style>