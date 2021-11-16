<template>
  <div class="main">
    <div class="button-bar">
      <Button class="m-r-4" text="Thêm" icon="fas fa-plus" />
      <Button text="Export" icon="fas fa-download" />
    </div>
    <div class="grid-container">
      <div class="grid-header">
        Show
        <input type="number" value="10" />
        entries
      </div>
      
      <Table class="grid" :data="stations" :fields="stationFields" iconOperation="fas fa-camera" @onclick-operation="openCameraScreen"/>
      
    </div>
    <div class="paging-bar">
      <div class="paging-left">Showing...</div>
      <div class="paging-right">
        <!-- <Pagination /> -->
      </div>
    </div>
  </div>
</template>

<script>

import StationApi from "../service/stationApi"
export default {
  
  data() {
    return {
      stations: [],
      stationFields: [
        { name: "objectType", text: "Search Loại trạm", typeSearch: "input" },
        { name: "objectTypeShortname", text: "Search Tên loại trạm", typeSearch: "input"},
        { name: "stationCode", text: "Search Mã trạm", typeSearch: "input"},
        { name: "stationName", text: "Search Tên trạm", typeSearch: "input"},
        { name: "longtitude", text: "Search Kinh độ", typeSearch: "input"},
        { name: "latitude", text: "Search Vĩ độ", typeSearch: "input"},
        { name: "provinceName", text: "Search Tỉnh", typeSearch: "input"},
        { name: "districtName", text: "Search Quận/Huyện", typeSearch: "input"},
        { name: "wardName", text: "Search Xã", typeSearch: "input"},
        { name: "address", text: "Search Địa chỉ", typeSearch: "input"},
        { name: "wardName", text: "Search Thuộc Sông", typeSearch: "input"},
        { name: "isActive", text: "", typeSearch: "select"},
      ],
    };
  },
  created(){
    this.createData()
  },
  methods: {
    async createData(){
      //this.stations = [];
      const res = await StationApi.getAll()
      this.stations = res.data
      
    },

    openCameraScreen(item){
      this.$emit("open-camera-screen", item);
    }
  },
};
</script>

<style>
.main {
  height: 100%;
}
.button-bar {
  display: flex;
  height: 40px;
  align-items: center;
  padding-left: 16px;
}
.grid-container {
  padding: 16px 16px 0 16px;
  height: calc(100% - 90px);
}
.grid-header {
  margin-bottom: 16px;
}
.grid-header input {
  height: 25px;
  width: 50px;
}
.grid {
  overflow: auto;
  height: calc(100% - 41px);
  max-width: 100%;
}
.paging-bar {
  height: 50px;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 30px;
}
</style>