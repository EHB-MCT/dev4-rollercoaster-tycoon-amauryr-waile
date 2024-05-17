<template>
    <div>
      <h2>Modifier l'Attraction</h2>
      <form @submit.prevent="updateAttraction">
        <label for="naam">Name :</label>
        <input type="text" id="naam" v-model="attractie.naam" required><br>
  
        <label for="capaciteit">Capacity :</label>
        <input type="number" id="capaciteit" v-model="attractie.capaciteit" required><br>
  
        <label for="categorie">Category :</label>
        <select v-model="selectedCategorie">
          <option v-for="categorie in categories" :key="categorie.id" :value="categorie">
            {{ categorie.naam }}
          </option>
        </select><br>
  
        <label for="bouwjaar">Year of construction :</label>
        <input type="number" id="bouwjaar" v-model="attractie.bouwjaar" required><br>
  
        <label for="afbeeldingUrl">Picture URL :</label>
        <input type="text" id="afbeeldingUrl" v-model="attractie.afbeeldingUrl" required><br>
  
        <label for="onrideVideoUrl">Vidéo Onride URL :</label>
        <input type="text" id="onrideVideoUrl" v-model="attractie.onrideVideoUrl"><br>
  
        <label for="lengte">Length :</label>
        <input type="number" id="lengte" v-model="attractie.lengte" required><br>
  
        <label for="tijdsduur">Time :</label>
        <input type="number" id="tijdsduur" v-model="attractie.tijdsduur" required><br>
  
        <label for="maximumHoogte">Maximum height :</label>
        <input type="number" id="maximumHoogte" v-model="attractie.maximumHoogte" required><br>
  
        <label for="maximumSnelheid">Maximum speed:</label>
        <input type="number" id="maximumSnelheid" v-model="attractie.maximumSnelheid" required><br>
  
        <button type="submit">Update</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        attractie: {
          naam: '',
          capaciteit: 0,
          bouwjaar: 0,
          afbeeldingUrl: '',
          onrideVideoUrl: '',
          lengte: 0,
          tijdsduur: 0,
          maximumHoogte: 0,
          maximumSnelheid: 0,
          categorie: null
        },
        categories: [],
        selectedCategorie: null
      };
    },
    created() {
      this.loadAttractie();
      this.loadCategories();
    },
    methods: {
      async loadAttractie() {
        const id = this.$route.params.id;
        try {
          const response = await fetch(`http://localhost:9000/attracties/${id}`);
          if (!response.ok) {
            throw new Error(`Error loading rollercoaster: ${response.statusText}`);
          }
          const data = await response.json();
          this.attractie = data;
          this.selectedCategorie = data.categorie;
        } catch (error) {
          console.error('Error loading rollercoaster:', error);
        }
      },
      async loadCategories() {
        try {
          const response = await fetch('http://localhost:9000/categories/all');
          if (!response.ok) {
            throw new Error(`Error loading categories: ${response.statusText}`);
          }
          const data = await response.json();
          this.categories = data;
        } catch (error) {
          console.error('Error loading categories:', error);
        }
      },
      async updateAttraction() {
        try {
          if (!this.selectedCategorie || !this.selectedCategorie.id) {
            console.error('Category not selected or category without ID');
            return;
          }
  
          this.attractie.categorie = {
            id: this.selectedCategorie.id,
            naam: this.selectedCategorie.naam
          };
  
          const response = await fetch(`http://localhost:9000/attracties/${this.$route.params.id}`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.attractie)
          });
  
          if (!response.ok) {
            throw new Error(`Error updating rollercoaster: ${response.statusText}`);
          }
  
          const updatedAttractie = await response.json();
          console.log('Rollercoaster updated', updatedAttractie);
          this.$router.push('/attracties/all');
        } catch (error) {
          console.error('Error updating rollercoaster', error);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  </style>
  