import {useCookies} from "vue3-cookies";
const {cookies} = useCookies();

export const customer = {
  namespaced:true,
  state:{
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
  }
    
}