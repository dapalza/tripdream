<template>
    <!-- <div class="container" @scroll="onScroll">
      <component :is="currentComponent" :key="componentKey" />

    </div> -->
    <router-view></router-view>
  </template>
  
  <script>
  export default {
    // data() {
    //   return {
    //     currentComponent: 'ComponentA',
    //     componentKey: 0,
    //   };
    // },
    // methods: {
    //   onScroll() {
    //     // 스크롤 이벤트가 발생하면 currentComponent를 변경합니다.
    //     this.currentComponent = 'ComponentB';
    //     // componentKey를 변경하여 컴포넌트가 갱신되도록 합니다.
    //     this.componentKey += 1;
    //   },
    // },
    // components: {
    //   ComponentA: {
    //     template: '<div>Component A</div>',
    //   },
    //   ComponentB: {
    //     template: '<div>Component B</div>',
    //   },
    // },
  };
  </script>