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
        border-color: #007bff;
        outline: none;
        align-items: center;    
        padding: 0 16px;
        border-radius: 0.25rem;
    }
    .btn-primary:hover {
        background-color: #0069d9;
    }
    .btn-primary .icon-button{
        width: 20px;
        height: 20px;
        display: flex;
        align-items: center;
        color: #fff;
    }
    .btn-primary .text-button{
        font-weight: bold;
        color: #fff;
    }
   
</style>