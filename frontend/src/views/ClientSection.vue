<template>
  <section class="client">
    <ul class="no-link">
      <li>{{ operadora.name }} pós</li>
      <li>{{ operadora.name }} controle</li>
      <li>{{ operadora.name }} Flex</li>
      <li>{{ operadora.name }} Multi</li>
      <li>Banda larga</li>
      <li>TV</li>
      <li>{{ operadora.name }} empresas</li>
    </ul>

    <div class="bg">
      <h1>Se conecte+ assinando um de nossos serviços</h1>
      <div class="quadro">
        <div v-for="(obj, index) in planos" :key="index" class="plano">
          <div class="topo">{{ obj.name.toUpperCase() }}</div>
          <div class="mega">
            <span class="red">{{ obj.size }}</span> MEGA
          </div>
          <div class="bonus">
            <div>
              <img
                src="../assets/divider-plus.svg"
                alt="divisor"
                loading="lazy"
                width="205"
                height="12"
                style="margin: auto"
              />
            </div>
            {{ obj.bonus }}
          </div>
          <div class="description">{{ obj.description }}</div>

          <div class="price">
            <div style="grid-area: por">
              <div class="red ten">Por</div>
              <div class="red ten">apenas</div>
              <div class="red thirty-five">R$</div>
            </div>
            <div class="red price-big">{{ obj.price.split(',')[0] }}</div>
            <div>
              <div class="red" style="font-size: 23px; font-weight: bolder">
                ,{{ obj.price.split(',')[1] }}
              </div>
              <div class="month">/ mês</div>
            </div>
          </div>
          <a href="#sign" class="sign" @click="option(obj.id)">Assine</a>
        </div>
      </div>
    </div>
    <div id="sign" class="sign-block">
      <div class="container" v-if="planos[0] != null">
        <h2>A {{ operadora.name }} desde já parabeniza pela excelente escolha!!!</h2>
        <form>
          <div class="label-input">
            <label for="nome">Nome:</label>
            <input
              type="text"
              id="nome"
              name="name"
              v-model="name"
              required
              autocomplete="false"
              placeholder="Informe seu nome completo..."
            />
          </div>
          <div class="label-input">
            <label> Escolha seu plano: </label>
            <div>
              <div class="radio" v-for="(obj, index) in planos" :key="index">
                <input
                  type="radio"
                  name="plano"
                  :id="obj.id"
                  v-model="picked"
                  :value="obj.id"
                  :checked="optionValue == obj.id ? true : false"
                />
                <label :for="obj.id">{{ obj.name.toUpperCase() }}</label>
              </div>
            </div>
          </div>
          <input type="submit" value="Salvar" @click="save" />
        </form>
      </div>
      <div v-else>
        <h2>Aguardando inclusão de pacotes de serviços!!!</h2>
      </div>
    </div>
  </section>
</template>

<script>
import { baseApiUrl } from '@/main'
import axios from 'axios'

export default {
  data: function () {
    return {
      picked: 1,
      name: '',
      optionValue: 0,
      operadora: { id: 1, name: '' },
      client: {},
      planos: []
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
    option(id) {
      this.optionValue = id
    },
    save(event) {
      event.preventDefault()
      this.client.name = this.name
      this.client.picked = this.picked
      axios
        .post(`${baseApiUrl}/clients`, this.client)
        .then(() => {
          console.log('Salvou o cliente com sucesso!')
        })
        .catch((e) => console.log('Erro inexperado:' + e))
    }
  },
  mounted() {
    this.loadOperadora()
    this.loadPlanos()
  }
}
</script>

<style scoped>
.client {
  display: grid;
}
.no-link {
  background-color: white;
  color: #686d76;
  margin: 20px 150px 20px 150px;
  display: flex;
  justify-content: space-around;

  list-style-type: none;
  font-size: 16px;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: bold;
}
.bg {
  background: linear-gradient(to bottom, #3c0304, #c3151b);
}

h1 {
  padding-top: 50px;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 32px;
  text-align: center;
  color: white;
}

.quadro {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  font-family: Arial, Helvetica, sans-serif;
  padding-bottom: 50px;
}

.plano {
  width: 260px;
  margin: 10px;
  display: grid;
  background-color: white;
  color: black;
  box-shadow: -5px 5px 8px rgba(0, 0, 0, 0.377);
  border-radius: 6px;
  text-align: center;
  justify-self: center;
}

.red {
  color: red;
}

.topo {
  background-color: #5f646d;
  height: 40px;
  border-radius: 6px 6px 0px 0px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bolder;
  color: white;
}
.mega {
  margin-top: 20px;
  font-size: 58px;
  font-weight: bold;
}
.description {
  padding: 10px;
  height: 30px;
  text-overflow: ellipsis;
  margin-bottom: 30px;
}

.price {
  margin: 8px;
  display: grid;
  grid-template-columns: auto auto auto;
  gap: 2px;
  grid-template-areas: 'por valor mes';
}
.ten {
  font-size: 10px;
  text-align: left;
}
.thirty-five {
  font-size: 35px;
  text-align: left;
}
.price-big {
  grid-area: valor;
  font-size: 85px;
  font-weight: bolder;
}
.month {
  grid-area: mes;
  font-size: 12px;
}
.sign {
  height: 20px;
  background-color: #ffc722;
  border-radius: 50px;
  border: none;
  margin: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: black;
  font-weight: bold;
}
.sign:hover {
  background-color: orange;
  cursor: pointer;
}

.sign-block {
  background-color: white;
  margin: 60px;
  display: flex;
  justify-content: center;
  align-self: center;
}
</style>