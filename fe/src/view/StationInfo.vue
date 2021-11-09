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
      <div class="grid">
        <Table :data="stations" :fields="stationFields" :searchable="true" iconOperation="fas fa-camera" @onclick-operation="openCameraScreen"/>
      </div>
    </div>
    <div class="paging-bar">
      <div class="paging-left">Showing...</div>
      <div class="paging-right">
        <Pagination />
      </div>
    </div>
  </div>
</template>

<script>
import Table from "../components/base/Table.vue";
export default {
  components: { Table },
  data() {
    return {
      stations: [],
      stationFields: [
        { name: "ins_name", text: "Search Loại trạm", typeSearch: "input" },
        { name: "ins_id", text: "Search Tên loại trạm", typeSearch: "input"},
        { name: "parameter_type_name", text: "Search Mã trạm", typeSearch: "input"},
        { name: "manufact", text: "Search Tên trạm", typeSearch: "input"},
        { name: "ins_model", text: "Search Kinh độ", typeSearch: "input"},
        { name: "status", text: "Search Vĩ độ", typeSearch: "input"},
      ],
    };
  },
  methods: {
    openCameraScreen(){
      this.$emit("open-camera-screen");
    }
  },
};
</script>

<style>
.button-bar {
  display: flex;
  height: 40px;
  align-items: center;
  padding-left: 16px;
}
.grid-container {
  padding: 16px 16px 0 16px;
}
.grid-header {
  margin-bottom: 16px;
}
.grid-header input {
  height: 25px;
  width: 50px;
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