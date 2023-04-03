<template>
  <!-- <CommonHead></CommonHead> -->
  <v-container>
  </v-container>
  <!-- <router-view></router-view> -->
  <header>MainPage</header>
</template>

<script>
// import CommonHead from '../area/common-head.vue';
import axios from 'axios';
export default {
  name: "HelloWorld",
  setup() {
    axios.get("http://localhost:8088/", { withCredentials: true }).then(res => {
      console.log(res);
    }).catch(err => {
      if(err)
        console.log(err +"!");
    });
    axios.post("http://localhost:8088/common", { "code_cg": "ADR", "code_dp": "6" }).then(res => {
      console.log(res);
    }).catch(err => {
      if(err)
        console.log(err +"?");
    });
  },
  data: () => ({
    ecosystem: [],
    importantLinks: [],
    whatsNext: [],
  }),
  components:{
    // CommonHead,
  },
}
</script>
