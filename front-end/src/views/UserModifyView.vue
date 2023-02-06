<template>

    <div id="profile-modify-layout">
        <header id="profile-modify-header">
    
          <div class="profile-modify-title">비밀번호 변경</div>
        </header>
    
        <main class="profile-modify-main">
        <!-- 비밀번호 -->
        <div class="pwd">
            <input type="password" id="input-pw" class="inputbox" name="input-pw" placeholder="비밀번호" @input="pwInput">
            <div v-if="pwActive" class="infobox">
              <p
                v-for="(condition, idx) in pwConditions"
                :key="idx"
                :class="[condition.valid? 'condition-valid': 'condition-invalid']"
              >
                {{ condition.comment }}
              </p>
            </div>
          </div>
          <!-- 비밀번호 재입력 -->
          <div>
            <input type="password" id="input-pw2" class="inputbox" name="input-pw2" placeholder="비밀번호 재입력" @input="pwInput2" @keyup.enter="toEmailAuth">
            <div v-if="pw2Active" class="infobox">
              <p
                v-for="(condition, idx) in pw2Conditions"
                :key="idx"
                :class="[condition.valid? 'condition-valid': 'condition-invalid']"
              >
                {{ condition.comment }}
              </p>
            </div>
          </div>
        </main>


        <div class="profile-modify-btn">
            <button class="modify-btn" @click="submit">변경하기</button>
        </div>
        
        <NavigationBar></NavigationBar>
      </div>
      </template>
      
      <script>
      import * as regex from '@/tools/regex.js'
      export default {
        name: 'UserModifyView',
        data() {
        return {
            password: null,
            password2: null,
            pwActive: false,
            pw2Active: false,
        }
      },
      methods: {
        submit() {
        if(!this.isvalid){
            alert("올바른 비밀번호을 입력하세요")
            return;
        }

        // const userDtoJson = new Blob([JSON.stringify(userDto)], { type: "application/json" })
        this.$axios({
            method: 'put',
            url: `${this.$baseUrl}/users`,
            headers: {
                'access-token' : this.$store.state.accessToken,
            },
            data: {
                userId: this.$store.state.id,
                password:this.password,
            }

        })
            .then(() => {
                // 성공, 실패 로직
                console.log("비밀번호 수정 성공")
                this.$router.push({ name: 'profile' })
            })
            .catch((error) => {
                console.log(error)
            })
        },
         // 비밀번호 입력 받기
        pwInput(event) {
        this.pwActive = true
        // 띄어쓰기 및 특수문자 제거
        this.password = regex.characterCheck(event.target.value)
        event.target.value = this.password
        },
        // 비밀번호 재입력 받기
        pwInput2(event) {
        this.pw2Active = true
        // 띄어쓰기 및 특수문자 제거
        this.password2 = regex.characterCheck(event.target.value)
        event.target.value = this.password2
        },
        
      },
      computed: {
        // input이 제대로 입력되었는지 여부 return
            isvalid() {
            // 아이디 중복 체크 / 비밀번호 조건 충족 여부 / 비밀번호 일치 여부
            if (this.pwConditions[0].valid === true & this.pwConditions[1].valid === true & this.pwConditions[2].valid === true & this.pw2Conditions[0].valid === true ) {
                return true
            } else {
                return false
            }
        },
        // 비밀번호 조건 충족 여부
    pwConditions() {
      let conditions = [
        {
          comment: 'X 6~50자. 띄어쓰기와 특수문자는 사용이 안 돼요',
          valid: false,
        },
        {
          comment: 'X 영어 대문자 또는 소문자가 포함되어야 해요.',
          valid: false,
        },
        {
          comment: 'X 숫자가 포함되어야 해요.',
          valid: false,
        }
      ]

        // 비밀번호 길이 체크
        if ( this.password ) {
            if (this.password.length >= 6 & this.password.length <= 50) {
            conditions[0].comment = 'O ' + conditions[0].comment.substring(1, )
            conditions[0].valid = true
            }
        }
        // 영어 포함 여부 체크
        if ( regex.englishCheck(this.password) ) {
            conditions[1].comment = 'O ' + conditions[1].comment.substring(1, )
            conditions[1].valid = true
        }
        // 숫자 포함 여부 체크
        if ( regex.numberCheck(this.password) ) {
            conditions[2].comment = 'O ' + conditions[2].comment.substring(1, )
            conditions[2].valid = true
        }
      return conditions
    },
    pw2Conditions() {
      let conditions = [
        {
          comment: 'X 비밀번호가 일치해야 해요.',
          valid: false,
        },
      ]
      // 비밀번호 일치 여부
      if ( this.password === this.password2 ) {
        conditions[0].comment = 'O ' + conditions[0].comment.substring(1, )
        conditions[0].valid = true
      }
      return conditions
    },
    
      },
      created() {
      
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
        height: 250px;
        .profile-modify-img{
            label {
                position: relative;
            }
            .profile-logo{
                position: absolute;
                font-size: 20px;
                font-weight: bold;
                width: 22px;
                height: 22px;
                border-radius: 50%;
                left: 65px;
                top: -65px;
                background-color: rgb(133, 235, 147);
            }
            img{
                width: 80px;
                height: 80px;
            }
    
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
    .pwd{
        margin-bottom: 30px;
    }
      </style>