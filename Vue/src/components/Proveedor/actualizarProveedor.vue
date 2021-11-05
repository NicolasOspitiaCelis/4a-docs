<template>
  <div>
    <button
      class="regresar"
      @click="$router.push('/user/administrar/g_proveedores')"
    >
      Regresar
    </button>
    <div class="cont">
      <div class="contenedor">
        <h1>Actualizar Proveedor</h1>
        <form v-if="!encontrado" v-on:submit.prevent="getProveedor">
          <div class="input-form">
            <label for="nombre">ID proveedor: </label>
            <input type="number" id="id" />
            <div class="btn-cont">
              <button class="form-button" type="submit">Consultar</button>
            </div>
          </div>
        </form>
        <form v-if="encontrado" v-on:submit.prevent="putProveedor">
          <div class="input-form">
            <label for="nombre">Id Proveedor: {{ this.proveedor.id }}</label>
          </div>
          <div class="input-form">
            <label for="nombre">Nombre : </label>
            <input type="text" v-model.trim="this.proveedor.name" />
          </div>
          <div class="btn-cont">
            <button class="form-button" type="submit">Actualizar</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "actualizarProveedor",
  data: function () {
    return {
      consulta: 0,
      encontrado: false,
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

          alert(error + ". ERROR: Fallo en la consulta.");
        });
    },
    putProveedor: async function () {
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
          `https://supermercado-be.herokuapp.com/proveedor-update/${this.consulta}/`,
          this.proveedor,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        )
        .then(() => {
          alert("Se ha actualizado el proveedor con éxito.");
          this.encontrado = false;
        })
        .catch((error) => {
          console.log(error);

          alert(error + ". ERROR: Fallo en la actualización del proveedor.");
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
.cont {
  display: flex;
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
  border: 1 px solid black;
}

.btn-cont > .form-button {
  background-color: white;
  width: 100%;
  height: 50px;
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
  width: 200px;
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
