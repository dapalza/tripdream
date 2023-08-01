<template>
    <div class="regist-view">

        <div>
            <v-btn @click="clickBack('/login')">&lt; 뒤로</v-btn>
        </div>
        <div class="move-area1">
            <div class="hand">
            </div>
        </div>
        <div class="regist-form-area">
            <div class="paper">
                <RegistForm @pushRegist="pushRegist"/>
            </div>
        </div>
    </div>
</template>

<script setup>
// import axios from 'axios';
import RegistForm from '../components/common/RegistForm.vue'
import {useRouter} from 'vue-router' 

import { getCurrentInstance } from 'vue';

const {proxy} = getCurrentInstance();


const pushRegist = (data) =>{
    console.log(data.value);
    var birthData = data.value.choose.birth.toISOString();
    var registData = {
        "email" : data.value.customer.email,
        "password" : data.value.customer.password,
        "nickname" : data.value.customer.nick,
        "gender" : data.value.choose.gender =="" ? "N" : getGender(data.value.choose.gender),
        "birth" : birthData.substr(0,10)
    }
    console.log(registData)

    proxy.$store.dispatch("customer/join",{
        url: proxy.$getUrl(),
        registData
    }).then(res =>{
        if(res.status==200){
            clickBack("/login");
        }
        else {
            console.log("check point : something trouble when do register")
        }
    })

    // try{

        // axios.post('/api/register',{
        //     registData
        // }).then((response) =>{
        //     console.log("check point : this is response")
        //     console.log(response)
        // }).catch((error) =>{
        //     console.log("check point : this is error from register")
        //     console.log(error)
        // });
    // } catch(error){
    //     console.log(error)
    }
const getGender = (genderIndex) =>{
    if (genderIndex ==0){
        return "N";
    }
    else if(genderIndex == 1){
        return "M"
    }
    return "F";
}
const router = useRouter();
const clickBack = (link) =>{
    router.replace(link);
}
</script>

<style>
.regist-view{
    height: 100vh;
    /* background-color: #efefef; */
}
.regist-form-area{
    position: relative;
    display: flex;
    justify-content: center;
    padding-top:10vh;
}
@keyframes fadeInDown{
    0%{
        opacity: 1;
        transform: translate3d(0,-100%,0);
    }
    to{
        opacity: 1;
        transform: translateZ(0);
    }
}
@keyframes fadeInOut{
    0%{
        opacity: 0;
        transform: translate3d(0,-300px,0);
    }
    50%{
        opacity: 1;
        transform: translateZ(0);
    }
    100%{
        opacity: 0;
        transform: translate3d(0,-100%,0);
    }
}
.move-area1{
    width:100%;
    height: 100%;
    position: fixed;
    top:-0px;
    display: flex;
    opacity: 0;
    animation:fadeInOut 4s;
    z-index: 1;
    justify-content: center;
}
.hand{
    background-image: url("../assets/BgImgHand.png");
    width:300px;
    height: 100%;
    
    background-size:contain;
}

.paper{
    max-width: 650px;
    background-image: url("../assets/BgImgPage.jpeg");
    width:80%;
    padding: 10px 0px 40px;
    z-index: 2;
    border: 1px solid lightgray;
    /* background-color: #fff; */
    border-radius: 2px;
    animation:fadeInDown 2s;
}
/* 
.paper::before,
.paper::after{
    content:'';
    position: absolute;
    bottom:10px;
    width:40%;
    height: 10px;
    box-shadow: 0 5px 14px rgba(0,0,0,.7);
    z-index: -1;
    transition: all .3s ease-in-out;
}

.paper::before{
    left:15px;
    transform:skew(-5deg) rotate(-5deg)
}

.paper::after{
    right: 15px;
    transform: skew(5deg) rotate(5deg);
}

.paper:hover::before{
    left: 5px;
}
.paper:hover::after{
    right: 5px;
} */


</style>