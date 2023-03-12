<template>
  <div>

  </div>
</template>

<script setup>
import {ref,onMounted,defineProps,defineEmits} from 'vue';
import {useRoute} from 'vue-router';
import axios from "axios";

  const socialCode = ref('');

  const props = defineProps({
    loginType:String,
  });
  const emit = defineEmits(['']);
  
  const socialRoute = useRoute();

  const kakaoDo = (code)=>{
    const url = "https://kauth.kakao.com/oauth/token"
    const data = {
      "grant_type":"authorization_code",
      "client_id" : "998ea5ac18af79fb613db9e18647988f",
      "client_secret" : "P6laJcnmFhJD4xiA2ZeRFZpMA2IOTpou",
      "redirect_url" : "http://localhost:8081/kakao-login",
      "code" : code
    }
    axios.get(url+"?client_id="+ data.client_id+"&client_secret="
    +data.client_secret+"&grant_type="+data.grant_type+"&redirect_uri="
    +data.redirect_url+"&code="+data.code).then(res =>{
      
      window.Kakao.Auth.setAccessToken(res.data.access_token);
      console.log(res);
    }).catch(err=>{
      console.log(err);
    });
  };

  socialCode.value = socialRoute.query.code;
  onMounted(()=>{
    console.log("test");
    console.log(socialCode.value);
    console.log(socialRoute.query);
    console.log(props.loginType);
    console.log(emit);
    kakaoDo(socialCode.value);
    var token = getCookie('authorize-access-token');
    if(token){
      window.Kakao.Auth.setAccessToken(token);
      window.Kakao.Auth.getStatusInfo()
      .then(function(res){
        if(res.status==='connected'){
          console.log('login success, token' + window.Kakao.Auth.getAccessToken);
        }
      }).catch(function(err){
        console.log(err);
        window.Kakao.Auth.setAccessToken(null);
      });
    }
    else {
      console.log("no token")
    }

    // window.Kakao.API.request({
    //   url:'/v2/user/me',
    // }).then(function(res) {
    //   console.log("result")
    //   console.log(JSON.stringify(res));
    //   console.log(res);
    // }).catch(function(err){
    //   console.log(err);
    // })
  });
  const getCookie=(name)=>{
    var parts = document.cookie.split(name + '=');
    if(parts.length===2) {return parts[1].split(';')[0];}
  }
// return ;
    
</script>

<style>

</style>