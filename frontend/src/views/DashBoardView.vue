<template>
  <div class="logo">TripDream</div>
  <div style="display:flex; position:fixed;top:0px;right:0px;">
    <div class="userState">
      D-365
    </div>
    <div>
      <svg enable-background="new 0 0 32 32" height="32px" id="Layer_1" version="1.1" viewBox="0 0 32 32" width="32px" xml:space="preserve" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><g>
        <polyline fill="none" points="   649,137.999 675,137.999 675,155.999 661,155.999  " stroke="#FFFFFF" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="2"/>
        <polyline fill="none" points="   653,155.999 649,155.999 649,141.999  " stroke="#FFFFFF" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="2"/>
        <polyline fill="none" points="   661,156 653,162 653,156  " stroke="#FFFFFF" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="2"/></g>
        <path d="M21.947,16.332C23.219,14.915,24,13.049,24,11c0-4.411-3.589-8-8-8s-8,3.589-8,8s3.589,8,8,8  c1.555,0,3.003-0.453,4.233-1.224c4.35,1.639,7.345,5.62,7.726,10.224H4.042c0.259-3.099,1.713-5.989,4.078-8.051  c0.417-0.363,0.46-0.994,0.097-1.411c-0.362-0.416-0.994-0.46-1.411-0.097C3.751,21.103,2,24.951,2,29c0,0.553,0.448,1,1,1h26  c0.553,0,1-0.447,1-1C30,23.514,26.82,18.615,21.947,16.332z M10,11c0-3.309,2.691-6,6-6s6,2.691,6,6s-2.691,6-6,6S10,14.309,10,11z  "/>
      </svg>
    </div>
    <v-btn>로그아웃</v-btn>
  </div>
  <div class="dashBoardHeader">
    <div>
      {{ userNickName }}님의 세계 지도
    </div>
  </div>
  <div style="width:100vw; height: 900px;margin-top: 20px; display:flex;flex-wrap: wrap;">

    <!-- <div :style="{width:mapWidth, height:mapHeight}"> -->
      <!-- <div style="width:550px;height: 230px;"> -->
      <MapComponent/>
    <!-- </div> -->
    <!-- <div class="main-btn-area" style="display: flex; justify-content: space-between;
    flex-wrap: wrap; flex:1 1 20%">
      <v-btn style="flex:1 1 40%">환율 정보</v-btn>
      <v-btn style="flex:1 1 40%">계획 관리</v-btn>
      <v-btn style="flex:1 1 40%">나의 앨범</v-btn>
      <v-btn style="flex:1 1 40%">여행 검색</v-btn>
    </div> -->
  </div>
</template>
  
<script setup>
import {ref,
 onMounted,
 onUnmounted,
} from "vue"

import MapComponent from '../components/common/MapComponent.vue';
const userNickName = ref("개발자");
onMounted(() => {
  // window.addEventListener("resize", resizeMap);
  setMapSize(window.innerWidth,window.innerHeight);
});
onUnmounted(() => {
  // window.removeEventListener('resize',resizeMap);
});
// const mapElement = ref(null);

// const resizeMap = () => {
//   setMapSize(window.innerWidth,window.innerHeight);
// };

const mapWidth = ref();
const mapHeight = ref();

const setMapSize = (width,height) =>{
  mapWidth.value = (width-10)+"px";
  mapHeight.value = (height-10) + "px";
  console.log(width);
  if(width<558){
    // 가로가 길다 = 세로가 짧다
    mapHeight.value = ((width-10)*23)/55;
  }
  else {
    mapWidth.value = "558px";
    mapHeight.value = ((558*23)/55)+"px";
  }
};
</script>
<style>
.dashBoardHeader{
  display: flex;
  justify-content: center;
}
.logo{
  position:fixed;top:0px;left:0px;
}
@media (max-width:558px) {
  .dashBoardHeader{
    display: none;
  }
  .logo{
    position: relative;
    display:flex;
    justify-content: center;
  }
  .userState{
    display: none;
  }
}
</style>  