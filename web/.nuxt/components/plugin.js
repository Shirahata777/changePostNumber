import Vue from 'vue'

const components = {
  Logo: () => import('../../components/Logo.vue' /* webpackChunkName: "components/logo" */).then(c => c.default || c),
  VuetifyLogo: () => import('../../components/VuetifyLogo.vue' /* webpackChunkName: "components/vuetify-logo" */).then(c => c.default || c),
  HeaderMain: () => import('../../components/header/header_main.vue' /* webpackChunkName: "components/header-main" */).then(c => c.default || c),
  Form: () => import('../../components/form/form.vue' /* webpackChunkName: "components/form" */).then(c => c.default || c),
  LeafletMainMap: () => import('../../components/leaflet/main_map.vue' /* webpackChunkName: "components/leaflet-main-map" */).then(c => c.default || c),
  Nav: () => import('../../components/nav/nav.vue' /* webpackChunkName: "components/nav" */).then(c => c.default || c)
}

for (const name in components) {
  Vue.component(name, components[name])
  Vue.component('Lazy' + name, components[name])
}
