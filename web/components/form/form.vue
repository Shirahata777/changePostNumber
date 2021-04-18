<template>
  <div class="">
    <validation-observer ref="observer" v-slot="{ invalid }">
      <form @submit.prevent="api_request">
        <validation-provider v-slot="{ errors }" name="Address" :rules="{ required: true, regex: '^[\u30a0-\u30ff\u3040-\u309f\u3005-\u3006\u30e0-\u9fcf0-9０-９]+$'}">
          <v-text-field v-model="addr" :error-messages="errors" label="住所を入力してください！" required></v-text-field>
        </validation-provider>
        <v-btn class="mr-4" type="submit" :disabled="invalid">submit</v-btn>
        <v-btn @click="clear">clear</v-btn>
      </form>

    </validation-observer>
    <h2>郵便番号：{{this.post}}</h2>
  </div>
</template>
<h1></h1>
<script>
import axios from 'axios';
import { ValidationProvider } from 'vee-validate';
import { ValidationObserver } from 'vee-validate';

export default{
  components: {
    ValidationProvider,
    ValidationObserver,
  },
  data() {
    return {
      addr: "",
      post: "",
      no_data: "郵便番号が検索できませんでした。ごぺんなさい！",
    }
  },
  methods: {
    submit () {
      this.$refs.observer.validate()
    },
    clear () {
      this.addr = ''
      this.$refs.observer.reset()
    },
    api_request() {
      axios.get(`/server/change_post?addr=${this.addr.replace(/\u200B/g, "")}`)
        .then((res) => {
          console.log(res);
          if (res.data.post !== "no_data" ) {
            this.post = res.data.post;
            return { post: res.data.post }
          }

      }).catch(error => {
          const {
            status,
            statusText
          } = error.response;
          console.log(`Error! HTTP Status: ${status} ${statusText}`);
          console.log(this.no_data);
          return { no_data: this.no_data }
        });
    }
  },
}

</script>
<style lang="scss" scoped>

</style>
