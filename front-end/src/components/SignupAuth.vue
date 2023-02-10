<template>
  <div>
    <div class="auth-layout">
      <p class="guide-comment">당신의 이메일 주소를 입력하세요.</p>
      <!-- 이메일 인증 Form -->
      <div class="auth-form">
        <div class="auth-input-form">
          <div class="account-id-box">
            <input type="email" id="input-email" class="inputbox" name="input-email" placeholder="이메일 주소" @input="emailInput">
            <button class="double-check btn-green" @click="doubleCheck">중복체크</button>
            <!-- <div v-if="emailActive" class="infobox">
              <p
                v-for="(condition, idx) in emailConditions"
                :key="idx"
                :class="[condition.valid? 'condition-valid': 'condition-invalid']"
              >
                {{ condition.comment }}
              </p>
            </div> -->
          </div>
        </div>
        <button v-if="isvalid" id="email-auth-btn" class="btn-green" @click="confirm">이메일 인증</button>
        <button v-else class="btn-green-inactive" id="email-auth-btn">이메일 인증</button>
      </div>
    </div>
    <auth-modal
      v-if="openAuthModal"
      :email="email">
    </auth-modal>
    <basic-modal
      v-if="openModal"
      :content="modalContent"
      :reload="modalReload"
      @close="modalClose"
    >
    </basic-modal>
  </div>
</template>

<script>
import AuthModal from '@/components/AuthModal.vue'
import BasicModal from '@/components/BasicModal'
// import { basicModalMixin } from '@/tools/basicModalMixin.js'

export default {
	name: 'SignupAuth',
  components: {
    AuthModal,
    BasicModal,
  },
  // mixins: [
  //   basicModalMixin,
  // ],
  data() {
    return {
      email: null,
      openAuthModal: false,
      emailActive: false,
      emailDoubleChecked: false,
      emailsended: false,
    }
  },
  methods: {
    // 인증
    confirm() {
      if (this.emailDoubleChecked) {
        // 모달 띄우기
      this.openAuthModal = true
      this.emailsended = true
      // 인증 작업
      const payload = {
        email: this.email,
      }
      this.$store.dispatch('authStart', payload)
      }
    },
    // 이메일 입력
    emailInput(event) {
      this.emailActive = true
      this.email = event.target.value
    },
    // 이메일 중복 체크
    doubleCheck() {
      // 중복체크가 아직 진행되지 않은 경우
      if (this.emailActive) {
        if (!this.emailDoubleChecked) {
          // 이메일 조건을 충족하지 못한 경우
          // if ( !this.emailConditions[0].valid ) {
          //   // this.openModal = true
          //   // this.modalContent = '이메일 형식을 지켜주세요.'
          //   const payload = {
          //     content: '이메일 형식을 지켜주세요.'
          //   }
          //   this.$store.dispatch('modalOpen', payload)
          // }
          // 이메일 조건을 충족한 경우
          // 중복체크
          this.$axios({
            method: 'get',
            url: `${this.$baseUrl}/users/check?type=email&value=${this.email}`
          })
            .then((response) => {
              // 응답 예시
              // 성공시(이미 존재할 때 ) : 200, already exists
              // 실패시(없을 때) : 200, success 
              // 서버 에러시 : 500, fail
  
              // 이미 이메일이 존재할 경우
              if ( response.data.message === 'already exists' ) {
                const payload = {
                  content: '이미 사용 중인 이메일이에요.'
                }
                this.$store.dispatch('modalOpen', payload)
                this.email = null
                // 입력된 이메일 삭제
                const emailTag = document.querySelector('#input-email')
                emailTag.value = null
              } else if ( response.data.message === 'success' ) {
                const payload = {
                  content: '사용할 수 있는 이메일이에요.'
                }
                this.$store.dispatch('modalOpen', payload)
                const emailInputTag = document.querySelector('#input-email')
                // 현재 이메일로 고정
                emailInputTag.setAttribute('disabled', true)
                this.emailDoubleChecked = true
              } else {
                const payload = {
                  content: '알 수 없는 에러가 발생했습니다. 고객센터에 문의해주세요.'
                }
                this.$store.dispatch('modalOpen', payload)
              }
            })
            .catch((error) => {
              console.log(error)
            })
          
        }
      }
    },
    // 모달 닫기
    modalClose() {
      this.$store.dispatch('modalClose')
    },
  },
  computed: {
    // 이메일 입력되었는지 여부 return
    isvalid() {
      if (this.emailDoubleChecked) {
        return true
      } else {
        return false
      }
    },
    // 이메일 조건
    // emailConditions() {
    //   let conditions = [{
    //     comment: 'X 이메일 형식을 지켜주세요.',
    //     valid: false,
    //   }]
    //   // 골뱅이 포함 여부 확인
    //   if (this.email) {
    //     if (this.email.includes('@')) {
    //     conditions[0].comment = 'O ' + conditions[0].comment.substring(1, )
    //     conditions[0].valid = true
    //     }
    //   }
    //   return conditions
    // },
    modalReload() {
      return this.$store.state.modalReload
    },
    openModal() {
      return this.$store.state.openModal
    },
    modalContent() {
      return this.$store.state.modalContent
    }
  },
  created() {
    // 아이디, 비밀번호 입력이 안 된 상태에서는 접근 불가
    if (!this.$store.state.id) {
      this.$router.push({ path: '/signup/main' })
    }
    this.emailDoubleChecked = false
    this.emailsended = false
  },
  watch: {
    openModal(newVal) {
    // focus를 이메일 인증 버튼으로 이동
      if (!newVal) {
        const emailAuthBtn = document.querySelector('#email-auth-btn')
        emailAuthBtn.focus()
      }
    }
  }
}
</script>

<style lang="scss">
</style>