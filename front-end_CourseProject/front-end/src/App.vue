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
nav ul {
  list-style-type: none;
  padding: 0;
}

nav ul li {
  display: inline;
  margin-right: 10px;
}

nav ul li a {
  text-decoration: none;
  color: red;
}
</style>
