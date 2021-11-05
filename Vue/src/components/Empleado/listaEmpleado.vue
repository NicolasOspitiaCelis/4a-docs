<template>
  <div>
    <button
      class="regresar"
      @click="$router.push('/user/administrar/g_empleados')"
    >
      Regresar
    </button>
    <div class="cont">
      <div class="contenedor">
        <h1>Lista de empleados</h1>

        <table class="tabla">
          <tr>
            <th>Id</th>
            <th>Nombre de usauario</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Cargo</th>
          </tr>
          <tr v-for="user in users" :key="user.id">
            <td v-text="user.id"></td>
            <td v-text="user.username"></td>
            <td v-text="user.name"></td>
            <td v-text="user.email"></td>
            <td v-text="user.cargo"></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "listaEmpleado",
  data: function () {
    return {
      users: [],
    };
  },
  methods: {
    getLista: async function () {
      if (
        localStorage.getItem("token_access") === null ||
        localStorage.getItem("token_refresh") === null
      ) {
        this.$emit("logOut");
        return;
      }
      await this.verifyToken();
      let token = localStorage.getItem("token_access");

      axios
        .get(`https://supermercado-be.herokuapp.com/users/`, {
          headers: { Authorization: `Bearer ${token}` },
        })
        .then((result) => {
          this.users = result.data;
        })
        .catch((error) => {
          console.log(error);

          alert(error + ". ERROR: Fallo en la consulta de usuarios.");
        });
    },
    verifyToken: function () {
      return axios
        .post(
          "https://supermercado-be.herokuapp.com/refresh/",
          { refresh: localStorage.getItem("token_refresh") },
          {
            headers: {},
          }
        )
        .then((result) => {
          localStorage.setItem("token_access", result.data.access);
        })
        .catch(() => {
          this.$emit("logOut");
        });
    },
  },
  created: function () {
    this.getLista();
  },
};
</script>
<style scoped>
.cont {
  display: flex;
}
table {
  border: 2px solid #9f83fa;
  border-radius: 3px;
  background-color: #9f83fa;
  font-size: 20px;
  color: white;
  text-align: center;
}
th {
  background-color: #ffffff;
  font-size: 30px;
  color: #9f83fa;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
td {
  background-color: #9f83fa;
}
.contenedor {
  margin: auto;
  margin-top: 10px;
  height: auto;
  width: 800px;
  background: #9f83fa;
  border-radius: 20px;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}
h1 {
  font-size: 40px;
  color: rgb(255, 255, 255);
  text-align: center;
}

.regresar {
  width: 150px;
  height: 40px;
  margin: 0px 0px 0px 50px;
  color: #000000;
  text-align: center;
  font-size: 20px;
  border-radius: 10px;
  background: #ffffff;
  border: 1px solid #000000;
  filter: blur(0.3px);
  border-radius: 20px;
  transition: all 0.5s;
}
.regresar:hover {
  width: 150px;
  height: 40px;
  margin: 0px 0px 0px 50px;
  color: #000000;
  text-align: center;
  font-size: 20px;
  border-radius: 10px;
  background: crimson;
  border: 1px solid #000000;
  filter: blur(0.3px);
  border-radius: 20px;
}
</style>