import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import {router} from './router/index.js'
import store from "./store"
import VueCookies from "vue3-cookies"
import global from "./commonGlobal/global.js"

loadFonts()

//const baseUrl = "http://localhost:8088";

//const app = 
createApp(App)
.use(vuetify)
.use(store)
.use(VueCookies,{
  expireTimes:"60",
  secure:true,
})
.use(router)
.use(global)
.provide('$store',store)  //?
 .mount('#app');

// app.config.globalProperties.$baseUrl = baseUrl;
// app.mount('#app');
// Vue.config.globalProperties.$getUrl = () =>{
//   return "http://localhost:8088"
// }
