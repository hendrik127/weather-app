
<template>


  <div class="container  ">
    <h2 class="text">{{getDay(forecast.date)}}</h2>
    <div class="container " >

      <div class="night daynnite" ref="night" v-on:click="toggleNight" >

        <div class="row">
          <p class="nighttext mode">Night</p>
          <img class="inverted icon" :src="getIcon(forecast.night.phenomenon)" alt="">


        </div>




        <p class="nighttext cardtemp">
          {{forecast.night.tempmin +"..."+forecast.night.tempmax+ "°C"}}
        </p>



      </div>





      <div class="day daynnite " ref="day" v-on:click="toggleDay">

        <div class="row">
          <p class="mode">Day</p>


          <img class="inverted icon" :src="getIcon(forecast.night.phenomenon)" alt="">

        </div>

<!--        <p>-->
<!--          {{forecast.day.phenomenon}}-->
<!--        </p>-->



<!--        <p v-if="!hiddenDay" >-->
<!--          {{forecast.day.text}}-->
<!--        </p>-->
        <p class=" cardtemp">
          {{forecast.day.tempmin +"..."+forecast.day.tempmax+ "°C"}}
        </p>

      </div>



    </div>
  </div>

</template>


<script>
export default {
  name: 'SecondaryForecast',
  props:[
    'forecast'
  ],
  methods:{


    getIcon: function (phenomen){

      let lower = phenomen.toLowerCase();
      //console.log(lower)

      if(lower.includes('snow')){
        return require('../assets/icons/iconmonstr-weather-56.svg')


      }
      else if(lower.includes('spells') ){

        return require('../assets/icons/iconmonstr-weather-8.svg')


      }
      else if(lower.includes('rain') || lower.includes('shower')){

        return require('../assets/icons/iconmonstr-weather-16.svg')


      }
      else if(lower.includes('cloud') ||  lower.includes('over')){

        return require('../assets/icons/iconmonstr-weather-12.svg')


      }
      else if(lower.includes('thunder') ){

        return require('../assets/icons/iconmonstr-weather-79.svg')


      }
      else if(lower.includes('hail') ){

        return require('../assets/icons/iconmonstr-weather-32.svg')


      }




      return require('../assets/icons/iconmonstr-weather-4.svg')





    },




    getDay: function(time){
      const weekday = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];

      const d = new Date(time);
      let day = d.getDay();
      return weekday[day];
    },

    toggleDay: function (){
      this.hiddenDay = !this.hiddenDay
    },

    toggleNight: function (){
      this.hiddenNight = !this.hiddenNight
    },


  },
  data() {

    return {
      active: false
    }

  },




}
</script>



<style>


</style>