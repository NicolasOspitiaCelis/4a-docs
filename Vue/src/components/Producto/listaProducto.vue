<template>
  <div>
    <button
      class="regresar"
      @click="$router.push('/user/administrar/g_productos')"
    >
      Regresar
    </button>
    <div class="cont">
      <div class="contenedor">
        <h1>Lista de Productos</h1>

        <table>
          <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Id proveedor</th>
            <th>Categoria</th>
          </tr>
          <tr v-for="producto in productos" :key="producto.id">
            <td v-text="producto.id"></td>
            <td v-text="producto.name"></td>
            <td v-text="producto.proveedor_id"></td>
            <td v-text="producto.category"></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "listaProducto",
  data: function () {
    return {
      productos: [],
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
        .get(`https://supermercado-be.herokuapp.com/productos/`, {
          headers: { Authorization: `Bearer ${token}` },
        })
        .then((result) => {
          this.productos = result.data;
        })
        .catch((error) => {
          console.log(error);

          alert(error + ". ERROR: Fallo en la consulta de productos.");
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

.contenedor {
  margin: auto;
  height: auto;
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
h1 {
  font-size: 40px;
  color: rgb(255, 255, 255);
  text-align: center;
}
button {
  width: 350px;
  height: 40px;
  margin: 30px 0 0 80px;
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
</style>