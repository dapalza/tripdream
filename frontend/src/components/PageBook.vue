<template>
    <div class="book">
      <div class="page" :class="{ 'flipped': flipped }" @click="flipPage">
        <div class="content">
          <!-- 페이지 내용 -->
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
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