<template>
  <div class="head-top">
    <ul>
      <li>
        <a @click="test()">고객센터</a>
      </li>
      <li>
        <a>관심상품</a>
      </li>
      <li>
        <a v-if="!state.loginState" href="login">로그인</a>
        <a v-if="state.loginState" @click="logout()">로그아웃</a>
      </li>
    </ul>
  </div>
  <div class="head-bottom">
    <img :src = "require(`@/assets/logo-imso.png`)"/>

    <!--  Dandy's Pale Zapping 멋쟁이의 옅은 재핑 
    재핑은 광고를 넘기기 위해 채널을 재빠르게 바꾸는 행위로
    멋쟁이들의 쇼핑을 채널 바꾸듯 쉽게쉽게 쇼핑을 할 수 있게 해주는
    것으로 목적을 잡기 좋을 거 같다???
    Dandy's Palatability Zapping
    구미당기는? 
    -->
    <div>
      menu 예정
    </div>
  </div>

  <div>
    <v-btn depressed color="primary" elevation="2" outlined @click="showModal">Mign Up</v-btn>
  </div>
  <teleport to="#destination">
      <!-- 자식 엘리먼트 접근 -->
    <ModalSignUp ref="modal">
      modal content
    </ModalSignUp>
  </teleport>
</template>

<script>
import ModalSignUp from "../modal/modal-signup.vue";
import { ref,reactive,getCurrentInstance,computed } from "vue";
import {useRouter} from 'vue-router'
export default {
  name: "CommonHead",
  components: {
    ModalSignUp
  },
  setup() {
    const {proxy} = getCurrentInstance();
    const test = () =>{
      console.log(computed(()=>proxy.$store.getters["customer/getCustomerNo"]))
    };
    const router = useRouter();
    const logout = () =>{
      proxy.$store.commit("tokenCookies/reset");
      router.go(0);
    };
    const needLogin = computed(()=>{
      proxy.$store.commit("tokenCookies/readStateFromStorage");
      if(proxy.$store.getters["tokenCookies/getAccessToken"]!=null)
        return true;
      else if(proxy.$store.getters["tokenCookies/getRefreshToken"]!=null){
        console.log("accessToken is null. but refreshToken is not null");
        proxy.$store.commit("tokenCookies/setAccessTokenRe",{
          token:"imsi-token",
          tokenTime:proxy.$getTokenTime(),
        });
        return true;
      }
      console.log("All token is dead");
      return false;
    });
    const state = reactive({
      loginState : needLogin,
    })
    const modal = ref(null);
    function showModal() {
      // VMmodal.vue에 접근하여 show 함수 실행
      modal.value.show();
    }
    return {
      modal,
      showModal,
      state,
      test,
      logout,
      needLogin
    };
  }
}
</script>

<style>
.head-top{
  display: flex;
  padding: 8px 40px 0px;
  height: 22px;
}
.head-top>ul{
  margin: 0px 0px 0px auto;
  display:flex;
  align-items:center;
  list-style:none;
  margin-left: auto;
  -webkit-box-align: center;
}
.head-top>ul>li{
  margin-left: 24px;
}
.head-top>ul>li>a{
  font-size:12px;
}
.head-bottom{
  display:flex;
  justify-content: space-between;
}
.head-bottom>img{
  
  height: 100px;
}
</style>