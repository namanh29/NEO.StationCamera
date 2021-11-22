<template>
  <div>
    <table class="table-Info">
      <thead>
        <!-- <th><input type="checkbox" /></th> -->
        <th>#</th>
        <th>Thao tác</th>

        <th v-for="(field, key) in fields" :key="key">
          <input
            v-if="field.typeSearch === 'input'"
            type="text"
            :placeholder="field.text"
            v-model="inputSearch[field.name]"
            @keyup.enter="onPressEnter"
          />
          <select
            v-else-if="field.typeSearch === 'select'"
            name="--Không chọn--"
            v-model="inputSearch[field.name]"
            @change="onChangeSelect"
          >
            <option disabled value="">--Không chọn--</option>
            <option
              v-for="(item, index) in field.items"
              :key="index"
              :value="item.value"
            >
              {{ item.text }}
            </option>
          </select>
          <div v-else>{{ field.text }}</div>
        </th>
      </thead>
      <tbody>
        <tr v-for="(item, index) in data" v-bind:key="index">
          <!-- <td><input type="checkbox" /></td> -->
          <td>{{ index + 1 }}</td>
          <td>
            <button class="btn-icon" @click="onClickOperation(item)">
              <i :class="iconOperation"></i>
            </button>
          </td>

          <td v-for="(field, key) in fields" :key="key">
            {{ item[field.name] }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      
    };
  },
  props: {
    fields: Array,
    data: Array,
    inputSearch: Object,
    iconOperation: String,
  },
  methods: {
    onClickOperation(item) {
      this.$emit("onclick-operation", item);
    },
    onPressEnter(){
      this.$emit("search", this.inputSearch);
      console.log(this.inputSearch);
    },
    onChangeSelect(){
      this.$emit("search", this.inputSearch);
    }
  },
};
</script>

<style scoped>
table {
  border-collapse: separate;
  width: 100%;
  border-spacing: 0;
}
th {
  position: sticky;
  top: 0;
  z-index: 5;
  background-color: #fff;
  border-top: 1px solid #bbb;
}
th,
td {
  height: 48px;
  padding: 0 16px;
  text-align: left;
  white-space: nowrap;
  border-bottom: 1px solid #bbb;
  border-right: 1px solid #bbb;
}

td:nth-child(1) {
  border-left: 1px solid #bbb;
}
th:nth-child(1) {
  border-left: 1px solid #bbb;
}
th input, th select {
  height: 27px;
  padding: 0 16px;
  border-radius: 3px;
  border: 1px solid #bbb;
}
.grid::-webkit-scrollbar-track {
  margin-top: 48px;
  width: 6px;
  background-color: #bbb;
}
.grid::-webkit-scrollbar {
  width: 5px !important;
  height: 5px !important;
}
.grid::-webkit-scrollbar-thumb {
  border-radius: 8px;
  background-color: #808080;
}
.btn-icon {
  border: none;
  outline: none;
  background: #fff;
}
</style>