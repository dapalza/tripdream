<template>
    <div>
        <v-btn @click="clickBack('/')">&lt; 뒤로</v-btn>
    </div>
    <div class="login-form-area">
        <LoginForm/>
    </div>
</template>

<script setup>
import LoginForm from '../components/common/LoginForm.vue'
import {useRouter} from "vue-router"

const router = useRouter();

const clickBack = (link) =>{
    router.replace(link);
}
</script>

<style>
.login-form-area{
    width:300px;
    height: 480px;
    position: relative;
    top : calc(( 100vh - 480px ) / 2);
}

@media (min-width: 800px) {
    .login-form-area{
        left: 60vw;
    }
    
}
@media (max-width: 800px) and (min-width: 300px) {
    .login-form-area{
        left: calc((100vw - 300px) / 2);
    }
}
@media (max-width: 300px) {
    .login-form-area{
        left: 0;
        width: 100vw;
    }
}
</style>