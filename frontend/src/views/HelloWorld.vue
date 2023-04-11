<template>
  <v-container>
  </v-container>
  <header>MainPage</header>
  <button style="margin: 10px;padding: 7px;border-radius: 10px; background-color: brown;" @click="localSwager">local</button>
  <button style="margin: 10px;padding: 7px;border-radius: 10px; background-color: aqua;" @click="fleescape">fleescape</button>
</template>

<script>
export default {
  name: "HelloWorld",
  setup() {
    const localSwager = ()=>{
      console.log("local move");
      window.open("http://localhost:8082/swagger-ui/index.html");
    }
    const fleescape = () =>{
      console.log("fleescape");
      window.open("http://fleescape.shop:8082/swagger-ui/index.html");
    }

    return {localSwager, fleescape};
  },
  data: () => ({
    ecosystem: [],
    importantLinks: [],
    whatsNext: [],
  }),
  components:{
  },
}
</script>
