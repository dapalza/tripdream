import {useCookies} from "vue3-cookies";
const {cookies} = useCookies();
import axios from "axios";

export const customer = {
  namespaced:true,
  state:{
    customer_email:"",
    customer_pw:"",
    customer_nick:"",
    customer_dob:"",
    customer_feet:"",
    customer_height:"",
    customerNo:"",
  },
  getters:{
    getCustomerNo(state){
      console.log(state)
      return state.customerNo;
    }
  },
  mutations:{
    setCustomerEmal(state, data){
      state.customer_email = data;
    },
    saveStateToStorage(state,params){
      cookies.set("customer.customer_nick",params.customer_nick,120);
      cookies.set("customer.customer_dob",params.customer_dob,120);
      cookies.set("customer.customer_feet",params.customer_feet,120);
      cookies.set("customer.customer_height",params.customer_height,120);
      cookies.set("customer.customerNo",params.customer_no,120);
    },
    setCustomer(state,params){
      console.log(params)
      state.customerNo = params.customer_no;
      state.customer_nick = params.customer_nick;
      state.customer_dob = params.customer_dob;
      state.customer_height = params.customer_height;
      state.customer_feet = params.customer_feet;
    }
  },
  actions:{
    join({commit},params){
      console.log(params);
      return new Promise((resolve,reject) => {
        // if(token이 존재하면) reject(에러번호);
        try{
          axios.post(params.url+"/join",{
            "customer_email" : params.customer_email,
            "customer_pw" : params.customer_pw,
            "customer_nick" : params.customer_nick,
            "customer_height":params.customer_height,
            "customer_feet": params.customer_feet,
            "customer_dob": params.customer_dob,
          })
          .then(res=>{
            console.log(res);
            commit('setCustomerEmal',true);
            resolve(res);
          }).catch(err=>{
            reject(err);
          });
        }catch(err){
          reject(err);
        }
      })
    },
    joinNick({commit},params){
      return new Promise((resolve,reject)=>{
        try{
          axios.get(
            params.url
            + "/join/nick?customer_nick="
            + params.customerNick
            ,{withCredentials: true}
          ).then(res =>{
            commit('setCustomerEmal',true);
            console.log(res.data);
            resolve(res.data);
          }).catch(err =>{
            reject(err);
          });
        }catch(err){
          reject(err);
        }
      })
    },
    joinEmail({commit},params){
      return new Promise((resolve,reject) => {
        try{
          axios.get(
            params.url
            + "/join/email?customer_email="
            + params.customerEmail
            , { withCredentials: true }
          ).then(res=>{
            console.log(res.data);
            //현재 반환 값이 true false임 같은 이메일이 있을 경우 false 반환
            commit('setCustomerEmal',true);
            resolve(res.data);
          }).catch(err =>{
            //백에서 customer.customerEmail 즉 파라미터를 
            //인식하지 못했을 경우 발생할듯하다.
            reject(err);
          });
        }catch(err){
          //이메일 정보가 없어도 반환은 되지만 특수의 케이스에서 반환 된다.
          //서버가 닫혔거나 api가 변경되었을 경우
          reject(err);
        }
      });
    }
  }
    
}