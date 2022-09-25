<template>




    <div class="mobile">
      <n-carousel  :space-between="20" draggable @update:current-index="(index) => selectItem(index)" >
        <SecondaryForecast v-for="forecast in forecasts" :key="forecast.date" v-bind:forecast="forecast"></SecondaryForecast>
      </n-carousel>
    </div>



  <div class="desktopwrapper">
    <div class="wrapper">

      <div class="desktop ">

        <SecondaryForecast v-for="(forecast,i) in forecasts" :key="forecast.date"
                           v-bind:forecast="forecast"
                           :class="{ activecard: i === activeItem}"
                           @click="selectItem(i)"
        ></SecondaryForecast>
      </div>

      <DescriptionBlock v-bind:text="this.activeText"
                        v-bind:places="this.activePlaces"
                        v-bind:sea="this.seaDescription"
                        v-bind:peipsi="this.peipsiDescription"
                        v-bind:winds="this.winds"></DescriptionBlock>

    </div>

  </div>











</template>

<script>


import SecondaryForecast from "./WeatherCard";
import DescriptionBlock from "./DescriptionBlock";

export default {



  name: 'HelloWorld',
  components: {DescriptionBlock, SecondaryForecast},

  data(){
    return {
      forecasts : [],
      texts: [],
      activeText: [],
      places:[],
      activePlaces:[],
      seaDescription : [],
      peipsiDescription:[],
      winds : [],
      activeItem: 0
    }
  },

  mounted() {

    fetch('/api/data/weather')
        .then(response => response.json())
        .then(data => {
          this.forecasts = data.forecasts.forecast
          this.forecasts.forEach(el => this.texts.push([el.night.text,el.day.text]))
          this.places[0] = data.forecasts.forecast[0].night.place
          this.places[1] = data.forecasts.forecast[0].day.place
          this.activePlaces = this.places
          this.activeText[0] = data.forecasts.forecast[0].night.text
          this.activeText[1] = data.forecasts.forecast[0].day.text

          this.seaDescription = this.forecasts[0].night.sea
          this.peipsiDescription = this.forecasts[0].night.peipsi
          this.winds = [this.forecasts[0].night.wind,this.forecasts[0].day.wind]


        })
  },
  methods: {
    selectItem(i) {

      this.activeItem = i;
      this.activeText = this.texts[i];
      this.activePlaces = []
      this.seaDescription = []
      this.peipsiDescription = []
      this.winds = []
      if (i === 0) {
        this.activePlaces = this.places
        this.seaDescription = this.forecasts[0].night.sea
        this.peipsiDescription  = this.forecasts[0].night.peipsi
        this.winds = [this.forecasts[0].night.wind,this.forecasts[0].day.wind]

      }


    },


    },



}




</script>
