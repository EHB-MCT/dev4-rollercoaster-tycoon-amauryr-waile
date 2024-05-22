<template>
  <div class="form-container">
    <h2>Edit Rollercoaster</h2>
    <form @submit.prevent="updateAttraction" class="attractie-form">
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

      <button type="submit">Update</button>
    </form>

    <div class="onderhoud-section">
      <h2>Add Maintenance</h2>
      <div class="form-group" v-for="(onderhoud, index) in onderhouds" :key="index">
        <label for="onderhoudDatum">Maintenance Date:</label>
        <input type="date" v-model="onderhoud.datum" />
        <label for="onderhoudOpgelost">Resolved:</label>
        <input type="checkbox" v-model="onderhoud.opgelost" />
        <button type="button" @click="removeOnderhoud(index)">Remove</button>
      </div>
      <button type="button" @click="addOnderhoud">Add Maintenance Date</button>
    </div>

    <div class="onderhoud-list">
      <h2>Maintenance Dates</h2>
      <ul>
        <li v-for="onderhoud in onderhouds" :key="onderhoud.id">
          <div>
            <p><strong>Date:</strong> {{ onderhoud.datum }}</p>
            <p><strong>Resolved:</strong> {{ onderhoud.opgelost ? 'Yes' : 'No' }}</p>
            <button @click="markAsResolved(onderhoud.id)" :disabled="onderhoud.opgelost">
              Mark as Resolved
            </button>
          </div>
        </li>
      </ul>
    </div>

    <div class="panne-section">
      <h2>Add Breakdown</h2>
      <div class="form-group" v-for="(panne, index) in pannes" :key="index">
        <label for="panneDescription">Description:</label>
        <input type="text" v-model="panne.description" />
        <label for="panneResolved">Resolved:</label>
        <input type="checkbox" v-model="panne.resolved" />
        <button type="button" @click="removePanne(index)">Remove</button>
      </div>
      <button type="button" @click="addPanne">Add Breakdown</button>
    </div>

    <div class="panne-list">
      <h2>Breakdowns</h2>
      <ul>
        <li v-for="panne in pannes" :key="panne.id">
          <div>
            <p><strong>Description:</strong> {{ panne.description }}</p>
            <p><strong>Resolved:</strong> {{ panne.resolved ? 'Yes' : 'No' }}</p>
            <button @click="markPanneAsResolved(panne.id)">Mark as Resolved</button>
          </div>
        </li>
      </ul>
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
      pannes: []
    }
  },
  created() {
    this.loadAttractie()
    this.loadCategories()
    this.loadOnderhouds()
    this.loadPannes()
  },
  methods: {
    addOnderhoud() {
      this.onderhouds.push({ datum: '', opgelost: false })
    },
    removeOnderhoud(index) {
      this.onderhouds.splice(index, 1)
    },
    addPanne() {
      this.pannes.push({ description: '', resolved: false })
    },
    removePanne(index) {
      this.pannes.splice(index, 1)
    },
    async loadAttractie() {
      const id = this.$route.params.id
      try {
        const response = await fetch(`http://localhost:9000/attracties/${id}`)
        if (!response.ok) {
          throw new Error(`Error loading rollercoaster: ${response.statusText}`)
        }
        const data = await response.json()
        this.attractie = data
        this.selectedCategorie = data.categorie
        this.onderhouds = data.onderhoudsbeurten || []
        this.pannes = data.pannes || []
      } catch (error) {
        console.error('Error loading rollercoaster:', error)
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
    },
    async loadOnderhouds() {
      const id = this.$route.params.id
      try {
        const response = await fetch(`http://localhost:9000/onderhoud/attractie/${id}`)
        if (!response.ok) {
          throw new Error(`Error loading maintenances: ${response.statusText}`)
        }
        this.onderhouds = await response.json()
      } catch (error) {
        console.error('Error loading maintenances:', error)
      }
    },
    async loadPannes() {
      const id = this.$route.params.id
      try {
        const response = await fetch(`http://localhost:9000/panne/attractie/${id}`)
        if (!response.ok) {
          throw new Error(`Error loading pannes: ${response.statusText}`)
        }
        this.pannes = await response.json()
      } catch (error) {
        console.error('Error loading pannes:', error)
      }
    },
    async updateAttraction() {
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
          onderhouds: this.onderhouds,
          pannes: this.pannes
        }

        const response = await fetch(`http://localhost:9000/attracties/${this.$route.params.id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(attractionData)
        })

        if (!response.ok) {
          throw new Error(`Error updating rollercoaster: ${response.statusText}`)
        }

        const updatedAttractie = await response.json()
        console.log('Rollercoaster updated:', updatedAttractie)
        this.$router.push('/attracties/all')
      } catch (error) {
        console.error('Error updating rollercoaster:', error)
      }
    },
    async markAsResolved(id) {
      try {
        const response = await fetch(`http://localhost:9000/onderhoud/${id}/resolve`, {
          method: 'PUT'
        })
        if (!response.ok) {
          throw new Error(`Error marking maintenance as resolved: ${response.statusText}`)
        }
        this.loadOnderhouds()
      } catch (error) {
        console.error('Error marking maintenance as resolved:', error)
      }
    },
    async markPanneAsResolved(id) {
      try {
        const response = await fetch(`http://localhost:9000/panne/${id}/resolve`, {
          method: 'PUT'
        })
        if (!response.ok) {
          throw new Error(`Error marking panne as resolved: ${response.statusText}`)
        }
        this.loadPannes()
        this.updateAttraction()
      } catch (error) {
        console.error('Error marking panne as resolved:', error)
      }
    }
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

.onderhoud-section,
.panne-section {
  margin-top: 20px;
}

.onderhoud-list ul,
.panne-section ul {
  list-style-type: none;
  padding: 0;
}

.onderhoud-list li,
.panne-section li {
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 10px;
  margin-bottom: 10px;
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
