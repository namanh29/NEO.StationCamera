<template>
    <div>
        <button class="btn-primary" v-on="$listeners">
            <div class="icon-button"><i :class="icon"></i></div>
            <div class="text-button">{{text}}</div>
        </button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            
        }
    },
    props: {
        text: String,
        icon: String
    },
}
</script>

<style scoped>
    .btn-primary{
        background-color: #007bff;
        height: 35px;
        display: flex;
        border: none;
        outline: none;
        align-items: center;
        padding: 0 16px;
    }
    .btn-primary .icon-button{
        width: 24px;
        height: 24px;
        display: flex;
        align-items: center;
        color: #fff;
    }
    .btn-primary .text-button{
        font-weight: bold;
        color: #fff;
    }
   
</style>