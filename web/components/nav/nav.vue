<template>
<!-- <v-card class="mx-auto overflow-hidden" height=""> -->
  <!-- <v-system-bar color="deep-purple darken-3"></v-system-bar> -->
  <div class="">
    <v-app-bar color="cyan accent-3" dense prominent>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" absolute left>
      <v-list nav dense>
        <v-list-item-group v-model="group" active-class="deep-purple--text text--accent-4">
          <v-list-item v-for="n in nav_data" :key="nav_data.tag">
            <v-list-item-title>
              <NuxtLink :to="n.link">{{n.tag}}</NuxtLink>
            </v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
<!-- </v-card> -->
</template>

<script>
export default {
  data: () => ({
    drawer: false,
    group: null,
    nav_data: [{
        tag: "トップ",
        link: "/"
      },{
        tag: "このサイトについて",
        link: "/about"
      },{
        tag: "お問い合わせ",
        link: "/contact"
      }
    ]
  }),

  watch: {
    group() {
      this.drawer = false
    },
  },
}
</script>

<style lang="scss" scoped>
aside{
  z-index: 10000;
}
header {
  height: 50px !important;
}

</style>
