<template>
  <div>
    <div id="auth-layout">
      <p class="guide-comment">당신의 이메일 주소를 입력하세요.</p>
      <!-- 이메일 인증 Form -->
      <div id="auth-form">
        <div id="auth-input-form">
          <input type="email" id="input-email" class="inputbox" name="input-email" placeholder="이메일 주소" v-model="email">
        </div>
        <button v-if="isvalid" class="btn-green" @click="toAuthModal">다음</button>
        <button v-else class="btn-green-inactive">다음</button>
      </div>
    </div>
    <auth-modal
      v-if="openModal">
    </auth-modal>
  </div>
</template>

<script>
import AuthModal from '@/components/AuthModal.vue'
export default {
	name: 'SignupAuth',
  components: {
    AuthModal,
  },
  data() {
    return {
      email: null,
      openModal: false,
    }
  },
  methods: {
    toAuthModal() {
      this.openModal = true
    }
  },
  computed: {
    // 이메일 입력되었는지 여부 return
    isvalid() {
      return true
    }
  }
}
</script>

<style lang="scss">
</style>