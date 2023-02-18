<template>
  <v-app>
    <CommonHead></CommonHead>
    <v-main>
      <HelloWorld/>
    </v-main>
    <CommonFoot></CommonFoot>
  </v-app>

  <!-- <TeleoportExample></TeleoportExample> -->
</template>

<script>
import CommonHead from './area/common-head.vue';
import CommonFoot from './area/common-foot.vue';
import HelloWorld from './components/HelloWorld.vue'
// import TeleoportExample from './teleport/TeleoportExample.vue';

export default {
  name: 'App',

  components: {
    HelloWorld,
    CommonHead,
    CommonFoot,
    // TeleoportExample
  },

  data: () => ({
    //
  }),
}
</script>

<style>
a{
  cursor: pointer;
    text-decoration: none;
    color: black;

}


</style>