<template>
    <div id="mission-create-main">
        <div class="mission-create-header">
            <span class="material-symbols-outlined mission-create-icon">
              inventory_2
            </span>
            <div>성장플랜 건의함</div>
            <p>나만의 성장 플랜을 만들어보세요!<br>성장플랜은 관리자의 검토 후 반영됩니다.</p>
        </div>
        <div class="mission-create-box">
            <div class="mission-create-category">
                <div class="mission-create-category-title">카테고리 선택</div>
                <select v-model="selected"  class="mission-create-category-value">
                    <option 
                        v-for   ="category in categoryList"
                        :key    ="category"
                        v-text  ="category"
                        :value  ="category  "
                    ></option>
                </select>
            </div>
            <div class="mission-create-name">
                <div class="mission-create-name-title">성장플랜 이름</div>
                <input @input="changeTitle" type="text" placeholder="성장플랜 이름을 작성하세요" class="mission-create-name-value"/>
            </div>
            <div class="mission-create-missions">
                <div class="mission-create-missions-title">미션 세부 선택</div>
                <input @input="change(0, $event)" type="text" placeholder="미션1을 작성하세요" class="mission-create-missions-value"/>
                <input @input="change(1, $event)" type="text" placeholder="미션2를 작성하세요" class="mission-create-missions-value"/>
                <input @input="change(2, $event)" type="text" placeholder="미션3을 작성하세요" class="mission-create-missions-value"/>
                <input @input="change(3, $event)" type="text" placeholder="미션4를 작성하세요" class="mission-create-missions-value"/>
                <input @input="change(4, $event)" type="text" placeholder="미션5를 작성하세요" class="mission-create-missions-value"/>
                
            </div>
            <div class="mission-create-desc">
                <div class="mission-create-desc-title">성장플랜 설명</div>
                <input @input="changeDesc" type="text" placeholder="성장플랜 설명을 작성하세요" class="mission-create-desc-value"/>
            </div>
            <button class="mission-create-submit" @click="createMission"> 제출하기 </button>
        </div>
        <NavigationBar></NavigationBar>
    </div>
  </template>
  
  <script>
  export default {
    name: 'MissionCerateView',
    data() {
        return {
            categoryList : ["건강", "관계", "행복"],
            selected : null,
            missions : ["", "", "", "", ""],
            title : "",
            desc : "",            
        }
    },
    methods : {
        changeTitle(event) {
            this.title = event.target.value;
        },
        changeDesc(event) {
            this.desc = event.target.value;
        },
        change(i, event) {
            this.missions[i] = event.target.value;
        },
        createMission(){
            if(!this.selected) {
                alert("카테고리를 선택하세요")
                return;
            } else if(this.title.length < 1) {
                alert("성장플랜 이름을 입력하세요.");
                return;
            }else if (this.title.length > 45 ) {
                alert("성장플랜 이름은 45자 이내로 작성해주세요.");
                return;
            } else if(this.desc.length < 1) {
                alert("성장플랜 설명을 입력하세요.")
                return;
            } else if (this.desc.length > 200) {
                alert("성장플랜 설명은 200자 이내로 작성해주세요.")
                return
            } 
            for(let i = 0; i < 5; i ++){
                if (this.missions[i].length < 1){
                    alert(`미션${i+1}의 내용을 입력하주세요`)
                    return 
                } else if(this.missions[i].length > 45) {
                    alert(`미션${i+1}의 내용을 45자 이내로 작성해주세요.`)
                    return
                }
            }

            const suggestionDto = {
                "categoryName" : this.selected,
                "mission1Name" : this.missions[0],
                "mission2Name" : this.missions[1],
                "mission3Name" : this.missions[2],
                "mission4Name" : this.missions[3],
                "mission5Name" : this.missions[4],
                "playlistName" : this.title,
                "suggestionContent" : this.desc,
            }
            this.$axios({
                method: "post",
                url: `${this.$baseUrl}/suggestion`,
                data : suggestionDto,
            })
            .then((response) => {
                if (response.data.message === "success") {
                    alert("성장플랜을 건의해주셔서 감사합니다.")
                    this.$router.push({
                        name: "profile",
                    });
                }
            })
            .catch((error) => {
                console.log(error);
            });
        },
    },
}

  </script>
  
  <style lang="scss">
    .mission-create-header {
        height: 20%;
        padding-top: 70px;
        font-weight: bold;
        font-size: 20px;
        align-items: center;
        margin-top: 10px;
        margin-bottom: 15px;
        div {
            margin-top: 10px;
        }
        p {
            color: $light-grey;
            font-size: 12px;
        }
    }
    .mission-create-box {
        width: 100%;
        padding: 0 5%;
        height: 80%;
        margin-bottom: 80px;
        margin-top: 30px;
        align-items: start;
        display: flex;
        flex-direction: column;
        gap: 30px;
        align-items: start;
        text-align: left;   
        overflow: scroll;
        
        .mission-create-category{
            height: 25%;
            width: 100%;
            display: flex;
            flex-direction: column;
            gap: 10px;
            .mission-create-category-title {
                
            }
            .mission-create-category-value{
                width: 40%;
                height: 32px;
                background: $light-yellow;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.07);
                border: none;
                border-radius: 10px;
                padding-left: 10px;
                &::placeholder {
                    color: $light-grey;
                    font-size: $fs-6;
                }
            }
        }
        .mission-create-name{
            height: 25%;
            width: 100%;
            display: flex;
            flex-direction: column;
            gap: 5px;
            .mission-create-name-title {
                
            }
            .mission-create-name-value{
                font-size: 12px;
                width: 100%;
                height: 32px;
                background: $light-yellow;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.07);
                border: none;
                border-radius: 10px;
                padding-left: 10px;
                &::placeholder {
                    color: $light-grey;
                    font-size: $fs-6;
                }
            }
            
        }
        .mission-create-missions{
            width: 100%;
            height: 25%;
            display: flex;
            flex-direction: column;
            gap: 5px;
            .mission-create-missions-title {
                
            }
            .mission-create-missions-value{
                margin-top: 3px;
                font-size: 12px;
                width: 100%;
                height: 32px;
                background: $light-yellow;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.07);
                border: none;
                border-radius: 10px;
                padding-left: 10px;
                &::placeholder {
                    color: $light-grey;
                    font-size: $fs-6;
                }
            }
            
        }
        .mission-create-desc{
            width: 100%;
            height: 25%;
            display: flex;
            flex-direction: column;
            gap: 5px;
            .mission-create-desc-title {
                
            }
            .mission-create-desc-value{
                font-size: 12px;
                width: 100%;
                height: 40px;
                background: $light-yellow;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.07);
                border: none;
                border-radius: 10px;
                padding-left: 10px;
                &::placeholder {
                    color: $light-grey;
                    font-size: $fs-6;
                }
            }
            
        }
    }

    .mission-create-submit{
        margin: 0 auto;
        width: 226px;
        height: 47px;

        background: $main-green;
        box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.45);
        border: none;
        border-radius: 999px;

        font-family: 'Abhaya Libre';
        font-style: normal;
        font-weight: 700;
        line-height: 100%;

        color: white;
    }
  </style>