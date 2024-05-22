<template>
  <div id="app">
    <nav>
      <ul>
        <li><router-link to="/attracties/all">List of rollercoaster</router-link></li>
        <li><router-link to="/attracties/create">Create an rollercoaster</router-link></li>
        <li><router-link to="/categories/all">List of categories</router-link></li>
        <li><router-link to="/categories/create">Create a category</router-link></li>
      </ul>
    </nav>
    <router-view @refreshAttractions="handleRefreshAttractions"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  methods: {
    handleRefreshAttractions() {
      this.$router.push('/attracties/all');
    }
  }
};
</script>

<style>
#app {
  font-family: Arial, Helvetica, sans-serif;
  text-align: center;
}

nav {
  background-color: #333;
  overflow: hidden;
}

nav ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
}

nav ul li {
  margin: 0 15px;
}

nav ul li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 20px;
  text-decoration: none;
}

nav ul li a:hover {
  background-color: #575757;
  color: white;
  border-radius: 4px;
}
</style>
