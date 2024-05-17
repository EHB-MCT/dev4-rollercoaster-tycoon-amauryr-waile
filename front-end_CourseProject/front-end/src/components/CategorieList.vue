<template>
  <div>
    <h2>List of Categories</h2>
    <ul>
      <li v-for="categorie in categories" :key="categorie.id">
        <div>
          <h3>{{ categorie.naam }}</h3>
          <button @click="deleteCategorie(categorie.id)">Delete</button>
          <button @click="editCategorie(categorie.id)">Edit</button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'CategorieList',
  data() {
    return {
      categories: [] 
    };
  },
  mounted() {
    this.loadCategories();
  },
  methods: {
    async loadCategories() {
      try {
        const response = await fetch('http://localhost:9000/categories/all');
        if (!response.ok) {
          throw new Error(`Error loading categories: ${response.statusText}`);
        }
        const data = await response.json();
        console.log('Categories:', data);
        this.categories = data;
      } catch (error) {
        console.error('Error loading categories:', error);
      }
    },
    async deleteCategorie(id) {
      try {
        const response = await fetch(`http://localhost:9000/categories/${id}`, {
          method: 'DELETE'
        });
        if (!response.ok) {
          throw new Error(`Error deleting category: ${response.statusText}`);
        }
        this.categories = this.categories.filter(categorie => categorie.id !== id); 
        alert('Category removed successfully!');
      } catch (error) {
        console.error('Error deleting category:', error);
        alert('Error deleting category. It may be associated with existing rollercoaster.');
      }
    },
    editCategorie(id) {
      this.$router.push(`/categories/edit/${id}`);
    }
  }
};
</script>
