<template>
    <div>
        <v-btn @click="clickBack('/login')">&lt; 뒤로</v-btn>
    </div>
    <div class="regist-form-area">
        <RegistForm @pushRegist="pushRegist"/>
    </div>
</template>

<script setup>
import RegistForm from '../components/common/RegistForm.vue'
import {useRouter} from 'vue-router' 
const pushRegist = (data) =>{
    console.log(data.value);
}
const router = useRouter();
const clickBack = (link) =>{
    router.replace(link);
}
</script>

<style>
.regist-form-area{
    position: relative;
    top:calc(100vh - 600px);
}
</style>