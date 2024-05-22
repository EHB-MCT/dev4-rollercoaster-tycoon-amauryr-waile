<template>
  <div class="form-container">
    <h2>Create Category</h2>
    <form @submit.prevent="createCategorie" class="categorie-form">
      <div class="form-group">
        <label for="naam">Name of category:</label>
        <input type="text" id="naam" v-model="categorieNaam" required>
      </div>
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

<style scoped>
.form-container {
  max-width: 720px;
  margin: 0 auto;
  padding: 16px;
  border: 1px solid #757474;
  border-radius: 8px;
  background-color: #ffffff;
  margin: 20px auto;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.categorie-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  display: flex;
  justify-content: space-between;
  margin-bottom: 12px;
}

.form-group label {
  flex: 1;
  margin-right: 25px;
}

.form-group input {
  flex: 2;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 2px;
}

button {
    padding: 11px 16px;
    border: none;
    border-radius: 2px;
    background-color: #000000;
    color: white;
    cursor: pointer;
    margin: 20px
  }
   
  button:hover {
    background-color: #5c5c5c;
  }
</style>
