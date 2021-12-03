<template>
  <div>
    <input
      class="input-box acc"
      :class="{ 'border-red': invalid }"
      :type="type"
      :value="value"
      :title="message"
      @input="updateValue($event.target.value)"
      @blur="onBlur($event.target)"
      ref="BaseInput"
    />
    <div v-show="isShowMsg" class="msg" ref="Message">{{ message }}</div>
  </div>
</template>

<script>
export default {
  created() {
    if (this.field == "salary") {
      this.textAlign = "right";
    }
  },
  props: {
    label: {
      type: String,
    },
    type: {
      type: String,
    },
    value: {
      type: [String, Number],
    },
    required: {
      type: Boolean,
      default: true,
    },
    field: {
      type: String,
    },
    width: {
      type: String,
    },
    tabindex: String,
  },

  data() {
    return {
      invalid: false,
      message: "",
      textAlign: "",
      isShowMsg: false,
    };
  },

  methods: {
    updateValue: function (value) {
      this.$emit("input", value);
    },

    /**
     * Hàm xử lý khi blur khỏi trường input
     * CreatedBy: PNANH (21/8/2021)
     */
    onBlur(e) {
      if (this.required == true) {
        if (e.value == "") {
          this.invalid = true;
          this.message = `${this.label} không được để trống`;
        } else {
          this.invalid = false;
          this.message = "";
        }
      }
    },

    /**
     * Hàm validate email
     * CreatedBy: PNANH (21/8/2021)
     */
    validateEmail(email) {
      var re =
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },

    /**
     * Hàm validate số điện thoại
     * CreatedBy: PNANH (21/8/2021)
     */
    validatePhone(phone) {
      var re = /^([+]?[\s0-9]+)?(\d{3}|[(]?[0-9]+[)])?([-]?[\s]?[0-9])+$/;
      return re.test(phone);
    },

    /**
     * Hàm validate tiền tệ
     * CreatedBy: PNANH (21/8/2021)
     */
    validateSalary(salary) {
      var re = /^[0-9]+$/;
      return re.test(salary);
    },
    formatCurrency(str) {
      return Intl.NumberFormat()
        .format(Number(str?.replaceAll(".", "")?.replaceAll(",", "")) || 0)
        .toString();
    },
  },
};
</script>

<style lang="css" scoped>
.input-box {
  width: 100%;
  height: 35px;
  border: 1px solid #ced4da;
  outline: none;
  border-radius: 0.25rem;
  padding: 6px 12px;
}
.input-box:focus {
  border-color: #80bdff;
  box-shadow: 0 0 0 0.2rem rgb(0 123 255 / 25%);
}
.border-red {
  border-color: red;
}
.msg {
  color: red;
}
</style>