<template>

  <div class="place">

    <div class="paragraphcontainer">

      <p class="text">
        {{this.phenomenon}}
      </p>
      <p class="text">
        {{this.name}}
      </p>
      <p class="text" >
        {{'min ' + this.tempmin + "°C"}}
      </p>


    </div>


  </div>

</template>

<script >

export default {

  name:"Place",

  props :[
      'name',
      'phenomenon',
      'tempmin'
  ]
}

</script>

<style>


</style>