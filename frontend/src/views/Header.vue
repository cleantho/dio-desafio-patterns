<template>
  <header class="header">
    <img alt="logo" src="@/assets/diochip.jpg" width="125" height="125" />
    <div>
      <nav>
        <RouterLink to="/">OPERADORA</RouterLink>
        <RouterLink to="/client">CLIENTE</RouterLink>
      </nav>
    </div>
  </header>
</template>

<script>
import { RouterLink, RouterView } from 'vue-router'
export default {
  components: { RouterLink, RouterView }
}
</script>

<style scope>
.header {  
  background: var(--base-red);
  color: white;
  display: flex;
  justify-content: start;
  align-items: center;
}
header img {
    margin-right: 20px;
}
nav a {
    padding: 20px 20px;
    color: white;
    text-decoration: none;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-weight: bold;    
}

nav a:hover {
    background-color: #ECE2E3;
    color: #FA0B32;
    text-shadow: none;
    border-radius: 6px;
    box-shadow: -10px 5px 12px black;
}
</style>