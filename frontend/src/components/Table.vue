<template>
  <table class="table">
    <thead>
      <tr>
        <th v-for="(obj, i) in head" :key="i">
          <div class="flex-container">
            {{ obj.label }}
            <div class="text-gray" v-if="obj.sortable">
              <i
                class="bi"
                :class="dark[0] == i && dark[1] == 'up' ? 'bi-arrow-up text-dark' : 'bi-arrow-up'"
                @click="changeIconUp(i)"
              ></i>
              <i
                class="bi"
                :class="
                  dark[0] == i && dark[1] == 'down' ? 'bi-arrow-down text-dark' : 'bi-arrow-down'
                "
                @click="changeIconDown(i)"
              ></i>
            </div>
          </div>
        </th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(obj, index) in datas" :key="index" :class="trStyle(index)">
        <td v-for="(item, i) in obj" :key="i">
          {{ item }}
          <slot :name="i" :item="obj"></slot>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: 'c-Table',
  props: {
    hover: Boolean,
    striped: Boolean,
    items: [{}],
    fields: [{}]
  },
  data() {
    return {
      head: [],
      datas: [],
      dark: [-1, '']
    }
  },
  watch: {
    items(newItems) {
      this.datas = newItems
      this.process()
    }
  },
  methods: {
    process() {
      let bd
      if (this.items[0]) {
        bd = Object.keys(this.items[0])
      }
      let header = []
      let temp = {}
      for (let index in bd) {
        temp = {
          key: bd[index],
          label: bd[index]
        }
        header[index] = temp
      }
      let bodys = [{}]
      if (this.fields != []) {
        header = this.fields
        for (let y in this.items) {
          bodys[y] = {}
          for (let x in header) {
            if (header[x].formatter)
              bodys[y][header[x].key] = header[x].formatter(this.items[y][header[x].key])
            else {
              bodys[y][header[x].key] = this.items[y][header[x].key]
            }
          }
        }
      }
      this.head = header
      this.datas = bodys
    },
    trStyle(index) {
      let style = this.hover ? 'hover' : ''
      if (this.striped) {
        style += !(index % 2) ? ' striped' : ' white'
      }
      return style
    },
    changeIconUp(index) {
      this.dark = [index, 'up']
      const chave = this.head[index].key
      this.datas = this.datas.sort((a, b) => {
        if (typeof a[chave] != 'number') {
          return b[chave].localeCompare(a[chave])
        } else {
          return b[chave] - a[chave]
        }
      })
    },
    changeIconDown(index) {
      this.dark = [index, 'down']
      const chave = this.head[index].key
      this.datas = this.datas.sort((a, b) => {
        if (typeof a[chave] != 'number') {
          return a[chave].localeCompare(b[chave])
        } else {
          return a[chave] - b[chave]
        }
      })
    }
  }
}
</script>

<style>
.table thead {
  background: white;
  border-top: 2px solid rgb(240, 240, 240);
  border-bottom: 2px solid rgb(240, 240, 240);
}
.flex-container {
  display: flex;  
  flex-wrap: nowrap;
  justify-content: space-between;
}
.striped {
  background: rgb(240, 240, 240);
}
.white {
  background: white;
}
.hover:hover {
  background: rgb(230, 230, 230);
}
.text-gray {
  color: gray;
}
.text-dark {
  color: black;
  text-shadow: 0px 0px 2px black;
}
</style>