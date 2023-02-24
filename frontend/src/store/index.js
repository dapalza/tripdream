import {createStore} from "vuex";
import {tokenCookies} from "@/store/modules/token"
import {customer} from "@/store/modules/customer"

const store = createStore({
  modules:{tokenCookies,customer},
  state:{
    loginFail:"",
  },
  getters:{
    getLoginFail(state){
      return state.loginFail;
    }
  },
  mutations:{
    setLoginFail(state,value){
      state.loginFail = value;
    }
  }

});
export default store;
