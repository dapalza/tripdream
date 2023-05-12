<template>
    <!-- <div ref="mapContainer" class="map-container"></div>
     -->
     <div>map</div>
  <div class="hello" ref="chartdiv"></div>
  <div class="hello" ref="chart2div"></div>
     
</template>
  

  
  <!-- stroke-opacity: 1;
  stroke: rgb(253, 253, 253); -->
<script>
import * as am4core from "@amcharts/amcharts4/core";
import * as am4maps from "@amcharts/amcharts4/maps";
import am4geodata_worldLow from "@amcharts/amcharts4-geodata/worldLow";

// import * as am5 from "@amcharts/amcharts5";
// import * as am5map from "@amcharts/amcharts5/map";

// import am5themes_Animated from "@amcharts/amcharts5/themes/Animated";
// import am5geodata_data_countries2 from "@amcharts/amcharts4-geodata/data/countries2";

import {onMounted,onUnmounted,ref} from "vue";
import {useRouter} from "vue-router"

export default {
    name: 'HelloWorld',
  mounted() {
    
    let chart2 = am4core.create(this.$refs.chart2div, am4maps.MapChart);
    chart2.series.push(new am4maps.GraticuleSeries());

    chart2.geodata = am4geodata_worldLow;
    chart2.panBehavior = "rotateLongLat";
    chart2.projection = new am4maps.projections.Orthographic();

// Create map polygon series
var polygonSeries = chart2.series.push(new am4maps.MapPolygonSeries());
polygonSeries.mapPolygons.template.strokeWidth = 0.1;

// Exclude Antartica
polygonSeries.exclude = ["AQ"];

polygonSeries.useGeodata = true;
// Configure series
var polygonTemplate = polygonSeries.mapPolygons.template;
polygonTemplate.tooltipText = "{name}";
polygonTemplate.fill = '#D3D3D3';

// Create hover state and set alternative fill color
var hs = polygonTemplate.states.create("hover");
hs.properties.fill = 'white';

// Create active state
var activeState = polygonTemplate.states.create("active");
activeState.properties.fill = "#228B22";

// Create an event to toggle "active" state
polygonTemplate.events.on("hit", function(ev) {
  ev.target.isActive = !ev.target.isActive;
})

//홈 버튼
var button = chart2.chartContainer.createChild(am4core.Button);
button.label.text = "전체보기";
button.padding(5, 5, 5, 5);
button.align = "right";
button.marginRight = 15;
button.events.on("hit", function() {
  chart2.goHome();
});

chart2.exporting.menu = new am4core.ExportMenu();
chart2.exporting.menu.align = "left";
chart2.exporting.menu.verticalAlign = "top";
  },

  beforeUnmount() {
    if (this.chart2) {
      this.chart2.dispose();
    }
  },
    setup(){
        // let scripts = [
        //     "https://cdn.amcharts.com/lib/4/core.js",
        //     "https://cdn.amcharts.com/lib/4/maps.js",
        //     "https://cdn.amcharts.com/lib/4/geodata/worldLow.js",
        // ];

        const router = useRouter();
        // let mapInstance;
        const mapContainer = ref(null);

        onMounted(() =>{
            // for (let i = 0; i < scripts.length; i++) {
            //     let script = document.createElement('script');
            //     script.setAttribute('type', 'text/javascript');
            //     script.setAttribute('src', scripts[i]);
            //     script.async = true;
            //     document.getElementsByTagName('head')[0].appendChild(script);
            // }
            // initMap(); 
            // window.addEventListener("resize", resizeMap);
        });
        // const initMap =() =>{
        // }
        // const initMap=()=> {
        //     setMapSize(window.innerWidth,window.innerHeight);
        //     mapContainer.value = new Datamap({
        //     element: mapContainer.value,
        //     // width: mapWidth.value,
        //     // height:mapHeight.value,
        // //     // 여기에 지도 설정값 입력
        //     });
        // };
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