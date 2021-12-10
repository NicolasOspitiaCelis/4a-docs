<template>
  <div>
    <button class="regresar" v-on:click="regresar">Regresar</button>
    <div class="cont">
      <div class="contenedor">
        <h1>Reporte de turnos de {{ reporte }}</h1>
        <table>
          <tr>
            <th>Numero turno</th>
            <th>Fecha</th>
            <th>Nombre</th>
            <th>Apellidos</th>
          </tr>
          <tr v-for="turno in turnos" :key="turno.date">
            <td v-text="turno.turn"></td>
            <td v-text="turno.date.substring(0, 10)"></td>
            <td v-text="turno.names"></td>
            <td v-text="turno.lastName"></td>
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
      turnList: [],
      turnos: [],
      reporte: "",
      cantidad: 0,
    };
  },
  apollo: {
    turnList: {
      query: gql`
        query TurnList($username: String!) {
          turnList(username: $username) {
            id
            entity
            document
            names
            lastName
            phone
            date
            turn
          }
        }
      `,
      variables() {
        return {
          username: localStorage.getItem("username"),
        };
      },
    },
  },
  methods: {
    regresar: function () {
      this.$emit("toAdmin");
    },
    filtro: function () {
      for (
        var i = 0;
        i < JSON.parse(JSON.stringify(this.turnList)).length;
        i++
      ) {
        if (JSON.parse(JSON.stringify(this.turnList))[i].entity == this.reporte)
          this.turnos.push(JSON.parse(JSON.stringify(this.turnList))[i]);
      }
    },
  },
  created: function () {
    this.reporte = localStorage.getItem("reporte");
    this.$apollo.queries.turnList.refetch();
    this.filtro();
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