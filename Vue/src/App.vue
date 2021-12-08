<template>
  <div id="app" class="app">
    <div class="Nav">
      <nav>
        <button v-on:click="toInicio">Inicio</button>
      </nav>
      <h1>CAR</h1>
      <nav>
        <button v-if="in_inicio && is_auth" v-on:click="toAdmin">
          Administración
        </button>
        <button v-if="is_auth" v-on:click="logOut">Cerrar sesión</button>
        <button v-if="!is_auth" v-on:click="cargarIniciarS">
          Iniciar sesión
        </button>
      </nav>
    </div>
    <br />
    <div name="main-component">
      <router-view v-on:completedLogIn="completedLogIn"> </router-view>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  name: "App",
  data: function () {
    return {
      is_auth: false,
      in_inicio: true,
    };
  },
  methods: {
    verifyAuth: function () {
      this.is_auth = localStorage.getItem("isAuth") || false;
      if (this.is_auth == false) this.$router.push({ name: "iniciar" });
      else this.$router.push({ name: "inicio" });
    },
    toInicio: function () {
      this.in_inicio = true;
      this.$router.push({ name: "ingresarInventario" });
    },
    toAdmin: function () {
      this.in_inicio = false;
      this.$router.push({ name: "administrar" });
    },
    logOut: function () {
      alert("Sesión Cerrada, Hasta luego " + localStorage.getItem("username"));
      localStorage.clear();
      this.verifyAuth();
    },
    completedLogIn: function (data) {
      localStorage.setItem("isAuth", true);
      localStorage.setItem("username", data.username);
      localStorage.setItem("token_access", data.token_access);
      localStorage.setItem("token_refresh", data.token_refresh);
      alert("Autenticación Exitosa, Bienvenido " + data.username);
      this.verifyAuth();
    },
    cargarIniciarS: function () {
      this.$router.push({ name: "iniciar" });
    },
  },
  created: function () {
    this.verifyAuth();
    localStorage.setItem("inInicio", true);
  },
};
</script>

<style>
#app {
  font-family: "Roboto", Times, serif, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  font-size: 35px;
  text-align: start;
  color: #000000;
}

.Nav {
  display: flex;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  font-size: 35px;
  text-align: start;
  color: #ffffff;
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100px;
  justify-content: space-around;
  align-items: center;
  background: #3475ec;
  border-radius: 20px;
}

nav {
  display: flex;
}

.Nav button {
  width: 200px;
  height: 40px;
  margin: 40px;
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

.Nav button:hover {
  width: 200px;
  height: 40px;
  margin: 40px;
  text-align: center;
  font-size: 20px;
  border-radius: 10px;
  filter: blur(0.3px);
  border-radius: 20px;
  color: #e5e7e9;
  background: #1fce54;
  border: 1px solid #283747;
}
</style>
