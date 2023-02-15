<template>
  <div>
    <div class="feed-creating-modal-bg">
      <div class="feed-creating-modal-box">
        <div class="loading">
          <span></span>   <!--1. span은 하나의 원이다. -->
          <span></span>
          <span></span>
        </div>
        <p>피드 업로드 중...</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FeedCreatingModal',

}
</script>

<style lang="scss">
.feed-creating-modal-bg {
	width: 100%;
	height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  background-color: white;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.feed-creating-modal-box {
	width: 90%;
  min-height: 100px;
	margin: auto;
	border-radius: 5px;
	padding: 20px 5px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  word-break: keep-all;
}

.loading span {
    display: inline-block; /* span 내부요소들을 한줄로 세우기 */
    width: 10px;
    height: 10px;
    // background-color: gray;
    border-radius: 50%;    /* span을 동그랗게 */
    animation: loading 1s 0s linear infinite;
    /* 이벤트명  반복시간  딜레이시간  이벤트처리부드럽게  이벤트무한반복*/
  }
  
  .loading span:nth-child(1) {  /*loading의 자식 중 첫번째 span*/
    /*nth-child : 형제 사이에서의 순서*/
    animation-delay: 0s;
    background-color: $light-red;
  }
  
  .loading span:nth-child(2) {
    animation-delay: 0.2s;
    background-color: orange;
  }
  
  .loading span:nth-child(3) {
    animation-delay: 0.4s;
    background-color: $main-green;
  }
@keyframes loading {        /*loading이라는 keyframe 애니메이션*/
    0%,                      /* 0, 50, 100은 구간*/
    100% {
      opacity: 0;            /* 안보였다가 */
      transform: scale(0.5); /*transform의 scale로 요소를 확대 또는 축소할 수 있음*/   
    }
    50% {
      opacity: 1;             /* 보였다가 */
      transform: scale(1.2); /* 1.2배 */
    }
  }
</style>