<template>
  <div id="app" class="container">
    <div class="row">
      <app-header></app-header>
    </div>
    <div class="row">
      <div class="cols-xs-12">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import Header from './components/Header'

export default {
  name: 'app',
  components: {
    appHeader: Header
  }
}
</script>

<style lang="scss">
  @import './assets/styles/app.scss';
</style>
