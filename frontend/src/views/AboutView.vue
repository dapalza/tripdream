<template>
  <div ref="bookElement" class="book" :style="{ marginTop:bookTop ,marginLeft:bookLeft}" @touchmove.prevent="onScroll">
    <div class="page" :class="{ 'flipped': flipped }" @click="flipPage">
      <div class="content">
        <component :is="componentInfo.componentName"></component>
      </div>
    </div>
  <button style="position:absolute; width: 72px; height: 30px; top: calc(100% - 40px);border-radius: 10px;
    left: calc(100% - 72px);
     background-color: aqua;"
     @click="changeViewLogin"
     >로그인</button>
  </div>
</template>
  
<script>
import { IntroPage1, IntroPage2, IntroPage3, IntroPage4,} from '../components/intoPages'
import { debounce} from 'lodash'
import { 
 onMounted,
 onUnmounted,
 ref,
 reactive,
 // onUpdated,
 // onBeforeMount,
} from 'vue';
import {useRouter} from "vue-router";


export default {
  components:{
    IntroPage1,
    IntroPage2,
    IntroPage3,
    IntroPage4,
  },
  setup(){
    const bookTop = ref("10px");
    const bookLeft = ref("10px");
    const pageLength = 4;
    const windowWidth = ref(window.innerWidth);
    const windowHeight = ref(window.innerHeight);
    const bookElement = ref(null);
    const bookHeight = ref(0);
    const bookWidth = ref(0);
    const tmp = ref(0);
    const wheelEvent = ref(false);
    const pageIndex = ref(0);
    const router = useRouter();
    const componentInfo = reactive({
      componentName: 'IntroPage1'
    })
    const lastY = ref(0);
    const dir_Y = ref(0);

    const changeViewLogin=()=>{
      router.replace('/login')
    } 


    const onScroll=(e)=>{
      const deltaY = e.touches[0].clientY - lastY.value;
      lastY.value = e.touches[0].clientY;
      if(dir_Y.value == 0){
        if(deltaY>0){
          dir_Y.value = 1;
          pageChange(-1)
        }
        else if(deltaY < 0){
          dir_Y.value = -1;
          pageChange(1)
        }
      }
      else {
        if(dir_Y.value*deltaY < 0){
          dir_Y.value = 0;
        }
      }
        // console.log(e);
    }

    const pageChange = (val) =>{
      pageIndex.value = (pageIndex.value+pageLength +val)%pageLength; 
      componentInfo.componentName = "IntroPage"+(pageIndex.value+1);
      console.log(pageIndex.value);
    };

    const handleWheel = (event) => {
      wheelEvent.value = true;
      const delta = Math.sign(event.deltaY)
      pageChange(delta)
    };
    const handleResize = () => {
      windowWidth.value = window.innerWidth;
      windowHeight.value = window.innerHeight;
      setBookSize(windowWidth.value,windowHeight.value);
    };

    const setBookSize = (width,height) =>{
      bookElement.value.style.width = (width-10)+"px";
      bookElement.value.style.height = (height-10)+"px";
      bookWidth.value = width-10;
      bookHeight.value = height-10;
      if(width>height){
        // 가로가 길다 = 세로가 짧다
        bookWidth.value = (height*8)/13;
        bookElement.value.style.width = bookWidth.value+"px";
      }
      else {
        bookHeight.value = (width/8)*13;
        if(bookHeight.value > height){
          bookWidth.value = (height*8)/13;
          bookElement.value.style.width = bookWidth.value+"px";
        }
        else {
          bookElement.value.style.height = bookHeight.value+"px";
        }
      }
      if(height - bookHeight.value <0){
        bookTop.value = "5px";
      }
      else {

        bookTop.value = ((height - bookHeight.value)/2) + "px";
      }
      bookLeft.value = ((width - bookWidth.value)/2) + "px";
    };
    onMounted(() => {
      window.addEventListener('wheel', debounce(handleWheel,300));
      window.addEventListener('resize', handleResize);
      setBookSize(windowWidth.value,windowHeight.value);
    });
    onUnmounted(() => {
      window.removeEventListener('resize',handleResize);
      window.removeEventListener('wheel',handleWheel);
    });
    return {
      windowWidth,
      windowHeight,
      bookElement,
      tmp,
      wheelEvent,
      pageIndex,
      componentInfo,
      bookLeft,
      bookTop,
      bookHeight,
      bookWidth,
      changeViewLogin,
      onScroll,
    }
  },  
  data() {
    return {
      flipped: false
    }
  },
  methods: {
    flipPage() {
      this.flipped = !this.flipped;
    }
  }
};
</script>
  
  <style>
  .book {
    width: 200px;
    height: 300px;
    position: relative;
    perspective: 1000px;
    padding: 0px;
  }
  
  .page {
    width: 100%;
    height: 100%;
    position: absolute;
    transform-style: preserve-3d;
    transition: all 0.5s ease;
  }
  
  .page.flipped {
    transform: rotateY(-180deg);
  }
  
  .content {
    width: 100%;
    height: 100%;
    backface-visibility: hidden;
    position: absolute;
    top: 0;
    left: 0;
    background-color: white;
    border: 1px solid black;
    padding: 10px;
    box-sizing: border-box;
  }
  </style>