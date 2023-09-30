<template>
  <div>
    <div class="card">
    <div class="color">
      <span></span>
    </div>
    <div class="details">
      <span class="courseName">{{course.name}}</span>
      <span class="courseCode">{{course.id}}</span>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'course-item',
  props: ['course']
}
</script>

<style scoped>
.card {
  display: inline;
  position: relative;
  width: 15rem;
  background: white;
  padding: 5px;
  border-radius: 10px;
  display: flex;
  cursor: pointer;
  box-shadow: 0 7px 25px rgba(0,0,0, 0.08);
}
.color {
  display: flex;
  width: 14rem;
  margin-left: 0;
  height: 10rem;
  margin-right: 15px;
  background-image: linear-gradient(to
    left, #dbfff9, #525e75);

  box-shadow: 0 7px 25px rgba(0,0,0, 0.08);
}
.button{
display: flex;
align-items: center;
min-width: none;
text-decoration: none;
padding: 0.5rem;
margin-bottom: 1rem;
}
.details {
  display: flex;
  flex-direction: column;
  color: #2c3e50;
}
@media (max-width: 1000px){
  .card {
  display: inline;
  position: relative;
  width: 13rem;
  background: white;
  padding: 0px;
  border-radius: 5px;
  display: flex;
  cursor: pointer;
  box-shadow: 0 7px 25px rgba(0,0,0, 0.08);
}

.color {
  display: flex;
  width: 12.9rem;
  margin-left: 0;
  height: 10rem;
  margin-right: 0;
  border-radius: 5px;
  background-image: linear-gradient(to
    left, #dbfff9, #525e75);

  box-shadow: 0 7px 25px rgba(0,0,0, 0.08);
}
.button{
display: flex;
align-items: center;
min-width: none;
text-decoration: none;
padding: 0.5rem;
margin-bottom: 1rem;
}
.details {
  display: flex;
  flex-direction: column;
  color: #2c3e50;
}
}
</style>
