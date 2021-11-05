<template>
  <div class="administrar-continer">
    <UserView class="UserView" v-bind:empleado="this.username" />

    <div class="contenedor">
      <h1>
        Bienvenid@ <span>{{ username }}</span>
      </h1>
      <button @click="$router.push('/user/administrar/g_proveedores')">
        Gestión de proveedores
      </button>
      <button @click="$router.push('/user/administrar/g_productos')">
        Gestión de productos
      </button>
      <button @click="$router.push('/user/administrar/g_inventario')">
        Gestión de inventario
      </button>
      <button @click="$router.push('/user/administrar/g_empleados')">
        Gestión de empleados
      </button>
    </div>
  </div>
</template>
<script>
import UserView from "./shared/empleadoView.vue";
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
  methods: {},
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
  background: #9f83fa;
  border-radius: 20px;
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.UserView {
  margin: 10px 0px 0px 500px;
}
div {
  margin: 10px 0px 0px -450px;
}
h1 {
  font-size: 40px;
  color: rgb(255, 255, 255);
  text-align: center;
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
  background: crimson;
  border: 1px solid #283747;
}
</style>