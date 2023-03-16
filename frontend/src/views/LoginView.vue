<template>
  <v-form class="login_area" ref="form" lazy-validation>
    <v-row>
      <v-col cols="12">
        <h3 
          class="input-title"
          :class="{'title-danger':hasError.customer_email}">
          이메일 주소
        </h3>
        <input 
          v-model="customer.customer_email"
          class="input-item"
          type="text"
          placeholder="예) exam@exam.ex"
          :class="{'input-danger': hasError.customer_email}"/>
        <p
          v-show="hasError.customer_email"
          class="input-error">
          {{errorMsg.customer_email}}
        </p>
      </v-col>
      <v-col cols="12">
        <h3 
          class="input-title"
          :class="{'title-danger':hasError.customer_pw}">
          비밀번호
        </h3>
        <input 
          v-model="customer.customer_pw"
          class="input-item"
          type="password"
          placeholder=""
          :class="{'input-danger': hasError.customer_pw}"/>
        <p
          v-show="hasError.customer_pw"
          class="input-error">
          {{errorMsg.customer_pw}}
        </p>
      </v-col>
      <v-col>
        <v-btn class="mt-3" color="black" @click="btnLogin()" outlined v-bind:disabled="btnState!=3">로그인</v-btn>
      </v-col>
      <v-col>
        <div v-show="state.loginFail">
          <p>
            아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다.
          </p>
          <p>
            입력하신 내용을 다시 확인해주세요.    
          </p>
        </div>
      </v-col>
      <ul class="look_box">
        <li class="look_list">
          <a class="look_link" @click="join()">
            이메일 가입
          </a>
        </li>
        
        <li class="look_list">
          <a class="look_link" >
            이메일 찾기
          </a>
        </li>
        
        <li class="look_list">
          <a class="look_link" >
            비밀번호 찾기
          </a>
        </li>
      </ul>
    </v-row>
    <div class="social-login">
      <button type="button" class="btn btn-login-naver full outline">
        <img :src="require(`@/assets/naver-btn.png`)"/>
        네이버 로그인
      </button>
      <button type="button" class="btn btn-login-kakao full outline"
      @click="openLoginPop(`/kakao-pop`)">
        <img :src="require(`@/assets/kakao-btn.png`)"/>
        카카오 로그인
      </button>
      <SocialLoginPop ref="winPopup"></SocialLoginPop>
      
    </div>
  </v-form>
</template>

<script>
import SocialLoginPop from "../area/WinPop.vue";
import { useRouter } from "vue-router";
import {ref, reactive,watch,getCurrentInstance,computed} from "vue"
// import {useRouter} from 'vue-router'
// import {useStore} from "vuex"

export default {
  name: "LoginView",
  components:{
    SocialLoginPop
  },
  setup(){
    const winPopup = ref();
    
    const openLoginPop = (uri)=>{
      winPopup.value.openWinPop( uri , 800, 700 );
    };

    const state = reactive({   
        statei: 'in',
        loginFail: computed(()=>proxy.$store.getters["getLoginFail"]),
    })
    const btnState = ref(0);
    
    const customer = reactive({
      customer_pw : "",
      customer_email : ""
    });
    const hasError = reactive({
      customer_email: false,
      customer_pw: false,
    });
    const errorMsg = reactive({
      customer_email: "",
      customer_pw: "",
    });
    
    const {proxy} = getCurrentInstance();
    const email_rule = proxy.$getRule("email");
    const pw_rule = proxy.$getRule("password");
    // const setLoginFail = (token) => proxy.$store.commit("setLoginFail",token);
    const router = useRouter();
    const join=()=>{
      router.replace('/join');
    };
    const btnLogin = () =>{
      proxy.$store.dispatch(
        "tokenCookies/login",
        {
          customer : customer,
          url:proxy.$getUrl(),
          // tokenTime : proxy.$getTokenTime(),
        }
      ).then(
        res=>{
        //  proxy.$store.commit("customer/saveStateToStorage",res);
          console.log(res);
          const userInfo ={
            value : res,
            expire : Date.now() + proxy.$getTokenTime(),
          }
          proxy.$setLocalStorage("customer", JSON.stringify(userInfo));
          proxy.$store.commit("setLoginFail","");
          proxy.$setLocalStorage("loginRefresh", "true");
          router.go(-1);
          customer.customer_email="";
        }
      ).catch(
        err =>{
          customer.customer_pw="";
          console.log("??"+err);
          proxy.$store.commit("setLoginFail",true);
        }
      );
    };
    watch(() =>[customer.customer_email],()=> {
      if(!customer.customer_email){
        errorMsg.customer_email = "이메일은 필수 입력사항입니다.";  
      }
      else if(!email_rule.test(customer.customer_email)){
        errorMsg.customer_email = "이메일 주소를 정확히 입력해주세요.";  
      }
      else {
        hasError.customer_email = false;
        btnState.value = btnState.value | 1;
        return;
      }
      btnState.value = btnState.value & 2;
      hasError.customer_email = true;
    });
    
    watch(() =>[customer.customer_pw],()=> {
      if(!pw_rule.test(customer.customer_pw)){
        hasError.customer_pw = true;
        errorMsg.customer_pw = "영문대소문자,숫자,특수문자를 조합하여 입력해주세요.(8-16자)";
        btnState.value = btnState.value & 1;
      }
      else {
        hasError.customer_pw = false;
        btnState.value = btnState.value | 2;
      }
    });
    

    return {
      openLoginPop,winPopup,join,btnLogin,btnState,errorMsg,hasError,state,customer};
  },
}
</script>

<style>
.login_area{
    margin:0 auto;
    padding: 60px 0 160px;
    width:400px;
}
.mt-3{
    width:400px;
    height: 52px;
    border-radius: 12px;
    font-size: 16px;
}
.look_box{
    width:400px;
    margin-top:20px;
    display: flex;
    justify-content: space-evenly;
    list-style: none;
}
.look_list{
    display:inline-flex;
    -webkit-box-align: start;
    align-items: flex-start;
    -webkit-box-flex: 1;
    flex:1;
}
.look_link{
    margin:auto;
    padding: 0 10px;
    display:inline-flex;
    font-size: 13px;
    letter-spacing: -.07px;
}
.input-item{
    width: 400px;
    border-bottom: 1px solid #ebebeb;
}

.input-error {
line-height: 16px;
font-size: 11px;
color: red;
}
.title-danger {
color: red;
}
.input-danger {
border-bottom: 1px solid red !important;
}
.input-title{
    font-size:13px;
    letter-spacing: -.07px;
    line-height: 18px;
}

.look_list+.look_list:before {
    content: "";
    margin-top: 3px;
    display: inline-block;
    width: 1px;
    height: 13px;
    background-color: #d3d3d3;
}



.social-login {
    margin-top: 40px;
}
.social-login>button>img {
  width:35px;
  height: 35px;

}

.btn{
  display: -webkit-inline-box;
  display: inline-flex;
  cursor: pointer;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  vertical-align: middle;
  text-align: center;
  color: rgba(34,34,34,.8);
  background-color: #fff;
}

.full{
  width: 100%;
  font-size: 16px;
  letter-spacing: -.16px;
  font-weight: 700;
  height: 52px;
  border-radius: 12px;
}
.outline{
  border: 1px solid #d3d3d3;
}
.btn.btn-login-kakao, .btn.btn-login-naver {
  position: relative;
  border-color: #ebebeb;
}
.social-login>.btn {
  margin-bottom: 8px;
}
.btn-login-kakao>img{
  padding: 5px;
}
.btn-login-kakao{
  background-color: #FEE500;
  color: #222;
}
.btn-login-naver {
  background-color: #03C75A;
  color: #FFF;
}
</style>