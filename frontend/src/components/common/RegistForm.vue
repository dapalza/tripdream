<template>
    <div class="regist-title" style="width: 100%;">

        <div class="common-border regist-title">
            <div class="">
                회원가입 신청서
            </div>
        </div>
    </div>
    <div class="regist-container">
        <div style="width: 278px; height:274px;" class="common-border">
            <div class="common-border" style="width:175px; height: 225px;
    margin: 0 auto;">
            </div>
            <div style="display:flex; justify-content: center;">
                <v-btn>사진 등록</v-btn>
            </div>
        </div>
        <v-form class="regist-form" ref="form">
            <div>
                <div class="regist-lable">*이메일</div>
                <input class="regist-input"
                        v-model="customer.email"/>
            </div>
            <div>
                <div class="regist-lable">*비밀번호</div>
                <input class="regist-input"
                    type="password"
                v-model="customer.password"/>
            </div>
            <div>
                <div class="regist-lable">*비밀번호 확인</div>
                <input class="regist-input"
                    type="password"
                v-model="checkPassword"/>
            </div>
            <div>
                <div class="regist-lable">*닉네임</div>
                <input class="regist-input"
                v-model="customer.nick"/>
            </div>
            <div>
                <div class="regist-lable">생일</div>
                <div class="regist-input">
                    <Datepicker
                        style="width:100%;"
                        v-model="choose.birth"
                        :locale="locale"
                    />
                </div>
            </div>
            <div>
                <div class="regist-lable">성별
                </div>
                <select v-model="choose.gender" class="regist-input">
                    <option v-for="gen in gender" :key="gen.value" :value="gen.value">{{ gen.text }}</option>
                </select>
            </div>
            <div class="agreement">
                <div>
                    <input type="checkbox" v-model="checkbox.a">
                    *TripDream 이용약관 동의(필수)
                    <a>약관확인</a>
                </div>
            </div>
            <div class="agreement">
                <div>
                    <input type="checkbox" v-model="checkbox.b">
                    프로모션 정보 수신 동의(선택)
                    <a>약관확인</a>
                </div>
            </div>
            <div class="agreement">
                <div>
                    <input type="checkbox" v-model="checkbox.c">
                    개인정보 수집 동의(선택)
                    <a>약관확인</a>
                </div>
            </div>
            <div class="agreement">
                <div style="display:flex; justify-content: space-between;">
                    <a>
                        *이 있는 항목은 반드시 입력하셔야합니다.
                    </a>
                    <v-btn @click="Submit()">회원가입</v-btn>
                </div>
            </div>
        </v-form>
    </div>
</template>

<script setup>
import {ref, reactive
,defineEmits
} from "vue"
// import {useRouter} from "vue-router"
import Datepicker from 'vue3-datepicker';
import { ko } from 'date-fns/locale';

const locale=reactive(ko);
const form = ref();
// const router = useRouter();
const customer = reactive({
    email: "",
    password: "",
    nick:"",
});
const choose = reactive({
    gender:"",
    birth:new Date(),
});
const checkbox = reactive({
    a:false,
    b:false,
    c:false
});
const gender = ref([
    {
        value:0,
        text:"Secret",
    },{
        value:1,
        text:"Male",
    },{
        value:2,
        text:"Female",
    }
]);
const emit = defineEmits(['pushRegist']);
var Submit = () =>{
    var userInfo = ref({
        customer : customer,
        choose: choose,
        checkbox: checkbox,
    });
    emit('pushRegist',userInfo);

}
const checkPassword = ref("");
// const doRegist = () =>{
//     console.log(customer);
// }

</script>

<style scoped>
.regist-form > div{
    display: flex;
}
.regist-form{
    border:1px solid black;
}
.regist-container{
    display: flex;
    flex-wrap: wrap;
    justify-content:center;
}
.common-border{
    border: 1px solid black;
}
.regist-form > div> * {
    border: 1px solid black;
}
.agreement{
    width: 278px;
}
.agreement > div> a{
    font-size:8pt;
}
.agreement>div{
    width: 100%;
}
.regist-form>div>div{
    font-size: 14px;
}
.regist-lable{
    width:110px;margin:0;
}
.regist-input{
    width:168px;
}
@media (max-width:558px) {
    .regist-title{
        width: 278px;
        display:flex;
        justify-content: center;
    }
}
@media (min-width:558px) {
    .regist-title{
            width: 558px;
            display:flex;
            justify-content: center;
    }
}
</style>