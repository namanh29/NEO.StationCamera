<template>
  <div
    class="combobox"
    @keydown="keyEvent($event)"
  >
    <input
      class="combobox-text"
      type="text"
      v-model="cbxInput"
      @input="autocomplete"
    />
    <button @click="toggleList">
      <div class="icon-button" :class="{ reverse: isShow }"><i class="fas fa-chevron-down"></i></div>
    </button>
    <div
      v-if="isShow"
      class="combobox-list"
    >
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
  },
  data() {
    return {
      comboboxItems: [],
      isShow: false,
      cbxInput: "",
      cbxItems: [],
      currentFocus: 0,
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
    this.comboboxItems = [...this.comboboxItems];
  },

  methods: {
    /**
     * Hàm xử lý khi nhấn vào button toggle
     * CreatedBy: PNANH (21/8/2021)
     */
    toggleList() {
      this.isShow = !this.isShow;
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
      console.log(this.comboboxItems);
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
  },
};
</script>

<style scoped>
.combobox {
  display: flex;
  position: relative;
  border: 1px solid #3385ff;
  border-radius: 4px;
  width: 100%;
  height: 32px;
}

.combobox .combobox-text {
  height: 100%;
  width: calc(100% - 32px);
  padding-left: 10px;
  outline: none;
  border: none;
}
.combobox button {
  height: 100%;
  width: 32px;
  border: none;
  outline: none;
  background-color: #fff;
  cursor: pointer;
  position: relative;
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
  top: calc(100% + 2px);
  left: -1px;
  right: -1px;
  z-index: 100;
  background-color: #fff;
  border: 1px solid #babec5;
  border-radius: 2px;
  padding: 2px 0;
}

.combobox .combobox-list .combobox-item {
  height: 32px;
  line-height: 32px;
  padding-left: 16px;
}
.combobox .combobox-list .combobox-item:hover {
  background-color: #ebedf0;
  color: #2ca01c;
  cursor: pointer;
}
.combobox .selected {
  background-color: #2ca01c !important;
  color: #fff !important;
  position: relative;
  align-items: center;
  line-height: 32px;
}

.focus-item {
  background-color: #ebedf0;
}

</style>
