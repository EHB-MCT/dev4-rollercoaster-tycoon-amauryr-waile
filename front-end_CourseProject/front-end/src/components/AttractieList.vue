<template>
  <div>
    <h2>List of rollercoasters</h2>
    <ul>
      <li v-for="attractie in attracties" :key="attractie.id">
        <div>
          <h3>{{ attractie.naam }}</h3>
          <p><strong>Capacity:</strong> {{ attractie.capaciteit }}</p>
          <p><strong>Category:</strong> {{ attractie.categorie ? attractie.categorie.naam : "undefined" }}</p>
          <p><strong>Year of construction:</strong> {{ attractie.bouwjaar }}</p>
          <p><strong>Length:</strong> {{ attractie.lengte }} meters</p>
          <p><strong>Time:</strong> {{ attractie.tijdsduur }} minutes</p>
          <p><strong>Max height:</strong> {{ attractie.maximumHoogte }} meters</p>
          <p><strong>Max speed:</strong> {{ attractie.maximumSnelheid }} km/h</p>

          <div v-if="attractie.afbeeldingUrl">
            <h4>Picture of rollercoaster:</h4>
            <img :src="attractie.afbeeldingUrl" alt="Picture of rollercoaster" style="max-width: 100%; height: auto;">
          </div>
          
          <div v-if="attractie.onrideVideoUrl">
            <h4>Vidéo Onride:</h4>
            <iframe
              :src="getYouTubeEmbedUrl(attractie.onrideVideoUrl)"
              frameborder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
              allowfullscreen
              style="width: 100%; height: 360px;">
            </iframe>
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
    };
  },
  mounted() {
    this.loadAttracties();
  },
  methods: {
    async loadAttracties() {
      try {
        const response = await fetch('http://localhost:9000/attracties/all');
        if (!response.ok) {
          throw new Error(`Error loading rollercoasters: ${response.statusText}`);
        }
        const data = await response.json();
        console.log('Attracties:', data);
        this.attracties = data; 
      } catch (error) {
        console.error('Error loading rollercoasters', error);
      }
    },
    async deleteAttraction(id) {
      try {
        const response = await fetch(`http://localhost:9000/attracties/${id}`, {
          method: 'DELETE'
        });
        if (!response.ok) {
          throw new Error(`Error deleting rollercoaster: ${response.statusText}`);
        }
        this.attracties = this.attracties.filter(attractie => attractie.id !== id); 
        alert('rollercoaster deleted!');
      } catch (error) {
        console.error('Error deleting rollercoaster', error);
      }
    },
    editAttraction(id) {
      this.$router.push(`/attracties/edit/${id}`);
    },
    getYouTubeEmbedUrl(videoUrl) {
      const videoId = videoUrl.split('v=')[1];
      const ampersandPosition = videoId ? videoId.indexOf('&') : -1;
      if (ampersandPosition !== -1) {
        return `https://www.youtube-nocookie.com/embed/${videoId.substring(0, ampersandPosition)}`;
      }
      return `https://www.youtube-nocookie.com/embed/${videoId}`;
    }
  }
};
</script>

<style scoped>
</style>
