<template>
    <div>
      <h2>Create Category</h2>
      <form @submit.prevent="createCategorie">
        <label for="naam">Name of category :</label>
        <input type="text" id="naam" v-model="categorieNaam" required>
        <button type="submit">Create</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        categorieNaam: ''
      };
    },
    methods: {
      async createCategorie() {
        try {
          const response = await fetch('http://localhost:9000/categories/create', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({ naam: this.categorieNaam })
          });
  
          if (!response.ok) {
            throw new Error(`Error creating category: ${response.statusText}`);
          }
  
          const newCategorie = await response.json();
          console.log('New category created:', newCategorie);
          this.categorieNaam = ''; 
          alert('Category created successfully!');
        } catch (error) {
          console.error('Error creating category:', error);
          alert('Error creating category. Try Again.');
        }
      }
    }
  };
  </script>
  