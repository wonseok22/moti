<template>
  <div>
    <carousel
      :per-page="1"
      :paginationPadding="2"
      :paginationActiveColor="'#04C584'"
      :paginationColor="'#dddddd'"
      paginationPosition="bottom"
      :paginationSize="7"
      :minSwipeDistance="200"
    >
      <slide class="slide">
        <div class="content">
          <div class="area">
            <br><br><br><br><br><br><br>
            <div class="title-box">
              <p style="font-size: 52px">moti</p>
            </div>
          </div>
          <div class="textarea">작지만 큰 실천을 도와주는 커뮤니티,<br />모티에 오신 걸 환영해요.</div>
        </div>
      </slide>
      <slide class="slide">
        <div class="content">
          <img class="intro-gif" src="@/assets/images/intro_1.gif" alt="" />
        </div>
        <div class="textarea">가운데 미션수행 탭을 누르면<br />수행할 성장 플랜을 선택할 수 있어요.</div>
      </slide>
      <slide class="slide">
        <div class="content">
          <img class="intro-gif" src="@/assets/images/intro_2.gif" alt="" />
        </div>
        <div class="textarea">
          하루에 한 번<br />마음에 드는 미션 하나를 선택해서 수행하고<br />후기를 작성하면 미션
          성공이에요.
        </div>
      </slide>
      <slide class="slide">
        <div class="content">
          <div class="area">
            <img class="clock-image" src="@/assets/images/clock_12am.png" alt="12am clock image" />
          </div>
          <div>
            <p class="textarea">
              일주일 중 5일 미션을 완수하면 성장플랜 완수에요.<br />당일 미션은 자정까지 수행
              가능해요!
            </p>
            <button class="btn-green" @click="go" style="margin-top: 30px; flex-self: flex-end">시작하기</button>
          </div>
        </div>
      </slide>
    </carousel>
  </div>
</template>
<script>
import { Carousel, Slide } from "vue-carousel";
import {mapState} from 'vuex';
export default {
  components: {
    Carousel,
    Slide,
  },
  computed:{
    ...mapState["type"],
  },
  methods: {
    go() {
      if(this.type=='kakao')
        this.$router.push({ name: 'feed' });
      else
        this.$router.push({ name: "login" });
    },
  },
};
</script>
<style lang="scss">
.slide {
  height: 95vh;
  width: auto;
}
.area {
  height: 70vh;
}
// .legacy-content {
//   height: 90vh;
//   display: flex;
//   flex-direction: column;
//   justify-content: space-around;
// }
.content {
  height: auto;
  margin-top: 10px;
}
.intro-gif {
  height: 70vh;
  width: auto;
}
.textarea {
  position: relative;
  bottom: 0%;
  margin-top: 50px;
  font-family: 'LINESeedKR-Rg';
}
.clock-image {
  width: 33vw;
  height: auto;
  align-self: center;
  margin-top: 70px;
}

</style>