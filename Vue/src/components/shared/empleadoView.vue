<template>
  <div class="contain">
    <img src="../../assets/user.png" alt="" />
    <h1>Empleado</h1>
    <h2>{{ empleado }}</h2>
  </div>
</template>

<script>
export default {
  name: "UserView",
  props: {
    empleado: String,
  },
};
</script>


<style scoped>
.contain {
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;
  background: #9f83fa;
  border-radius: 20px;
  height: 450px;
  font: 1em sans-serif;
  font-size: 15px;
  padding: 50px;
}

img {
  width: 200px;
  height: 200px;
}
</style>
