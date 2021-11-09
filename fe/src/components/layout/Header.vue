<template>
  <div class="header">
    <span v-if="screen === 'station'">Quản lý thông tin trạm quan trắc</span>
    <span v-if="screen === 'camera'">Quản lý thông tin trạm quan trắc >> Quản lý Camera</span>
  </div>
  
  
</template>

<script>
export default {
  props: {
    screen: String
  }
}
</script>
<style scoped>
.header {
  height: 60px;
  width: 100%;
  border-bottom: 1px solid rgba(0,0,0,.125);
  display: flex;
  align-items: center;
  padding-left: 24px;
  font-size: 24px;
}
</style>