<template>
  <div id="app">
    <Header :screen="screen"/>
    <Body @set-screen="setScreen"/>
  </div>
</template>

<script>
import Header from './components/layout/Header.vue'
import Body from './components/layout/Body.vue'
export default {
  name: 'App',
  components: {
    Header, Body
  },
  data() {
    return {
      screen: "station"
    }
  },
  methods: {
    setScreen(screen){
      this.screen = screen
    }
  },
}
</script>

<style>
@import url('./css/main.css');
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  display: flex;
  flex-direction: column;
}
</style>
