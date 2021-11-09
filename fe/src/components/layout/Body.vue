<template>
  <div>
    <StationInfo v-if="screen === 'station'" @open-camera-screen="openCameraScreen"/>
    <Camera v-if="screen === 'camera'" @close-screen="closeCameraScreen"/>
  </div>
  
</template>
<script> 
import Camera from '../../view/Camera.vue'
import StationInfo from '../../view/StationInfo.vue'
export default {
  components: {
    StationInfo,
    Camera
  },
  data() {
    return {
      screen: "station"
    }
  },
  methods: {
    openCameraScreen(){
      this.screen = "camera";
      this.$emit("set-screen", this.screen)
    },
    closeCameraScreen(){
      this.screen = "station";
      this.$emit("set-screen", this.screen)
    }
  },
}
</script>