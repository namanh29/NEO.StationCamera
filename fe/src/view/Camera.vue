<template>
  <div>
    <div class="info-form">
      <div class="title">Quản lý camera từng trạm</div>
      <div class="form-row">Trạm đo</div>
      <div class="form-row">
        <div class="input-label m-b-30">
          <div class="label">Loại thiết bị <span>*</span></div>
          <Combobox/>
        </div>
        <div class="input-label m-b-30">
          <div class="label">Tên Camera <span>*</span></div>
          <input class="input-box" type="text" />
        </div>
      </div>
      <div class="form-row">
        <div class="input-label m-b-30">
          <div class="label">Vị trí lắp đặt <span>*</span></div>
          <input class="input-box" type="text" />
        </div>
        <div class="input-label m-b-30">
          <div class="label">Camera Ip <span>*</span></div>
          <input class="input-box" type="text" />
        </div>
      </div>
      <div class="form-row">
        <div class="input-label m-b-30">
          <div class="label">Trạng thái <span>*</span></div>
          <Combobox/>
        </div>
        <div class="input-label m-b-30">
          <div class="label">User/Pass đăng nhập <span>*</span></div>
          <div class="input-acc">
            <input class="input-box acc" type="text" />
            <span>/</span>
            <input class="input-box acc" type="password" />
          </div>
          
        </div>
      </div>
    </div>

    <div class="wrap-button">
      <Button v-if="state === 'add'" class="btn" text="Thêm" icon="fas fa-plus" @click="closeScreen"/>
      <Button v-if="state === 'edit'" class="btn" text="Cập nhật" icon="fas fa-edit"/>
      <Button class="btn" text="Làm mới" icon="fas fa-redo" @click="closeScreen"/>
      <Button class="btn" text="Quay Lại" icon="fas fa-long-arrow-alt-left" @click="closeScreen"/>
    </div>
    
    <div class="grid-container">
      <div class="grid-header">
        Show
        <input type="number" value="10" />
        entries
      </div>
      <div class="grid">
        <Table :data="stations" :fields="cameraFields" iconOperation="fas fa-edit" @onclick-operation="updateCamera"/>
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

export default {
  
  data() {
    return {
      state: 'add',
      cameraFields: [
        { name: "ins_name", text: "Search Tên camera", typeSearch: "input" },
        { name: "ins_id", text: "Search loại thiết bị", typeSearch: "input" },
        { name: "parameter_type_name", text: "", typeSearch: "select" },
        { name: "manufact", text: "Search vị trí lắp đặt", typeSearch: "input" },
        { name: "ins_model", text: "Ip camera" },
      ],
    }
  },
  
  methods: {
    closeScreen(){
      this.$emit("close-screen")
    },
    updateCamera(){
      this.state = 'edit'
    }
  },
}
</script>

<style scoped>
.info-form{
  margin: 15px 7.5px 0 7.5px;
  position: relative;
  border: 1px gray solid;
  padding: 10px
}
.info-form .title{
  position: absolute;
  top: -10px;
  left: 20px;
  background-color: #fff;
  padding: 0 5px;
  font-weight: bold;
}
.form-row{
  display: flex;
  justify-content: space-between;
  margin-bottom: 16px;
}
.input-label{
  width: 50%;
  padding: 0 5px;
}
.input-label .label {
  margin-bottom:8px;
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