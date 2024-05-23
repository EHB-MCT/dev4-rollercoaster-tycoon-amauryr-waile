<template>
  <div>
    <h2>List of rollercoasters</h2>
    <ul class="attractie-list">
      <li v-for="attractie in attracties" :key="attractie.id" class="attractie-item">
        <div>
          <h2 :class="{ breakdown: hasUnresolvedPannes(attractie.pannes) }">
            {{ attractie.naam }}
          </h2>
          <p><strong>Capacity:</strong> {{ attractie.capaciteit }}</p>
          <p>
            <strong>Category:</strong>
            {{ attractie.categorie ? attractie.categorie.naam : 'undefined' }}
          </p>
          <p><strong>Year of construction:</strong> {{ attractie.bouwjaar }}</p>
          <p><strong>Length:</strong> {{ attractie.lengte }} meters</p>
          <p><strong>Time:</strong> {{ attractie.tijdsduur }} seconds</p>
          <p><strong>Max height:</strong> {{ attractie.maximumHoogte }} meters</p>
          <p><strong>Max speed:</strong> {{ attractie.maximumSnelheid }} km/h</p>

          <div v-if="attractie.afbeeldingUrl">
            <h4>Picture of rollercoaster:</h4>
            <img
              :src="attractie.afbeeldingUrl"
              alt="Picture of rollercoaster"
              class="attractie-image"
            />
          </div>

          <div v-if="attractie.onrideVideoUrl">
            <h4>Onride Video:</h4>
            <iframe
              :src="getYouTubeEmbedUrl(attractie.onrideVideoUrl)"
              frameborder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
              allowfullscreen
              class="attractie-video"
            >
            </iframe>
          </div>

          <div
            v-if="attractie.onderhoudsbeurten && attractie.onderhoudsbeurten.length"
            class="onderhoud"
          >
            <h4>Maintenance Dates:</h4>
            <ul>
              <li v-for="onderhoud in attractie.onderhoudsbeurten" :key="onderhoud.id">
                <p><strong>Date:</strong> {{ onderhoud.datum }}</p>
                <p><strong>Resolved:</strong> {{ onderhoud.opgelost ? 'Yes' : 'No' }}</p>
              </li>
            </ul>
          </div>

          <div v-if="attractie.pannes && attractie.pannes.length" class="panne">
            <h4>Breakdown:</h4>
            <ul>
              <li v-for="panne in attractie.pannes" :key="panne.id">
                <p v-if="!panne.resolved"><strong>Description:</strong> {{ panne.description }}</p>
                <p v-if="!panne.resolved">
                  <strong>Resolved:</strong> {{ panne.resolved ? 'Yes' : 'No' }}
                </p>
              </li>
            </ul>
            <p><strong>Total Resolved Breakdowns:</strong> {{ attractie.resolvedPannesCount }}</p>
          </div>

          <button @click="deleteAttraction(attractie.id)">Delete</button>
          <button @click="editAttraction(attractie.id)">Edit</button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'AttractieList',
  data() {
    return {
      attracties: []
    }
  },
  mounted() {
    this.loadAttracties()
  },
  methods: {
    async loadAttracties() {
      try {
        const response = await fetch('http://localhost:9000/attracties/all')
        if (!response.ok) {
          throw new Error(`Error loading rollercoasters: ${response.statusText}`)
        }
        const data = await response.json()
        console.log('Attracties:', data)
        this.attracties = data
      } catch (error) {
        console.error('Error loading rollercoasters', error)
      }
    },
    async deleteAttraction(id) {
      try {
        const response = await fetch(`http://localhost:9000/attracties/${id}`, {
          method: 'DELETE'
        })
        if (!response.ok) {
          throw new Error(`Error deleting rollercoaster: ${response.statusText}`)
        }
        this.attracties = this.attracties.filter((attractie) => attractie.id !== id)
        alert('Rollercoaster deleted!')
      } catch (error) {
        console.error('Error deleting rollercoaster', error)
      }
    },
    editAttraction(id) {
      this.$router.push(`/attracties/edit/${id}`)
    },
    getYouTubeEmbedUrl(videoUrl) {
      const videoId = videoUrl.split('v=')[1]
      const ampersandPosition = videoId ? videoId.indexOf('&') : -1
      if (ampersandPosition !== -1) {
        return `https://www.youtube-nocookie.com/embed/${videoId.substring(0, ampersandPosition)}`
      }
      return `https://www.youtube-nocookie.com/embed/${videoId}`
    },
    hasUnresolvedPannes(pannes) {
      return pannes.some((panne) => !panne.resolved)
    }
  }
}
</script>

<style scoped>
.attractie-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  padding: 0;
}

.onderhoud li,
.panne li {
  list-style: none;
}

.attractie-item {
  list-style-type: none;
  width: 48%;
  box-sizing: border-box;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.breakdown {
  color: red;
}

.attractie-item img {
  max-width: 100%;
  height: auto;
}

.attractie-item iframe {
  width: 100%;
  height: 200px;
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
