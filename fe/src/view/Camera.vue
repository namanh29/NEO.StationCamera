<template>
  <div>
    <div class="info-form">
      <div class="title">Quản lý camera từng trạm</div>
      
        <div id="station-title" class="label">Trạm đo <span>{{station.stationId}} - {{station.stationName}}</span></div>
      
      <div class="form-row">
        <div class="input-label m-b-30">
          <div class="label">Loại thiết bị <span>*</span></div>
          <Combobox
            :items="instrumentationCombobox"
            :selectedItem="camera.cameraId"
            @update-item="(item) => updateCombobox(item, 'instrumentation')"
          />
        </div>
        <div class="input-label m-b-30">
          <div class="label">Tên Camera <span>*</span></div>
          <input class="input-box" type="text" v-model="camera.cameraName" />
        </div>
      </div>
      <div class="form-row">
        <div class="input-label m-b-30">
          <div class="label">Vị trí lắp đặt <span>*</span></div>
          <input class="input-box" type="text" v-model="camera.position" />
        </div>
        <div class="input-label m-b-30">
          <div class="label">Camera Ip <span>*</span></div>
          <input class="input-box" type="text" v-model="camera.cameraIp" />
        </div>
      </div>
      <div class="form-row">
        <div class="input-label m-b-30">
          <div class="label">Trạng thái <span>*</span></div>
          <Combobox 
            :items="cameraStatus" 
            :selectedItem="camera.status" 
            @update-item="(item) => updateCombobox(item, 'status')"/>
        </div>
        <div class="input-label m-b-30">
          <div class="label">User/Pass đăng nhập <span>*</span></div>
          <div class="input-acc">
            <input
              class="input-box acc"
              type="text"
              v-model="camera.userLogin"
            />
            <span>/</span>
            <input
              class="input-box acc"
              type="password"
              v-model="camera.passLogin"
            />
          </div>
        </div>
      </div>
    </div>

    <div class="wrap-button">
      <Button
        v-if="state === 'add'"
        class="btn"
        text="Thêm"
        icon="fas fa-plus"
        @click="addCamera"
      />
      <Button
        v-if="state === 'edit'"
        class="btn"
        text="Cập nhật"
        icon="fas fa-edit"
        @click="updateCamera"
      />
      <Button
        class="btn"
        text="Làm mới"
        icon="fas fa-redo"
        @click="resetForm"
      />
      <Button
        class="btn"
        text="Quay Lại"
        icon="fas fa-long-arrow-alt-left"
        @click="closeScreen"
      />
    </div>

    <div class="grid-container">
      <div class="grid-header">
        Show
        <input type="number" value="10" />
        entries
      </div>
      <div class="grid">
        <Table
          :data="cameras"
          :fields="cameraFields"
          iconOperation="fas fa-edit"
          @onclick-operation="clickUpdate"
        />
      </div>
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
import CameraApi from "../service/cameraApi";
import InstrumentationApi from "../service/instrumentationApi";
export default {
  data() {
    return {
      state: "add",
      cameraStatus: [
        { value: 0, text: "Không dùng/Không có/Xóa" },
        { value: 1, text: "Có trong trạm" },
        { value: 2, text: "Đang lỗi" },
        { value: 3, text: "Đang mang đi bảo hành" },
      ],
      cameraFields: [
        { name: "cameraName", text: "Search Tên camera", typeSearch: "input" },
        { name: "insName", text: "Search loại thiết bị", typeSearch: "input" },
        { name: "statusName", typeSearch: "select", items: [
          { value: 0, text: "Không dùng/Không có/Xóa" },
          { value: 1, text: "Có trong trạm" },
          { value: 2, text: "Đang lỗi" },
          { value: 3, text: "Đang mang đi bảo hành" }
        ]
      },
        {
          name: "position",
          text: "Search vị trí lắp đặt",
          typeSearch: "input",
        },
        { name: "cameraIp", text: "Ip camera" },
      ],
      cameras: [],
      camera: {},
      instrumentations: [],
      instrumentationCombobox: [],
    };
  },

  props: {
    station: Object,
  },

  created() {
    this.createData();
  },

  methods: {
    async createData() {
      const promise = await Promise.all([
        CameraApi.getByStationId(this.station.stationId),
        InstrumentationApi.getAll(),
      ]);

      this.cameras = await promise[0].data;
      this.instrumentations = await promise[1].data;
      var me = this;
      this.instrumentations.forEach(function (item, index) {
        me.instrumentationCombobox[index] = {
          value: item.insId,
          text: `${item.insCode} - ${item.insName} - ${item.manufact}`,
        };
      });
      this.instrumentationCombobox = [...this.instrumentationCombobox];
    },
    closeScreen() {
      this.$emit("close-screen");
    },
    clickUpdate(item) {
      this.state = "edit";
      this.camera = item;
    },
    updateCombobox(item, type){
      if(type === 'instrumentation'){
        this.camera = {...this.camera, cameraId: item.value}
      }
      else if(type === 'status'){
        this.camera = {...this.camera, status: item.value}
      }
    },
    async addCamera() {
      try {
        this.camera = {...this.camera, stationId: this.station.stationId}
        const res = await CameraApi.add(this.camera);
        console.log(res)
        if (res.status === 200) {
          alert("Thêm thành công");
        }
        this.reloadData();
      } catch (error) {
        alert("Có lỗi xảy ra" + error);
      }
    },
    async updateCamera(){
      try {
        this.camera = {...this.camera, stationId: this.station.stationId}
        const res = await CameraApi.update(this.camera.id, this.camera)
        console.log(res)
        alert("Thêm thành công");
        this.reloadData();
      } catch (error) {
        alert("Có lỗi xảy ra" + error);
      }
    },
    resetForm(){
      this.camera = {};
    },
    async reloadData(){
      this.cameras = []
      try {
        const res = await CameraApi.getByStationId(this.station.stationId);
        this.cameras = res.data
      } catch (error) {
        alert("Có lỗi xảy ra" + error);
      }
    }
  },
};
</script>

<style scoped>
.info-form {
  margin: 15px 7.5px 0 7.5px;
  position: relative;
  border: 1px gray solid;
  padding: 10px;
}
.info-form .title {
  position: absolute;
  top: -10px;
  left: 20px;
  background-color: #fff;
  padding: 0 5px;
  font-weight: bold;
}
.form-row {
  display: flex;
  justify-content: space-between;
  margin-bottom: 16px;
}
#station-title{
  margin: 16px;
}
#station-title span {
  color: #003eff;
}
.input-label {
  width: 50%;
  padding: 0 5px;
}
 .label {
  margin-bottom: 8px;
  font-weight: 700;
}

.label span {
  color: red;
}
.input-box {
  width: 100%;
  height: 30px;
  border: 1px solid #ced4da;
  outline: none;
  border-radius: 0.25rem;
  padding-left: 10px;
}
.wrap-button {
  display: flex;
  justify-content: center;
}
.wrap-button .btn {
  margin: 0.5em 1em;
}
.input-acc {
  display: flex;

  align-items: center;
}
</style>