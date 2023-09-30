<template>
    <div>
        <h2>Upload files here</h2>
        <div class="upload">
        <form @submit.prevent="submitFile()" enctype="multipart/form-data">
      <div v-if="message" :class="`message ${error ? 'is-danger' : 'is-success'}`">
        <div class="message-body">{{message}}</div>
      </div>
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input type="file" name="myFiles" id="file" ref="file" v-on:change="handleFileUpload()"/>
      </label>
      <button v-on:click="submitFile()">Submit</button>
    </div>
    </form>
    </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'filesPage',
  props: ['filePage'],
  data() {
    return {
      file: '',
      message: '',
      error: false
    }
  },
  methods: {

    async submitFile() {
      const formData = new FormData()
      formData.append('file', this.file)
      try {
        await axios.post('http://localhost:3000/upload', formData,
          {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          })
        console.log('success')
        this.message = 'File has been uploaded'
        this.file = ''
        this.error = false
      } catch (err) {
        console.log(err)
        this.message = 'Something went wrong'
        this.error = true
      }
    },
    handleFileUpload() {
      this.file = this.$refs.file.files[0]
      this.error = false
      this.message = ''
    }
  }
}
</script>

<style scoped>
h2 {
  margin-top: 15px;
}
.upload {
    margin-top: 55px;
}
button {
    background-color:  darkcyan;
    text-decoration-color: black;
}
#file {
    width: 90%;
}
@media(max-width: 1000px){
  h2 {
  font-size: 1rem;
  margin-top: 5px;
}
button {
    margin-top: 15px;
}
#file {
    width: 80%;
}
}
</style>
