<template>
    <div>
      <h2>Fill in the student's information</h2>
      <h4>Social security number</h4>
        <form>
            <input type="ssn" required v-model="ssn" placeholder="SSN">
        </form>
        <h4>First name</h4>
        <form>
            <input type="firstName" required v-model="fName" placeholder="John">
        </form>
        <h4>Last name</h4>
        <form>
            <input type="lastName" required v-model="lName" placeholder="Andersson">
        </form>
        <h4>Email address</h4>
        <form>
            <input type="emailAddress" required v-model="email" placeholder="example@mail.com">
        </form>
        <button @click="addStudent">Submit</button>

    </div>
</template>

<script>

import axios from 'axios'

export default {
  name: 'courseAddStudentPage',
  props: ['courseAddStudentPage'],
  data() {
    return {
      ssn: '',
      fName: '',
      lName: '',
      email: ''
    }
  },
  methods: {
    /**
     * Get the student's information from the form and add the student under the correct course.
     * In case of success, the web page should prompt the user with a success message (of any kind).
     * Otherwise, an informative message regarding the issue shall be displayed (of any kind).
     * All the crashes/exceptions/errors shall be handled.
     * The student model is pre-made and its relationship to the course model can be seen under the models in backend.
     * Create the students based on the models and according to the relationship between a student and a course.
     * The endpoint for this function is also missing in the backend.
     */
    async addStudent() {
      try {
        // Create an object with the student data
        const studentData = {
          ssn: this.ssn,
          firstName: this.fName,
          lastName: this.lName,
          email: this.email
        }

        // Make an HTTP POST request to your backend to add the student
        const response = await axios.post(`/api/courses/${this.courseAddStudentPage}/students`, studentData)

        // Check if the request was successful
        if (response.status === 201) {
          // Student added successfully, display a success message
          console.log('Student added successfully:', response.data)
          // You can also reset the form fields if needed
          this.ssn = ''
          this.fName = ''
          this.lName = ''
          this.email = ''
        } else {
          // Handle other response codes or error cases
          console.error('Failed to add student:', response.data)
          // You can display an error message to the user
        }
      } catch (error) {
        // Handle network errors or other exceptions
        console.error('An error occurred:', error)
        // You can display an error message to the user
      }
    }
  }
}
</script>

<style scoped>
h2 {
  margin-top: 15px;

}
input {
  border: none;
  outline: none;
  border-radius: 20px;
  font-size: 20m;
  background-color: #e6e6e6;
  color: #333;
  letter-spacing: 1px;
  width: 20rem;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 15px;

}
h4 {
  padding-top: 30px;

}
button {
    color: #fff;
    padding: 14px 64px;
    margin: 32px auto;
    text-transform: uppercase;
    letter-spacing: 2px;
    font-weight: bold;
    border-radius: 10px;
    background-color: darkcyan;
    cursor: pointer;
    transition: opacity 0.4;
  }
  button:hover {
    opacity: 0.9;
  }
@media (max-width: 1000px){
  h2 {
  font-size: 1em;
  margin-top: 15px;
}
h4 {
  font-size: 1em;
  padding-top: 30px;

}
input {
  border-radius: 20px;
  font-size: 1em;
  width: 15rem;

}
button {
  width: 150px;
  padding: 7px 25px;

  }
}
</style>
