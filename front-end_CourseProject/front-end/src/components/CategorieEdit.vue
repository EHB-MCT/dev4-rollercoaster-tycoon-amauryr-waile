<template>
  <div class="form-container">
    <h2>Modify category</h2>
    <form @submit.prevent="updateCategorie" class="categorie-form">
      <div class="form-group">
        <label for="naam">Name:</label>
        <input type="text" id="naam" v-model="categorie.naam" required>
      </div>
      <button type="submit">Update</button>
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
          body: JSON.stringify(this.categorie)
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
