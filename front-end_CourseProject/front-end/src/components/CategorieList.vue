<template>
  <div class="categories-container">
    <h2>List of Categories</h2>
    <ul class="categories-list">
      <li v-for="categorie in categories" :key="categorie.id" class="categorie-item">
        <div class="categorie-content">
          <h3>{{ categorie.naam }}</h3>
          <button @click="deleteCategorie(categorie.id)" class="delete-btn">Delete</button>
          <button @click="editCategorie(categorie.id)" class="edit-btn">Edit</button>
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

<style scoped>
.categories-container {
  max-width: 720px;
  margin: 0 auto;
  padding: 16px;
  border: 1px solid #757474;
  border-radius: 8px;
  background-color: #ffffff;
  margin: 20px auto;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.categories-list {
  list-style-type: none;
  padding: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.categorie-item {
  width: 100%;
  max-width: 600px;
  margin-bottom: 15px;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.categorie-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.categorie-content h3 {
  margin: 0;
  font-weight: normal
}

.categorie-content button {
  margin-left: 10px;
  padding: 8px 12px;
  border: none;
  border-radius: 2px;
  cursor: pointer;
}

.delete-btn {
  background-color: #000000;
  color: white;
}

.edit-btn {
  background-color: #000000;
  color: white;
}

.delete-btn:hover {
  background-color: #5c5c5c;
}

.edit-btn:hover {
  background-color: #5c5c5c;
}
</style>
