export { default as Logo } from '../../components/Logo.vue'
export { default as VuetifyLogo } from '../../components/VuetifyLogo.vue'
export { default as HeaderMain } from '../../components/header/header_main.vue'
export { default as Form } from '../../components/form/form.vue'
export { default as LeafletMainMap } from '../../components/leaflet/main_map.vue'
export { default as Nav } from '../../components/nav/nav.vue'

export const LazyLogo = import('../../components/Logo.vue' /* webpackChunkName: "components/logo" */).then(c => c.default || c)
export const LazyVuetifyLogo = import('../../components/VuetifyLogo.vue' /* webpackChunkName: "components/vuetify-logo" */).then(c => c.default || c)
export const LazyHeaderMain = import('../../components/header/header_main.vue' /* webpackChunkName: "components/header-main" */).then(c => c.default || c)
export const LazyForm = import('../../components/form/form.vue' /* webpackChunkName: "components/form" */).then(c => c.default || c)
export const LazyLeafletMainMap = import('../../components/leaflet/main_map.vue' /* webpackChunkName: "components/leaflet-main-map" */).then(c => c.default || c)
export const LazyNav = import('../../components/nav/nav.vue' /* webpackChunkName: "components/nav" */).then(c => c.default || c)
