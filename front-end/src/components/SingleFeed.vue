<template>
  <div class="single-feed">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lily+Script+One&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link href="https://fonts.googleapis.com/css2?family=Lily+Script+One&display=swap" rel="stylesheet">
    <!-- 하나의 피드에서 제목과 유저 프로필에 해당되는 부분 -->
    <div class="feed-header">
        <span class="material-symbols-outlined"
        style="color:#A3A3A3; font-size:44px">
            account_circle
        </span>
        <div>   
            <div>
                <span class="material-symbols-outlined"
                style="color:#A3A3A3">
                    workspace_premium 
                </span>
                <p>이상화다이상해</p>
                <button>
                    팔로우
                </button>
            </div>
            <p>‘초보자를 위한 운동’ 의 ‘1km 걷기’ 달성</p>
        </div>
    </div>
    <!-- 피드의 글본문에 해당되는 부분 -->
    <div class="feed-text">
        <p v-if="isThereImg()" v-line-clamp:20="2">{{ text }}</p>
        <p v-if="isThereImg2()">{{ text }}</p>
        <button v-if="isThereImg()">
            <p>더보기</p>
        </button>
    </div>
    <!-- 피드의 이미지에 해당되는 부분 -->
    <div class="feed-image">
        
    </div>
    <!-- 좋아요와 댓글개수와 관련되는 부분 -->
    <div class="like-comments">
        <p>좋아요 X개</p>
        <p>댓글 x개</p>
    </div>
    <!-- 좋아요 댓글 공유 버튼에 해당되는 부분 -->
    <div class="feed-btns">
        <span class="material-symbols-outlined"
        style="color:#A3A3A3;">
            favorite
        </span>

        <span class="material-symbols-outlined"
        style="color:#A3A3A3;">
            mode_comment
        </span>

        <span class="material-symbols-outlined"
        style="color:#A3A3A3;">
            share
        </span>
    </div>
  </div>
</template>

<script>

export default {
    name: 'SingleFeed',
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
        }
    },
}

</script>

<style>
</style>