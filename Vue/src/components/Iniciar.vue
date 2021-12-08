<template>
  <div class="logIn_user">
    <div class="container_logIn_user">
      <h2>Iniciar sesión</h2>
      <form v-on:submit.prevent="processLogInUser">
        <input
          type="text"
          v-model="user.username"
          placeholder="Nombre de usuario"
        />
        <br />
        <input
          type="password"
          v-model="user.password"
          placeholder="Contraseña"
        />
        <br />
        <button type="submit">Iniciar Sesion</button>
      </form>
    </div>
  </div>
</template>
<script>
import gql from "graphql-tag";
export default {
  name: "Iniciar",
  data: function () {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    processLogInUser: async function () {
      await this.$apollo
        .mutate({
          mutation: gql`
            mutation ($credentials: CredentialsInput!) {
              logIn(credentials: $credentials) {
                refresh
                access
              }
            }
          `,
          variables: {
            credentials: this.user,
          },
        })
        .then((result) => {
          let dataLogIn = {
            username: this.user.username,
            token_access: result.data.logIn.access,
            token_refresh: result.data.logIn.refresh,
          };
          this.$emit("completedLogIn", dataLogIn);
        })
        .catch((error) => {
          alert("ERROR 401: Credenciales Incorrectas.");
        });
    },
  },
};
</script>
<style>
.logIn_user {
  margin: 0 0 0 35%;
  height: 550px;
  width: 100%;
  display: flex;
  left: 25%;
  top: 0%;
  align-items: center;
}
.container_logIn_user {
  border: 3px solid #1fce54;
  border-radius: 10px;
  width: 25%;
  height: 60%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.logIn_user h2 {
  color: #3475ec;
}
.logIn_user form {
  width: 70%;
}
.logIn_user input {
  height: 40px;
  width: 100%;
  box-sizing: border-box;
  padding: 10px 20px;
  margin: 5px 0;
  border: 1px solid #1fce54;
  border-radius: 5px;
}
.logIn_user button {
  width: 100%;
  height: 40px;
  color: #e5e7e9;
  background: #1fce54;
  border: 1px solid #e5e7e9;
  border-radius: 5px;
  padding: 10px 25px;
  cursor: pointer;
  transition: all 0.5s;
}
.logIn_user button:hover {
  color: #e5e7e9;
  background: crimson;
  border: 1px solid #283747;
}

p {
  font-size: 14px;
  margin-top: 3px;
}
</style>
