<template>
  <div>
    Kakao Login View
  </div>
  <socialLoginControler loginType="kakao"></socialLoginControler>
</template>
  
<script>
import socialLoginControler from '../mixins/socialLoginControler.vue'

export default {
  name: "KakaoLoginView",
  components:{
    socialLoginControler
  },
  setup(){

    return {};
  },
}
</script>
  
<style>

</style>