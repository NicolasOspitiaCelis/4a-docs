<template>
  <div>
    <button
      class="regresar"
      @click="$router.push('/user/administrar/g_proveedores')"
    >
      Regresar
    </button>
    <div class="contenedor">
      <h1>Eliminar Proveedor</h1>
      <form v-if="!encontrado" v-on:submit.prevent="getProveedor">
        <div class="input-form">
          <label for="nombre">Id Proveedor: </label>
          <input type="number" id="id" />
        </div>

        <div class="btn-cont">
          <button class="form-button" type="submit">Buscar</button>
        </div>
      </form>
      <br />
      <div v-if="encontrado">
        <table>
          <thead>
            <tr>
              <th>Id</th>
              <th>Nombre de proveedor</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{{ this.proveedor.id }}</td>
              <td>{{ this.proveedor.name }}</td>
            </tr>
          </tbody>
        </table>
        <div class="btn-cont">
          <button class="form-button" v-on:click="deleteProveedor">
            Eliminar
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "eliminarProveedor",
  data: function () {
    return {
      encontrado: false,
      consulta: 0,
      proveedor: {
        id: 0,
        name: "",
      },
    };
  },
  methods: {
    getProveedor: async function () {
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
          `https://supermercado-be.herokuapp.com/proveedor/${
            document.getElementById("id").value
          }/`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        )
        .then((result) => {
          this.proveedor.id = result.data.id;
          this.proveedor.name = result.data.name;
          this.encontrado = true;
          this.consulta = document.getElementById("id").value;
        })
        .catch((error) => {
          console.log(error);

          alert(error + ". ERROR: Fallo en la consulta del proveedor.");
        });
    },
    deleteProveedor: async function () {
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
        .delete(
          `https://supermercado-be.herokuapp.com/proveedor-delete/${this.consulta}/`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        )
        .then((result) => {
          alert("Se ha eliminado el proveedor con éxito.");
          this.encontrado = false;
        })
        .catch((error) => {
          console.log(error);

          alert("ERROR: Fallo en la eliminación del proveedor.");
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
  created: function () {},
};
</script>
<style scoped>
.contenedor {
  margin: auto;
  height: 450px;
  width: 500px;
  padding: 10px;
  background: #9f83fa;
  border-radius: 20px;
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.input-form {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
  margin-bottom: 5px;
}

.input-form label {
  font-size: 20px;
}

.input-form input {
  padding: 5px;
  border-radius: 5px;
  border: 1 px solid black;
}

.btn-cont > .form-button {
  background-color: white;
  width: 100%;
  font-size: 20px;
  border-radius: 10px;
  cursor: pointer;
  filter: blur(0.3px);
  transition: all 0.5s;
}

.btn-cont > .form-button:hover {
  background-color: crimson;
  filter: blur(0.3px);
}

h1 {
  font-size: 40px;
  color: rgb(255, 255, 255);
  text-align: center;
}
button {
  width: 350px;
  height: 40px;
  margin-bottom: 5px;
  color: #000000;
  text-align: center;
  font-size: 20px;
  border-radius: 10px;
  background: #ffffff;
  border: 1px solid #000000;
  filter: blur(0.3px);
  border-radius: 20px;
}

button:hover {
  width: 350px;
  height: 40px;
  text-align: center;
  font-size: 20px;
  border-radius: 10px;
  border: 1px solid #000000;
  filter: blur(0.3px);
  border-radius: 20px;
  color: #e5e7e9;
  background: crimson;
  border: 1px solid #283747;
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
  background-color: #f1f1f1;
}
</style>
