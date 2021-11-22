<template>
  <div class="page-num">
    <div class="page-first">
      <button @click="onClickFirstPage" :disabled="isInFirstPage">First</button>
    </div>
    <div class="page-pre">
      <button @click="onClickPrePage" :disabled="isInFirstPage">
        Previous
      </button>
    </div>

    <div class="page-num-item" v-for="(page, index) in pages" :key="index">
      <button
        @click="onClickPage(page.name)"
        :disabled="page.isDisabled"
        :class="{ active: isPageActive(page.name) }"
      >
        {{ page.name }}
      </button>
    </div>

    <div class="page-next">
      <button @click="onClickNextPage" :disabled="isInLastPage">Next</button>
    </div>
    <div class="page-last">
      <button @click="onClickLastPage" :disabled="isInLastPage">Last</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    maxVisibleButtons: {
      type: Number,
      required: false,
      default: 3,
    },
    totalPages: {
      type: Number,
      required: true,
    },

    currentPage: {
      type: Number,
      required: true,
    },
  },
  computed: {
    startPage() {
      // Khi ở page đầu
      if (this.currentPage === 1) {
        return 1;
      }
      // Khi ở page cuối
      if (this.currentPage === this.totalPages) {
        return this.totalPages - this.maxVisibleButtons + 1;
      }
      // Khi ở các page giữa
      return this.currentPage - 1;
    },
    endPage() {
      return Math.min(
        this.startPage + this.maxVisibleButtons - 1,
        this.totalPages
      );
    },
    pages() {
      const range = [];
      for (let i = this.startPage; i <= this.endPage; i++) {
        range.push({ name: i, isDisabled: i === this.currentPage });
      }
      return range;
    },
    isInFirstPage() {
      return this.currentPage === 1;
    },
    isInLastPage() {
      return this.currentPage === this.totalPages;
    },
  },
  methods: {
    /**
     * Xử lý sự kiện khi click trang đầu
     * CreatedBy: PNANH (20/8/2021)
     */
    onClickFirstPage() {
      this.$emit("pagechanged", 1);
    },
    /**
     * Xử lý sự kiện khi click trang trước
     * CreatedBy: PNANH (20/8/2021)
     */
    onClickPrePage() {
      this.$emit("pagechanged", this.currentPage - 1);
    },
    /**
     * Xử lý sự kiện khi click trang
     * CreatedBy: PNANH (20/8/2021)
     */
    onClickPage(page) {
      this.$emit("pagechanged", page);
    },
    /**
     * Xử lý sự kiện khi click trang sau
     * CreatedBy: PNANH (20/8/2021)
     */
    onClickNextPage() {
      this.$emit("pagechanged", this.currentPage + 1);
    },
    /**
     * Xử lý sự kiện khi click trang cuối
     * CreatedBy: PNANH (20/8/2021)
     */
    onClickLastPage() {
      this.$emit("pagechanged", this.totalPages);
    },
    isPageActive(page) {
      return this.currentPage === page;
    },
  },
};
</script>

<style scoped>
.page-num {
  display: flex;
}

.page-num button {
  border: 1px solid #dee2e6;
  outline: none;
  height: 35px;
  padding: 0 12px;
  background-color: #fff;
  color: #007bff;
  text-align: center;
  font-size: 14px;
  font-weight: 400;
}
button:disabled {
  color: #212529 !important;
  background-color: #fff !important;
  cursor: default;
}

.page-num button:hover {
  background-color: #e9ecef;
  color: #0056b3;
}
.page-num-item .active {
  color: #fff !important;
  background-color: #007bff !important;
  border-color: #007bff;
}
</style>