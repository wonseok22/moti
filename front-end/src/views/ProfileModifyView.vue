<template>

<div id="profile-modify-layout" v-if="profile">
    <header id="profile-modify-header">

      <div class="profile-modify-title">내 프로필 설정</div>
    </header>

    <main class="profile-modify-main">
        <div class="profile-modify-img">
            <label for="image-input"><img :src="profileImageUrl" alt="프로필 사진"/><div class="profile-logo">+</div></label>
            <input 
                @change="inputImage"  
                type="file" multiple id="image-input" style="visibility:hidden;"
            >
        </div>
        <!-- <h4>닉네임</h4> -->
        <div class="profile-modify-nickname">
            <input type="text" id="input-box" class="inputbox" name="input-nickname" 
            placeholder="닉네임 입력" @input="nicknameInput" :value="nickname">
            <button class="btn-green" @click="doubleCheck">중복체크</button>
        </div>
        
        <!-- <h4>자기소개</h4> -->
        <div class="profile-modify-desc">
            <textarea placeholder="자기소개 입력 (50자 이내)" :value="content" @input="inputContent" class="profile-desc" name="feed-create-input" maxlength="50"></textarea>    
        </div>
    </main>
    <div class="profile-modify-btn">
        <button class="modify-btn" @click="submit">변경하기</button>
    </div>
    
    <NavigationBar></NavigationBar>
    <basic-modal
        v-if="openModal"
        :content="modalContent"
        @close="modalClose"
        >
    </basic-modal>
  </div>
</template>

<script>
import * as regex from '@/tools/regex.js'
import BasicModal from '@/components/BasicModal'
import { basicModalMixin } from '@/tools/basicModalMixin.js'

export default {
  name: 'ProfileModifyView',
  data() {
  return {
    profile:null,   
    content: null,
    images: null,
    nickname:null,
    isprivate: false,
    profileImageUrl:require(`@/assets/images/default_profile.jpg`),
  }
},
components: {
  BasicModal,
},
mixins: [
  basicModalMixin,
],
methods: {
  nicknameInput(event) {
    this.nicknameActive = true
    // 띄어쓰기 및 특수문자 제거
    const regexResult = regex.characterCheckNickname(event.target.value)
    this.nickname = regexResult[0]
    if (regexResult[1]) {
      this.modalContent = regexResult[1]
      this.openModal = true
    }
    event.target.value = this.nickname
  },
  // 닉네임 중복 체크
  doubleCheck() {
    // 임시로 중복체크 true로 바꿔줌
    // this.idDoubleChecked = true
    
    // 닉네임 조건을 충족하지 못한 경우
    if ( !this.nicknameConditions[0].valid ) {
      this.openModal = true
      this.modalContent = '닉네임 형식을 지켜주세요.'
    }
    // 닉네임 조건을 충족한 경우
    else {
      // 중복체크
      this.$axios({
        method: 'get',
        url: `${this.$baseUrl}/users/check?type=nickname&value=${this.nickname}`
      })
        .then((response) => {
          // 응답 예시
          // 성공시(이미 존재할 때 ) : 200, already exists
          // 실패시(없을 때) : 200, success 
          // 서버 에러시 : 500, fail

          // 이미 아이디가 존재할 경우
          if ( response.data.message === 'already exists' ) {
            this.openModal = true
            this.modalContent = '이미 사용 중인 닉네임이에요.'
            this.id = null
          } else if ( response.data.message === 'success' ) {
            this.openModal = true
            this.modalContent = '사용할 수 있는 닉네임이에요.'
            this.nicknameDoubleChecked = true
          } else {
            this.openModal = true
            this.modalContent = '알 수 없는 에러가 발생했습니다. 고객센터에 문의해주세요.'
          }
        })
        .catch((error) => {
          console.log(error)
        })
      }
  },
  // 프로필 수정
  submit() {

    if (!this.nicknameDoubleChecked && (this.nickname !== this.profile.nickname)) {
        alert("닉네임 중복체크를 완료해주세요")
        return 
    }
    const formData = new FormData()
    const profileDto = {
        userId: this.$store.state.id,
        userDesc: this.content,
        nickname : this.nickname,
      }
        
    const profileDtoJson = new Blob([JSON.stringify(profileDto)], { type: "application/json" })
    formData.append('profileDto', profileDtoJson)
    // 이미지
    if (this.images) {
      formData.append('profileImage', this.images[0])
    } 
    this.$axios({
      method: 'put',
      url: `${this.$baseUrl}/profile`,
      headers: {
          'access-token' : this.$store.state.accessToken,
          'Content-Type': 'multipart/form-data',
      },
      data: formData,
    })
      .then(() => {
        // 성공, 실패 로직
        this.$router.push({ name: 'profile' })
      })
      .catch((error) => {
        // 토큰 재발급
        if (error.response.status == 401) {
          this.$store.dispatch('tokenRegeneration')
        }
      })
  },
  // 작성 내용 저장하기
  inputContent(event) {
    this.content = event.target.value
  },
  // 이미지 받기
  inputImage(event) {
      if(this.images) {
          if(confirm("이미 선택된 사진이 있습니다. 바꾸시겠습니까?")){
              this.images = event.target.files 
          }
      } else {
          this.images = event.target.files 
      }
      this.profileImageUrl = URL.createObjectURL(this.images[0])
  },

},
computed: {
  isvalid() {
    if (this.nicknameDoubleChecked) {
      return true
    } else {
      return false
    }
  },
  nicknameConditions() {
    let conditions = [{
      comment: 'X 2~16자. 띄어쓰기와 특수문자는 사용이 안 돼요',
      valid: false,
    }]
    // 닉네임 길이 체크
    if ( this.nickname ) {
      if ( this.nickname.length >= 2 & this.nickname.length <= 16) {
      conditions[0].comment = 'O ' + conditions[0].comment.substring(1, )
      conditions[0].valid = true
      }
    }
    return conditions
  }
},
created() {
  this.$axios({
    method: 'get',
    url: `${this.$baseUrl}/profile?userId=${this.$store.state.id}`
  }).then((response) => {
    this.profile=response.data.profile
    this.nickname = this.profile.nickname
    this.content = this.profile.userDesc
    if (this.profile.profileImageUrl){
      this.profileImageUrl = this.profile.profileImageUrl
    }
    if (this.profile.userId === this.$store.state.id){
      this.isMyProfile = true;
    } else {
      this.isFollow = this.isFollowing()
    } 
    // 내가 아닌 경우 팔로우인지 팔로우 취소인지 체크하는 요청

    }).catch((error) =>{
      console.log(error)
    })
}
}
</script>

<style lang="scss">
$feed-create-footer-height: 5%;

// 기본 레이아웃
#profile-modify-layout {
height: inherit;
display: flex;
flex-direction: column;

padding: 0px 10px;
}

// header 레이아웃
#profile-modify-header {


  padding-top: 100px;
font-weight: bold;
font-size: 20px;
align-items: center;
margin-top: 10px;
margin-bottom: 15px;

// 취소
}

.profile-modify-main {

  margin-top: 50px;
  height: 350px;
  .profile-modify-img{
    // position: relative;
      margin: 0 auto;
      width: 90px;
      height: 90px;
      border-radius: 100%;
      overflow: hidden;
      align-items: center;
      vertical-align: middle;
      // border: 1px solid rgb(223, 223, 223);
      label {
          position: relative;
      }
      img{

        object-fit: cover;
          width: 90px;
          height: 90px;
      }

  }
  .profile-modify-img::before{
    content: "+";
    z-index: 9999;
    position: absolute;
    font-size: 20px;
    font-weight: bold;
    width: 22px;
    height: 22px;
    border-radius: 50%;
    left: 57%;
    background-color: rgb(133, 235, 147);
  }
}


.profile-modify-nickname{
  display: flex;
  width: 80%;
  height: 42px;
  margin: 0 auto;
  margin-top: 30px;
  .inputbox{
      width: 70%;
      float: left;
  }
  .btn-green{
      width: 80px;
      height: 60%;
      margin-top: 8px;
      margin-left: 5px;
      font-size: 12px;
      align-items: center;
  }
}

.profile-modify-desc{
  height: 150px;
  width: 80%;
  margin: 0 auto;
  margin-top: 30px;
  .profile-desc{
      width: 100%;
      height: 100px;
      border-radius: 5px;
      padding: 8px;
      background: #FFFDF5;
      box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.07);
      border: 1px solid #eee;
      border-radius: 10px;
      padding-left: 10px;
      font-size: 14px;
  }
}

.profile-modify-btn {

  .modify-btn{
      width: 226px;
      height: 47px;
      background: #04C584;
      // box-shadow: 0px 1px 3px rgb(0 0 0 / 45%);
      border: none;
      border-radius: 999px;
      font-family: 'Abhaya Libre';
      font-style: normal;
      font-weight: 700;
      // line-height: 100%;
      color: white;
      cursor: pointer;
  }
}

</style>