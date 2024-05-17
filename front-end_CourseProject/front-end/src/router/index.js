import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '@/components/HomePage.vue';
import AttractieList from '@/components/AttractieList.vue';
import AttractieCreate from '@/components/AttractieCreate.vue';
import AttractieEdit from '@/components/AttractieEdit.vue';
import CategorieList from '@/components/CategorieList.vue';
import CategorieForm from '@/components/CategorieForm.vue';
import CategorieEdit from '@/components/CategorieEdit.vue';

const routes = [
  { path: '/', component: HomePage },
  {path: '/categories/create', component: CategorieForm},
  { path: '/attracties/create', component: AttractieCreate },
  { path: '/attracties/edit/:id', component: AttractieEdit,  props: true},
  { path: '/attracties/all', component: AttractieList },
  { path: '/categories/all', component: CategorieList },
  { path: '/categories/edit/:id', component: CategorieEdit, props: true },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
