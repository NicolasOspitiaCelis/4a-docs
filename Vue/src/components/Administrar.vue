<template>
  <div class="administrar-continer">
    <UserView class="UserView" v-bind:empleado="this.username" />

    <div class="contenedor">
      <h1>
        Bienvenid@ <span>{{ username }}</span>
      </h1>
      <select id="selected">
        <option selected value="0">Elige una opcion</option>
        <option value="1">Acueducto</option>
        <option value="2">Energía</option>
        <option value="3">Policía</option>
        <option value="4">Hacienda</option>
        <option value="5">DIAN</option>
        <option value="6">Educación</option>
        <option value="7">ICBF</option>
        <option value="8">Comisaria</option>
      </select>
      <button v-on:click="generarReporte">Generar reporte</button>
    </div>
  </div>
</template>
<script>
import UserView from "./shared/empleadoView.vue";
import gql from "graphql-tag";
export default {
  name: "Administrar",
  components: {
    UserView,
  },
  data: function () {
    return {
      username: localStorage.getItem("username") || "none",
    };
  },
  methods: {
    generarReporte: async function () {
      if (
        localStorage.getItem("token_access") === null ||
        localStorage.getItem("token_refresh") === null
      ) {
        this.$emit("logOut");
        return;
      }
      localStorage.setItem("token_access", "");
      await this.$apollo
        .mutate({
          mutation: gql`
            mutation ($refresh: String!) {
              refreshToken(refresh: $refresh) {
                access
              }
            }
          `,
          variables: {
            refresh: localStorage.getItem("token_refresh"),
          },
        })
        .then((result) => {
          localStorage.setItem("token_access", result.data.refreshToken.access);
        })
        .catch((error) => {
          this.$emit("logOut");
          return;
        });

      let combo = document.getElementById("selected");
      if (combo.options[combo.selectedIndex].text == "Elige una opcion")
        return alert("Debes elegir una opción para generar un reporte");
      let selected = combo.options[combo.selectedIndex].text;
      return this.$emit("toReport", selected);
    },
  },
  created: function () {
    if (localStorage.getItem("isAuth") == false) $router.push("/user/iniciar");
  },
};
</script>
<style scoped>
.administrar-continer {
  display: flex;
  justify-content: space-around;
}

.contenedor {
  height: 450px;
  width: 450px;
  background: #1fce54;
  border-radius: 20px;
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.UserView {
  margin: 10px 0px 0px 500px;
  background: #1fce54;
}
div {
  margin: 10px 0px 0px -450px;
}
h1 {
  font-size: 43px;
  color: rgb(255, 255, 255);
  text-align: center;
}
h2 {
  font-size: 30px;
  color: rgb(255, 255, 255);
  text-align: center;
}
select {
  width: 300px;
  height: 40px;
  margin-bottom: 5px;
  color: #000000;
  text-align: center;
  font-size: 20px;
  border-radius: 10px;
  background: #ffffff;
  border: 1px solid #000000;
  filter: blur(0.4px);
  border-radius: 20px;
  cursor: pointer;
  transition: all 0.5s;
}
select:hover {
  width: 300px;
  height: 40px;
  text-align: center;
  font-size: 20px;
  border-radius: 10px;
  border: 1px solid #000000;
  filter: blur(0.4px);
  border-radius: 20px;
  color: #e5e7e9;
  background: #3475ec;
  border: 1px solid #283747;
}
select option {
  text-align: center;
  font-size: 15px;
}
button {
  width: 300px;
  height: 40px;
  margin-bottom: 5px;
  color: #000000;
  text-align: center;
  font-size: 20px;
  border-radius: 10px;
  background: #ffffff;
  border: 1px solid #000000;
  filter: blur(0.4px);
  border-radius: 20px;
  cursor: pointer;
  transition: all 0.5s;
}

button:hover {
  width: 300px;
  height: 40px;
  text-align: center;
  font-size: 20px;
  border-radius: 10px;
  border: 1px solid #000000;
  filter: blur(0.4px);
  border-radius: 20px;
  color: #e5e7e9;
  background: #3475ec;
  border: 1px solid #283747;
}
</style>