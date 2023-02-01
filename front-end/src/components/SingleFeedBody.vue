<template>
  <div class="single-feed">
    <!-- 피드의 글본문에 해당되는 부분 -->
    <div class="feed-text">
        <p v-show="isThereImg()" v-line-clamp:20="2">{{ BodyData.content }}</p>
        <p v-show="isThereImg2()">{{ BodyData.content }}</p>
        <button v-if="isThereImg()">
            <p>더보기</p>
        </button>
    </div>
    <!-- 피드의 이미지에 해당되는 부분 -->
    <div class="feed-image">
        
    </div>
    <!-- 좋아요와 댓글개수와 관련되는 부분 -->
    <div class="like-comments">
        <p>좋아요 {{ BodyData.likes }}개</p>
        <p>댓글 {{ BodyData.commentNum }}개</p>
    </div>
    <!-- 좋아요 댓글 공유 버튼에 해당되는 부분 -->
    <div class="feed-btns">
        <span class="material-symbols-outlined"
        v-show="BodyData.likes == false"
        style="color:#A3A3A3;">
            favorite
        </span>

        <span class="material-icons-outlined"
        v-show="BodyData.likes == true"
        style="color:#FFB100;">
            favorite
        </span>

        <span 
        v-show="this.$route.params.feedId === undefined"
        v-on:click="moveToComment" 
        class="material-symbols-outlined"
        style="color:#A3A3A3;">
            mode_comment
        </span>

        <span 
        v-show="this.$route.params.feedId !== undefined"
        class="material-icons-outlined"
        style="color:#04C584;">
            mode_comment
        </span>

        <span class="material-icons-outlined"
        style="color:#A3A3A3;">
            share
        </span>
    </div>
  </div>
</template>

<script>

export default {
    name: 'SingleFeed',
    props: {
        BodyData: Object,
    },  
    data() {
        return{
            text: '이건 테스트를 위해 적어놓은 아무말이며, 나는 이것을 위한 노력을 1도 사용하지 않았다. 아무리 그래도 3줄은 넘어야하기 때문에 계속해서 적는것이 좋을거 같다.',
            text2: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse in nunc sit amet libero semper blandit semper id eros. Praesent consequat tincidunt congue. Sed elit justo, blandit vitae arcu id, rhoncus laoreet urna. Quisque volutpat magna bibendum, mattis dolor vel, interdum massa. Suspendisse eleifend a nibh ut euismod. Sed efficitur justo in mi consequat, quis porttitor dui imperdiet. Aliquam nec condimentum nunc. Pellentesque non orci orci. Aenean consequat porta velit eu malesuada. Sed feugiat dapibus commodo. Cras dolor lacus, pharetra scelerisque ullamcorper sed, tincidunt et purus.',
        }
    },
    methods: {
        isThereImg() {
            return false
        },
        isThereImg2() {
            return true
        },
        moveToComment() {
            this.$axios({
                method:'get',
                url:this.$baseUrl + '/feed/1' + '/red'
            })
            .then((res) => {
                const data = res.data.feed
                this.$router.push({name:'comment', params: {feedId:1}, query:{FeedData: data}})
            })
            .catch((error) => {
                console.log(error.message)
            })
        },
    },
}

</script>

<style>
</style>