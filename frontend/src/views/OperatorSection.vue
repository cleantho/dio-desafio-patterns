<template>
  <section class="operator">
    <div class="title">
      <h1>Sistema de Gestão</h1>
    </div>
    <div class="global">
      <div class="container">
        <h2>Defina o nome da sua Operadora</h2>
        <form>
          <div class="label-input">
            <label for="nome">Nome:</label>
            <input
              type="text"
              id="nome"
              name="name"
              v-model="operadora.name"
              required
              autocomplete="false"
              placeholder="Informe o Nome da operadora..."
            />
          </div>
          <input type="submit" value="Salvar" @click="save" />
        </form>
      </div>

      <div class="container">
        <h2>Envie mensagem para seus Clientes</h2>
        <form>
          <div class="label-input">
            <label for="msn">Mensagem:</label>
            <input
              type="text"
              id="msn"
              name="msn"
              v-model="operadora.msn"
              required
              autocomplete="false"
              placeholder="Digite sua mensagem..."
            />
          </div>
          <input type="submit" value="Enviar" @click="post" />
        </form>
      </div>

      <div class="container">
        <h2>Crie pacotes para seus clientes</h2>
        <form>
          <div class="label-input">
            <label> Tipo de pacote: </label>
            <div>
              <div class="radio">
                <input type="radio" id="internet" name="tipo" v-model="tipo" value="1" checked />
                <label for="internet">Internet</label>
              </div>
              <div class="radio">
                <input type="radio" id="celular" name="tipo" v-model="tipo" value="2" />
                <label for="celular">Celular</label>
              </div>
            </div>
          </div>
          <div class="label-input">
            <label for="plano">Nome:</label>
            <input type="text" id="plano" name="name" v-model="plano.name" autocomplete="false" />
          </div>
          <div class="label-input">
            <label for="mega">Mega:</label>
            <input type="text" id="mega" name="size" v-model="plano.size" />
          </div>
          <div class="label-input">
            <label for="preco">Preço:</label>
            <input type="text" id="preco" name="price" v-model="plano.price" />
          </div>
          <div class="label-input">
            <label for="descricao">Descrição:</label>
            <textarea
              id="descricao"
              name="description"
              rows="4"
              maxlength="200"
              v-model="plano.description"
            ></textarea>
          </div>
          <input type="submit" value="Salvar" @click="savePlano" />
        </form>
      </div>

      <div class="container">
        <h2>Lista de Pacotes</h2>
        <ctable class="table" hover striped :items="planos" :fields="fields"> </ctable>
      </div>

      <div class="container">
        <h2>Lista de Clientes</h2>
        <ctable class="table" hover striped :items="clientes" :fields="fieldsClientes">
          <template #actions="data">
            <i class="bi bi-envelope" @click="show(data.item)"></i>
          </template>
        </ctable>
      </div>
    </div>
  </section>
</template>

<script>
import { baseApiUrl } from '@/main'
import ctable from '@/components/Table.vue'
import axios from 'axios'
// v-show="data.notify != undefined"
export default {
  name: 'Operator',
  components: {
    ctable
  },
  data: function () {
    return {
      operadora: { id: 1, name: '' },
      tipo: 1,
      plano: {},
      planos: [],
      clientes: [],
      fields: [
        { key: 'tipo', label: 'Tipo', sortable: true },
        { key: 'name', label: 'Nome', sortable: true },
        { key: 'size', label: 'Mega', sortable: true },
        { key: 'description', label: 'Description', sortable: true },
        { key: 'price', label: 'Preço', sortable: true }
      ],
      fieldsClientes: [
        { key: 'id', label: 'Código', sortable: true },
        { key: 'name', label: 'Nome', sortable: true },
        { key: 'picked_name', label: 'Plano', sortable: true },
        { key: 'actions', label: 'Mensagem' }
      ]
    }
  },
  methods: {
    loadOperadora() {
      const url = `${baseApiUrl}/operator`
      axios
        .get(url)
        .then((res) => {
          this.operadora.name = res.data.name
        })
        .catch((e) => console.log('Erro inexperado:' + e))
    },
    loadPlanos() {
      const url = `${baseApiUrl}/plan`
      axios
        .get(url)
        .then((res) => {
          this.planos = res.data
        })
        .catch((e) => console.log('Erro inexperado:' + e))
    },
    loadClientes() {
      const url = `${baseApiUrl}/clients`
      axios
        .get(url)
        .then((res) => {
          this.clientes = res.data
        })
        .catch((e) => console.log('Erro inexperado:' + e))
    },
    save(event) {
      event.preventDefault()
      axios
        .put(`${baseApiUrl}/operator`, this.operadora)
        .then(() => {
          console.log('Salvou Operadora com sucesso!')
        })
        .catch((e) => console.log('Erro inexperado:' + e))
    },
    post(event) {
      event.preventDefault()      
      axios
        .post(`${baseApiUrl}/operator`, this.operadora.msn)
        .then(() => {
          console.log('Mensagem enviada com sucesso!')
        })
        .catch((e) => console.log('Erro inexperado:' + e))
    },
    savePlano(event) {
      event.preventDefault()
      if (this.tipo == 1) {
        this.plano.tipo = 'Internet'
      } else {
        this.plano.tipo = 'Phone'
      }
      axios
        .post(`${baseApiUrl}/plan`, this.plano)
        .then(() => {
          console.log('Salvou plano com sucesso!')
        })
        .catch((e) => console.log('Erro inexperado:' + e))
    },
    show(item) {
      const url = `${baseApiUrl}/clients/${item.id}`
      axios
        .get(url)
        .then((res) => {
          window.alert(res.data.notify)
        })
        .catch((e) => console.log('Erro inexperado:' + e))
    }
  },
  beforeMount() {
    this.loadOperadora()
    this.loadPlanos()
    this.loadClientes()
  },
  moutend() {
    this.loadClientes()
  }
}
</script>

<style>
.operator {
  background-color: #f4f2f2;
  display: grid;
}

.title h1 {
  display: flex;
  justify-content: center;
  color: black;
  font-family: 'Courier New', Courier, monospace;
  font-size: 48px;
}

.global {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.container {
  margin-bottom: 40px;
  margin-left: 20px;
  padding: 10px;
  width: 500px;
  display: block;
  background-color: white;
  color: black;
  box-shadow: -10px 5px 12px black;
  border-radius: 6px;
  text-align: center;
  justify-self: center;
}

.container h2 {
  color: var(--base-red);
}

.container form {
  display: block;
}

.label-input {
  display: grid;
  grid-template-columns: 25% 75%;
  margin-bottom: 10px;
  text-align: right;
}

.table {
  color: black;
  width: 500px;
}

.table tbody td {
  text-align: left;
}
</style>