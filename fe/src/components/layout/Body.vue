<template>
  <div class="body">
    <StationInfo v-if="screen === 'station'" @open-camera-screen="openCameraScreen"/>
    <Camera v-if="screen === 'camera'" @close-screen="closeCameraScreen" :station="station"/>
    <ToastMessage :newToast="toast"/>
  </div>
  
</template>
<script> 
import Camera from '../../view/Camera.vue'
import StationInfo from '../../view/StationInfo.vue'
import {mapGetters} from 'vuex'
export default {
  components: {
    StationInfo,
    Camera
  },
  data() {
    return {
      screen: "station",
      station: {}
    }
  },
  computed: {
    ...mapGetters({toast: 'toast'})
  },
  methods: {
    openCameraScreen(item){
      this.screen = "camera";
      this.station = item
      this.$emit("set-screen", this.screen)
      console.log(item)
    },
    closeCameraScreen(){
      this.screen = "station";
      this.$emit("set-screen", this.screen)
    }
  },
}
</script>
<style scoped>
.body{
  /* height: calc(100vh - 60px); */
  flex: 1;
}
</style>