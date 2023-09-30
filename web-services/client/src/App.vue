<template>
  <div id="app">
    <Menu />
    <div class="main">
      <Header />
      <router-view/>
    </div>
  </div>
</template>

<script>
import Menu from './components/Menu.vue'
import Header from './components/Header.vue'

export default {
  components: { Menu, Header }
}
</script>

<style scoped>
 #app {
  font-family: 'Montserrat', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.main {
  position: absolute;
  width: calc(100% - 210px);
  left: 210px;
  min-height: 100vh;
  background: white;
  position: relative;
}
#app-login {
  font-family: 'Montserrat', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #303433;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.background {
  width: 100%;
  min-height: 100vh;
  background-image: linear-gradient(to
    left, #c3dede, white);
  display: flex;
  flex-direction: column;
}
.login-main {
  position: absolute;
  width: calc(100% - 250px);
  top: 80px;
  left: 130px;
  min-height: 78vh;
  background: white;
  display: flex;
  justify-content: space-between;
  text-align: center;
  color: #303433;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  width: 80%;
  display: grid;
  grid-template-columns: 1fr 1fr;
}

section {
  display: flex;
  justify-content: center;
  align-items: center;
}

.section.side {
  background-size: 100% 102%;
}

.side img {
  margin-left: -55px;
  width: 90%;
  max-width: 90%;
}

.login-container {
  max-width: 500px;
  padding: 24px;
  padding-left: 65px;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #fff;
}

.title {
  text-transform: uppercase;
  font-size: 3em;
  font-weight: bold;
  text-align: center;
  letter-spacing: 1px;
}
.separator {
  width: 150px;
  height: 4px;
  background-color: darkcyan;
  margin: 24px;
}
.welcome-message {
  display: flex;
  flex-direction: row;
  text-align: center;
  font-size: 1.1em;
  line-height: 28px;
  margin-bottom: 30px;
  color: #696969;
}
.login-form {
  width: 100%;
  display: flex;
  flex-direction: column;
}
.form-control {
  width: 100%;
  position: relative;
  margin-bottom: 24px;
}
input,
button {
  border: none;
  outline: none;
  border-radius: 30px;
  font-size: 1.1m;
}
input {
  width: 100%;
  background-color: #e6e6e6;
  color: #333;
  letter-spacing: 0.5px;
  padding: 14px 64px;
}
input ~ i {
  position: absolute;
  left: 32px;
  top: 50%;
  transform: translateY(-50%);
  color: #888;
  transition: color   0.4s;
  }
  input:focus ~ i {
    color: darkcyan;
  }
  button.submit {
    color: #fff;
    padding: 14px 64px;
    margin: 32px auto;
    text-transform: uppercase;
    letter-spacing: 2px;
    font-weight: bold;
    background-image: linear-gradient(to
    right, darkcyan, black);
    cursor: pointer;
    transition: opacity 0.4;
  }
  button.submit:hover {
    opacity: 0.9;
  }
  .logoL img {
  width: 10%;
  max-width: 10%;
  padding-bottom: 5px;
  margin-left: -57rem;
  }
  .logout {
    margin-left: 1120px;
    text-transform: uppercase;
    letter-spacing: 2px;
    cursor: pointer;
    background-color:#f5f5f5;
    border-radius: 10px;
    size: 10px;
    transition: opacity 0.4;
  }

@media(max-width: 1000px)
 {
  #app {
  font-family: 'Montserrat', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#app-login {
  font-family: 'Montserrat', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #303433;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.background {
  width: auto;
  height: auto;
  background-image: linear-gradient(to
    left, #c3dede, white);
  display: flex;
  flex-direction: column;
}
.login-main {
  position: relative;
  height: calc(100% - 20px);
  top: 10px;
  left: 40px;
  background: white;
  display: flex;
  flex-direction: column;
  justify-content: baseline;
  text-align: center;
  color: #303433;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  height: 40%;
}

section {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.section.side {
  background-size: 60% 102%;
}

.side img {
 margin-left: 0px;
 height: 400px;
 width: 320px;
 max-width: 320px;
 max-height: 400px;
 image-orientation:from-image;
}

.login-container {
  max-width: 20px;
  padding: 2px;
  padding-left: 190px;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #fff;
}

.title {
  text-transform: uppercase;
  font-size: 1em;
  font-weight: bold;
  text-align: center;
  letter-spacing: 1px;
}
.separator {
  width: 10px;
  height: 2px;
  background-color: darkcyan;
  margin: 6px;
}
.welcome-message {
  display: none;
}
.login-form {
  width: 200px;
  display: flex;
  flex-direction: column;
}
.form-control {
  width: 100%;
  position: relative;
  margin-bottom: 24px;
}
input,
input {
  width: 100%;
  background-color: #e6e6e6;
  color: #333;
  letter-spacing: 0.5px;
  padding: 3px 32px;
}
input ~ i {
  position: absolute;
  left: 5px;
  top: 50%;
  transform: translateY(-50%);
  color: #888;
  transition: color   0.4s;
  }
  input:focus ~ i {
    color: darkcyan;
  }
  button.submit {
    padding: 7px 20px;
    margin: 5px auto;
  }
  button.submit:hover {
    opacity: 0.9;
  }
  .logoL img {
  width: 10%;
  max-width: 10%;
  padding-bottom: 5px;
  margin-left: -57rem;
  }
  .main{
    position: relative;
    width: calc(100% - 70px);
    left: 70px;
  }
  .logout{
    margin-left: 70%;
    margin-bottom: 2rem;
    letter-spacing: 1px;
    size: 2px;
  }
  }
</style>
