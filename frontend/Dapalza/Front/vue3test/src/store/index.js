import {createStore} from "vuex";

export default createStore({
  state:{
    access_token: "",
    refresh_token:"",
    customer:{
      customer_nick:"",
      customer_dob:"",
      customer_feet:"",
      customer_height:"",
    }
  },
  getters:{
    getAccessToken(state){
      return state.access_token;
    },
    getRefreshToken(state){
      return state.refresh_token;
    }
  },
  mutations:{
    setAccessToken(state,value){
      state.access_token = value;
    },
    setRefreshToken(state,value){
      state.refresh_token = value;
    }
  }

});