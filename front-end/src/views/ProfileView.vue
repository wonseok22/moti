<template>
  <div class="profile-main" v-if="profile">
    <!-- 프로필 페이지 최상단 유저이름과 메뉴바 -->
    <div class="profile-header">
      <div class="profile-nickname">
        <img
          v-if="profile.achievementImageUrl"
          :src="profile.achievementImageUrl"
          alt="대표업적 뱃지"
          class="profile-achieve"
        />

        <p>{{ profile.nickname }}</p>
        <button v-if="!isMyProfile && !isFollow" class="follow" @click="follow">팔로우</button>
        <button v-if="!isMyProfile && isFollow" class="unfollow" @click="unfollow">
          팔로우 취소
        </button>
      </div>
      <!-- <img :src=""></img> -->

      <button class="material-icons-outlined" @click="menu">menu</button>
    </div>

    <div class="profile-info">
      <div class="profile-info-title">
        <div class="profile-img-wrap">
          <img :src="profileImageUrl" alt="프로필 사진" />
        </div>
        <div>
          <div>{{ profile.playlistCompleteCnt }}</div>
          <div>키운식물</div>
        </div>
        <div @click="openFollowModal">
          <div>{{ profile.following }}</div>
          <div>팔로우</div>
        </div>
        <div @click="openFollowerModal">
          <div>{{ profile.follower }}</div>
          <div>팔로워</div>
        </div>
      </div>
      <div class="profile-info-desc">
        {{ profile.userDesc }}
      </div>
    </div>

    <div class="profile-menu">
      <nav id="CategoryNav">
        <div class="profile-feed" @click="feed()">
          <span class="material-symbols-outlined"> feed </span>
        </div>
        <div class="profile-pl" @click="playlist()">
          <span class="material-symbols-outlined"> nature </span>
        </div>
        <div class="profile-achive" @click="achive()">
          <span class="material-symbols-outlined"> workspace_premium </span>
        </div>
      </nav>
      <div class="profile-underbar">
        <div id="bar" class="bar1"></div>
      </div>
    </div>

    <div class="profile-detail">
      <div class="profile-detail-slide">
        <SearchUserId :keyword="`${profile.userId}`"  
        @deleteFeed="deleteFeed"></SearchUserId>
        <SearchMyPl :keyword="`${profile.userId}`" @openModalPl="openModalPl"></SearchMyPl>
        <SearchAchieve :keyword="`${profile.userId}`" @openModal="openModal"></SearchAchieve>
      </div>
    </div>
    <NavigationBar></NavigationBar>

    <div class="modal" v-if="modal">
      <div class="white-bg">
        <h3>{{ achievement.achievementName }}</h3>
        <img :src="achievement.achievementImageUrl" alt="업적 이미지" />
        <p>{{ achievement.achievementDesc }}</p>
        <button
          v-if="
            achievement.achieved &&
            isMyProfile &&
            achievement.achievementImageUrl === profile.achievementImageUrl
          "
          @click="deleteAchieve()"
          class="modal-btn"
          style="margin-right: 5px"
        >
          대표뱃지 삭제
        </button>
        <button
          v-if="
            achievement.achieved &&
            isMyProfile &&
            achievement.achievementImageUrl !== profile.achievementImageUrl
          "
          @click="registAchieve(achievement.achievementId)"
          class="modal-btn"
          style="margin-right: 5px"
        >
          대표뱃지 등록
        </button>
        <button @click="modal = false" class="modal-close">X</button>
      </div>
    </div>

    <div class="pl-modal" v-if="plModal">
      <div class="pl-white-bg">
        <div class="pl-img-wrap">
          <img
            class="pl-img"
            :src="pl.playlist.flower.flowerImageUrl"
            alt="플레이리스트 아이콘"
            :class="pl.done < 5 ? 'grayscale' : ''"
          />
        </div>
        <div class="pl-title">{{ pl.playlist.playlistName }}</div>
        <div class="pl-desc">{{ pl.playlist.playlistDesc }}</div>
        <div class="pl-date">시작날짜 : {{ pl.startDate }}</div>
        <div class="pl-icon">
          <img
            src="@/assets/images/1_soil.png"
            alt="진행도 이미지"
            :class="pl.done < 1 ? 'grayscale' : ''"
          />
          <img
            src="@/assets/images/2_watering_can.png"
            alt="진행도 이미지"
            :class="pl.done < 2 ? 'grayscale' : ''"
          />
          <img
            src="@/assets/images/3_sprout.png"
            alt="진행도 이미지"
            :class="pl.done < 3 ? 'grayscale' : ''"
          />
          <img
            src="@/assets/images/4_sun.png"
            alt="진행도 이미지"
            :class="pl.done < 4 ? 'grayscale' : ''"
          />
          <img
            :src="pl.playlist.flower.flowerImageUrl"
            alt="진행도 이미지"
            :class="pl.done < 5 ? 'grayscale' : ''"
          />
        </div>
        <button
          @click="openFeedMyRecord(pl.userPlaylistId, pl.playlist.flower.flowerImageUrl)"
          class="pl-btn"
          v-if="pl.done > 4 || !pl.isDone"
        >
          발자취 보기
        </button>
        <button
          @click="plRetry(pl.userPlaylistId)"
          class="pl-btn"
          v-if="pl.done < 5 && pl.isDone && isMyProfile"
        >
          재도전
        </button>
        <button @click="plModal = false" class="modal-close">X</button>
      </div>
    </div>

    <div class="menu-modal" v-if="menuModal" @click="menuModal = false">
      <div class="menu-white-bg">
        <ul>
          <li @click="moveProfileModify">
            <div class="menu-items">
              <span class="material-symbols-outlined"> person </span>
              <div>프로필 설정</div>
            </div>
          </li>
          <li @click="moveUserModify" v-if="this.type == 'default'">
            <div class="menu-items">
              <span class="material-symbols-outlined"> settings_suggest </span>
              <div>비밀번호 변경</div>
            </div>
          </li>
          <li @click="moveCreateMission">
            <div class="menu-items">
              <span class="material-symbols-outlined">
              inventory_2
              </span>
              <div>성장플랜 건의함</div>
            </div>
          </li>
          <li @click="logout">
            <div class="menu-items">
              <span class="material-symbols-outlined"> settings_power </span>
              <div>로그아웃</div>
            </div>
          </li>
          <li @click="deleteUser">
            <div class="menu-items bottom">
              <span class="material-symbols-outlined"> logout </span>
              <div>회원탈퇴</div>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <div class="follow-modal" v-show="followModal">
      <!-- <div class="follow-modal" > -->
      <div class="follow-modal-close" @click="followModal = false"></div>
      <div class="follow-white-bg">
        <div class="follow-menu">
          <nav id="CategoryNav">
            <div class="following" @click="moveFollowing()">팔로우</div>
            <div class="follower" @click="moveFollower()">팔로워</div>
          </nav>
          <div class="follow-underbar">
            <div id="followBar" class="bar1"></div>
          </div>
        </div>

        <div class="follow-detail">
          <div class="follow-detail-slide">
            <FollowingList :key="followKey" :keyword="`${profile.userId}`" @click-follow="clickFollow"></FollowingList>
            <FollowerList :key="followerKey" :keyword="`${profile.userId}`" @click-follower="clickFollower"></FollowerList>
          </div>
        </div>
      </div>
    </div>

    <div v-if="isCommentClicked" class="comment-page">
      <FeedComment />
    </div>

    <div v-if="recordView" class="comment-page">
      <FeedMyRecord
        :feeds="myRecord"
        :flowerImageUrl="flowerImageUrl"
        @closeRecordModal="closeRecordModal"
      ></FeedMyRecord>
    </div>
    <div class="feed-delete-modal"
      v-show="isDelete">
        <div class="feed-delete-modal-body">
          <p> 해당 피드를 정말로 삭제하시겠습니까?</p>
          <div>
            <button @click="finalNo">취소</button>
            <button @click="finalOk">삭제</button>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import SearchUserId from "@/components/SearchUserId.vue";
import SearchMyPl from "@/components/SearchMyPl.vue";
import SearchAchieve from "@/components/SearchAchieve.vue";
import FollowerList from "@/components/FollowerList.vue";
import FollowingList from "@/components/FollowingList.vue";
import FeedComment from "@/components/FeedComment.vue";
import FeedMyRecord from "@/components/FeedMyRecord.vue";

export default {
  name: "ProfileView",
  data() {
    return {
      followKey : 0,
      followerKey: -1,
      profile:null,
      isMyProfile:false,
      profileImageUrl:require(`@/assets/images/default_profile.jpg`),
      isFollow:false,
      modal: false,
      menuModal:false,
      plModal :false,
      followModal:false,
      pl:null,
      isDelete: false,
      deleteId: null,
      recordView: false,
      myRecord: null,
      flowerImageUrl: null,
    }
  },
  components: {
    SearchUserId,
    SearchMyPl,
    SearchAchieve,
    FollowerList,
    FollowingList,
    FeedComment,
    FeedMyRecord,
  },
  created() {
    this.$axios({
      method: "get",
      url: `${this.$baseUrl}/profile?userId=${this.$store.state.profileTargetId}`,
    })
      .then((response) => {
        if (response.status == 202) {
          alert("202 응답");
        }
        this.profile = response.data.profile;
        if (this.profile.profileImageUrl) {
          this.profileImageUrl = this.profile.profileImageUrl;
        }
        if (this.profile.userId === this.$store.state.id) {
          this.isMyProfile = true;
        } else {
          this.isFollow = this.isFollowing();
        }
        // 내가 아닌 경우 팔로우인지 팔로우 취소인지 체크하는 요청
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    clickFollow(adding){
      this.followerKey --;
      this.profile.following += adding;
    },
    clickFollower(adding){
      this.followKey ++;
      this.profile.following += adding;
    },
    moveCreateMission() {
      this.$router.push({
        name: "MissionCreateView",
      });
    },
    logout() {
      this.$store.dispatch("logout");
    },
    deleteUser() {
      if (confirm("정말로 탈퇴하시겠습니까??")) {
        this.$axios({
          method: "delete",
          headers: {
            "access-token": this.$store.state.accessToken,
          },
          url: `${this.$baseUrl}/users/${this.$store.state.id}`,
        })
          .then((response) => {
            if (response.status == 200) {
              alert("정상적으로 탈퇴되었습니다. moti를 이용해 주셔서 감사합니다.");
              this.$store.commit("LOGOUT");
              this.$router
                .push({
                  name: "landing",
                })
                .catch(() => {});
            } else {
              alert("회원탈퇴 실패. 재로그인 후 다시 시도하세요");
              this.$router
                .push({
                  name: "landing",
                })
                .catch(() => {});
            }
          })
          .catch((error) => {
            if (error.response.state == 401) {
              this.$store.dispatch("tokenRegeneration");
            }
          });
      }
    },
    feed() {
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".profile-detail-slide");
      slide.style.left = 0;
      bar.className = "bar1";
    },
    playlist() {
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".profile-detail-slide");
      slide.style.left = "-100vw";
      bar.className = "bar2";
    },
    achive() {
      const bar = document.getElementById("bar");
      const slide = document.querySelector(".profile-detail-slide");
      slide.style.left = "-200vw";
      bar.className = "bar3";
    },
    isFollowing() {
      const targetId = this.profile.userId;
      const loginId = this.$store.state.id;
      this.$axios({
        method: "get",
        url: `${this.$baseUrl}/profile/follow/check/${loginId}/${targetId}`,
      })
        .then((response) => {
          if (response.data.message === "success") {
            this.isFollow = response.data.check;
          }
        })
        .catch((error) => {
          console.log(error);
        });
      return false;
    },
    follow() {
      this.$axios({
        method: "get",
        url: `${this.$baseUrl}/profile/follow/${this.$store.state.id}/${this.profile.userId}?type=follow`,
      })
        .then((response) => {
          if (response.data.message === "success") {
            this.profile.follower += 1;
            this.isFollow = true;
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    plRetry(userPlaylistId) {
      if (confirm("정말로 재도전 하시겠습니까?")) {
        this.$axios({
          method: "put",
          url: `${this.$baseUrl}/playlist/${userPlaylistId}`,
        })
          .then(() => {
            // console.log(response)
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    unfollow() {
      this.$axios({
        method: "get",
        url: `${this.$baseUrl}/profile/follow/${this.$store.state.id}/${this.profile.userId}?type=unfollow`,
      })
        .then((response) => {
          if (response.data.message === "success") {
            this.profile.follower -= 1;
            this.isFollow = false;
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    openModal(achievement) {
      this.achievement = achievement;
      this.modal = true;
    },
    openModalPl(pl) {
      this.pl = pl;
      this.plModal = true;
    },
    closeRecordModal() {
      this.recordView = false;
    },
    menu() {
      this.menuModal = true;
    },
    moveProfileModify() {
      this.$router.push({
        name: "profileModifyView",
      });
    },
    moveUserModify() {
      this.$router.push({
        name: "userModifyView",
      });
    },
    moveFollower() {
      const bar = document.getElementById("followBar");
      const slide = document.querySelector(".follow-detail-slide");

      slide.style.left = "-100vw";
      bar.className = "bar2";
    },
    moveFollowing() {
      const bar = document.getElementById("followBar");
      const slide = document.querySelector(".follow-detail-slide");

      slide.style.left = 0;
      bar.className = "bar1";
    },
    registAchieve(achievementId) {
      this.$axios({
        method: "put",
        url: `${this.$baseUrl}/achievement`,
        data: {
          userId: this.$store.state.id,
          achievementId: achievementId,
        },
      })
        .then(() => {
          alert("대표뱃지가 등록되었습니다.");
          location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteAchieve() {
      this.$axios({
        method: "put",
        url: `${this.$baseUrl}/achievement`,
        data: {
          userId: this.$store.state.id,
          achievementId: 0,
        },
      })
        .then(() => {
          alert("대표뱃지가 삭제되었습니다.");
          location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteFeed(feedId) {
      document.body.classList.add("stop-scroll")
      this.isDelete = true
      this.deleteId = feedId
    },
    finalOk() {
      this.$store.dispatch("feedDelete", this.deleteId)
      document.body.classList.remove("stop-scroll")
      // for(let i=0; i<this.feeds.length;i++){
      //   if(this.feeds[i].feedId === this.deleteId){
      //     this.feeds.splice(i,1)
      //   }
      // }
      this.isDelete = false
      window.location.reload()
    },
    finalNo() {
      document.body.classList.remove("stop-scroll")
      this.isDelete = false
    },
    moveProfile(targetId) {
      this.$store.commit("UPDATE_PROFILE_TARGET_ID", targetId);
      this.$router
        .push({
          name: "profile",
        })
        .catch(() => {
          location.reload();
        });
      },
    openFeedMyRecord(userPlaylistId, flowerImageUrl) {
      this.recordView = true;
      this.plModal = false;
      this.$axios({
        method: "get",
        url: `${this.$baseUrl}/feed/search/${this.$store.state.targetUd}/${userPlaylistId}`,
      })
        .then((response) => {
          if (response.data.message === "success") {
            this.myRecord = response.data.feeds;
            this.flowerImageUrl = flowerImageUrl;
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    openFollowModal() {
      this.followModal = true
      this.moveFollowing()
      
    },
    openFollowerModal() {
      this.followModal = true
      this.moveFollower()
      
    },
  },
  
  computed: {
    ...mapState(["type"]),
    isCommentClicked() {
      return this.$store.getters.isCommentClicked;
    },
  },
};
</script>

<style lang="scss">
.modal {
  position: fixed;
  background: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  .white-bg {
    position: relative;
    padding-top: 3px;
    padding-bottom: 10px;
    border-radius: 20px;
    background-color: #fff;
    width: 300px;
    height: 250px;
    margin: 300px auto;
    img {
      width: 70px;
      height: 70px;
    }
    .modal-close {
      position: absolute;
      top: 10px;
      left: 85%;
      font-size: 20px;
      background-color: #ffffff;
      font-weight: bold;
      color: rgb(0, 0, 0);
      border-radius: 7px;
      padding: 8px 8px;
      border: 0 solid #000;
    }
  }
  .modal-btn {
    background-color: $main-green;
    font-weight: bold;
    color: white;
    border-radius: 7px;
    padding: 8px 15px;
    border: 0 solid #000;
    bottom: 20px;
    margin: 0 auto;
  }
}

.menu-modal {
  position: fixed;
  background: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  .menu-white-bg {
    animation-name: menuSlidein;
    animation-fill-mode: forwards;
    animation-duration: 0.7s;
    position: fixed;
    // border-radius: 15px 15px 0 0;
    background-color: #fff;
    width: 100%;
    height: 100%;
    // left:100%;
    ul {
      text-align: left;
      text-decoration: none;
      list-style: none;
      padding: 0 0 0 0;
      li {
        .bottom {
          color: red;
          position: absolute;
          bottom: 0;
        }
        .menu-items {
          line-height: 1.8;
          font-size: 16px;
          font-weight: bold;
          margin: 20px 0;
          padding: 0 5px;
          display: flex;
          span {
            margin: 0 15px;
          }
        }
      }
    }
  }
  @keyframes menuSlidein {
    from {
      left: 100%;
    }

    to {
      left: 50%;
    }
  }
}

.follow-modal {
  position: fixed;
  background: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  .follow-modal-close {
    width: 100%;
    height: 18%;
  }
  .follow-white-bg {
    animation-name: followSlidein;
    animation-fill-mode: forwards;
    animation-duration: 0.3s;
    position: fixed;
    border-radius: 15px 15px 0 0;
    background-color: #fff;
    width: 100%;
    height: 80%;
    margin: 0 auto;
  }
  @keyframes followSlidein {
    from {
      top: 100%;
    }

    to {
      top: 20%;
    }
  }
}

.result-box {
  padding-left: 30px;
  .follow-info {
    height: 50px;
    margin-bottom: 10px;
    display: flex;
    .follow-info-img-wrap {
      width: 50px;
      height: 50px;
      border-radius: 100%;
      overflow: hidden;
      .follow-info-image {
        width: 50px;
        height: 50px;
      }
    }
    .follow-info-nickname {
      padding-left: 10px;
      font-size: 16px;
      font-weight: bold;
      line-height: 2.8;
    }
  }
}

.pl-modal {
  position: fixed;
  background: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  .pl-white-bg {
    position: relative;
    padding-top: 3px;
    padding-bottom: 10px;
    border-radius: 20px;
    background-color: #fff;
    width: 300px;
    height: 380px;
    margin: 200px auto;
    img {
      width: 70px;
      height: 70px;
    }
    .modal-close {
      position: absolute;
      top: 10px;
      left: 85%;
      font-size: 20px;
      background-color: #ffffff;
      font-weight: bold;
      color: rgb(0, 0, 0);
      border-radius: 7px;
      padding: 8px 8px;
      border: 0 solid #000;
    }
    .pl-img-wrap {
      margin-top: 20px;
    }
    .pl-title {
      font-size: 24px;
      font-weight: bold;
      margin: 10px auto;
    }
    .pl-desc {
      height: 70px;
      width: 80%;
      margin: 10px auto;
      font-size: 14px;
      font-family: "LINESeedKR-Rg";
    }
    .pl-date {
      margin: 10px auto;
      font-size: 14px;
    }
    .pl-icon {
      width: 75%;
      margin: 20px auto;
      justify-content: space-between;
      display: flex;
      img {
        width: 40px;
        height: 40px;
        .grayscale {
          filter: grayscale(100%);
        }
      }
    }
    .pl-btn {
      background-color: $main-green;
      font-weight: bold;
      color: white;
      border-radius: 7px;
      padding: 8px 15px;
      border: 0 solid #000;
      bottom: 20px;
      margin: 0 auto;
    }
  }
}
</style>
