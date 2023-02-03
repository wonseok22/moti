<template>
    <main>
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
        openModal(achievement) {
            this.$emit("openModal", achievement);
        }

    }
  }
  </script>
  <style lang="scss">
    .flex {
        display: flex;
        justify-content: flex-start;
        flex-wrap: wrap; // 복수의 행
    }
    .achieve-lists{
        width: 90px;
        height: 90px;
        margin-top: 40px;
        // margin-right: 5.2%;
        margin-left: 7px;
        margin-right: 7px;
        img {
            width: 70px;
            height: 70px;
        }
    }
    .grayscale{
        filter: grayscale(100%);
    }
  </style>