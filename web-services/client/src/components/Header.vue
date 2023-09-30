<template>
<div class="topbar">
    <div class="user">
        <h3>Hello there</h3>
    </div>
</div>
</template>

<script>
export default {
  name: 'header',
  props: ['header']
}
</script>

<style scoped>
.topbar {
    width: 100%;
    height: 50px;
    display: flex;
    justify-content: space-between;
    align-content: center;
    padding: 0 10px;
    background-color: #f5f5f5;
}
.title-header {
    font-size: 1rem;
    color: black;
    margin-top: 1rem;
    margin-left: 1rem;
}
.user {
    display: flex;
    margin-top: 1rem;
    margin-left: 540px;
}
@media (max-width: 1000px){
.topbar {
    width: 103%;
    height: 50px;
    display: flex;
    justify-content: space-between;
    align-content: center;
    background-color: #f5f5f5;
}
.user {
    display: flex;
    margin-top: 1rem;
    margin-left: 40%;
}
h3 {
    font-size: 120%;
}

}

</style>
