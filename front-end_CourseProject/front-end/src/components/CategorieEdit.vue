<template>
    <div>
      <h2>Modify category</h2>
      <form @submit.prevent="updateCategorie">
        <label for="naam">name :</label>
        <input type="text" id="naam" v-model="categorie.naam" required><br>
        <button type="submit">update</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        categorie: {
          naam: ''
        }
      };
    },
    created() {
      this.loadCategorie();
    },
    methods: {
      async loadCategorie() {
        const id = this.$route.params.id;
        try {
          const response = await fetch(`http://localhost:9000/categories/${id}`, {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json'
            }

          });
          if (!response.ok) {
            throw new Error(`Error loading category: ${response.statusText}`);
          }
          const data = await response.json();
          this.categorie = data;
        } catch (error) {
          console.error('Error loading category:', error);
        }
      },
      async updateCategorie() {
        try {
          const response = await fetch(`http://localhost:9000/categories/${this.$route.params.id}`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.categorie),
         
          });
  
          if (!response.ok) {
            throw new Error(`Error updating category: ${response.statusText}`);
          }
  
          const updatedCategorie = await response.json();
          console.log('Category updated:', updatedCategorie);
          this.$router.push('/categories/all');
        } catch (error) {
          console.error('Error updating category:', error);
        }
      }
    }
  };
  </script>
  
  <style scoped>
    </style>
  