<template>
  <button class="btn-primary" v-on="$listeners">
    <div class="icon-button"><i :class="icons[icon]"></i></div>
    <div class="text-button">{{ text }}</div>
  </button>
</template>

<script>
export default {
  data() {
    return {
      icons: {
        add: "fas fa-plus",
        edit: "fas fa-edit",
        reset: "fas fa-redo",
        back: "fas fa-long-arrow-alt-left",
        export: "fas fa-download"
      },
    };
  },
  props: {
    text: String,
    icon: {
      type: String,
      default: "",
    },
  },
};
</script>

<style scoped>
.btn-primary {
  background-color: #007bff;
  height: 35px;
  display: flex;
  border-color: #007bff;
  outline: none;
  align-items: center;
  padding: 0 16px;
  border-radius: 0.25rem;
  min-width: 100px;
}
.btn-primary:hover {
  background-color: #0069d9;
}
.btn-primary .icon-button {
  width: 20px;
  height: 20px;
  display: flex;
  align-items: center;
  color: #fff;
}
.btn-primary .text-button {
  font-weight: bold;
  color: #fff;
}
</style>