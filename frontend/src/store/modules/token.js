import {useCookies} from "vue3-cookies";
const {cookies} = useCookies();

import axios from "axios";

export const tokenCookies ={
  namespaced:true,
  state:{
    customerNo:"customerNo test",
    accessToken:null,
    refreshToken:null,
    needLogin:true,
    tokenLifeTime:5,
  },
  getters:{
    getCustomerNo(state){
      return state.customerNo;
    },
    getNeedLogin(state){
      return state.needLogin;
    },
    getAccessToken(state){
      state.accessToken = cookies.get("login.accessToken");
      return cookies.get("login.accessToken");
//      return state.accessToken;
    },
    getRefreshToken(state){
      return state.refreshToken;
    }
  },
  mutations:{
    needLogin(state,data){
      state.needLogin = data;
    },
    //인증 연장?
    saveStateToStorage(state){
      cookies.set("login.customerNo",state.customerNo,20);
      cookies.set("login.accessToken",state.accessToken,20);
      cookies.set("login.refreshToken",state.refreshToken,20);
    },
    setAccessTokenRe(state,params){
      //params.token부분은 없애고 axios를 통해 서버로 부터 값을 받아서 처리로 변경해야함
      const lifeTime = params.tokenTime == null? 10: params.tokenTime;
      cookies.set("login.accessToken",params.token,lifeTime);
    },

    readStateFromStorage(state){
      if(cookies.get("login.customerNo")!=null){
        state.customerNo = cookies.get("login.customerNo");
      }
      if(cookies.get("login.accessToken")!=null){
        state.accessToken = cookies.get("login.accessToken");
      }
      if(cookies.get("login.refreshToken")!=null){
        state.refreshToken = cookies.get("login.refreshToken");
      }
    },
    reset(state){
      state.customerNo="";
      state.accessToken=null;
      state.refreshToken=null;
      cookies.remove("login.customerNo");
      cookies.remove("login.accessToken");
      cookies.remove("login.refreshToken");
    }
  },
  actions:{
    login({commit},params){
      return new Promise((resolve,reject) =>{
        try{    
          axios.post("/api/signIn",params.customer)
          .then(res=>{
            console.log(res.data);
            const access = res.data.customer_email;
            const refresh = res.data.customer_pw;
            const lifeTime = params.tokenTime ==null?10:params.tokenTime;
            
            cookies.set('login.accessToken',access,lifeTime);
            cookies.set('login.refreshToken',refresh,lifeTime*10);
            commit('needLogin',false);
            resolve(res.data);
          }).catch(err =>{
            console.log("login Fail"+err);
            reject(err);
          });
        } catch(err){
          console.error("what's going on?"+err);
          reject(err);
        }
      })
    }
  }

};