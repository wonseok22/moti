<template>
  <div id="app">
    <router-view/>
    <width-warning-view
      v-if="tooWide"
    >
    </width-warning-view>
  </div>
</template>

<script>
import WidthWarningView from '@/views/WidthWarningView'

export default {
  name: 'App',
  components: {
    WidthWarningView
  },
  data() {
    return {
      nowWidth: null,
    }
  },
  computed: {
    tooWide() {
      window.addEventListener("resize", () => {
        this.nowWidth = screen.width
      })
      if (this.nowWidth > 450) {
        // 이메일 인증 완료 페이지일 경우
        const authcompTag = document.querySelector('#authcomp-layout')
        if (authcompTag) {
          return false
        }
        return true
      } else {
        return false
      }
    }
  },
  mounted() {
    this.nowWidth = screen.width
  }
}

</script>


<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;

  height: inherit;
}

// 컴포넌트 중앙 정렬 등
.account-form-comp {
  height: $base-height;
  margin: 0px 10px;
  
  display: flex;
  flex-direction: column;
  justify-content: center;
}

// 컴포넌트 모든 콘텐츠에 대한 css
.account-layout {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;

  height: $account-height
}

// input 태그와 다음 버튼에 대한 css
.account-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px;
  margin: 20px;
  margin-top: 100px;
  gap: 26px;

  // position: absolute;
  width: 332px;
  // height: 245px;
  left: 14px;
  top: 217px;
}

.account-id-box {
  position: relative;
}

// input 태그에 대한 css
.account-input-form {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  // padding-left: ;
  gap: 23px;

  width: 332px;
}

.account-sub {
  display: flex;
  flex-direction: column;
  color: $dark-grey;
  gap: 15px;
}

.login-signup {
  display: flex;
  gap: 10px;
}

.to-kakao {
  font-size: $fs-6;
}

.kakao-login-img {
  width: 60px;
}

.condition-valid {
  @include condition-isvalid()
}

.condition-invalid {
  @include condition-isvalid($light-red)
}
</style>
