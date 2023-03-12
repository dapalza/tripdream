<template>
  <v-app>
    <!-- 로그인시 re-render작업이 안되어 로그인 반영이 안됨
    새로고침을 해주는 방법이 있으나 아니다 싶음
    re-render 방식을 좀 더 공부해 보고 재도전 -->
    <!-- <CommonHead></CommonHead> -->
    <v-main>
      <router-view></router-view>
    </v-main>
    <!-- <CommonFoot></CommonFoot> -->
  </v-app>

  <!-- <TeleoportExample></TeleoportExample> -->
</template>

<script>
// import CommonHead from './area/common-head.vue';
// import CommonFoot from './area/common-foot.vue';
// import TeleoportExample from './teleport/TeleoportExample.vue';

export default {
  name: 'App',

  components: {
    // CommonHead,
    // CommonFoot,
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