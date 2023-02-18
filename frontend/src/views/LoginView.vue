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
          v-show=" hasError.customer_pw"
          class="input-error">
          {{errorMsg.customer_pw}}
        </p>
      </v-col>
      <v-col>
        <v-btn class="mt-3" color="black" @click="btnLogin()" outlined v-bind:disabled="btnState!=3">로그인</v-btn>
      </v-col>    
      <ul class="look_box">
        <li class="look_list">
          <a class="look_link" href="/join">
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
  </v-form>
</template>

<script>
import axios from "axios";
import {ref, reactive,watch} from "vue"
import {useRouter} from 'vue-router'
import {useStore} from "vuex"

export default {
name: "LoginView",
setup(){
    const state = reactive({   
        statei: 'in',
    })
    const store = useStore();
    const router = useRouter();
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
    

    const setSession = (token) => store.commit("setAccessToken",token);

    const btnLogin = () =>{
      axios.post("http://localhost:8088/login",{
        "customer_email" : customer.customer_email,
        "customer_pw" : customer.customer_pw,
      }).then(res=>{
        console.log(res.data);
        setSession(res.data.customer_email);
      }).then(err=>{
        if(err==null){
          router.push({name:"main"})
          
        }
        else {
          err;
        }
      })
    };


    watch(() =>[customer.customer_email],()=> {
        const email_rule = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
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
      const pw_rule = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
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
    

  return {btnLogin,btnState,errorMsg,hasError,state,customer};
},
}
</script>

<style>
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
.login_area{
    margin:0 auto;
    padding: 60px 0 160px;
    width:400px;
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
</style>