<template>
    <div class="page-num">
        <div class="page-first">
            <button @click="onClickFirstPage" :disabled="isInFirstPage">
              First
            </button>
            
        </div>
        <div class="page-pre">
            <button @click="onClickPrePage" :disabled="isInFirstPage">
              Previous
            </button>
        </div>
        
        
        <div class="page-num-item"
            v-for="(page,index) in pages" :key="index">
            <button @click="onClickPage(page.name)" :disabled="page.isDisabled" :class="{active: isPageActive(page.name)}">
              {{page.name}}
            </button>
        </div>
        
        <div class="page-next">
            <button @click="onClickNextPage" :disabled="isInLastPage">
                Next
            </button>
        </div>
        <div class="page-last">
            
            <button @click="onClickLastPage" :disabled="isInLastPage">
                Last
            </button>
        </div>
    </div>
</template>

<style scoped>
    .page-num {
        display: flex;
    }
    .page-pre {
        margin-right: 13px;
    }
    .page-next {
        margin-left: 13px;
    }
    .page-num button {
        border: 1px solid #bbb;
        outline: none;
        height: 35px;
        padding: 0 12px;
        background-color: #fff;
        text-align: center;
        font-size: 13px;
        
    }
    .page-num-item button, .page-first button, .page-last button{
        display: inline-block;
        padding: 0 6.5px;
    }
    .page-num button:hover {
        /* background-color: #e5e5e5; */
        cursor: pointer;
    }
    .page-num-item .active  {
        color: #111;
        font-weight: 700 !important;
        border: 1px solid #e0e0e0;
    }
</style>