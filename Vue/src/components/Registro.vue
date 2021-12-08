<template>
  <div class="signUp_user">
    <div class="container_signUp_user">
      <h2>Registrar empleado</h2>
      <form v-on:submit.prevent="processSignUp">
        <input type="text" v-model="user.username" placeholder="Username" />
        <br />
        <input type="password" v-model="user.password" placeholder="Password" />
        <br />
        <input type="text" v-model="user.name" placeholder="Name" />
        <br />
        <input type="email" v-model="user.email" placeholder="Email" />
        <button type="submit">Registrarse</button>
        <p>
          Ya tienes una cuenta?
          <a href="#" v-on:click="loadLogin"> Inicia sesion!</a>
        </p>
      </form>
    </div>
  </div>
</template>
<script>
import gql from "graphql-tag";
export default {
  name: "SignUp",
  data: function () {
    return {
      user: {
        username: "",
        password: "",
        name: "",
        email: "",
      },
    };
  },
  methods: {
    processSignUp: function () {
      axios
        .post("https://supermercado-be.herokuapp.com/user/", this.user, {
          headers: {},
        })
        .then((result) => {
          let dataSignUp = {
            username: this.user.username,
            token_access: result.data.access,
            token_refresh: result.data.refresh,
          };

          this.$emit("completedSignUp", dataSignUp);
        })
        .catch((error) => {
          console.log(error);

          alert(error);
        });
    },
    loadLogin: function () {
      this.$router.push({ name: "iniciar" });
    },
  },
};
</script>
<style>
.signUp_user {
  margin: 100px;
  padding: 0%;
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.container_signUp_user {
  border: 3px solid #283747;

  border-radius: 10px;

  width: 25%;
  height: 60%;
  display: flex;

  flex-direction: column;

  justify-content: center;

  align-items: center;
}
.signUp_user h2 {
  color: #283747;
}
.signUp_user form {
  width: 70%;
}
.signUp_user input {
  height: 40px;
  width: 100%;
  box-sizing: border-box;
  padding: 10px 20px;
  border-radius: 5px;
  border: 1px solid #283747;
}
.signUp_user button {
  width: 100%;
  height: 40px;
  color: #e5e7e9;
  background: #283747;
  border: 1px solid #e5e7e9;
  border-radius: 5px;
  margin-top: 50px;
  cursor: pointer;
  transition: all 0.5s;
}
.signUp_user button:hover {
  color: #e5e7e9;
  background: crimson;
  border: 1px solid #283747;
}

p {
  margin: 0px;
}
</style>
