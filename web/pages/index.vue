<template>
  <div class="">
    <h1>住所を入力してください！</h1>
    <form class="" @submit.prevent="api_request">
     <input type="text" v-model="addr" name="addr">
     <input type="submit" value="検索">
     <h2>{{this.post}}</h2>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
export default {

  components: {
  },
  data() {
    return {
      addr: '',
      post: '',
    }
  },
  methods: {
    api_request() {
      // console.log(this.addr);
      axios.get(`/server/change_post?addr=${this.addr}`)
        .then((res) => {
          console.log(res);
          this.post = res.data.post;
        return { post: res.data.post }
      }).catch(error => {
          const {
            status,
            statusText
          } = error.response;
          console.log(`Error! HTTP Status: ${status} ${statusText}`);
        });
    }
  }
}
</script>
