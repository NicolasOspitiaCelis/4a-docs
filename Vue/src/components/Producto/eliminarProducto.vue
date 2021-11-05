<template>
  <div>
    <button
      class="regresar"
      @click="$router.push('/user/administrar/g_productos')"
    >
      Regresar
    </button>

    <div class="contenedor">
      <h1>Eliminar Producto</h1>
      <form v-if="!encontrado" v-on:submit.prevent="getProducto">
        <div class="input-form">
          <label for="nombre">Id Producto: </label>
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
              <th>Nombre</th>
              <th>Id proveedor</th>
              <th>Categoria</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{{ this.producto.id }}</td>
              <td>{{ this.producto.name }}</td>
              <td>{{ this.producto.proveedor }}</td>
              <td>{{ this.producto.category }}</td>
            </tr>
          </tbody>
        </table>
        <div class="btn-cont">
          <button class="form-button" v-on:click="deleteProducto">
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
  name: "eliminarProducto",
  data: function () {
    return {
      consulta: 0,
      encontrado: false,
      producto: {
        id: 0,
        name: "",
        proveedor: {},
        category: "",
      },
    };
  },
  methods: {
    getProducto: async function () {
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
          `https://supermercado-be.herokuapp.com/producto/${
            document.getElementById("id").value
          }/`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        )
        .then((result) => {
          this.producto.id = result.data.id;
          this.producto.name = result.data.name;
          this.producto.proveedor = result.data.proveedor;
          this.producto.category = result.data.category;
          this.encontrado = true;
          this.consulta = document.getElementById("id").value;
        })
        .catch((error) => {
          console.log(error);

          alert(error + ". ERROR: Fallo en la consulta del producto.");
        });
    },
    deleteProducto: async function () {
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
          `https://supermercado-be.herokuapp.com/producto-delete/${this.consulta}/`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        )
        .then((result) => {
          alert("Se ha eliminado el producto con éxito.");
          this.encontrado = false;
        })
        .catch((error) => {
          console.log(error);

          alert("ERROR: Fallo en la eliminación del producto.");
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
  height: 40px;
  font-size: 20px;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.5s;
}

.btn-cont > .form-button:hover {
  background-color: crimson;
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