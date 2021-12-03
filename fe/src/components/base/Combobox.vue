<template>
  <div>
    <div
      class="combobox"
      @keydown="keyEvent($event)"
      :class="{ 'cbx-active': cbxActive, 'border-red': invalid }"
    >
      <input
        class="combobox-text"
        type="text"
        v-model="cbxInput"
        @focus="cbxActive = true"
        @blur="onBlur($event.target)"
        @input="autocomplete"
        ref="BaseInput"
        :title="message"
      />
      <button @click="toggleList">
        <div class="icon-button" :class="{ reverse: isShow }">
          <i class="fas fa-chevron-down"></i>
        </div>
      </button>
      
      <div v-if="isShow" class="combobox-list">
        <div
          class="combobox-item"
          v-for="(cbxItem, index) in cbxItems"
          :class="{
            selected: cbxItem.isSelected,
            'focus-item': index == currentFocus,
          }"
          :key="index"
          @click="clickItem(cbxItem)"
        >
          {{ cbxItem.text }}
        </div>
      </div>
    </div>
    <div v-show="isShowMsg" class="msg" ref="Message">{{message}}</div>
  </div>
</template>


<script>
export default {
  props: {
    items: Array,
    selectedItem: {
      type: [String, Number],
    },
    isDisable: {
      type: Boolean,
      default: false,
    },
    required: {
      type: Boolean,
      default: true
    },
    label: {
      type: String,
      default: "Trường này"
    }
  },
  data() {
    return {
      comboboxItems: [],
      isShow: false,
      cbxInput: "",
      cbxItems: [],
      currentFocus: 0,
      cbxActive: false,
      invalid: false,
      message: "",
      isShowMsg: false
    };
  },
  watch: {
    selectedItem: {
      handler: function (val) {
        this.cbxInput = "";
        this.comboboxItems.forEach((item) => {
          item.isSelected = false;
          if (item.value === val) {
            this.cbxInput = item.text;
            item.isSelected = true;
          }
        });
      },
      deep: true,
    },
    items: {
      handler: function (items) {
        var me = this;
        items.forEach(function (item, index) {
          me.comboboxItems[index] = {
            value: item.value,
            text: item.text,
            isSelected: false,
          };

          if (me.comboboxItems[index].value === me.selectedItem) {
            me.comboboxItems[index].isSelected = true;
            me.cbxInput = me.comboboxItems[index].text;
          }
        });
        this.comboboxItems = [...this.comboboxItems];
        console.log(this.comboboxItems);
      },
      deep: true,
    },
  },
  created() {
    var me = this;
    this.items.forEach(function (item, index) {
      me.comboboxItems[index] = {
        value: item.value,
        text: item.text,
        isSelected: false,
      };

      if (me.comboboxItems[index].value === me.selectedItem) {
        me.comboboxItems[index].isSelected = true;
        me.cbxInput = me.comboboxItems[index].text;
      }
    });
    //this.comboboxItems = [...this.comboboxItems];
    //console.log(this.comboboxItems);
  },

  mounted() {
    document.addEventListener("click", this.handleClickOutside);
  },
  // xóa sự kiện này khi thoát khỏi xóa component
  destroyed() {
    document.removeEventListener("click", this.handleClickOutside);
  },

  methods: {
    /**
     * Hàm xử lý khi nhấn vào button toggle
     * CreatedBy: PNANH (21/8/2021)
     */
    toggleList() {
      this.isShow = !this.isShow;
      this.cbxActive = this.isShow;
      //this.$refs.BaseInput.focus();
      this.cbxItems = this.comboboxItems;
      this.currentFocus = 0;
    },

    /**
     * Hàm xử lý khi chọn 1 item trong combobox
     * CreatedBy: PNANH (21/8/2021)
     */
    clickItem(cbxItem) {
      this.$refs.BaseInput.focus();
      this.currentFocus = 0;
      this.cbxInput = cbxItem.text;
      this.comboboxItems.forEach((item) => {
        item.isSelected = false;
        if (item.value == cbxItem.value) {
          item.isSelected = true;

          this.$emit("update-item", item);
        }
      });

      //this.$refs.BaseInput.blur();
      this.isShow = !this.isShow;
      //console.log(this.comboboxItems);
    },

    /**
     * Hàm autocomplete
     * CreatedBy: PNANH (21/8/2021)
     */
    autocomplete() {
      this.currentFocus = 0;
      var me = this;
      this.isShow = true;

      var val = this.cbxInput;
      if (val.length != 0) {
        this.cbxItems = [];
        this.comboboxItems.forEach(function (item) {
          item.isSelected = false;
          if (item.text.toUpperCase().includes(val.toUpperCase())) {
            me.cbxItems.push(item);
          }
        });
      } else {
        this.cbxItems = [];
      }
      if (this.cbxItems.length === 0) {
        this.isShow = false;
      }
    },

    onBlur(e) {
      this.cbxActive = false;
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
     * Hàm xử lý sự kiện sử dụng bàn phím
     * CreatedBy: PNANH (21/8/2021)
     */
    keyEvent(event) {
      if (this.cbxItems.length != 0) {
        console.log(this.cbxItems);
        if (event.keyCode == 40) {
          event.preventDefault();
          this.currentFocus++;
          if (this.currentFocus >= this.cbxItems.length) this.currentFocus = 0;
          if (this.currentFocus < 0)
            this.currentFocus = this.cbxItems.length - 1;
        } else if (event.keyCode == 38) {
          event.preventDefault();
          this.currentFocus--;
          if (this.currentFocus >= this.cbxItems.length) this.currentFocus = 0;
          if (this.currentFocus < 0)
            this.currentFocus = this.cbxItems.length - 1;
        } else if (event.keyCode == 13) {
          event.preventDefault();
          if (this.currentFocus > -1) {
            this.clickItem(this.cbxItems[this.currentFocus]);
          }
        }
      }
    },
    handleClickOutside(event) {
      if (!this.$el.contains(event.target)) {
        this.cbxActive = false;
        this.isShow = false;
      }
    },
  },
};
</script>

<style scoped>
.combobox {
  display: flex;
  position: relative;
  border: 1px solid #ced4da;
  border-radius: 4px;
  width: 100%;
  height: 35px;
}
.combobox:focus-within {
  border-color: #80bdff !important;
  box-shadow: 0 0 0 0.2rem rgb(0 123 255 / 25%);
}

.combobox .combobox-text {
  height: 100%;
  width: calc(100% - 35px);
  padding: 0 10px;
  outline: none;
  border: none;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  border-radius: inherit;
}
.combobox button {
  height: 100%;
  width: 35px;
  border: none;
  outline: none;
  background-color: #fff;
  cursor: pointer;
  position: relative;
  border-radius: inherit;
}
.combobox button:hover {
  background-color: #e9ebee;
}

.combobox .icon-button {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translateX(-50%) translateY(-50%) rotate(0deg);
  transition: transform 0.3s linear;
}
.combobox .reverse {
  transform: translateX(-50%) translateY(-50%) rotate(180deg);
  transition: transform 0.3s linear;
}
.combobox .combobox-list {
  position: absolute;
  top: calc(100% + 0.2rem);
  left: -1px;
  right: -1px;
  z-index: 100;
  background-color: #fff;
  border: 1px solid #babec5;
  border-radius: 2px;
  padding: 2px 0;
  max-height: 250px;
  overflow: auto;
}

.combobox .combobox-list .combobox-item {
  padding: 8px 16px;
}
.combobox .combobox-list .combobox-item:hover {
  background-color: #0074f0;
  color: #fff;
  cursor: pointer;
}
.combobox .selected {
  background-color: #5897fb !important;
  color: #fff !important;
  position: relative;
  align-items: center;
}

.focus-item {
  background-color: #ebedf0;
}
/* .cbx-active {
  border-color: #80bdff !important;
  box-shadow: 0 0 0 0.2rem rgb(0 123 255 / 25%);
} */
.border-red {
  border: 1px solid #ff4747;
}
.msg {
  
  color: red;
  
}
</style>
