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
        <select v-model="selectedCategorie" id="categorie">
          <option v-for="categorie in categories" :key="categorie.id" :value="categorie">
            {{ categorie.naam }}
          </option>
        </select>
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
      onderhouds: []
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
  justify-content: space-between;
  margin-bottom: 12px;
}

.form-group label {
  flex: 1;
  margin-right: 25px;
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
</style>
