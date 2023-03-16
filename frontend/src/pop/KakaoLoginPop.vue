<template>
  <div>카카오 로그인으로 넘어가는 중입니다. 기다려주세요!!</div>
</template>

<script>
import  {onMounted} from 'vue';


export default {
  
  name: "KakaoLoginPop",
  
  setup(){
    onMounted(()=>{
      console.log("setup mount");
      doKakaoLogin();
    });


    const doKakaoLogin=() =>{
      console.log("router kakao");
      window.Kakao.Auth.authorize({
        redirectUri:'http://localhost:8088/kakao-login',
        state: 'userme',
      })
    }
    return {doKakaoLogin};
  }
}
</script>

<style>

</style>