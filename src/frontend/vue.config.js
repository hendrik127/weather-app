



// https://vitejs.dev/config/
module.exports = {
  devServer:{
    port:3000,
    proxy:{
      '/api/data/weather':{
        target : 'http://localhost:8080/',
        ws: true,
        changeOrigin: true
      }
    }
  },

}
