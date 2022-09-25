<template>

  <div class="description ">





    <div >

      <div class="datacol">

        <h1>Night</h1>

        <h3>
          Weather
        </h3>
        <p>
          {{this.text[0]}}
        </p>



        <div v-if="renderTable">




          <n-table :bordered="false" :single-line="false">
            <thead>
            <tr>
              <th>Location</th>
              <th>Weather</th>
              <th>Min. Temperature</th>

            </tr>
            </thead>
            <tbody>

            <tr v-for="location in this.places[0]" :key="location.name"
            >
              <td>{{ location.name }}</td>
              <td>
              <img class="textspace" :src="getIcon(location.phenomenon)">
                <p>                  {{location.phenomenon}}
                </p>
              </td>
              <td>{{ location.tempmin }}</td>

            </tr>


            </tbody>
          </n-table>

        </div>

        <div v-if="renderTable">

          <h3>Winds</h3>

          <n-table :bordered="false" :single-line="false">
            <thead>
            <tr>
              <th>Location</th>
              <th>Direction</th>
              <th>Range</th>

            </tr>
            </thead>
            <tbody>

            <tr v-for="wind in this.winds[0]" :key="wind.name"
            >
              <td>{{ wind.name }}</td>
              <td>{{ wind.direction }}</td>
              <td>{{ wind.speedmin +'...'+ wind.speedmax +" m/s"}}</td>

            </tr>


            </tbody>
          </n-table>

        </div>



      </div>

      <div class="datacol">

        <h1>Day</h1>

        <h3>
          Weather
        </h3>

        <p>
          {{this.text[1]}}
        </p>



        <n-table v-if="renderTable" :bordered="false" :single-line="false">



          <thead>
          <tr>
            <th>Location</th>
            <th>Weather</th>
            <th>Max. Temperature</th>

          </tr>
          </thead>
          <tbody>

          <tr v-for="location in this.places[1]" :key="location.name"
          >
            <td>{{ location.name }}</td>
            <td>
              <img class="textspace" :src="getIcon(location.phenomenon)">
              <p>                  {{location.phenomenon}}
              </p>
            </td>
            <td>{{ location.tempmax }}</td>

          </tr>


          </tbody>
        </n-table>

        <div v-if="renderTable">

          <h3>Winds</h3>

          <n-table :bordered="false" :single-line="false">
            <thead>
            <tr>
              <th>Location</th>
              <th>Direction</th>
              <th>Range</th>

            </tr>
            </thead>
            <tbody>

            <tr v-for="wind in this.winds[1]" :key="wind.name"
            >
              <td>{{ wind.name }}</td>
              <td>{{wind.direction}}
              </td>
              <td>{{ wind.speedmin +'...'+ wind.speedmax +" m/s"}}</td>

            </tr>


            </tbody>
          </n-table>

        </div>

        <div v-if="renderTable">
          <h3>
            Sea
          </h3>
          <p>
            {{sea}}
          </p>
          <h3>Peipsi</h3>
          <p>
            {{peipsi}}
          </p>

        </div>

      </div>





    </div>








  </div>

</template>

<script >

export default {

  name:"DescriptionBlock",

  props :[
    'text','places','sea','peipsi','winds'


  ],

  methods:{
    findRotation(wind){

      let direction = wind.split(" ")[0]

      let directions = ['North','Northeast','East','Southeast','South','Southwest','West','Northwest']

      let result = directions.findIndex(direction)


      return result




    },

    getIcon: function (phenomen){

      let lower = phenomen.toLowerCase();


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

    }},

  computed:{

    renderTable: function (){

      return this.places.length > 0;
    },


  }
}

</script>

<style>


</style>