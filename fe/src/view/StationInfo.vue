<template>
  <div class="main">
    <div class="button-bar">
      <Button class="m-r-4" text="Thêm" icon="add" />
      <Button text="Export" icon="export" />
    </div>
    <div class="grid-container">
      <div class="grid-header">
        Show
        <select v-model="perPage">
          <option value="10">10</option>
          <option value="25">25</option>
          <option value="50">50</option>
          <option value="100">100</option>
        </select>
        entries
      </div>

      <Table
        class="grid"
        :data="stations"
        :fields="stationFields"
        iconOperation="fas fa-camera"
        @onclick-operation="openCameraScreen"
        :inputSearch="stationFilter"
        @search="getStationFilter"
      />
    </div>
    <div class="paging-bar">
      <div class="paging-left">
        Showing {{ firstIndex }} to {{ lastIndex }} of
        {{ totalRecords }} entries
      </div>
      <div class="paging-right">
        <Pagination
          :total-pages="totalPages"
          :current-page="currentPage"
          @pagechanged="onPageChange"
        />
      </div>
    </div>
    <Loader v-if="isShowLoader" />
  </div>
</template>

<script>
import StationApi from "../service/stationApi";
import { mapActions } from "vuex";
export default {
  data() {
    return {
      stations: [],
      stationFields: [
        { name: "objectType", text: "Search Loại trạm", typeSearch: "input" },
        {
          name: "objectTypeShortname",
          text: "Search Tên loại trạm",
          typeSearch: "input",
        },
        { name: "stationCode", text: "Search Mã trạm", typeSearch: "input" },
        { name: "stationName", text: "Search Tên trạm", typeSearch: "input" },
        { name: "longtitude", text: "Search Kinh độ", typeSearch: "input" },
        { name: "latitude", text: "Search Vĩ độ", typeSearch: "input" },
        { name: "provinceName", text: "Search Tỉnh", typeSearch: "input" },
        {
          name: "districtName",
          text: "Search Quận/Huyện",
          typeSearch: "input",
        },
        { name: "wardName", text: "Search Xã", typeSearch: "input" },
        { name: "address", text: "Search Địa chỉ", typeSearch: "input" },
        { name: "riverName", text: "Search Thuộc Sông", typeSearch: "input" },
        {
          name: "isActiveText",
          typeSearch: "select",
          items: [
            { value: 0, text: "Không dùng/Không có/Xóa" },
            { value: 1, text: "Đang hoạt động" },
          
          ],
          value: "",
        },
      ],

      // pagination
      currentPage: 1,
      totalPages: 0,
      totalRecords: 0,
      perPage: 10,
      stationFilter: {
        objectType: "",
        objectTypeShortname: "",
        stationCode: "",
        stationName: "",
        longtitude: "",
        latitude: "",
        provinceName: "",
        districtName: "",
        wardName: "",
        address: "",
        riverName: "",
        isActiveText: "",
      },

      isShowLoader: false,
    };
  },
  watch: {
    perPage() {
      this.getStationFilter(this.stationFilter);
    },
  },
  computed: {
    firstIndex() {
      if (this.totalRecords > 0)
        return this.perPage * (this.currentPage - 1) + 1;
      else return 0;
    },
    lastIndex() {
      return this.perPage * this.currentPage < this.totalRecords
        ? this.perPage * this.currentPage
        : this.totalRecords;
    },
  },
  created() {
    this.createData();
  },
  methods: {
    ...mapActions(["addToast"]),
    async createData() {
      this.isShowLoader = true;
      var me = this
      //this.stations = [];
      try {
        const res = await StationApi.getPagingAndFilter(
          this.stationFilter,
          1,
          this.perPage
        );
        this.stations = res.data.records;
        this.totalPages = res.data.totalPages;
        this.totalRecords = res.data.totalRecords;
        setTimeout(function () {
          me.isShowLoader = false;
        }, 400);
      } catch (error) {
        this.addToast({ type: "error", message: "Có lỗi xảy ra" });
      }
    },

    openCameraScreen(item) {
      this.$emit("open-camera-screen", item);
    },

    async getStationFilter(inputSearch) {
      this.stations = [];
      this.currentPage = 1;
      this.stationFilter = inputSearch;
      try {
        const res = await StationApi.getPagingAndFilter(
          inputSearch,
          this.currentPage,
          this.perPage
        );
        this.totalPages = res.data.totalPages;
        this.totalRecords = res.data.totalRecords;
        this.stations = res.data.records;
        console.log(res.data);
      } catch (error) {
        this.addToast({ type: "error", message: "Có lỗi xảy ra" });
      }
    },
    async onPageChange(page) {
      console.log(page);
      this.currentPage = page;
      this.stations = [];
      try {
        const res = await StationApi.getPagingAndFilter(
          this.stationFilter,
          page,
          this.perPage
        );
        this.stations = res.data.records;
      } catch (error) {
        this.addToast({ type: "error", message: "Có lỗi xảy ra" });
      }
    },
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

select {
  height: calc(1.8125rem + 2px);
  border-radius: 0.2rem;
  outline: none;
  border: 1px solid #ced4da;
}
select:focus {
  border-color: #80bdff;
  box-shadow: inset 0 1px 2px rgb(0 0 0 / 8%), 0 0 5px rgb(128 189 255 / 50%);
}
</style>