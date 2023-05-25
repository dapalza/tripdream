<template>
    <div class="login-container">
        <v-form class="login-form" ref="form">
            <v-row>
                <!-- <v-col>
                    <p>email</p>
                    <input class="input-item" v-model="customer.email" type="text"/>
                </v-col> -->
                <div style="width:270px; margin:10px 15px">
                    <v-text-field
                    label="Email"
                    hide-details="auto"
                    v-model="customer.email"
                    variant="outlined"
                    ></v-text-field>
                </div>
                <div style="width:270px; margin:0px 15px">
                    <v-text-field
                    label="Password"
                    hide-details="auto"
                    v-model="customer.password"
                    variant="outlined"
                    type="password"
                    ></v-text-field>
                    <!-- <v-col>
                        <p>password</p>
                        <input class="input-item" v-model="customer.password" type="password"/>
                    </v-col> -->
                </div>
            </v-row>
            <v-row class="login-botten-area">
                <v-btn style="background-color: #f7d8d5;" @click="doLogin()">로그인</v-btn>
                <v-btn style="background-color: #fff3c8;" @click="goRegist()">회원가입</v-btn>
                <v-btn style="background-color: #d1eff0;">이메일/비밀번호 찾기</v-btn>
            </v-row>
            <v-row class="socal-login-btn-area">
                <div class="socal-login-btn" @click="goKakaoLogin()">
                    <img :src="require('../../assets/common/social/kakaoLogin.png')"/>
                </div>
                <div class="socal-login-btn" @click="goGoogleLogin()">
                <img
                :src="require('../../assets/common/social/googleLogin.png')"
                />
                </div>
                <div style="height: 50px; width: 200px; margin: 0 auto;
    border-radius: 5px;display: flex; background-color: #03C75A;" @click="goNaverLogin()">
                    <img
                    :src="require('../../assets/common/social/naverIcon.png')"
                    class = "social-naver-btn"/>
                    <div style="height:50px;line-height: 50px; color:white; width:100%;text-align: center;">
                        네이버 로그인
                    </div>
                </div>
<!--            페이스북 로그인 public/index.html에서 주석 풀어주면 나오는데 함수가 없어서 진행은 안됨
                <div class="fb-login-button" data-width="" data-size="" data-button-type="" data-layout="" data-auto-logout-link="true" data-use-continue-as="false"></div> -->
            </v-row>
        </v-form>
    </div>
</template>

<script setup>
import {ref, reactive} from "vue"
import {useRouter} from "vue-router"
const form = ref();
const router = useRouter();
const customer = reactive({
    email: "",
    password: "",
});
const doLogin = () =>{
    console.log(customer.email + " : "+ customer.password)
    if(customer.email=="DEV" && customer.password=="1"){
        router.replace('/main')
    }
}
const goRegist = () =>{
    router.replace('/regist')
}
const goKakaoLogin = () =>{
    console.log("kakaoLogin")
}
const goGoogleLogin = () =>{
    console.log("googleLogin")
}
const goNaverLogin = () =>{
    console.log("NaverLogin")
}

</script>

<style scoped>
.input-item{
    width: 270px;
    border-bottom: 1px solid #ebebeb;
}
.socal-login-btn-area{
    position: absolute;
    bottom: 10px;
    width: 300px;
}
.socal-login-btn{
    width: 200px;
    margin : 0 auto;
}
.socal-login-btn >img{
    width: 100%;
}
.v-img__img{
    width: 240px;
}
.social-naver-btn{
    width:50px;
}
.login-container{
    padding-top : 5%;
    height: 100%;
    width: 100%;
}
.login-botten-area > button{
    margin: 10px 15px 0;
    padding: 5px;
    width:90%;
    border-radius: 20px;
}
.login-form{
    padding : 5%;
    width:100%;
    margin: 5%;
    height: 100%;
    background-color: #b1ababe1;
    border-radius: 20px;
}

</style>