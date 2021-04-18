<template>
  <v-app dark class="mx-auto overflow-hidden">
    <Header />
    <v-main>
      <v-container>
        <nuxt />
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Header from "~/components/header/header_main.vue";

export default {
  components: {
    Header
  },
  data () {
    return {
    }
  }
}
</script>
<style lang="scss">
$color-pack: false;
@import '~vuetify/src/styles/main.sass';

a {
  text-decoration: none;
}
</style>
