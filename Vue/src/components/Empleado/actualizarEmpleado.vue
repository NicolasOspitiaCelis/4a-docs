<template>
  <button
    class="regresar"
    @click="$router.push('/user/administrar/g_empleados')"
  >
    Regresar
  </button>
  <div class="cont">
    <div class="contenedor">
      <h1>Actualizar Empleado</h1>
      <p>(Se debe actualizar la contraseña obligatoriamente)</p>
      <form v-if="!encontrado" v-on:submit.prevent="getEmpleado">
        <div class="input-form">
          <label for="nombre">ID usuario: </label>
          <input type="number" id="id" />
          <div class="btn-cont">
            <button class="form-button" type="submit">Consultar</button>
          </div>
        </div>
      </form>
      <form v-if="encontrado" v-on:submit.prevent="putEmpleado">
        <div class="input-form">
          <label for="nombre">ID de usuario: {{ this.user.id }}</label>
        </div>
        <div class="input-form">
          <label for="nombre">Nombre de usuario: </label>
          <input type="text" v-model.trim="this.user.username" />
        </div>
        <div class="input-form">
          <label for="nombre">Password: </label>
          <input type="password" v-model.trim="this.user.password" />
        </div>
        <div class="input-form">
          <label for="nombre">Nombre: </label>
          <input type="text" v-model.trim="this.user.name" />
        </div>
        <div class="input-form">
          <label for="nombre">Email: </label>
          <input type="email" v-model.trim="this.user.email" />
        </div>
        <div class="input-form">
          <label for="nombre">Cargo: </label>
          <input
            type="text"
            v-model.trim="this.user.cargo"
            placeholder="Administrador o Empleado"
          />
        </div>

        <div class="btn-cont">
          <button class="form-button" type="submit">Actualizar</button>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "actualizarEmpleado",
  data: function () {
    return {
      is_auth: false,
      consulta: 0,
      encontrado: false,
      user: {
        id: 0,
        username: "",
        password: "",
        name: "",
        email: "",
        cargo: "",
      },
    };
  },
  methods: {
    getEmpleado: async function () {
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
        .get(
          `https://supermercado-be.herokuapp.com/user/${
            document.getElementById("id").value
          }/`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        )
        .then((result) => {
          this.user.id = result.data.id;
          this.user.username = result.data.username;
          this.user.password = result.data.password;
          this.user.name = result.data.name;
          this.user.email = result.data.email;
          this.user.cargo = result.data.cargo;
          console.log(result.data);
          this.encontrado = true;
          this.consulta = document.getElementById("id").value;
        })
        .catch((error) => {
          console.log(error);

          alert(error + ". ERROR: Fallo en la consulta del usuario.");
        });
    },
    putEmpleado: async function () {
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
        .put(
          `https://supermercado-be.herokuapp.com/user-update/${this.consulta}/`,
          this.user,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        )
        .then(() => {
          alert("Se ha actualizado el usuario con éxito.");
          localStorage.setItem("username", this.user.username);
          this.encontrado = false;
        })
        .catch((error) => {
          console.log(error);

          alert(error + ". ERROR: Fallo en la actualización del usuario.");
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
    this.is_auth = localStorage.getItem("isAuth");
  },
};
</script>
<style scoped>
.cont {
  display: flex;
}
p {
  margin: 10px;
}
.contenedor {
  margin: auto;
  height: 450px;
  min-width: 450px;
  background: #9f83fa;
  border-radius: 20px;
  align-items: center;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.input-form {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 15px;
  margin-bottom: 5px;
}

.input-form label {
  font-size: 20px;
}

.input-form input {
  padding: 10px;
  border-radius: 5px;
  border: 1px solid black;
}

.btn-cont > .form-button {
  background-color: white;
  width: 100%;
  font-size: 20px;
  border-radius: 8px;
  padding: 10px;
  cursor: pointer;
  transition: all 0.5s;
}

.btn-cont > .form-button:hover {
  background: crimson;
}

.btn-cont {
  display: flex;
  gap: 10px;
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

table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  text-align: left;
  font-size: 20px;
  border: 1px solid #000000;
  padding: 5px;
  background-color: #f5edf8;
}
</style>