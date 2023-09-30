<template>
  <div>
    <div class="title">
      <h2>Students</h2>
    </div>
    <div class="dd">
    <input class="form-control" type="text" placeholder="Search a first name" v-model="search">
    <button class="btn" @click="findStudent">Search</button>
    <button @submit.prevent="goBack()" class="btn" @click="goBack">Back</button>
    </div>
    <div class="tt" v-if="!searched">
     <div v-for="student in students" v-bind:key="student._id">
        <student-item v-bind:student="student" v-on:del-student="deleteStudent"/>
     </div>
     </div>
     <div class="tt" v-else-if="searched">
      <p>{{filteredStudents.firstName}} {{filteredStudents.lastName}}</p>
      <p>Email: {{filteredStudents.emailAddress}}</p>
    </div>
  </div>
</template>

<script>
import { Api } from '@/Api'
import StudentItem from './StudentItem'

export default {
  name: 'courseStudentListPage',
  props: ['courseStudentListPage'],
  components: { StudentItem },
  mounted() {
    this.getAllStudents()
  },
  data() {
    return {
      students: [],
      search: '',
      searched: false,
      filteredStudents: {}
    }
  },
  methods: {
    /**
     * Get the list of all students enrolled under this course.
     * All the crashes/exceptions/errors shall be handled.
     * The student model is pre-made and its relationship to the course model can be seen under the models in backend.
     * The endpoint for this function is also missing in the backend.
     */
    getAllStudents() {
      // TODO
    },
    deleteStudent(id) {
      Api.delete(`/courses/${this.$route.params.id}/students/${id}`, {
        headers: {
          loginToken: localStorage.loginToken
        }
      })
        .then(response => {
          const index = this.students.findIndex(student => student._id === id)
          this.students.splice(index, 1)
          alert('The student was removed successfully!')
        })
        .catch(error => {
          console.log(error)
        })
    },
    findStudent() {
      const size = this.students.length
      for (let i = 0; i < size; i++) {
        if (this.students[i].firstName === this.search) {
          this.filteredStudents = this.students[i]
          this.searched = true
        }
      }
    },
    goBack() {
      this.searched = false
    }
  }
}
</script>

<style scoped>
  .dd
  {
  display: flex;
  flex-direction: row;
  }
  .tt {
    display: flex;
    flex-direction: column;
    align-content: center;
    height: 150px;
    background: white;
    padding: 8px;
    border-radius: 10px;
    cursor: default;
    width: 70rem;
    margin-left: 50px;

  }
  .button-control{
    display: flex;
    flex-direction: row;
    margin-left: 1rem;
    margin-right: 1rem;
    margin-top: 0rem;

  }
  .form-control {
    font-size: 18pt;
      display: flex;
      flex-direction: row;
      width: 40rem;
      height: 2rem;
      margin-left: 15rem;
      margin-bottom: 1rem;
      margin-top: 0rem ;
      margin-right: 0rem;

  }
  .title {
  margin-top: 15px;
  margin-left: -1080px;
  }
  .btn{
      background-color:  darkcyan;
      text-decoration-color: black;
      padding: 5px;
      display: flex;
      flex-direction: row;
      margin-left: 1rem;
      margin-right: 0rem;
      margin-top: 0rem;
      margin-bottom: 1rem;
      font-size: 13pt;
      height: 2rem;
      border: none;
    }
    @media (max-width: 1000px) {
      .tt {
    display: flex;
    flex-direction: column;
    align-content: center;
    height: 150px;
    padding: 4px;
    border-radius: 5px;
    width: 90%;
    margin-left: 10px;

  }
      .form-control{
      font-size: 10pt;
      display: flex;
      flex-direction: row;
      width: 60%;
      height: 1rem;
      margin-left: 0rem;
      margin-bottom: 1rem;
      margin-top: 1rem ;
      margin-right: 0rem;
      }
      .btn{
      padding: 1px;
      display: flex;
      flex-direction: row;
      margin-left: 3px;
      margin-right: 0rem;
      margin-top: 1rem;
      margin-bottom: 1rem;
      width: fit-content;
      font-size:.5em;
      height: 1rem;
      text-align: center;
      }
      .btn2{
      padding: 2px;
      margin-left: 1rem;
      margin-right: 0rem;
      margin-top: 1rem;
      margin-bottom: 1rem;
      font-size: 5pt;
      height: 1rem;
      text-align: center;
      }
      p{
        font-size: 1em;
      }
      h2 {
      text-align: left;
      padding-left: 1100px;
    }
    }
  </style>
