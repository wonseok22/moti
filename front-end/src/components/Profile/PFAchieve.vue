<template>
    <main>
        <h2>업적</h2>
        <div class="result-box flex">
            <div v-for="(achievement,idx) in achievements" :key="idx" class="achieve-lists">
                <img :src="achievement.achievementImageUrl" alt="업적 이미지" :class="achievement.achieved ? `` : 'grayscale'"
                @click="openModal(achievement)">
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
        this.$axios({
            method: 'get',
            url: `${this.$baseUrl}/achievement/${this.keyword}`
            }).then((response) => {
            this.achievements = response.data.achievements;
            }).catch((error) =>{
            console.log(error);
        })
    },

    methods : {
        openModal(achievement) {
            this.$emit("openModal", achievement);
        }

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
        margin: 7px 7px;
        img {
            width: 70px;
            height: 70px;
        }
    }
    .grayscale{
        filter: grayscale(100%);
    }
  </style>