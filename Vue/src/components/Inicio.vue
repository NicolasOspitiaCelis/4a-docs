<template>
  <div v-if="!inCompra" class="cont">
    <div class="contenedor">
      <h1>Productos en Venta</h1>
      <br />
      <table class="tabla">
        <tr>
          <th>Producto</th>
          <th>Existencias</th>
        </tr>
        <tr v-for="producto in inventario" :key="producto.id">
          <td v-if="producto.amount > 0" v-text="producto.name"></td>
          <td v-if="producto.amount > 0" v-text="producto.amount"></td>
          <button
            v-if="producto.amount > 0"
            class="form-button"
            v-on:click="comprar(producto.id, producto.name, producto.amount)"
          >
            Comprar
          </button>
        </tr>
      </table>
    </div>
  </div>
  <div v-if="inCompra" class="cont">
    <button class="regresar" @click="inCompra = !inCompra">Regresar</button>
    <div class="contenedor2">
      <h1>Compra de {{ registroInventario.name }}</h1>
      <p>
        La cantidad a comprar debe ser igual o menor a
        {{ registroInventario.amount }}
      </p>
      <form v-on:submit.prevent="Compra">
        <div class="input-form">
          <label for="nombre">Cantidad a comprar:</label>
          <input type="number" v-model="compra.amountC" />
        </div>
        <div class="btn-cont">
          <button class="form-button" type="submit">Comprar</button>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "Inicio",
  components: {},
  data: function () {
    return {
      inCompra: false,
      inventario: [],
      registroInventario: {
        id: 0,
        name: "",
        amount: 0,
      },
      compra: {
        inventario_id: "",
        name: "",
        amountC: "",
      },
    };
  },
  methods: {
    getLista: function () {
      axios
        .get(`https://supermercado-be.herokuapp.com/inventarios/`, {
          headers: {},
        })
        .then((result) => {
          this.inventario = result.data;
        })
        .catch((error) => {
          console.log(error);

          alert(error + ". ERROR: Fallo en la consulta de inventarios.");
        });
    },
    Compra: function () {
      this.compra.inventario_id = this.registroInventario.id;

      if (this.compra.amountC > this.registroInventario.amount) {
        alert("La cantidad a comprar no puede ser mayor a las existencias.");
        return;
      }
      axios
        .post(`https://supermercado-be.herokuapp.com/compra/`, this.compra, {
          headers: {},
        })
        .then(() => {
          this.getLista();
          this.inCompra = false;
          alert("Compra registrada exitosamente.");
        })
        .catch((error) => {
          console.log(error);
          alert(error + ". ERROR: Fallo en el registro de la compra.");
        });
    },
    comprar: function (id, name, amount) {
      this.inCompra = true;
      this.registroInventario.id = id;
      this.registroInventario.name = name;
      this.compra.name = name;
      this.registroInventario.amount = amount;
    },
  },
  created: function () {
    this.getLista();
  },
};
</script>
<style scoped>
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
  background: crimson;
}

.btn-cont {
  display: flex;
  gap: 10px;
}
li {
  margin: auto;
}
p {
  font-size: 20px;
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
h1 {
  font-size: 40px;
  margin: auto;
  width: 800px;
  margin: auto;
  background: #9f83fa;
  border-radius: 20px;
  color: #ffffff;
}
.contenedor2 {
  height: auto;
  width: 900px;
  margin: -45px 0px 0px 250px;
  background: #9f83fa;
  border-radius: 20px;
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.contenedor {
  height: auto;
  width: 900px;
  margin: auto;
  background: #9f83fa;
  border-radius: 20px;
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
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
</style>