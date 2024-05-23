<template>
  <div class="form-container">
    <h1>Create new Rollercoaster</h1>
    <form @submit.prevent="createAttractionWithOnderhoud" class="attractie-form">
      <div class="form-group">
        <label for="naam">Name:</label>
        <input type="text" id="naam" v-model="attractie.naam" required />
      </div>
      <div class="form-group">
        <label for="capaciteit">Capacity:</label>
        <input type="number" id="capaciteit" v-model="attractie.capaciteit" required />
      </div>
      <div class="form-group">
        <label for="categorie">Category:</label>
        <div class="categorie-selection">
          <select v-model="selectedCategorie" id="categorie">
            <option v-for="categorie in categories" :key="categorie.id" :value="categorie">
              {{ categorie.naam }}
            </option>
          </select>
          <button type="button" @click="showCategoryModal = true">Add Category</button>
        </div>
      </div>
      <div class="form-group">
        <label for="bouwjaar">Year of construction:</label>
        <input type="number" id="bouwjaar" v-model="attractie.bouwjaar" required />
      </div>
      <div class="form-group">
        <label for="afbeeldingUrl">Picture URL:</label>
        <input type="text" id="afbeeldingUrl" v-model="attractie.afbeeldingUrl" required />
      </div>
      <div class="form-group">
        <label for="onrideVideoUrl">Onride Video URL:</label>
        <input type="text" id="onrideVideoUrl" v-model="attractie.onrideVideoUrl" />
      </div>
      <div class="form-group">
        <label for="lengte">Length - meters:</label>
        <input type="number" id="lengte" v-model="attractie.lengte" required />
      </div>
      <div class="form-group">
        <label for="tijdsduur">Time - seconds:</label>
        <input type="number" id="tijdsduur" v-model="attractie.tijdsduur" required />
      </div>
      <div class="form-group">
        <label for="maximumHoogte">Maximum Height - meters:</label>
        <input type="number" id="maximumHoogte" v-model="attractie.maximumHoogte" required />
      </div>
      <div class="form-group">
        <label for="maximumSnelheid">Maximum Speed - km/h:</label>
        <input type="number" id="maximumSnelheid" v-model="attractie.maximumSnelheid" required />
      </div>

      <div class="form-group" v-for="(onderhoud, index) in onderhouds" :key="index">
        <label for="onderhoudDatum">Maintenance Date:</label>
        <input type="date" v-model="onderhoud.datum" />
        <label for="onderhoudOpgelost">Resolved:</label>
        <input type="checkbox" v-model="onderhoud.opgelost" />
        <button type="button" @click="removeOnderhoud(index)">Remove</button>
      </div>
      <button type="button" @click="addOnderhoud">Add Maintenance Date</button>
      <button type="submit">Create</button>
    </form>
    <div v-if="showCategoryModal" class="modal-overlay">
      <div class="modal-container">
        <h2>Create Category</h2>
        <form @submit.prevent="createCategorie" class="categorie-form">
          <div class="form-group">
            <label for="newCategorieNaam">Name of category:</label>
            <input type="text" id="newCategorieNaam" v-model="newCategorieNaam" required />
          </div>
          <button type="submit">Create</button>
          <button type="button" @click="showCategoryModal = false">Close</button>
        </form>
      </div>
    </div>
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
      selectedCategorie: null,
      onderhouds: [],
      showCategoryModal: false,
      newCategorieNaam: ''
    }
  },
  methods: {
    addOnderhoud() {
      this.onderhouds.push({ datum: '', opgelost: false })
    },
    removeOnderhoud(index) {
      this.onderhouds.splice(index, 1)
    },
    async createAttractionWithOnderhoud() {
      try {
        if (!this.selectedCategorie || !this.selectedCategorie.id) {
          console.error('Category not selected or category without ID.')
          return
        }

        this.attractie.categorie = {
          id: this.selectedCategorie.id,
          naam: this.selectedCategorie.naam
        }

        const attractionData = {
          ...this.attractie,
          onderhouds: this.onderhouds
        }

        console.log('Rollercoaster data before sending:', attractionData)

        const response = await fetch('http://localhost:9000/attracties/create', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(attractionData)
        })

        if (!response.ok) {
          throw new Error(`Error creating rollercoaster: ${response.statusText}`)
        }

        const newAttractie = await response.json()
        console.log('New Rollercoaster created:', newAttractie)

        this.$emit('refreshAttractions')
        this.$router.push('/attracties/all')
      } catch (error) {
        console.error('Error creating the rollercoaster or maintenance:', error)
      }
    },
    async createCategorie() {
      try {
        const response = await fetch('http://localhost:9000/categories/create', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ naam: this.newCategorieNaam })
        })

        if (!response.ok) {
          throw new Error(`Error creating category: ${response.statusText}`)
        }

        const newCategorie = await response.json()
        console.log('New category created:', newCategorie)
        this.categories.push(newCategorie)
        this.newCategorieNaam = ''
        this.showCategoryModal = false
      } catch (error) {
        console.error('Error creating category:', error)
        alert('Error creating category. Try Again.')
      }
    },
    async loadCategories() {
      try {
        const response = await fetch('http://localhost:9000/categories/all')
        if (!response.ok) {
          throw new Error(`Error loading categories: ${response.statusText}`)
        }
        this.categories = await response.json()
      } catch (error) {
        console.error('Error loading categories:', error)
      }
    }
  },
  mounted() {
    this.loadCategories()
  }
}
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

.attractie-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  display: flex;
  align-items: center;
  margin-bottom: 12px;
}

.form-group label {
  flex: 1;
  margin-right: 10px;
  text-align: right;
}

.form-group input,
.form-group select {
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
  margin: 20px;
}

button:hover {
  background-color: #5c5c5c;
}

.categorie-selection {
  display: flex;
  align-items: center;
  flex: 2;
  padding: 0;
}

.categorie-selection select {
  flex: 1;
  margin-right: 10px;
}

.categorie-selection button {
  padding: 10px 16px;
  flex: 0 0 auto;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-container {
  background: white;
  padding: 20px;
  border-radius: 8px;
  width: 400px;
  max-width: 100%;
}
</style>
