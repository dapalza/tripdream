<template>
  <v-container>
  </v-container>
  <router-view></router-view>
  <header>MainPage</header>
</template>

<script>
import axios from 'axios';
export default {
  name: "HelloWorld",
  setup() {
    axios.get("http://localhost:8088/", { withCredentials: true }).then(res => {
      console.log(res);
    }).then(err => {
      console.log(err +"!");
    });
    axios.post("http://localhost:8088/common", { "code_cg": "ADR", "code_dp": "6" }).then(res => {
      console.log(res);
    }).then(err => {
      console.log(err +"?");
    });
  },
  data: () => ({
    ecosystem: [],
    importantLinks: [],
    whatsNext: [],
  }),
  components: { }
}
</script>
