<template>
  <div>
    <div id="auth-layout">
      <p class="guide-comment">당신의 이메일 주소를 입력하세요.</p>
      <!-- 이메일 인증 Form -->
      <div id="auth-form">
        <div id="auth-input-form">
          <input type="email" id="input-email" class="inputbox" name="input-email" placeholder="이메일 주소" @input="emailInput">
          <div v-if="emailActive">
              <p
                v-for="(condition, idx) in emailConditions"
                :key="idx"
                :class="[condition.valid? 'condition-valid': 'condition-invalid']"
              >
                {{ condition.comment }}
              </p>
            </div>
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
      emailActive: false,
    }
  },
  methods: {
    toAuthModal() {
      this.openModal = true
    },
    // 이메일 입력
    emailInput(event) {
      this.emailActive = true
      this.email = event.target.value
    }
  },
  computed: {
    // 이메일 입력되었는지 여부 return
    isvalid() {
      return true
    },
    emailConditions() {
      let conditions = [{
        comment: 'X 이메일 형식을 지켜주세요.',
        valid: false,
      }]
      // 골뱅이 포함 여부 확인
      if ( this.email.includes('@') ) {
        conditions[0].comment = 'O ' + conditions[0].comment.substring(1, )
        conditions[0].valid = true
      }
      return conditions
    }
  }
}
</script>

<style lang="scss">
</style>