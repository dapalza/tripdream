<template>
    <div class="modal" v-show="isOpen" @click="hide">
    </div>
    <div class="modal-content" v-show="isOpen"> 
        <span class="close" @click="hide">&times;</span>
        회원가입
        <div> 프로필 사진</div>
        <v-form ref="form" lazy-validation>

            <v-row>
                <v-col cols="12">
                    <h3 
                        class="input-title"
                        :class="{'title-danger':hasError.email}">
                    이메일 주소
                    </h3>
                    <input 
                        v-model="member.email"
                        class="input-item"
                        type="text"
                        placeholder="예) exam@exam.ex"
                        :class="{'input-danger': hasError.email}"/>
                    <p
                        v-show="valid.email"
                        class="input-error">
                        {{errorMsg.email}}
                    </p>
                </v-col>
                <v-col cols="12">
                    <h3 
                        class="input-title"
                        :class="{'title-danger':hasError.pw}">
                    비밀번호
                    </h3>
                    <input 
                        v-model="member.pw"
                        class="input-item"
                        type="password"
                        placeholder="영문대소문자,숫자,특수문자로 (8~16자이내)"
                        :class="{'input-danger': hasError.pw}"/>
                    <p
                        v-show="valid.pw"
                        class="input-error">
                        {{errorMsg.pw}}
                    </p>
                </v-col>
                <v-col cols="12">
                    <h3 
                        class="input-title"
                        :class="{'title-danger':hasError.validatePassword}">
                    비밀번호 확인
                    </h3>
                    <input 
                        v-model="validatePassword"
                        class="input-item"
                        type="password"
                        placeholder="영문대소문자,숫자,특수문자로 (8~16자이내)"
                        :class="{'input-danger': hasError.validatePassword}"/>
                    <p
                        v-show="valid.validatePassword"
                        class="input-error">
                        {{errorMsg.validatePassword}}
                    </p>
                </v-col>
                <v-col cols="12">
                    <v-text-field 
                    v-model="member.nickname" 
                    label="닉네임*" 
                    :rules="rules.user_nickname_rule"
                    type="text"
                    required
                    prepend-icon="mdi-account"
                    ></v-text-field>
                </v-col>
                <v-col>
                    <v-btn class="mt-3" color="" outlined v-bind:disabled="btnState!=7">회원가입</v-btn>
                </v-col>    
            </v-row>

        </v-form>
        <!-- <div> 이메일 :(본인 인증?)</div>
        <div> 성별 :(선택?일경우 남자 여자 비공개)</div>
        <div> 키 :(선택?)</div>
        <div> 발 사이즈 :(선택?)</div>
        <div> 연령 혹은 생년월일 :(선택?)</div> -->
    </div>
</template>

<script>
import {ref, reactive,watch} from "vue"
//import {mapState
    // , mapActions,mapMutations
//} from "vuex"
//import {user_ids_rule} from "../util/rules.js"

export default {
    name: "ModalSignUp",
    setup(){
        const state = reactive({   
            statei: 'in',
        })
//        const member = ref({ id: "",pw: "", nickname:"",email:""});
        const validatePassword = ref("");

        const btnState = ref(0);

        const member = reactive({
            id : "",
            pw : "",
            nickname : "",
            email : ""
        });
        const valid = reactive({
            email: false,
            pw: false,
            validatePassword: false,
        });
        const hasError = reactive({
            email: false,
            pw: false,
            validatePassword: false,
        });
        const errorMsg = reactive({
            email: "a",
            pw: "",
            validatePassword: "",
        });
        const isOpen = ref(false);
        const hide = () =>{
            isOpen.value = false;
        };
        const rules = reactive({
            user_pw_check_rule:[
                v =>  /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/.test(v) || '영문대소문자,숫자,특수문자를 조합하여 입력해주세요.(8-16자)',
                v => v === member.pw ||'패스워드가 일치하지 않습니다.'
            ],
            user_id_rule: [
                v => !!v || '아이디는 필수 입력사항입니다.',
                v => /^[a-zA-Z0-9]*$/.test(v) || '아이디는 영문+숫자만 입력 가능합니다.',
                v => !( v && v.length >= 15) || '아이디는 15자 이상 입력할 수 없습니다.'
            ],
            user_pw_rule: [
                v =>  /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/.test(v) || '영문대소문자,숫자,특수문자를 조합하여 입력해주세요.(8-16자)'
            ],
            user_nickname_rule:[
                v => /^([a-zA-z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,9}$/.test(v) || '한글,영문,숫자를 조합하여 입력해주세요.(2-10자)'
            ],
            
        })
        const show = () => {
            isOpen.value = true;
        };
        
        watch(() =>[member.email],()=> {
            const email_rule = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
            if(!member.email){
                errorMsg.email = "이메일은 필수 입력사항입니다.";  
            }
            else if(!email_rule.test(member.email)){
                errorMsg.email = "이메일 주소를 정확히 입력해주세요.";  
            }
            else {
                valid.email = false;
                hasError.email = false;
                btnState.value = btnState.value | 1;
                return;
            }
            btnState.value = btnState.value & 6;
            valid.email = true;
            hasError.email = true;
        });
        
        watch(() =>[member.pw],()=> {
            const pw_rule = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
            if(!pw_rule.test(member.pw)){
                valid.pw = true;
                hasError.pw = true;
                errorMsg.pw = "영문대소문자,숫자,특수문자를 조합하여 입력해주세요.(8-16자)";
                btnState.value = btnState.value & 5;
            }
            else {
                valid.pw = false;
                hasError.pw = false;
                btnState.value = btnState.value | 2;
            }
        });
        
        watch(() =>[validatePassword.value],()=> {
            if(validatePassword.value != member.pw){
                valid.validatePassword = true;
                hasError.validatePassword = true;
                errorMsg.validatePassword = "비밀번호가 일치하지 않습니다.";
                btnState.value = btnState.value & 3;
            }
            else {
                valid.validatePassword = false;
                hasError.validatePassword = false;
                btnState.value = btnState.value | 4;
            }
            console.log(btnState.value);
        });


        return {btnState,rules,errorMsg,valid,hasError,validatePassword,state, isOpen, hide, show,member};
    },
    computed:{
        
    },
    methods:{
        // ...mapActions(['REQUEST_JOIN']),
        // ...mapMutations(['OPEN_MODAL','START_LOADING']),
        // joinRequest(){
        //     if(this.$refs.form.validate()){
        //         this.START_LOADING();
        //         this.REQUEST_JOIN(this.user);
        //     }
        // },
        // async REQUEST_JOIN(context, user){
        //     try {
        //         // const response = await requestJoinUser(user);
        //         const response = await requestJoinUser(user);
        //         // context.commit('OPEN_MODAL',setModalTexts(true));
        //         return response;
        //     }
        //     catch(e){
        //         console.log(e);
        //         // context.commit('OPEN_MODAL',setModalTexts(false));
        //     }
        // }
    }
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

.close{
    padding: 10px;
    cursor: pointer;
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
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
</style>