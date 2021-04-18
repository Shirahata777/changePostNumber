import Vue from 'vue'
import { extend,ValidationObserver, ValidationProvider, setInteractionMode,localize } from 'vee-validate'
import ja from 'vee-validate/dist/locale/ja.json'
import { required, digits, regex } from 'vee-validate/dist/rules'
// import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'

setInteractionMode('eager')
extend('required', {
  ...required,
  message: '{_field_}：フィールドが空です！住所を入力してください',
})
extend('regex', {
  ...regex,
  message: '住所の形式が違います。日本語以外は含めないでください！「入力値：{_value_}」',
})

Vue.component('validation-provider', ValidationProvider)
Vue.component('validation-observer', ValidationObserver)

localize('ja', ja)
