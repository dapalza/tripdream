<template>
    회원가입
    <v-form ref="form" lazy-validation>
      <v-row>
        <v-col v-for="(setting,i) in form_setting" :key="i" cols="12">
          <h3 
            class="input-title"
            :class="{
              'title-danger':(error_handler.state[i]>0)
              ,'title-no-danger': (error_handler.state[i]<0)
          }">
          {{setting.show_text}}
          </h3>
          <input 
            v-model="setting.values"
            class="input-item"
            :type="setting.input_type"
            :placeholder="setting.placeholder"
            :class="{
              'input-danger': (error_handler.state[i]>0)
              ,'input-no-danger': (error_handler.state[i]<0)
          }"/>
          <p
            v-show="(error_handler.state[i]!=0)"
            :class="{
              'input-error':(error_handler.state[i]>0)
              ,'input-no-error':(error_handler.state[i]<0)
          }">
          {{error_handler.msg[i]}}
          </p>
        </v-col>
        <v-col cols="12">
          <h3 class="input-title">
          키(cm)
          </h3>
          <input 
            v-model="customer.customer_height"
            class="input-item"
            type="text"
            maxlength="3"
          />
        </v-col>
        <v-col cols="12">
          <h3 class="input-title">
          발사이즈(mm)
          </h3>
          <input 
            v-model="customer.customer_feet"
            class="input-item"
            type="text"
            maxlength="3"
          />
        </v-col>
        <v-col cols="12">
          <h3 class="input-title">
          생년월일(6자리)
          </h3>
          <input 
            v-model="customer.customer_dob"
            class="input-item"
            type="text"
            maxlength="6"
          />
        </v-col>
      <v-col>
        <v-btn class="mt-3" color="" @click=btnSubmitMember() outlined v-bind:disabled="btn_state!=15">회원가입</v-btn>
      </v-col>    
    </v-row>

  </v-form>
  <!--
  <div> 성별 :(선택?일경우 남자 여자 비공개)</div>-->
</template>

<script>
import {ref, reactive,watch} from "vue"
import axios from 'axios';
import {useRouter} from 'vue-router'

export default {
  name: "JoinView",
  setup(){
    const state = reactive({   
        statei: 'in',
    })
    //버튼의 활성화를 컨트롤할 변수
    const btn_state = ref(8);

    const gro_url = "http://localhost:8088/";
    const router = useRouter();

    const form_setting = reactive(
      [
        {
          input_type:"text",
          placeholder:"예) exam@exam.ex",
          show_text: "이메일 주소*",
          values: "",
          style: 0,
        },
        {
          input_type:"password",
          placeholder:"영문대소문자,숫자,특수문자로 (8~16자이내)",
          show_text: "비밀번호*",
          values: "",
          style: 0,
        },
        {
          input_type:"password",    
          placeholder:"영문대소문자,숫자,특수문자로 (8~16자이내)",
          show_text: "비밀번호 확인*",
          values: "",
          style: 0,
        },
        {
          input_type:"text",    
          placeholder:"한글,영문,숫자를 입력해주세요.(2-10자)",
          show_text: "닉네임*",
          values: "",
          style: 0,
        },
      ]
    );
        //회원가입시 넘어갈 정보
    const customer = reactive({
      customer_height:"",
      customer_feet:"",
      customer_dob:"",
    });

    const error_handler = reactive({
      state:[0,0,0,0],
      msg:["","","",""]
    });
    let maxState = (1<<error_handler.state.length)-1;
        
    const btnStateController=(idx,swt,msg)=>{
      const state_idx = 1<<idx;
      error_handler.state[idx] = swt;
      error_handler.msg[idx] = msg;
      btn_state.value = swt==1? btn_state.value&(maxState-state_idx):btn_state.value|state_idx;
    };
    const btnSubmitMember=()=>{
      axios.post(gro_url+"join",{
        "customer_email" : form_setting[0].values,
        "customer_pw" : form_setting[1].values,
        "customer_nick" : form_setting[3].values,
        "customer_height":customer.customer_height,
        "customer_feet":customer.customer_feet,
        "customer_dob":customer.customer_dob,
      })
      .then(res =>{
        console.log(res.data);
      }).then(err=>{
        //회원가입 성공
        if(err==null){
          console.log("err is null");
          router.push({name:"login"})
        }
        //회원가입 실패
        else {
          console.log("err : "+err);
        }
      })
    }

        // 유효성 검사
    watch(() =>[form_setting[0].values],()=> {
      const email_rule = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
      if(!form_setting[0].values){
        btnStateController(0,1,"이메일은 필수 입력사항입니다.");
      }
      else if(!email_rule.test(form_setting[0].values)){
        btnStateController(0,1,"이메일 주소를 정확히 입력해주세요.");
      }
      else {
        axios.get(gro_url+"join/email?customer_email="+form_setting[0].values, { withCredentials: true }
        ).then(res => {
          if(!res.data){
            btnStateController(0,1,"사용할 수 없는 이메일입니다.");
          }
          else{
            btnStateController(0,-1,"사용할 수 있는 이메일입니다.");
            return;
          }
        }).then(err => {
          err
        });
      }
    });
    
    watch(() =>[form_setting[1].values],()=> {
      const pw_rule = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
      const swt = !pw_rule.test(form_setting[1].values)||!form_setting[1].values? 1:-1;
      btnStateController(1,swt,"영문대소문자,숫자,특수문자를 조합하여 입력해주세요.(8-16자)");
    });
    
    watch(() =>[form_setting[2].values],()=> {
      const swt = form_setting[2].values != form_setting[1].values? 1: -1;
      btnStateController(2,swt,"비밀번호가 일치하지 않습니다.");
    });
    watch(() =>[form_setting[3].values],()=> {
      const nick_rule = /^([a-zA-z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,9}$/;
      if(form_setting[3].values==""){
        btnStateController(3,-1,"");
        return;
      }
      if(!nick_rule.test(form_setting[3].values)){
        btnStateController(3,1,"한글,영문,숫자를 입력해주세요.(2-10자)");
      }
      else {
        axios.get(gro_url+"join/nick?customer_nick="+form_setting[3].values, { withCredentials: true }
        ).then(res => {
          console.log(res.data);
          const ww = res.data;
          if(ww==1){
            btnStateController(3,1,"사용할 수 없는 닉네임입니다.");
          }
          else{
            btnStateController(3,-1,"사용할 수 있는 닉네임입니다.");
            return;
          }
        }).then(err => {
            err
        });
      }
    });
    watch(() =>[customer.customer_height],()=> {
      return customer.customer_height = customer.customer_height.replace(/[^0-9]/g,'');
    });
    watch(() =>[customer.customer_feet],()=> {
      return customer.customer_feet = customer.customer_feet.replace(/[^0-9]/g,'');
    });

    watch(() =>[customer.customer_dob],()=> {
      return customer.customer_dob = customer.customer_dob.replace(/[^0-9]/g,'');
    });

    return {form_setting,btnStateController,btnSubmitMember,error_handler,btn_state,state,customer};
  },
}
</script>

<style>
.modal {
  position: absolute;
  z-index: 100;
  left:0;
  top:0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0,0,0,0.7);
}

.modal-content{
  position: fixed;
  background-color: #ffffff;
  top:0;
  left:0;
  margin: auto;
  z-index: 200;
  padding: 20px;
  width: 500px;
  border: 1px solid #888;
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
  
.input-no-error {
  line-height: 16px;
  font-size: 11px;
  color: greenyellow;
}
.title-no-danger {
  color: greenyellow;
}
.input-no-danger {
  border-bottom: 1px solid greenyellow !important;
}
</style>