<template>
  <v-app>
    <v-main>
      <!-- <PageBook/> -->
      <ScrollingTest/>
    </v-main>
  </v-app>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
// import PageBook from './components/PageBook.vue'
import ScrollingTest from './components/ScrollingTest.vue'

export default {
  name: 'App',

  components: {
    // HelloWorld,
    ScrollingTest,
  },

  data: () => ({
    //
  }),
}
</script>
<style>
/* 수직 스크롤바 숨기기 */
::-webkit-scrollbar {
  width: 0;
  height: 0;
}

/* 수평 스크롤바 숨기기 */
::-webkit-scrollbar-horizontal {
  display: none;
}

/* 수직 스크롤바 숨기기 */
::-webkit-scrollbar-vertical {
  display: none;
}
</style>