import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import { router } from './router/index.js'
import store from "./store"
import VueCookies from "vue3-cookies"
import global from "./global/global.js"

loadFonts()

createApp(App)
  .use(vuetify)
  .use(store)
  .use(VueCookies,{
    expireTimes:"60",
    secure:true,
  })
  .use(router)
  .use(global)
  .mount('#app')
