<template>
  <div class="cont">
    <div class="contenedor">
      <h1>Turno CAR</h1>
      <form v-on:submit.prevent="generarTurno">
        <div class="input-form">
          <label for="nombre">Documento : </label>
          <input type="number" v-model="turn.document" />
        </div>
        <div class="input-form">
          <label for="nombre">Nombre : </label>
          <input type="text" v-model="turn.names" />
        </div>
        <div class="input-form">
          <label for="nombre">Apellidos : </label>
          <input type="text" v-model="turn.lastName" />
        </div>
        <div class="input-form">
          <label for="nombre">Telefono : </label>
          <input type="text" v-model="turn.phone" />
        </div>
        <div class="input-form">
          <label for="nombre">Entidad : </label>
          <select v-model="turn.entity">
            <option>Acueducto</option>
            <option>Energía</option>
            <option>Policía</option>
            <option>Hacienda</option>
            <option>DIAN</option>
            <option>Educación</option>
            <option>ICBF</option>
            <option>Comisaria</option>
          </select>
        </div>
        <div class="btn-cont">
          <button class="form-button" type="submit">Generar turno</button>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import gql from "graphql-tag";
export default {
  name: "inicio",
  data: function () {
    return {
      turn: {
        entity: "",
        document: 0,
        names: "",
        lastName: "",
        phone: "",
      },
    };
  },
  methods: {
    generarTurno: async function () {
      await this.$apollo
        .mutate({
          mutation: gql`
            mutation Mutation($turn: TurnInput!) {
              createTurn(turn: $turn) {
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
          variables: {
            turn: this.turn,
          },
        })
        .then((result) => {
          alert("Turno registrado correctamente");
          this.$emit("toResume", result);
        })
        .catch((error) => {
          alert("Error al generar el turno. " + error);
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
  background: #1fce54;
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
  border: 1px solid black;
}
.input-form select {
  padding: 10px;
  border-radius: 5px;
  border: 1px solid black;
  width: 61%;
}

.btn-cont > .form-button {
  background-color: white;
  width: 100%;
  font-size: 20px;
  border-radius: 8px;
  padding: 10px;
  cursor: pointer;
  transition: all 0.5s;
}

.btn-cont > .form-button:hover {
  background-color: #3475ec;
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
  background-color: #f5edf8;
}
</style>