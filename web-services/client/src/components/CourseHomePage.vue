<template>
  <div class="container">
    <div v-if="textExist">
        <h2>{{courseName}}</h2>
        <p>{{courseText}}</p>
    </div>
    <div v-else-if="!textExist">
        <h2>{{courseName}}</h2>
        <h4>The home page is empty at the moment.</h4>
        <h5>Fell free to provide the needed information.</h5>
        <textarea class="textArea" rows= "15" cols="125" type="text" name="input" placeholder="type here" required="required" v-model="givenText">
        </textarea>
        <div>
          <button @click="addTheText">Submit</button>
        </div>
    </div>
  </div>
</template>

<script>
import { Api } from '@/Api'

export default {
  name: 'courseHomePage',
  props: ['courseHomePage'],
  mounted() {
    this.getTheText()
  },
  data() {
    return {
      courseName: '',
      courseText: '',
      textExist: Boolean,
      givenText: ''
    }
  },
  methods: {
    getTheText() {
      Api.get(`/courses/${this.$route.params.id}`, {
      })
        .then(response => {
          this.courseName = response.data.course.name
          this.courseText = response.data.course.text
          if (this.courseText.trim().length === 0) {
            this.textExist = false
          } else {
            this.textExist = true
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    addTheText() {
      Api.patch(`/courses/${this.$route.params.id}`, {
        text: this.givenText
      }
      )
        .then(response => {
          this.courseText = response.data.course.text
          window.location.reload()
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}
</script>

<style scoped>
h2 {
  margin-top: 15px;
  text-align: left;
}
h4 {
  margin-top: 15px;
  margin-left: -70px;
}
input {
  width: 1000px;
  height: 500px;
}
.text-box {
 border: solid 1px black;
 min-width:100px;
 padding: 5px;
 display: inline-block;
}
.text-box:focus{
  outline:0;
}
textarea {
  margin-top: 25px;
}
button {
  background-color:  darkcyan;
  text-decoration-color: black;
  padding: 5px;
}
@media(max-width: 1000px){
  h2 {
  font-size: 1em;
  margin-top: 15px;
  text-align: left;
}
h4 {
  font-size: .75em;
  margin-top: 15px;
  margin-left: 0px;
  text-align: left;
}
h5{
  font-size: .75em;
  text-align: left;
}
.text-box {
 border: solid 1px black;
 min-width: 45px;
 padding: 0px;
 display: inline-block;
 margin-left: 10px;
 margin-right:4px;
}
.text-box:focus{
  outline:0;
}
textarea {
  margin-left: 24px;
  margin-right: 4px;
  margin-top: 8px;
  width: 200px;
  height: 300px;
}
input {
  width: 25px;
  height: 200px;
}
.textArea {
  width: 80%;
}
}
</style>
