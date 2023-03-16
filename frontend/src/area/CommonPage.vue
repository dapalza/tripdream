<template>
    <CommonHead></CommonHead>
    <router-view></router-view>
    <CommonFoot></CommonFoot>
</template>

<script>
import CommonFoot from '../views/CommonFoot';
import CommonHead from '../views/CommonHead';

export default {
    name:"CommonPage",
    components:{
        CommonHead,
        CommonFoot
    },
}


</script>

<style>

</style>