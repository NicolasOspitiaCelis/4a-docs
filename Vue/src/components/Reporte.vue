<template>
  <div>
    <button
      class="regresar"
      @click="$router.push('/user/administrar/g_inventario')"
    >
      Regresar
    </button>
    <div class="cont">
      <div class="contenedor">
        <h1>Reporte de turnos</h1>

        <table>
          <tr>
            <th>Numero turno</th>
            <th>Fecha</th>
            <th>Hora</th>
            <th>Usuario</th>
          </tr>
          <tr v-for="inventario in inventarios" :key="inventario.id">
            <td v-text="inventario.id"></td>
            <td v-text="inventario.name"></td>
            <td v-text="inventario.category"></td>
            <td v-text="inventario.producto_id"></td>
            <td v-text="inventario.amount"></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>
<script>
import gql from "graphql-tag";
export default {
  name: "reporte",
  data: function () {
    return {
      inventarios: [],
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

      axios
        .get(`https://supermercado-be.herokuapp.com/inventarios/`, {
          headers: {},
        })
        .then((result) => {
          this.inventarios = result.data;
        })
        .catch((error) => {
          console.log(error);

          alert(error + ". ERROR: Fallo en la consulta de inventarios.");
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
  min-width: 600px;
  background: #1fce54;
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
  border: 2px solid #1fce54;
  border-radius: 3px;
  background-color: #1fce54;
  font-size: 20px;
  color: white;
  text-align: center;
}
th {
  background-color: #ffffff;
  font-size: 30px;
  color: #1fce54;
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