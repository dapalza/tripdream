import {ref,onMounted} from 'vue';
import {useRoute} from 'vue-router';

const socialLogin = {
  data() {
    return {
      loginResult:{
        status:''
      }
    }
  },
  methods:{
    doSocialLogin(socialType){
      const apiPath = '/user/social-login'
      const bodyData={
        code: this.$route.query.code,
        userType: socialType
      }
      console.log(apiPath + " and " + bodyData);
      console.log(bodyData);
      console.log(bodyData.code);
      console.log(bodyData.userType);
      // this.$axios.post(apiPath,bodyData).then(response =>{
      //     this.loginResult.status = 'SUCCESS'
      //     this.$cookie.setCookies('user-key', response.data.id)
      //     window.opener.location.replace('/')
      //     window.close()
      // }).catch(error =>{
      //     this.loginResult.status = 'FAIL'
      //     console.log('error',error)
      // })
    }
  },
  setup(){
    const socialCode = ref('');

    const socialRoute = useRoute();

    socialCode.value = socialRoute.query.location;
    onMounted(()=>{
      console.log("test");
      console.log(socialCode.value);
    })
    return {socialCode};
  }
    
}

export default socialLogin