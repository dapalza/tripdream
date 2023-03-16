<template>
  <div>
  </div>
</template>

<script>

export default{
  name: "WinPopup",

  setup(props,context){
    const {emit}= context;
    const closeWinPop= (newWin)=>{
      console.log("maybe close");
      if(newWin) {
        newWin.close();
        console.log("close!");
        newWin = null;
        emit("event1","close");
      }
    };
    const openWinPop = (uri, height,width) =>{

      var newWin = window.open(uri,"_blank","width="+width+", height="+height+",left=200,top=200, resizeable=no,status=no'");
      
      var loop = setInterval(function(){
        if(newWin.closed){
          closeWinPop(newWin);
          clearInterval(loop);
          alert('closed');
        }
      },500);
    };

    return {openWinPop,closeWinPop};
  }
}
</script>

<style scoped>

</style>