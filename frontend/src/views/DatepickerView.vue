<template>test
  </template>
  
  <script lang="ts" setup>
  </script>
  
  <style scoped>
  .cell {
    height: 30px;
    padding: 3px 0;
    box-sizing: border-box;
  }
  .cell .text {
    width: 24px;
    height: 24px;
    display: block;
    margin: 0 auto;
    line-height: 24px;
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    border-radius: 50%;
  }
  .cell.current .text {
    background: #626aef;
    color: #fff;
  }
  .cell .holiday {
    position: absolute;
    width: 6px;
    height: 6px;
    background: var(--el-color-danger);
    border-radius: 50%;
    bottom: 0px;
    left: 50%;
    transform: translateX(-50%);
  }
  </style>
  