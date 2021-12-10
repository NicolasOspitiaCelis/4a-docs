<template>
  <div>
    <button class="regresar" v-on:click="regresar">Regresar</button>
    <div class="cont">
      <div class="contenedor">
        <h1>Resumen del turno generado</h1>

        <table>
          <tr>
            <th>Numero turno</th>
            <th>Fecha</th>
            <th>Nombre</th>
            <th>Apellidos</th>
          </tr>
          <tr>
            <td>{{ turno.data.createTurn.turn }}</td>
            <td>
              {{ turno.data.createTurn.date.substring(0, 10) }}
            </td>
            <td>{{ turno.data.createTurn.names }}</td>
            <td>{{ turno.data.createTurn.lastName }}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "resumen",
  data: function () {
    return {
      turno: {},
    };
  },
  methods: {
    regresar: function () {
      this.$emit("toInicio");
    },
  },
  created: function () {
    this.turno = JSON.parse(localStorage.getItem("turn"));
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