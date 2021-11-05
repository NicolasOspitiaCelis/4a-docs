<template>
  <div>
    <button
      class="regresar"
      @click="$router.push('/user/administrar/g_proveedores')"
    >
      Regresar
    </button>
    <div class="contenedor">
      <h1>Ingresar Proveedor</h1>
      <form v-on:submit.prevent="ingresarProveedor">
        <div class="input-form">
          <label for="nombre">Nombre : </label>
          <input type="text" v-model="proveedor.name" />
        </div>
        <div class="btn-cont">
          <button type="submit" class="form-button">Registrar</button>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "ingresarProveedor",
  data: function () {
    return {
      proveedor: {
        name: "",
      },
    };
  },
  methods: {
    ingresarProveedor: async function () {
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
        .post(
          "https://supermercado-be.herokuapp.com/proveedor/",
          this.proveedor,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        )
        .then(() => {
          alert("Registro exitoso.");
        })
        .catch((error) => {
          console.log(error);

          alert(error + ". ERROR: Fallo en el registro del proveedor.");
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
  width: 450px;
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
  height: 45px;
  font-size: 20px;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.5s;
}

.btn-cont > .form-button:hover {
  background: crimson;
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
  filter: blur(0.4px);
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
  filter: blur(0.4px);
  border-radius: 20px;
}
transition-group-enter-active {
  animation: bounce-in 0.5s;
}
transition-group-leave-active {
  animation: bounce-in 0.5s reverse;
}
</style>