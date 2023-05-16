<template>
    <!-- <div ref="mapContainer" class="map-container"></div>
     -->
     <div>map</div>
     <div id="chartdiv"></div>
     
</template>
  

  
  <!-- stroke-opacity: 1;
  stroke: rgb(253, 253, 253); -->
<script>
// import {} from './multiSeriesMap.js';
import Datamap from "datamaps";
import {onMounted,onUnmounted,ref} from "vue";
import {useRouter} from "vue-router"
  
  export default {
    setup(){
        const router = useRouter();
        // let mapInstance;
        const mapContainer = ref(null);

        onMounted(() =>{
            initMap(); 
            // window.addEventListener("resize", resizeMap);
        });
        const initMap=()=> {
            setMapSize(window.innerWidth,window.innerHeight);
            mapContainer.value = new Datamap({
            element: mapContainer.value,
            // width: mapWidth.value,
            // height:mapHeight.value,
        //     // 여기에 지도 설정값 입력
            });
        };
    const mapWidth = ref();
    const mapHeight = ref();

    const setMapSize = (width,height) =>{
    mapWidth.value = (width-10);
    mapHeight.value = (height-10);
    const tmpWidth = mapWidth.value /55;
    const tmpHeight = mapHeight.value/23;
    console.log(width);
    if(tmpWidth<tmpHeight){
        // 가로가 길다 = 세로가 짧다
        console.log("?")
        mapHeight.value = ((width-10)*23)/55;
    }
    else {
        mapWidth.value = ((height-10)*55)/23;
    }
    };
        
      const resizeMap=()=> {
            
        router.go(0);
        // mapInstance.resize();
        // mapContainer.value.resize();
        // if (this.map) {
        // }
      };
        onUnmounted(()=> {
          window.removeEventListener("resize", resizeMap);
        });

        return {mapContainer,setMapSize}
    },
  };
  </script>
  
  <style>
  .map-container {
    width: 100vw;
    height: 100%;
  }
  .datamaps-region {
    stroke-opacity: 0.1;
    stroke: rgb(23, 253, 253);
}
.datamaps-subunit{
    stroke-opacity: 0.1;
    stroke: rgb(23, 253, 253);
}
  </style>