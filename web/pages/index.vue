<template>
<div class="">
  <h1>住所を入力してください！</h1>
  <!-- <form class="" @submit.prevent="api_request">
     <input type="text" v-model="addr" name="addr">
     <input type="submit" value="検索">
     <h2>郵便番号：{{this.post}}</h2>
    </form> -->
  <Form />
  <Map :map_data="map_data"/>
</div>
</template>

<script>
import axios from 'axios';
import Map from '~/components/leaflet/main_map.vue'
import Form from '~/components/form/form.vue'

export default {
  components: {
    Map,
    Form,
  },
  data() {
    return {
      addr: '',
      post: '',
      map_data: {
        center: [35.6814999, 139.7611213],
        zoom: 10,
        url: "http://{s}.tile.osm.org/{z}/{x}/{y}.png",
        // マーカーを置く場所一覧（観光地）
        markers: {
          center: {
            latlon: [35.6814999, 139.7611213],
            message: "center"
          },
          kugunaribeach: {
            latlon: [38.873171, 141.63164],
            message: "kugunaribeach"
          },
        },
      },

    }
  },
}
</script>
