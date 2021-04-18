<template>
<div id="map">
  <no-ssr>
    <!-- 気仙沼大島の緯度・経度 -->
    <l-map :zoom="map_data.zoom" :center="map_data.center">
      <l-tile-layer :url="map_data.url"></l-tile-layer>
      <l-marker v-for="m of map_data.markers" :lat-lng="m.latlon">
        <l-popup>{{m.message}}</l-popup>
      </l-marker>
    </l-map>
  </no-ssr>
</div>
</template>

<script>
export default {
  props: ["map_data"],
}
</script>

<style lang="scss" scoped>
#map {
    height: 400px;
    width: auto;
}
</style>
