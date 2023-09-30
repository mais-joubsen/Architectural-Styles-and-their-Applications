<template>
    <div class="card">
    <div class="name">
      <h5>{{student.firstName}} {{student.lastName}}</h5>
    </div>
    <h5>Email: {{student.emailAddress}}</h5>
    <button id="x" @click="$emit('del-student', student._id)">X</button>
  </div>
</template>

<script>
export default {
  name: 'student-item',
  props: ['student']
}
</script>

<style scoped>
.card {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 100px;
  background: white;
  padding: 8px;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 0 7px 25px rgba(0,0,0, 0.08);
  padding-top: 4%;

}
.name {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-left: 5px;
}
#x {
  height: 60%;
  width: 3%;
  padding: 0.2%;
}
@media (max-width: 1000px){
    .card{

      height: 110px;
      padding-right: 5px;
      padding-left: 5px;
      border-radius: 5px;
    }
h5 {
  font-size: 1em;
  padding: 5px;
}
#x {
  height: 40%;
  width: 7%;
  padding: 0.2%;
}
  }
</style>
