<template>
  <div class="single-feed">
    <!-- 피드의 글본문에 해당되는 부분 -->
    <div class="feed-text">
        <p v-show="isThereImage === 0" v-line-clamp:20="2">{{ BodyData.content }}</p>
        <p v-show="isThereImage !== 0">{{ BodyData.content }}</p>
        <button v-if="isThereImage !== 0">
            <p>더보기</p>
        </button>
    </div>
    <!-- 피드의 이미지에 해당되는 부분 -->
    <carousel
     class="carousel" 
     :per-page="1"
     :paginationPadding="2"
     paginationActiveColor="#04C584"
     paginationColor="#ffff"
     paginationPosition="bottom-overlay"
     :paginationSize="7"
     :minSwipeDistance="130"
     >
        <slide class="single-slide" v-for="(feed, idx) of this.BodyData.feedImages" :key="idx">
            <img :src="feed.feedImageUrl" alt="motiImage">
        </slide>
    </carousel>
    <!-- 좋아요와 댓글개수와 관련되는 부분 -->
    <div class="like-comments">
        <p>좋아요 {{ this.likeCnt }}개</p>
        <p @click="moveToComment">댓글 {{  BodyData.comments.length }}개</p>
    </div>
    <!-- 좋아요 댓글 공유 버튼에 해당되는 부분 -->
    <div class="feed-btns">
        <span class="material-symbols-outlined"
        v-show="!this.isLike"
        @click="makeLike"
        style="color:#A3A3A3;">
            favorite
        </span>

        <span class="material-icons-outlined"
        v-show="this.isLike"
        @click="deleteLike"
        style="color:#FF5B5B;">
            favorite
        </span>

    <!-- 댓글 버튼 -->
        <span 
        v-show="this.$route.params.feedId === undefined"
        @click="moveToComment" 
        class="material-symbols-outlined"
        style="color:#A3A3A3;">
            mode_comment
        </span>

        <span 
        v-show="this.$route.params.feedId !== undefined"
        class="material-symbols-outlined"
        style="color:#04C584;">
            mode_comment
        </span>
    
    <!-- 공유 버튼 -->
        <span class="material-symbols-outlined"
        style="color:#A3A3A3;"
        @click="shareViaWebShare">
            share
        </span>
    </div>
  </div>
</template>

<script>
import { Carousel, Slide } from 'vue-carousel'

export default {
    name: 'SingleFeed',
    props: {
        BodyData: Object,
    },  
    components: {
        Carousel,
        Slide,
    },
    data() {
        return{
            isLike: this.BodyData.hit,
            likeCnt: this.BodyData.likes,
            isThereImage: 0,
        }
    },
    methods: {
        async moveToComment() {
            const resp = this.$store.dispatch("getSingleFeed", this.BodyData.feedId)
            const result = await resp 
            await this.$store.dispatch("putSingleFeed", result.data.feed)
            await this.$EventBus.$emit(this.BodyData.feedId)
            //await this.$router.push({name:"comment", params: {feedId:this.BodyData.feedId}}).catch(() => {})
            
        },
        makeLike() {
            this.$store.dispatch("makeLike", this.BodyData.feedId)
            this.isLike = true
            this.likeCnt += 1
        },
        deleteLike() {
            this.$store.dispatch("deleteLike", this.BodyData.feedId)
            this.isLike = false
            this.likeCnt -= 1
        },
        shareViaWebShare() {
            navigator.share({
                title: this.$store.state.nowFeed.missionName,
                text: this.BodyData.content,
                url: window.location.href
            })
        }
    },
    computed: {
        webShareApiSupported() {
            return navigator.share
        }
    },
    created( ) {
        this.isThereImage = this.BodyData.feedImages.length
        console.log(this.BodyData)
    }
}

</script>

<style>
</style>