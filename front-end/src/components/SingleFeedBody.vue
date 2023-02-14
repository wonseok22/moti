<template>
  <div :id="`feed-${feedIdx}`">
    <!-- 피드의 글본문에 해당되는 부분 -->
    <div class="feed-text">
        <p 
            v-if="isThereImage && !isCommentCheck && overThreeLines" 
            v-line-clamp="2" 
            class="text-not-all"
            :class="`single-feed-content-${feedIdx}`"
            >
            {{ BodyData.content }}
        </p>
        <p 
            v-else
            class="text-all"
        >
            {{ BodyData.content }}
        </p>
        <button 
            v-if="isThereImage && BodyData.content && !isCommentCheck && overThreeLines"
            :class="`single-feed-btn-${feedIdx}`"
            >
            <p 
                @click="seeMore" 
                id="see-more">더보기</p>
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
        v-show="!this.$store.state.isComment"
        @click="moveToComment" 
        class="material-symbols-outlined"
        style="color:#A3A3A3;">
            mode_comment
        </span>

        <span 
        v-show="this.$store.state.isComment"
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
        feedIdx: Number,
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
            isComment: false,
            overThreeLines: false,
        }
    },
    methods: {
        async moveToComment() {
            const y = window.scrollY
            const resp = this.$store.dispatch("getSingleFeed", this.BodyData.feedId)
            const result = await resp 
            await this.$store.dispatch("putSingleFeed", result.data.feed)
            await this.$store.dispatch("putScrollHeight", y)
            await this.$store.dispatch("showComment")
            document.body.style.overflow = "hidden"
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
            console.log(this.BodyData)
            let imageUrl = this.BodyData.feedImages.length != 0? this.BodyData.feedImages[0].feedImageUrl:""
            console.log(imageUrl)
            window.Kakao.Share.sendDefault({
            objectType: 'feed',
            content: {
                title: `${this.BodyData.playlistName}의 ${this.BodyData.missionName}`,
                description: this.BodyData.content,
                imageUrl: imageUrl,
                link: {
                mobileWebUrl: 'https://moti.today/feed',
                webUrl: 'https://moti.today/feed',
                },
            },
            itemContent: {
                profileText: this.BodyData.nickname,
                profileImageUrl: this.BodyData.profileImageUrl,
            },
            social: {
                likeCount: this.BodyData.likes,
                commentCount: this.BodyData.comments.length,
            },
            buttons: [
                {
                title: '웹으로 이동',
                link: {
                    mobileWebUrl: 'https://moti.today/feed',
                    webUrl: 'https://moti.today/feed',
                },
                },
            ],
            });
        },
        // 더 보기
        seeMore() {
            const seeMoreContent = document.querySelector(`.single-feed-content-${this.feedIdx}`)
            seeMoreContent.style.removeProperty('-webkit-line-clamp')
            const seeMoreBtn = document.querySelector(`.single-feed-btn-${this.feedIdx}`)
            seeMoreBtn.remove()

        }
    },
    computed: {
        webShareApiSupported() {
            return navigator.share
        },
        isCommentCheck() {
            return this.$store.state.isComment
        }
    },
    created( ) {
        this.isThereImage = this.BodyData.feedImages.length
        // 댓글 작성 페이지일 경우
        if (String(this.$options._componentTag) === 'FeedComment') {
            this.isComment = true
        }
    },
    mounted() {
        const feedContentTag = document.querySelector(`#feed-${this.feedIdx} > .feed-text > p`)
        const tagHeight = feedContentTag.offsetHeight
        // const lineHeight = parseInt(feedContentTag.style.lineHeight)
        const lines = tagHeight / (1.15 * 16)
        
        if (lines >= 3) {
            this.overThreeLines = true
        }
        
    },
}

</script>

<style lang="scss">
</style>